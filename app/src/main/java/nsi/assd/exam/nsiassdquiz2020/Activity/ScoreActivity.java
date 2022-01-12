package nsi.assd.exam.nsiassdquiz2020.Activity;

import androidx.appcompat.app.AppCompatActivity;
import nsi.assd.exam.nsiassdquiz2020.R;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ScoreActivity extends AppCompatActivity {
    private TextView highScore;
    public static TextView score;
    public static  int myScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        score = findViewById(R.id.score_text);
        TextView total = findViewById(R.id.total_text);
        Button doneBtn = findViewById(R.id.done_btn);

        loadAds();

        myScore = Integer.parseInt(String.valueOf(getIntent().getIntExtra("score",0)));
        score.setText(String.valueOf(myScore));
        total.setText("OUT OF " + (getIntent().getIntExtra("total", 0)));



        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadAds() {
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}
