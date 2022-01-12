package nsi.assd.exam.nsiassdquiz2020.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import nsi.assd.exam.nsiassdquiz2020.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class NsiBook extends AppCompatActivity {
    private final String PDF_LINK ="https://firebasestorage.googleapis.com/v0/b/nsiassd2020.appspot.com/o/security_operations_level_3.pdf?alt=media&token=2c9a211f-f935-44ec-a5ab-85026f3b35c3";
    private final String MY_PDF = "security_operations_level_3.pdf";
    private SeekBar seekBar;
    private PDFView pdfView;
    private TextView seekBarText, downloadingTxt;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nsi_book);
        Toolbar toolbar = findViewById(R.id.book_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("NSI Book");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pdfView = findViewById(R.id.pdfView);
        //seekBarText = findViewById(R.id.seek_bar_txt);
        //downloadingTxt = findViewById(R.id.downloading_txt);
       // initSeekBar();
        //downloadPdf();
        openPDF();
        loadAds();
    }
    private void openPDF(){
        pdfView.fromAsset("nsi_book.pdf")
                .enableSwipe(true)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .fitEachPage(true) // fit each page to the view, else smaller pages are scaled relative to largest page.
                .pageSnap(false) // snap pages to screen boundaries
                .pageFling(true) // make a fling change only a single page like ViewPager
                .nightMode(false) // toggle night mode.load();
                .load();

    }
   /* private void initSeekBar(){
        seekBar = findViewById(R.id.seek_bar);
        seekBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        seekBar.getThumb().setColorFilter(Color.RED,PorterDuff.Mode.SRC_IN);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int val = (progress*(seekBar.getWidth() -3 *seekBar.getThumbOffset())) / seekBar.getMax();
                seekBarText.setText("" + progress);
                seekBarText.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

     private void downloadPdf(){
         new AsyncTask<Void, Integer, Boolean>() {
             @Override
             protected Boolean doInBackground(Void... voids) {
                 return downloadPdf();
             }
             private Boolean downloadPdf(){
                 try {
                     File file = getFileStreamPath("security_operations_level_3.pdf");
                     if (file.exists())
                         return true;
                     try {
                         FileOutputStream fileOutputStream = openFileOutput("security_operations_level_3.pdf", Context.MODE_PRIVATE);
                         URL u = new URL(PDF_LINK);
                         URLConnection conn = u.openConnection();
                         int connectionLength = conn.getContentLength();
                         InputStream input = new BufferedInputStream(u.openStream());
                         byte data[] = new byte[connectionLength];
                         long total = 0;
                         int count;

                         while ((count = input.read(data)) != -1){
                             total += count;
                             publishProgress((int) ((total *100) / connectionLength));
                             fileOutputStream.write(data, 0,count);
                         }
                         fileOutputStream.flush();
                         fileOutputStream.close();
                         input.close();
                         return true;

                     } catch (IOException e) {
                         e.printStackTrace();
                         return false;
                     }

                 } catch (Exception e) {
                     e.printStackTrace();
                 }
                 return false;
             }

             @Override
             protected void onProgressUpdate(Integer... values) {
                 super.onProgressUpdate(values);
                 seekBar.setProgress(values[0]);
             }

             @Override
             protected void onPostExecute(Boolean aBoolean) {
                 super.onPostExecute(aBoolean);
                 if (aBoolean){
                     openPdf();
                 }else {
                     Toast.makeText(NsiBook.this, "Unable to download...please check your internet connection", Toast.LENGTH_SHORT).show();
                 }
             }
         }.execute();
        }
        private void openPdf(){
        try {
            File file = getFileStreamPath("security_operations_level_3.pdf");
            Log.e("file", "file" + file.getAbsolutePath());
            seekBar.setVisibility(View.GONE);
            downloadingTxt.setVisibility(View.GONE);
            pdfView.setVisibility(View.VISIBLE);
            pdfView.fromFile(file)
                    .enableSwipe(true)
                    .enableDoubletap(true)
                    .defaultPage(0)
                    .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                    .password(null)
                    .scrollHandle(null)
                    .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                    // spacing between pages in dp. To define spacing color, set view background
                    .spacing(0)
                    .pageFitPolicy(FitPolicy.WIDTH)
                    .fitEachPage(true) // fit each page to the view, else smaller pages are scaled relative to largest page.
                    .pageSnap(false) // snap pages to screen boundaries
                    .pageFling(true) // make a fling change only a single page like ViewPager
                    .nightMode(false) // toggle night mode
                    .load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();

        }

        return super.onOptionsItemSelected(item);
    }
    private void loadAds() {
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitialAd_adUnitId));
        adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }
    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    finish();
                }
            });
        } else {
            super.onBackPressed();
        }
    }
    }
