package com.CARRIEDO.catmilkgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class End3Activity extends AppCompatActivity {
    TextView score1;
    TextView score2;
    TextView score3;
    TextView score4;
    TextView score5;

    ArrayList<Double> score_sp_list = new ArrayList<Double>();
    private AdView mAdView;
    private MediaPlayer mp;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.release();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end3);
        getSupportActionBar().hide();

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

        score1 = findViewById(R.id.end3_score_1);
        score2 = findViewById(R.id.end3_score_2);
        score3 = findViewById(R.id.end3_score_3);
        score4 = findViewById(R.id.end3_score_4);
        score5 = findViewById(R.id.end3_score_5);
        Button exit_btn = findViewById(R.id.end3_btn_exit);
        Button again_btn = findViewById(R.id.end3_btn_again);
        TextView score = findViewById(R.id.end3_tv_score);

        Intent intent = getIntent();
        String s_score = intent.getExtras().getString("score3");
        score.setText(" "+s_score+" ");


        Gson gson = new Gson();
        // 조회를 위환 sp
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        // 수정이나 입력을 위한 sp 에디터
        SharedPreferences.Editor editor = sp.edit();

        //조회
        String json = sp.getString("score_list3","");
        ArrayList<Double> list_int = (ArrayList<Double>)gson.fromJson(json,ArrayList.class);

        if(list_int==null)
        {
            ArrayList<Double> temp = new ArrayList<Double>();
            list_int = temp;
            list_int.add(Double.valueOf(0));
            list_int.add(Double.valueOf(0));
            list_int.add(Double.valueOf(0));
            list_int.add(Double.valueOf(0));
            list_int.add(Double.valueOf(0));
        }

        // SharedPreference 에서 정보 가져와서 리스트 초기화 해 놓고 추가 작업시작
        score_sp_list = list_int;
        score_sp_list.add(Double.valueOf(s_score));
        Collections.sort(score_sp_list, new Descending());
        if(score_sp_list.size() > 6)
        {
            score_sp_list.remove(6);
        }
        String json_0 =gson.toJson(score_sp_list);
        editor.putString("score_list3",json_0);
        editor.commit();
        // 추가하고 정렬해서 sharedPreference 에 리스트 저장

        // 뷰 캐스팅
        score1.setText(String.valueOf(score_sp_list.get(0)));
        score2.setText(String.valueOf(score_sp_list.get(1)));
        score3.setText(String.valueOf(score_sp_list.get(2)));
        score4.setText(String.valueOf(score_sp_list.get(3)));
        score5.setText(String.valueOf(score_sp_list.get(4)));


        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
                v.startAnimation(anim);
                Intent intent = new Intent(End3Activity.this, StartActivity.class);
                startActivity(intent);
                finish();
            }
        });

        again_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
                v.startAnimation(anim);
                Intent intent = new Intent(End3Activity.this, Main3Activity.class);
                intent.putExtra("score3",0);
                startActivity(intent);
                finish();
            }
        });
    }

    class Descending implements Comparator<Double> {

        public int compare(Double a, Double b)
        {
            return b.compareTo(a);
        }
    }
}
