package nsi.assd.exam.nsiassdquiz2020.Activity;

import static android.content.ContentValues.TAG;
import static nsi.assd.exam.nsiassdquiz2020.R.*;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;
import java.util.List;

import nsi.assd.exam.nsiassdquiz2020.Adapter.AvatarGridAdapter;
import nsi.assd.exam.nsiassdquiz2020.R;

public class VirtualExamGrid extends AppCompatActivity implements AvatarGridAdapter.WebViewClickListener {
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_virtual_exam_grid);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Virtual Exam");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("https://aesecurityguard.com/nsi-quiz1/");
        imageUrls.add("https://aesecurityguard.com/nsi-quiz2/");
        imageUrls.add("https://aesecurityguard.com/nsi-quiz3/");
        imageUrls.add("https://aesecurityguard.com/nsi-quiz4/");
        imageUrls.add("https://aesecurityguard.com/nsi-quiz5/");
        imageUrls.add("https://aesecurityguard.com/nsi-quiz6/");
        imageUrls.add("https://aesecurityguard.com/nsi-quiz7/");

        // Add more URLs as needed...

        GridView gridView = findViewById(R.id.grid_view);
        AvatarGridAdapter adapter = new AvatarGridAdapter(this, imageUrls);
        gridView.setAdapter(adapter);

        loadAds();


    }

    private void loadAds() {
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


    @Override
    public void onWebViewClick(String url) {
        WebView webView = findViewById(R.id.webView);
        webView.loadUrl(url);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {// API 5+ solution
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}