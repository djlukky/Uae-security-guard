package nsi.assd.exam.nsiassdquiz2020.Adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import nsi.assd.exam.nsiassdquiz2020.R;

public class InterstitialAdHelper {
    public static InterstitialAd mInterstitialAd;
    public static AdRequest adRequest;
    public  static final String TAG = "InterstitialAdHelper";

    public static void interAds (Context context){
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(context, context.getString(R.string.interstitialAd_adUnitId), adRequest,
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

}
