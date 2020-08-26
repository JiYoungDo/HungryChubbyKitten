package com.CARRIEDO.catmilkgame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Objects;



public class StartActivity extends AppCompatActivity {

    private AdView mAdView;
    Handler handler = new Handler();
    private MediaPlayer mp;


    // exit 버튼 : 로그 아웃
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Objects.requireNonNull(getSupportActionBar()).hide();

        mp = MediaPlayer.create(this, R.raw.bgm);
        mp.setLooping(true);
        mp.start();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Button start_btn = findViewById(R.id.start_ib_start);
        Button logout_btn = findViewById(R.id.start_ib_logout);
        Button rule_btn = findViewById(R.id.start_ib_rule);

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
                v.startAnimation(anim);
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
                v.startAnimation(anim);
                // SharedPreferences에 저장된 값들을 exit 버튼을 누르면 삭제하기 위해
                // ShardPreferences를 불러온다. LoginActivity에서 만든 이름으로.

                SharedPreferences auto = getSharedPreferences("idStr", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = auto.edit();
                // editor.clear()는 auto에 들어있는 모든 정보를 기기에서 지운다.
                editor.clear();
                editor.apply();
                Toast.makeText(StartActivity.this,"로그아웃 되었습니다.",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        rule_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
                v.startAnimation(anim);
                Intent intent = new Intent (StartActivity.this, RuleActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.release();
    }
}
