package nsi.assd.exam.nsiassdquiz2020.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import nsi.assd.exam.nsiassdquiz2020.Adapter.BookmarkAdapter;
import nsi.assd.exam.nsiassdquiz2020.Model.QuestionModel;
import nsi.assd.exam.nsiassdquiz2020.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static nsi.assd.exam.nsiassdquiz2020.Activity.QuestionActivity.FILE_NAME;
import static nsi.assd.exam.nsiassdquiz2020.Activity.QuestionActivity.KEY_NAME;

public class BookmarkActivity extends AppCompatActivity {
    private SharedPreferences.Editor editor;
    private List<QuestionModel> bookmarkList;
    private Gson gson;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        Toolbar bookmarkToolbar = findViewById(R.id.bookmark_toolbar);
        setSupportActionBar(bookmarkToolbar);
        getSupportActionBar().setTitle("Bookmarks");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.bookmark_rv);
        MobileAds.initialize(this);
        loadAds();

        preferences = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        gson = new Gson();
        getBoomark();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);

        recyclerView.setLayoutManager(layoutManager);


        BookmarkAdapter adapter = new BookmarkAdapter(bookmarkList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        storedBookmark();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getBoomark() {
        String json = preferences.getString(KEY_NAME, "");
        Type type = new TypeToken<List<QuestionModel>>() {
        }.getType();
        bookmarkList = gson.fromJson(json, type);
        if (bookmarkList == null) {
            bookmarkList = new ArrayList<>();
        }
    }

    private void storedBookmark() {
        String json = gson.toJson(bookmarkList);
        editor.putString(KEY_NAME, json);
        editor.commit();
    }

    private void loadAds() {
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}

