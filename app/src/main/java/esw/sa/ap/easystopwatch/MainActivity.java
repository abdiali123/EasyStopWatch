package esw.sa.ap.easystopwatch;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

import com.example.abdi.easystopwatch.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    Button btnStart, btnStop;
    ImageView icanchor;
    Animation roundingalone;
    Chronometer timerHere;
    AdView mAdview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-8256988727898497~1392892042");

        mAdview = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        icanchor = findViewById(R.id.icanchor);
        timerHere = findViewById(R.id.timerHere);

        //create optional animation
        btnStop.setAlpha(0);

        //Load Animation
        roundingalone = AnimationUtils.loadAnimation(this, R.anim.roundingalone);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // passing animation
                icanchor.startAnimation(roundingalone);
                btnStop.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnStart.animate().alpha(0).setDuration(300).start();
                //Start Time
                timerHere.setBase(SystemClock.elapsedRealtime());
                timerHere.start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //passing animation
                btnStart.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnStop.animate().alpha(0).setDuration(300).start();
                //stop time
                timerHere.setBase(SystemClock.elapsedRealtime());
                timerHere.stop();
                icanchor.clearAnimation();
            }
        });

    }
}