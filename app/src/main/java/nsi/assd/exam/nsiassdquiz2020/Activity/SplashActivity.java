package nsi.assd.exam.nsiassdquiz2020.Activity;

import androidx.appcompat.app.AppCompatActivity;
import nsi.assd.exam.nsiassdquiz2020.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    Animation topAnimation, bottomAnimation;
    ImageView splashImage;
    LinearLayout splashText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.button_animation);

        splashImage = findViewById(R.id.splash_image);
        splashImage.setAnimation(topAnimation);

        splashText = findViewById(R.id.splash_text);
        splashText.setAnimation(bottomAnimation);




        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }

}
