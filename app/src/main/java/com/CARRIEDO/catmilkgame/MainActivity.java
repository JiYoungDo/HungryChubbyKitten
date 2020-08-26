package com.CARRIEDO.catmilkgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.CountDownTimer;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView score;
    ImageView cat_img1;
    ImageView cat_img2;
    ImageView cat_img3;
    ImageView cat_img4;
    ImageView cat_img5;
    ImageView cat_img6;
    ImageView cat_img7;
    ImageView cat_img8;
    ImageView milk_img1;
    ImageView milk_img2;
    ImageView milk_img3;
    ImageView milk_img4;
    ImageView milk_img5;
    ImageView milk_img6;
    ImageView milk_img7;
    ImageView milk_img8;
    ImageView milk_img9;
    ImageView milk_img10;
    Button fillbtn;
    Thread thread = null;

    private MediaPlayer mp;
//    private MediaPlayer btnsound;

    Handler handler = new Handler();

    ImageView[] img_array = new ImageView[8];
    ImageView[] milk_array = new ImageView[10];

    int[] imageID = {R.id.main_iv_cat_1, R.id.main_iv_cat_2, R.id.main_iv_cat_3, R.id.main_iv_cat_4,
            R.id.main_iv_cat_5, R.id.main_iv_cat_6, R.id.main_iv_cat_7, R.id.main_iv_cat_8};

    int[] milkimgID ={R.id.main_iv_milk_1,R.id.main_iv_milk_2,R.id.main_iv_milk_3,R.id.main_iv_milk_4,
            R.id.main_iv_milk_5,R.id.main_iv_milk_6,R.id.main_iv_milk_7,R.id.main_iv_milk_8,
            R.id.main_iv_milk_9,R.id.main_iv_milk_10};

    String TAG_ON = "on";    //for tag
    String TAG_ON2 ="on2";
    String TAG_OFF = "off";

    int score_int = 0;
    int milk_count;

    // for timer
    private static int MILLISNFUTURE = 31*1000; // 총시간
    private static int COUNT_DOWN_INTERVAL = 1000;  // Tick 시간
    TextView timer;
    CountDownTimer countdownTimer;
    private CountDownTimer countDownTimeronResume;
    static long millisecondsleft;
    boolean isPause = false;

//    ArrayList<String> array = new ArrayList<>();
//    // ArrayList → Json 으로 변환.
//    String SETTINGS_PLAYER_JSON = "settings_item_json";


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.release();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        milk_count = 10;
        timer = (TextView)findViewById(R.id.main_tv_timer);
        getSupportActionBar().hide();

        mp = MediaPlayer.create(this,R.raw.bgm);
        mp.setLooping(true);
        mp.start();

//        catsound = MediaPlayer.create(this,R.raw.catsound);
//        btnsound = MediaPlayer.create(this, R.raw.btnclick);
//        catsound.setLooping(false);
//        btnsound.setLooping(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("milkcount",String.valueOf(milk_count));

        score = findViewById(R.id.main_tv_score);
        cat_img1 = findViewById(R.id.main_iv_cat_1);
        cat_img2 = findViewById(R.id.main_iv_cat_2);
        cat_img3 = findViewById(R.id.main_iv_cat_3);
        cat_img4 = findViewById(R.id.main_iv_cat_4);
        cat_img5 = findViewById(R.id.main_iv_cat_5);
        cat_img6 = findViewById(R.id.main_iv_cat_6);
        cat_img7 = findViewById(R.id.main_iv_cat_7);
        cat_img8 = findViewById(R.id.main_iv_cat_8);
        milk_img1 =findViewById(R.id.main_iv_milk_1);
        milk_img2 =findViewById(R.id.main_iv_milk_2);
        milk_img3 =findViewById(R.id.main_iv_milk_3);
        milk_img4 =findViewById(R.id.main_iv_milk_4);
        milk_img5 =findViewById(R.id.main_iv_milk_5);
        milk_img6 =findViewById(R.id.main_iv_milk_6);
        milk_img7 =findViewById(R.id.main_iv_milk_7);
        milk_img8 =findViewById(R.id.main_iv_milk_8);
        milk_img9 =findViewById(R.id.main_iv_milk_9);
        milk_img10 =findViewById(R.id.main_iv_milk_10);
        fillbtn =findViewById(R.id.main_fill_btn);

        score.setText("0");

        // fill 누르면 우유 늘어난다.
        fillbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // btnsound.start();
                if(milk_count <10) {
                    milk_count++;
                    Log.d("milkcount", String.valueOf(milk_count));
                } else
                {
                    milk_count = 10;
                }

            }
        });

        // Pause 가 아니면
        if(isPause == false)
        {
            countdownTimer = new CountDownTimer(MILLISNFUTURE,COUNT_DOWN_INTERVAL) {
                @Override
                public void onTick(long millisUntilFinished) {
                    millisecondsleft = millisUntilFinished;
                    timer.setText(""+String.format("%02d",millisUntilFinished/1000));
                }
                @Override
                public void onFinish() {

                    timer.setText("Finish.");
                    Intent intent = new Intent(MainActivity.this, EndActivity.class);
                    intent.putExtra("score",String.valueOf(score_int));

                    if(score_int > 25)
                    {
                        Intent intent2 = new Intent(MainActivity.this, Main2Activity.class);
                        intent2.putExtra("score2",score_int);
                        Toast.makeText(getApplicationContext(),"잘했군요! 다음 레벨로~!",Toast.LENGTH_SHORT).show();
                        startActivity(intent2);
                        finish();
                    }
                    else
                    {
                        startActivity(intent);
                        finish();
                    }

                }
            }.start();
        }
        else
        {
            countDownTimeronResume = new CountDownTimer(millisecondsleft,COUNT_DOWN_INTERVAL) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Toast.makeText(MainActivity.this,"ONPAUSED",Toast.LENGTH_SHORT).show();
                    millisecondsleft = millisUntilFinished;
                    timer.setText(""+String.format("%02d",millisUntilFinished/1000));
                }
                @Override
                public void onFinish() {

                    timer.setText("Finish.");
                    Intent intent = new Intent(MainActivity.this, EndActivity.class);
                    intent.putExtra("score",String.valueOf(score_int));

                    if(score_int > 25)
                    {
                        Intent intent2 = new Intent(MainActivity.this, Main2Activity.class);
                        intent2.putExtra("score2",score_int);
                        Toast.makeText(getApplicationContext(),"잘했군요! 다음 레벨로~!",Toast.LENGTH_SHORT).show();
                        startActivity(intent2);
                        finish();
                    }
                    else
                    {
                        startActivity(intent);
                        finish();
                    }
                }
            }.start();
        }

        Thread milkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)
                {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            for(int i = 0; i<milk_array.length; i++)
                            {
                                milk_array[i] = findViewById(milkimgID[i]);
                                milk_array[i].setImageResource(R.drawable.milk);

                                if(milk_count != 10)
                                {
                                    int k = milk_count;
                                    for(int j = 0; j<k; j++)
                                    {
                                        milk_array[j].setImageResource(R.drawable.milk);
                                    }
                                    for(int t = k; t<10; t++)
                                    {
                                        if(t== -1)
                                        {
                                            t =0;
                                        }
                                        milk_array[t].setImageResource(R.drawable.milk_bg);

                                    }
                                }
                            }
                        }
                    });
                    try {
                        Thread.sleep(100);
                    }catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
        milkThread.start();



        for (int i = 0; i < img_array.length; i++) {
            // 모든 이미지를 cat_down 으로 만들고
            img_array[i] = findViewById(imageID[i]);
            img_array[i].setImageResource(R.drawable.cat1down);
            img_array[i].setTag(TAG_OFF);

            // 이미지를 클릭 했을 때 태그 값이 off 면(구멍안에 있는) score -- , on 이면(=위로 올라와있는) score ++
            img_array[i].setOnClickListener(new View.OnClickListener() {
                int countclick = 0;
                @Override
                public void onClick(View v) {
                    milk_count -=1;
                    countclick ++;
                    //catsound.start();
                    Log.d("milk count",String.valueOf(milk_count));

                    // 사용자 milk_count 가 다 없어지면 게임 오버
                    if(milk_count < 1)
                    {
                        Intent intent = new Intent(MainActivity.this, EndActivity.class);
                        intent.putExtra("score",String.valueOf(score_int));
                        startActivity(intent);
                        finish();
                    }

                    if (v.getTag().toString().equals(TAG_ON)) {
                        countclick = 0;
                        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
                        ((ImageView)v).startAnimation(anim);
                        // Toast.makeText(getApplicationContext(), "good", Toast.LENGTH_SHORT).show();
                        score.setText(String.valueOf(score_int++));
                        ((ImageView) v).setImageResource(R.drawable.cat1down);
                        v.setTag(TAG_OFF);

                    }
                    else if (v.getTag().toString().equals(TAG_ON2)) {
                        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
                        ((ImageView)v).startAnimation(anim);
                        if(countclick ==2)
                        {
                            //Toast.makeText(getApplicationContext(), "good", Toast.LENGTH_SHORT).show();
                            score.setText(String.valueOf(score_int+=3));
                            ((ImageView) v).setImageResource(R.drawable.cat1down);
                            v.setTag(TAG_OFF);
                            countclick =0;
                        }
                        // Toast.makeText(getApplicationContext(), "more milk!!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        countclick = 0;
                        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
                        ((ImageView)v).startAnimation(anim);
                        // Toast.makeText(getApplicationContext(), "bad",Toast.LENGTH_SHORT).show();
                        if (score_int <= 0) {
                            score_int = 0;
                            score.setText(String.valueOf(score_int));
                        } else {
                            score.setText(String.valueOf(score_int));
                        }
                        // 우유 1개 원하는 고양이, 우유 2개 원하는 고양이 랜덤으로 나올 수 있도록.
                        Random rd = new Random();
                            if((rd.nextInt(1000)+1)%2 == 0)
                            {
                                ((ImageView) v).setImageResource(R.drawable.cat1);
                                v.setTag(TAG_ON);
                            }
                            else
                            {
                                ((ImageView) v).setImageResource(R.drawable.cat2);
                                v.setTag(TAG_ON2);
                            }
                    }
                }
            });
        }


        for (int i = 0; i < img_array.length; i++) {
            // i는 DThread 클래스 정의 시에 고양이 인덱스 번호이다.
            new Thread(new DThread(i)).start();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(countdownTimer != null)
        {
            countdownTimer.cancel();
            isPause = true;
        }
        if(countDownTimeronResume != null)
        {
            countDownTimeronResume.cancel();
        }

    }

    final Handler onHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Random rd = new Random();
            if((rd.nextInt(1000)+1)%2 == 0)
            {
                img_array[msg.arg1].setImageResource(R.drawable.cat1);
                img_array[msg.arg1].setTag(TAG_ON);
            }
            else
            {
                img_array[msg.arg1].setImageResource(R.drawable.cat2);
                img_array[msg.arg1].setTag(TAG_ON2);
            }
        }
    };

    final Handler offHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            img_array[msg.arg1].setImageResource(R.drawable.cat1down);
            img_array[msg.arg1].setTag(TAG_OFF);   // 내려오면 OFF 태그 달아줌
        }
    };


    class DThread implements Runnable {
        int index = 0; // 고양이 번호

        public DThread(int index)
        {
            this.index = index;
        }

        @Override
        public void run() {
            while (true) {
                try {

                    int offtime = new Random().nextInt(5000) + 1000; //Random.nextInt(int n) : int 타입의 0~ 매개값까지의 난수를 리턴한다.
                    Thread.sleep(offtime); // 두더지 내려가 있는 시간
                    Message msg1 = new Message();
                    msg1.arg1 = index;  // 메세지 값을 고양이 번호로 설정하고
                    onHandler.sendMessage(msg1);    // 고양이 번호를 메세지로 보낸다.

                    int ontime = new Random().nextInt(1000) + 700;
                    Thread.sleep(ontime);   // 두더지가 올라가 있는 시간
                    Message msg2 = new Message();
                    msg2.arg1 = index;
                    offHandler.sendMessage(msg2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}



