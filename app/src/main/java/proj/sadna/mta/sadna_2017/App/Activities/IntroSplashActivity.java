package proj.sadna.mta.sadna_2017.app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import proj.sadna.mta.sadna_2017.R;

public class IntroSplashActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TextView txt= (TextView) findViewById(R.id.txtview);
        txt.setAnimation(AnimationUtils.loadAnimation(this,R.anim.slide_left));

        int secondsDelayed = 3;
        new Handler().postDelayed(new Runnable()
        {
            public void run()
            {
                startActivity(new Intent(IntroSplashActivity.this, MainActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);
    }


}