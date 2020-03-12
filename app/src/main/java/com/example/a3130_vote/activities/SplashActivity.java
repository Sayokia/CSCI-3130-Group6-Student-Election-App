package com.example.a3130_vote.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a3130_vote.SharedPrefs;

import com.example.a3130_vote.R;

/**
 * The type Splash activity.
 */
public class SplashActivity extends AppCompatActivity {

    private Button mGetStartedButton;
    private TextView mWelcomeTextView;
    private ImageView mAgoraImageView;
    private SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mGetStartedButton = findViewById(R.id.getstarted);
        mWelcomeTextView = findViewById(R.id.welcome_text);
        mAgoraImageView = findViewById(R.id.agora_image);
        sharedPrefs = new SharedPrefs(this);
        if (sharedPrefs.getLogedInKey() != null)
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        mGetStartedButton.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_from_bottom));
        mWelcomeTextView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_from_bottom));
        mAgoraImageView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_from_top));
    }

    /**
     * Gets started.
     *
     * @param view the view
     */
    public void getStarted(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
