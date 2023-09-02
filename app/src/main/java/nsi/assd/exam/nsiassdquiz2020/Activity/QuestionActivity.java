package nsi.assd.exam.nsiassdquiz2020.Activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import nsi.assd.exam.nsiassdquiz2020.Model.QuestionModel;
import nsi.assd.exam.nsiassdquiz2020.R;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class QuestionActivity extends AppCompatActivity {
    public static final String FILE_NAME = "NSIASSD";
    public static final String KEY_NAME = "QUESTIONS";


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private TextView question, noIndicator;
    private FloatingActionButton bookmarkBtn;
    private Button shareBtn, nextBtn;
    private LinearLayout optionContainer;
    private int count = 0;
    private int position = 0;
    private int myScore = 0;
    private List<QuestionModel> list;
    private Dialog loadingDialog;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private List<QuestionModel> bookmarkList;
    private Gson gson;
    private int matchedQuestionPosition;
    private String setId;
    private Button optionA, optionB, optionC, optionD;
    private TextView startCountDownTimer;
    private InterstitialAd mInterstitialAd;
    private CountDownTimer countDownTimer;
    private Intent scoreIntent, highScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = findViewById(R.id.question_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        startCountDown();

        question = findViewById(R.id.question_textview);
        startCountDownTimer = findViewById(R.id.text_view_countdown);
        noIndicator = findViewById(R.id.indicator_text);
        shareBtn = findViewById(R.id.share_btm);
        bookmarkBtn = findViewById(R.id.bookmark_btn);
        nextBtn = findViewById(R.id.next_btn);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        optionContainer = findViewById(R.id.options_containers);

        preferences = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        gson = new Gson();
        getBookmark();
       loadAds();


        if (!isConnected(QuestionActivity.this)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(QuestionActivity.this, R.string.connection_failed, Toast.LENGTH_SHORT).show();
                }
            }, 3000);
        }

        bookmarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modelMatch()) {
                    bookmarkList.remove(matchedQuestionPosition);
                    bookmarkBtn.setImageDrawable(getDrawable(R.drawable.ic_bookmark_border));
                    Toast.makeText(QuestionActivity.this, "Removing from bookmark ", Toast.LENGTH_SHORT).show();
                } else {
                    bookmarkList.add(list.get(position));
                    bookmarkBtn.setImageDrawable(getDrawable(R.drawable.bookmark));
                    Toast.makeText(QuestionActivity.this, "Saving in Bookmark", Toast.LENGTH_SHORT).show();
                }
            }
        });
        setId = getIntent().getStringExtra("setId");

        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.loading);
        loadingDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.rounded_corner));
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);
        Runnable loadingRunnable = new Runnable() {
            @Override
            public void run() {
                loadingDialog.cancel();
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(loadingRunnable, 3000);

        list = new ArrayList<>();
        loadingDialog.show();
        myRef
                .child("SETS").child(setId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String id = dataSnapshot1.getKey();
                    String question = dataSnapshot1.child("question").getValue().toString();
                    String a = dataSnapshot1.child("optionA").getValue().toString();
                    String b = dataSnapshot1.child("optionB").getValue().toString();
                    String c = dataSnapshot1.child("optionC").getValue().toString();
                    String d = dataSnapshot1.child("optionD").getValue().toString();
                    String correctAns = dataSnapshot1.child("correctAns").getValue().toString();

                    list.add(new QuestionModel(id, question, a, b, c, d, correctAns, setId));
                }

                if (list.size() > 0) {
                    for (int i = 0; i < 4; i++) {
                        optionContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                checkAnswer((Button) v);
                            }
                        });
                    }
                    playAnim(question, 0, list.get(position).getQuestion());
                    nextBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            nextBtn.setEnabled(false);
                            nextBtn.setAlpha(0.7f);
                            enableOption(true);
                            position++;
                            if (position == list.size()) {
                                if (mInterstitialAd!= null) {
                                    mInterstitialAd.show(QuestionActivity.this);
                                    return;
                                }
                                ///score activity
                                scoreIntent = new Intent(QuestionActivity.this, ScoreActivity.class);
                                highScore = new Intent(QuestionActivity.this,MainActivity.class);
                                scoreIntent.putExtra("score", myScore);
                                scoreIntent.putExtra("total", list.size());
                                startActivity(scoreIntent);
                                startActivity(highScore);
                                finish();
                                return;
                            }
                            count = 0;
                            playAnim(question, 0, list.get(position).getQuestion());
                        }
                    });

                    shareBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String body = list.get(position).getQuestion() + "\n" +
                                    list.get(position).getA() + "\n" +
                                    list.get(position).getB() + "\n" +
                                    list.get(position).getC() + "\n" +
                                    list.get(position).getD();
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("text/plain ");
                            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Quiz Challenge");
                            shareIntent.putExtra(Intent.EXTRA_TEXT, body);
                            startActivity(Intent.createChooser(shareIntent, "share via"));
                        }
                    });

                } else {
                    finish();
                    Toast.makeText(QuestionActivity.this, "No Questions", Toast.LENGTH_SHORT).show();
                }
                loadingDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(QuestionActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        storedBookmark();
    }

    private void playAnim(final View view, final int value, final String data) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                countDownTimer.start();
                if (myRef == null) {
                    bookmarkBtn.setVisibility(View.GONE);
                }

                if (optionA.getText().toString().isEmpty()) {
                    optionA.setVisibility(View.GONE);
                } else {
                    optionA.setVisibility(View.VISIBLE);
                }
                if (optionB.getText().toString().isEmpty()) {
                    optionB.setVisibility(View.GONE);
                } else {
                    optionB.setVisibility(View.VISIBLE);
                }
                if (optionC.getText().toString().isEmpty()) {
                    optionC.setVisibility(View.GONE);
                } else {
                    optionC.setVisibility(View.VISIBLE);
                }
                if (optionD.getText().toString().isEmpty()) {
                    optionD.setVisibility(View.GONE);
                } else {
                    optionD.setVisibility(View.VISIBLE);
                }

                if (value == 0 && count < 4) {
                    String option = "";
                    if (count == 0) {
                        option = list.get(position).getA();
                    } else if (count == 1) {
                        option = list.get(position).getB();
                    } else if (count == 2) {
                        option = list.get(position).getC();
                    } else if (count == 3) {
                        option = list.get(position).getD();
                    }
                    playAnim(optionContainer.getChildAt(count), 0, option);
                    count++;
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (value == 0) {
                    try {
                        ((TextView) view).setText(data);
                        noIndicator.setText(position + 1 + "/" + list.size());
                        if (modelMatch()) {
                            bookmarkBtn.setImageDrawable(getDrawable(R.drawable.bookmark));
                        } else {
                            bookmarkBtn.setImageDrawable(getDrawable(R.drawable.ic_bookmark_border));
                        }
                    } catch (ClassCastException ex) {
                        ((Button) view).setText(data);
                    }
                    view.setTag(data);
                    playAnim(view, 1, data);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void checkAnswer(Button selectedOption) {
        enableOption(false);
        nextBtn.setEnabled(true);
        nextBtn.setAlpha(1);
        String correctAns = list.get(position).getAnswer();
        if (selectedOption.isPressed()){
            countDownTimer.cancel();
        }
        if (selectedOption.getText().toString().equals(correctAns)) {
            //correct
            myScore++;
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
            selectedOption.setTextColor(ColorStateList.valueOf(Color.parseColor("#4CAF50")));


        } else {
            ///incorrect
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF0000")));
            selectedOption.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")));
            Button correctOption = (Button) optionContainer.findViewWithTag(list.get(position).getAnswer());
            correctOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
            correctOption.setTextColor(ColorStateList.valueOf(Color.parseColor("#4CAF50")));

        }
    }


    private void enableOption(boolean enable) {
        for (int i = 0; i < 4; i++) {
            optionContainer.getChildAt(i).setEnabled(enable);
            if (enable) {
                optionContainer.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
                optionA.setTextColor(Color.BLACK);
                optionB.setTextColor(Color.BLACK);
                optionC.setTextColor(Color.BLACK);
                optionD.setTextColor(Color.BLACK);

            }
        }
    }

    private void getBookmark() {
        String json = preferences.getString(KEY_NAME, "");
        Type type = new TypeToken<List<QuestionModel>>() {
        }.getType();
        bookmarkList = gson.fromJson(json, type);
        if (bookmarkList == null) {
            bookmarkList = new ArrayList<>();
        }
    }

    private boolean modelMatch() {
        boolean matched = false;
        int i = 0;
        for (QuestionModel model : bookmarkList) {
            if (model.getQuestion().equals(list.get(position).getQuestion())
                    && model.getAnswer().equals(list.get(position).getAnswer())
                    && model.getSet().equals(list.get(position).getSet())) {
                matched = true;
                matchedQuestionPosition = i;
            }
            i++;
        }

        return matched;
    }

    private void storedBookmark() {
        String json = gson.toJson(bookmarkList);
        editor.putString(KEY_NAME, json);
        editor.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadAds() {
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

       // mInterstitialAd = new InterstitialAd(this);
        //mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitialAd_adUnitId));
        //mInterstitialAd.loadAd(new AdRequest.Builder().build());
        //mInterstitialAd.setAdListener(new AdListener() {
           // @Override
           // public void onAdClosed() {
               // super.onAdClosed();
                //mInterstitialAd.loadAd(new AdRequest.Builder().build());

        adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this,(getString(R.string.interstitialAd_adUnitId)), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });
        /*Intent scoreIntent = new Intent(QuestionActivity.this, ScoreActivity.class);
        scoreIntent.putExtra("score", myScore);
        scoreIntent.putExtra("total", list.size());
        startActivity(scoreIntent);
        finish();*/
    }

    public boolean isConnected(android.content.Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnected()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else {
            return false;
        }

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(QuestionActivity.this, R.style.Theme_AppCompat_Light_Dialog_Alert)
                .setTitle("Quiz not completed!")
                .setMessage("Are you sure you want to quit this quiz?")
                .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mInterstitialAd!= null){
                            mInterstitialAd.show(QuestionActivity.this);
                        }else {
                            Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        }
                            Intent scoreIntent = new Intent(QuestionActivity.this, ScoreActivity.class);
                            scoreIntent.putExtra("score", myScore);
                            scoreIntent.putExtra("total", list.size());
                            startActivity(scoreIntent);
                            finish();
                        }
                })
                .setNegativeButton("Cancel", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void startCountDown() {
        countDownTimer = new CountDownTimer(46000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                int x = (int) (millisUntilFinished / 1000);
                if ( 10 < x){
                    startCountDownTimer.setTextColor(Color.WHITE);
                }else {
                    startCountDownTimer.setTextColor(Color.RED);

                }
                startCountDownTimer.setText("" + String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            @Override
            public void onFinish() {
                Button correctOption = optionContainer.findViewWithTag(list.get(position).getAnswer());//index out of bound
                correctOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
                nextBtn.setEnabled(true);
                nextBtn.setAlpha(1);
                enableOption(false);
                startCountDownTimer.setText("Times up!!!");
            }
        }.start();
    }
    /*  public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have mobile data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }*/

}


