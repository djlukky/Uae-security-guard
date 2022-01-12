package nsi.assd.exam.nsiassdquiz2020.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import nsi.assd.exam.nsiassdquiz2020.Adapter.NoteGridAdapter;
import nsi.assd.exam.nsiassdquiz2020.R;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.List;

public class NoteSetsActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_sets);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));

        loadAds();

        GridView gridview = findViewById(R.id.grid_view);
        List<String> sets = NoteCategoryActivity.list.get(getIntent().getIntExtra("position", 0)).getSets();
        NoteGridAdapter adapter = new NoteGridAdapter(sets, getIntent().getStringExtra("title"),mInterstitialAd);
        gridview.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadAds() {
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitialAd_adUnitId));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
}
