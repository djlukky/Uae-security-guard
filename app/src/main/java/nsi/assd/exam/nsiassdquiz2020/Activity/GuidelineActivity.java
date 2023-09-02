package nsi.assd.exam.nsiassdquiz2020.Activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import nsi.assd.exam.nsiassdquiz2020.R;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class GuidelineActivity extends AppCompatActivity {
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guideline);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Guideline");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadAds();

        WebView webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(true);
        webView.getSettings().setSupportZoom(true);
        String htmlText = " %s ";
        String myData = "<html><body style=text-align:justify><b>MINISTERIAL DECISION 557 OF 2008\n\n" +
                "         Executive order of federal law 37 of 2006\n\n" +
                "         concerning private security companies\n\n" +
                "          Art (104) (7) (8) <p> DISCIPLINE</b><br>" +

                "The security guard must carry two black opens and a notebook at all" +
                " time when on duty. Disciplinary offences may attract a penalty of a fine or other action.<p>" +
                "<b>WARNING <br>\n Federal Law No. 35 of 1992 Penal Procedures Law \n CIVIL ACTION RELATED TO PENAL ACTION\n article (22)</b><p>" +
                "Whoever sustains a direct personal injury from\n" +
                "the crime may sue for civil right against the accused during the collection of evidence\n" +
                "enquiry proceedings. before the court of law hearing the penal. action. or at any stage of the\n" +
                "action until the closure of pleadings.<p>" +

                "<b>GUIDELINE</b><br> Can lead to Civil or Criminal Action taken against you, your colleagues and" +
                " your company, if you fail to act properly and do your duty. Follow the rule in this notebook, " +
                "these rule have been designed to help you. the notebook is your Official memory and official record of your " +
                "activities when on duty. Writing your actions down truthfully may protect and your company <p>" +

                "<b> REPORT WRITING RULE</b><br>Your report should be written at the time the event happens or as soon after as you can" +
                ". DO NOT LEAVE WRITING YOUR NOTES UNTIL LATER OR TOMORROW " +
                "BECAUSE YOUR WILL FORGET IMPORTANT INFORMATION AS TIME PASSES AND THIS MAY CAUSE YOU AND YOUR COMPANY PROBLEMS.<p>" +

                "<b>NOTEBOOK RULE</b><br>All security guard are issued with two notebooks. The notebook is an official document and must be carried at all time by guard and supervisors whilst on duty.<p>" +

                "<b>MANAGING YOUR NOTEBOOK</b>" +
                "<ol>"+
                "<li>You must make a daily entry every day, include the date, time of your shift, your duty, your location, serial numbers of your equipment, radio number. Next you will write a report of any incident which happen on that shift. When you gave finished your duty put the time you finished, sign it and put a line through the rest of the blank page. Your Supervisor may check and sign your notebook, it must carried at all times when on duty. See example below.</li><p>" +
                "<li>Carry two black ink pens with your notebook at all times. The public prosecutor may want to photocopy your notes for any case your might be involved in and black ink is best for photocopies. </li><p>" +
                "<li>NO ERASURES. NO LEAVING PAGES, NO BLACKING OUT WORDS, NO OVERWRITING, NO WRITING BETWEEN LINES, NO SPACES, NO WHITE OUT. Corrections should be made with single line through the word to be change, your initial next to the change, then make the change and put date. </li><p>" +
                "<li>All report should start as follows: Day Date, Time and Place (DDTP)</li><p>" +
                "<li>A good report should cover all the following points:  Who, Where, When, What, Why and How: (5 Wives and 1 Husband)</li><p>" +
                "<li> WHO Covers the names of everyone involved, the accused, complainant, injured persons, police who attended CID. doctors, so everyone involved.</li><p>" +
                "<li> WHERE covers the place the incident happened, and any other places, e.g Police Station, hospital, the bank, and so any place. Example: I took the witness Mr. Ali to the Security Office to write his statement.</li><p>" +
                "<li> WHEN Covers DAY, DATE and TIME</li><p>" +
                "<li> WHAT Covers whst happened, a theft, drugs, robbery from the bank or ATM, a suicide, etc.</li><p>" +
                "<li>WHY Covers why the incident happened. Example, the accident happened because the front tyre was old and exploded.</li><p>" +
                "<li>HOW covers how the incident happened. Example, the accident happened because the car was driven too fast and the tire was old.</li><p>" +
                "</ol>"+
                "<b>Federal Law No. 35 of 1992 on Penal Procedures Law\n RED HANDEDNESS\n Articles (42) (Arrest)</b><br>" +
                "A crime is Red Handed if it is disclosed Instantly upon commission, or shortly after its occurrence. A crime is red handed if the victim pursues the suspect, or if people follow with a clamour after its occurrence, or if the suspect is arrested shortly occurrence and is in possession of anything that indicate he is a principal or an accomplice to the crime or he presently exhibits traces or sign which indicate that he is the offender.<p>" +
                "<b>Article (48 Arresting an Accused</b><p>" +
                "Whoever witness the culprit red handed in an act of crime or misdemeanor shall surrender the culprit to the nearest public authority without the need for a warrant of arrest. The suspect news about the real reason and handed over security agencies.<p>" +
                "<b>Guideline:-</b> A Security Guard has  3 duties when handed the accused a competent authorities:" +
                "<ol>" +
                "<li>Identify Yourself</li><p>" +
                "<li>Tell the accused the TRUE reason of his/her handed over the competent authorities.</li><p>" +
                "<li>tell the accused he/she will be handed over, e.g. You will be handed over tho the police services</li><p>" +
                "</ol>" +

                "</body></Html>" ;

        webView.loadData(String.format(htmlText, myData), "text/html", "utf-8");
    }
    private void loadAds() {
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

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
                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });
    }
    @Override
    public void onBackPressed() {
        if (mInterstitialAd!= null){
            mInterstitialAd.show(GuidelineActivity.this);
        }else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
        super.onBackPressed();
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
}