package nsi.assd.exam.nsiassdquiz2020.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.GridLayoutAnimationController;
import android.view.animation.LayoutAnimationController;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import eu.dkaratzas.android.inapp.update.Constants;
import eu.dkaratzas.android.inapp.update.InAppUpdateManager;
import eu.dkaratzas.android.inapp.update.InAppUpdateStatus;
import hotchemi.android.rate.AppRate;
import nsi.assd.exam.nsiassdquiz2020.Adapter.MainAdapter;
import nsi.assd.exam.nsiassdquiz2020.Model.ImageSliderModel;
import nsi.assd.exam.nsiassdquiz2020.OtherClass.AboutDeveloper;
import nsi.assd.exam.nsiassdquiz2020.R;
import static nsi.assd.exam.nsiassdquiz2020.Activity.ScoreActivity.myScore;

public class MainActivity extends AppCompatActivity implements InAppUpdateManager.InAppUpdateHandler {

    private Toolbar toolbar;
    private int totalCount;
    private RecyclerView recyclerView;
    private List<String> title;
    private List<Integer> icon;
    private MainAdapter adapter;
    private InAppUpdateManager inAppUpdateManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        List<ImageSliderModel> imageSliderModelList = new ArrayList<>();
        updateApp();
        appRate();
        Drawer();

        recyclerView = findViewById(R.id.homeRecyclerView);

        title = new ArrayList<>();
        icon = new ArrayList<>();
        adapter = new MainAdapter(this, title, icon);

        icon.add(R.drawable.start_quiz_256);
        icon.add(R.drawable.notes_256);
        icon.add(R.drawable.bookmarks_256);
        icon.add(R.drawable.tips_256);
        icon.add(R.drawable.guideline_icon_256);
        icon.add(R.drawable.nsi_book_256);
        icon.add(R.drawable.about_uae_256);
        icon.add(R.drawable.guideline_256);
        icon.add(R.drawable.virtual_test_256);

        title.add(getString(R.string.start_quiz));
        title.add(getString(R.string.notes));
        title.add(getString(R.string.bookmarks));
        title.add(getString(R.string.tips));
        title.add(getString(R.string.guideline));
        title.add(getString(R.string.nsi_book));
        title.add(getString(R.string.about_uee));
        title.add(getString(R.string.useful_contacts));
        title.add(getString(R.string.virtual_exam));


        GridLayoutManager gridLayoutManager = (new GridLayoutManager(MainActivity.this, 2));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);

        int rsID = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(MainActivity.this, rsID);
        recyclerView.setLayoutAnimation(animation);

        MobileAds.initialize(this);
        loadAds();
        loadData();
        saveData();
    }

    private void appRate() {

        AppRate.with(this)
                .setInstallDays(1)
                .setLaunchTimes(3)
                .setRemindInterval(2)
                .monitor();
        AppRate.showRateDialogIfMeetsConditions(this);
        AppRate.with(this).shouldShowRateDialog();

    }

    private void loadAds() {
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.notification) {
            Intent intent = new Intent(this, NotificationsActivity.class);
            startActivity(intent);
        } else if (id == R.id.exit_app) {
            finish();
        } else if (id == R.id.update_checker) {
            updateApp();
        }

        return super.onOptionsItemSelected(item);
    }

    private void Drawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.abt_developer) {
                    Intent intent = new Intent(MainActivity.this, AboutDeveloper.class);
                    startActivity(intent);

                } else if (item.getItemId() == R.id.nav_apps) {
                    Uri uri = Uri.parse("https://play.google.com/store/apps/developer?id=Ramesh+Aryal");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);


                } else if (item.getItemId() == R.id.nav_privacy_policy) {
                    PrivacyPolicyDialog();

                } else if (item.getItemId() == R.id.nav_Terms_and_conditions) {
                    termAndConditionsDialog();


                } else if (item.getItemId() == R.id.nav_rate) {
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=nsi.assd.exam.nsiassdquiz2020");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                } else if (item.getItemId() == R.id.nav_share) {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=nsi.assd.exam.nsiassdquiz2020");
                    shareIntent.setType("Text/plain");
                    startActivity(Intent.createChooser(shareIntent, "Share Via"));

                } else if (item.getItemId() == R.id.nav_send) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"ramesh.aryal86@gmail.com"});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "About NSI ASSD");
                    intent.putExtra(Intent.EXTRA_TEXT, "");
                    intent.setType("Text/plain");
                    intent.setPackage("com.google.android.gm");
                    startActivity(Intent.createChooser(intent, "Send email:"));

                } else if (item.getItemId() == R.id.whatsapp_me) {
                    try {
                        String mobile = "+971551366480";
                        String msg = "Hello Developer,";
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + mobile + "&text=" + msg)));
                    } catch (Exception e) {
                        //whatsApp app not install
                    }

                } else if (item.getItemId() == R.id.check_update) {
                    updateApp();
                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("saveScore", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("scoreValue", String.valueOf(myScore));
        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("saveScore", MODE_PRIVATE);
        myScore = Integer.parseInt(sharedPreferences.getString("scoreValue", String.valueOf(MODE_PRIVATE)));

    }

    private void PrivacyPolicyDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.privacy_policy_dialog);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        WebView webView = dialog.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://nsi-assd-quiz-2021.flycricket.io/privacy.html");
        Button okBtn = dialog.findViewById(R.id.ok_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void termAndConditionsDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.privacy_policy_dialog);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        WebView webView = dialog.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://nsi-assd-quiz-2021.flycricket.io/terms.html");
        Button okBtn = dialog.findViewById(R.id.ok_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void updateApp() {
        inAppUpdateManager = InAppUpdateManager.Builder(this,101)
                .resumeUpdates(true)
                .mode(Constants.UpdateMode.FLEXIBLE)
                .snackBarAction("An update has just been downloaded")
                .snackBarAction("RESTART")
                .handler(this);
        inAppUpdateManager.checkForAppUpdate();

    }


    @Override
    public void onInAppUpdateError(int code, Throwable error) {

    }

    @Override
    public void onInAppUpdateStatus(InAppUpdateStatus status) {
        if (status.isDownloaded()){
            View view = getWindow().getDecorView().findViewById(android.R.id.content);
            Snackbar snackbar = Snackbar.make(view,
                    "An update has just been downloaded",
                    Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inAppUpdateManager.completeUpdate();

                }
            });
            snackbar.show();
        }

    }
}
