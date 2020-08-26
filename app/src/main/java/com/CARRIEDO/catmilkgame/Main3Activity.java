package com.CARRIEDO.catmilkgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
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

import java.util.Random;

public class Main3Activity extends AppCompatActivity {


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

    ImageView fish_img1;
    ImageView fish_img2;
    ImageView fish_img3;
    ImageView fish_img4;
    ImageView fish_img5;
    ImageView fish_img6;
    ImageView fish_img7;
    ImageView fish_img8;
    ImageView fish_img9;
    ImageView fish_img10;

    ImageView mouse_img1;
    ImageView mouse_img2;
    ImageView mouse_img3;
    ImageView mouse_img4;
    ImageView mouse_img5;
    ImageView mouse_img6;
    ImageView mouse_img7;
    ImageView mouse_img8;
    ImageView mouse_img9;
    ImageView mouse_img10;


    Button fishfillbtn;
    Button milkfillbtn;
    Button mousefillbtn;

    Handler handler = new Handler();

    ImageView[] img_array = new ImageView[8];
    ImageView[] milk_array = new ImageView[10];
    ImageView[] fish_array = new ImageView[10];
    ImageView[] mouse_array = new ImageView[10];

    int[] imageID = {R.id.main3_iv_cat_1, R.id.main3_iv_cat_2, R.id.main3_iv_cat_3, R.id.main3_iv_cat_4,
            R.id.main3_iv_cat_5, R.id.main3_iv_cat_6, R.id.main3_iv_cat_7, R.id.main3_iv_cat_8};

    int[] milkimgID ={R.id.main3_iv_milk_1,R.id.main3_iv_milk_2,R.id.main3_iv_milk_3,R.id.main3_iv_milk_4,
            R.id.main3_iv_milk_5,R.id.main3_iv_milk_6,R.id.main3_iv_milk_7,R.id.main3_iv_milk_8,
            R.id.main3_iv_milk_9,R.id.main3_iv_milk_10};

    int[] fishimgID ={R.id.main3_iv_fish_1,R.id.main3_iv_fish_2,R.id.main3_iv_fish_3,R.id.main3_iv_fish_4
            ,R.id.main3_iv_fish_5,R.id.main3_iv_fish_6,R.id.main3_iv_fish_7,R.id.main3_iv_fish_8
            ,R.id.main3_iv_fish_9,R.id.main3_iv_fish_10};

    int[] mouseimgID ={R.id.main3_iv_mouse_1, R.id.main3_iv_mouse_2, R.id.main3_iv_mouse_3, R.id.main3_iv_mouse_4,
            R.id.main3_iv_mouse_5, R.id.main3_iv_mouse_6, R.id.main3_iv_mouse_7, R.id.main3_iv_mouse_8,
            R.id.main3_iv_mouse_9,R.id.main3_iv_mouse_10};

    String TAG_MILK_ON = "milk_on";    //for tag
    String TAG_MILK_ON2 ="milk_on2";
    String TAG_OFF = "off";
    String TAG_FISH_ON ="fish_on";
    String TAG_FISH_ON2 = "fish_on2";
    String TAG_MOUSE_ON = "mouse_on";
    String TAG_MOUSE_ON2 = "mouse_on2";

    int score_int;
    int milk_count;
    int fish_count;
    int mouse_count;

    private static int MILLISNFUTURE = 31*1000; // 총시간
    private static int COUNT_DOWN_INTERVAL = 1000;  // Tick 시간
    TextView timer;
    CountDownTimer countdownTimer;
    private CountDownTimer countDownTimeronResume;
    static long millisecondsleft;
    boolean isPause = false;

    private MediaPlayer mp;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.release();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getSupportActionBar().hide();

        mp = MediaPlayer.create(this, R.raw.bgm);
        mp.setLooping(true);
        mp.start();

        milk_count = 10;
        fish_count = 10;
        mouse_count = 10;

        timer = (TextView)findViewById(R.id.main3_tv_timer);
    }

    @Override
    protected void onResume() {
        super.onResume();

        score = findViewById(R.id.main3_tv_score);
        Intent intent = getIntent();
        score_int = intent.getExtras().getInt("score3",0);
        score.setText(String.valueOf(score_int));

        cat_img1 = findViewById(R.id.main3_iv_cat_1);
        cat_img2 = findViewById(R.id.main3_iv_cat_2);
        cat_img3 = findViewById(R.id.main3_iv_cat_3);
        cat_img4 = findViewById(R.id.main3_iv_cat_4);
        cat_img5 = findViewById(R.id.main3_iv_cat_5);
        cat_img6 = findViewById(R.id.main3_iv_cat_6);
        cat_img7 = findViewById(R.id.main3_iv_cat_7);
        cat_img8 = findViewById(R.id.main3_iv_cat_8);

        milk_img1 =findViewById(R.id.main3_iv_milk_1);
        milk_img2 =findViewById(R.id.main3_iv_milk_2);
        milk_img3 =findViewById(R.id.main3_iv_milk_3);
        milk_img4 =findViewById(R.id.main3_iv_milk_4);
        milk_img5 =findViewById(R.id.main3_iv_milk_5);
        milk_img6 =findViewById(R.id.main3_iv_milk_6);
        milk_img7 =findViewById(R.id.main3_iv_milk_7);
        milk_img8 =findViewById(R.id.main3_iv_milk_8);
        milk_img9 =findViewById(R.id.main3_iv_milk_9);
        milk_img10 =findViewById(R.id.main3_iv_milk_10);

        fish_img1 = findViewById(R.id.main3_iv_fish_1);
        fish_img2 = findViewById(R.id.main3_iv_fish_2);
        fish_img3 = findViewById(R.id.main3_iv_fish_3);
        fish_img4 = findViewById(R.id.main3_iv_fish_4);
        fish_img5 = findViewById(R.id.main3_iv_fish_5);
        fish_img6 = findViewById(R.id.main3_iv_fish_6);
        fish_img7 = findViewById(R.id.main3_iv_fish_7);
        fish_img8 = findViewById(R.id.main3_iv_fish_8);
        fish_img9 = findViewById(R.id.main3_iv_fish_9);
        fish_img10 = findViewById(R.id.main3_iv_fish_10);

        mouse_img1 = findViewById(R.id.main3_iv_mouse_1);
        mouse_img2 = findViewById(R.id.main3_iv_mouse_2);
        mouse_img3 = findViewById(R.id.main3_iv_mouse_3);
        mouse_img4 = findViewById(R.id.main3_iv_mouse_4);
        mouse_img5 = findViewById(R.id.main3_iv_mouse_5);
        mouse_img6 = findViewById(R.id.main3_iv_mouse_6);
        mouse_img7 = findViewById(R.id.main3_iv_mouse_7);
        mouse_img8 = findViewById(R.id.main3_iv_mouse_8);
        mouse_img9 = findViewById(R.id.main3_iv_mouse_9);
        mouse_img10 = findViewById(R.id.main3_iv_mouse_10);


        milkfillbtn =findViewById(R.id.main3_fill_btn);
        fishfillbtn = findViewById(R.id.main3_fill2_btn);
        mousefillbtn = findViewById(R.id.main3_fill3_btn);


        milkfillbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(milk_count <10) {
                    milk_count++;
                } else
                {
                    milk_count = 10;
                }
            }
        });

        fishfillbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fish_count <10)
                {
                    fish_count++;
                }else
                {
                    fish_count = 10;
                }
            }
        });

        mousefillbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mouse_count <10)
                {
                    mouse_count++;
                } else
                {
                    mouse_count = 10;
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
                    Intent intent = new Intent(Main3Activity.this, End3Activity.class);
                    intent.putExtra("score3",String.valueOf(score_int));
                    startActivity(intent);
                    finish();
                }
            }.start();
        }
        else
        {
            countDownTimeronResume = new CountDownTimer(millisecondsleft,COUNT_DOWN_INTERVAL) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Toast.makeText(Main3Activity.this,"ONPAUSED",Toast.LENGTH_SHORT).show();
                    millisecondsleft = millisUntilFinished;
                    timer.setText(""+String.format("%02d",millisUntilFinished/1000));
                }
                @Override
                public void onFinish() {

                    timer.setText("Finish.");
                    Intent intent = new Intent(Main3Activity.this, End3Activity.class);

                    intent.putExtra("score3",String.valueOf(score_int));
                    startActivity(intent);
                    finish();
                }
            }.start();
        }

        // 사용자의 우유 보이는 뷰 관리 하는 스레드
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
                                        if(t<0)
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

        // 사용자의 물고기 보이는 뷰 관리 하는 스레드
        Thread fishThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)
                {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            for(int i = 0; i<fish_array.length; i++)
                            {
                                fish_array[i] = findViewById(fishimgID[i]);
                                fish_array[i].setImageResource(R.drawable.fish);

                                if(fish_count != 10)
                                {
                                    int k = fish_count;
                                    for(int j = 0; j<k; j++)
                                    {
                                        fish_array[j].setImageResource(R.drawable.fish);
                                    }
                                    for(int t = k; t<10; t++)
                                    {
                                        if(t < 0)
                                        {
                                            t =0;
                                        }
                                        fish_array[t].setImageResource(R.drawable.milk_bg);

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
        fishThread.start();

        // 사용자의 쥐 보이는 뷰 관리 하는 스레드
        Thread mouseThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)
                {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            for(int i = 0; i<mouse_array.length; i++)
                            {
                                mouse_array[i] = findViewById(mouseimgID[i]);
                                mouse_array[i].setImageResource(R.drawable.mouse);

                                if(mouse_count != 10)
                                {
                                    int k = mouse_count;
                                    for(int j = 0; j<k; j++)
                                    {
                                        mouse_array[j].setImageResource(R.drawable.mouse);
                                    }
                                    for(int t = k; t<10; t++)
                                    {
                                        if(t < 0)
                                        {
                                            t =0;
                                        }
                                        mouse_array[t].setImageResource(R.drawable.milk_bg);
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
        mouseThread.start();

        //


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
                    countclick ++;
                    Log.d("milk count",String.valueOf(milk_count));

                    // 사용자 milk_count 가 다 없어지면 게임 오버
                    if(milk_count < 1)
                    {
                        Intent intent = new Intent(Main3Activity.this, End3Activity.class);
                        intent.putExtra("score3",String.valueOf(score_int));
                        startActivity(intent);
                        finish();
                    }

                    if(fish_count < 1)
                    {
                        Intent intent = new Intent(Main3Activity.this, End3Activity.class);
                        intent.putExtra("score3",String.valueOf(score_int));
                        startActivity(intent);
                        finish();
                    }

                    if(mouse_count < 1)
                    {
                        Intent intent = new Intent(Main3Activity.this, End3Activity.class);
                        intent.putExtra("score3",String.valueOf(score_int));
                        startActivity(intent);
                        finish();
                    }

                    if (v.getTag().toString().equals(TAG_MILK_ON)) {
                        milk_count -=1;
                        countclick = 0;
                        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
                        ((ImageView)v).startAnimation(anim);
                        score.setText(String.valueOf(score_int++));
                        ((ImageView) v).setImageResource(R.drawable.cat1down);
                        v.setTag(TAG_OFF);

                    } else if (v.getTag().toString().equals(TAG_FISH_ON)) {
                        fish_count-=1;
                        countclick = 0;
                        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
                        ((ImageView)v).startAnimation(anim);
                        score.setText(String.valueOf(score_int++));
                        ((ImageView) v).setImageResource(R.drawable.cat1down);
                        v.setTag(TAG_OFF);

                    } else if (v.getTag().toString().equals(TAG_MOUSE_ON)) {
                        mouse_count-=1;
                        countclick = 0;
                        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
                        ((ImageView)v).startAnimation(anim);
                        score.setText(String.valueOf(score_int++));
                        ((ImageView) v).setImageResource(R.drawable.cat1down);
                        v.setTag(TAG_OFF);

                    }
                    else if (v.getTag().toString().equals(TAG_MILK_ON2)) {
                        milk_count -=1;
                        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
                        ((ImageView)v).startAnimation(anim);
                        if(countclick ==2)
                        {
                            score.setText(String.valueOf(score_int+=3));
                            ((ImageView) v).setImageResource(R.drawable.cat1down);
                            v.setTag(TAG_OFF);
                            countclick =0;
                        }
                    } else if (v.getTag().toString().equals(TAG_FISH_ON2)) {
                        fish_count-=1;
                        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                        ((ImageView) v).startAnimation(anim);
                        if (countclick == 3) {
                            score.setText(String.valueOf(score_int += 6));
                            ((ImageView) v).setImageResource(R.drawable.cat1down);
                            v.setTag(TAG_OFF);
                            countclick = 0;
                        }
                    }else if (v.getTag().toString().equals(TAG_MOUSE_ON2)) {
                        mouse_count-=1;
                        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                        ((ImageView) v).startAnimation(anim);
                        if (countclick == 2) {
                            score.setText(String.valueOf(score_int += 4));
                            ((ImageView) v).setImageResource(R.drawable.cat1down);
                            v.setTag(TAG_OFF);
                            countclick = 0;
                        }
                    }

                    else {
                        countclick = 0;
                        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
                        ((ImageView)v).startAnimation(anim);

                        score.setText(String.valueOf(score_int));
                        // 우유 1개 원하는 고양이, 우유 2개 원하는 고양이 랜덤으로 나올 수 있도록.
                        Random rd = new Random();
                        if((rd.nextInt(1000)+1)%6 == 0)
                        {
                            ((ImageView) v).setImageResource(R.drawable.catfish1);
                            v.setTag(TAG_FISH_ON);
                        }
                        else if((rd.nextInt(1000)+1)%6 == 1)
                        {
                            ((ImageView) v).setImageResource(R.drawable.catfish3);
                            v.setTag(TAG_FISH_ON2);
                        }
                        else if((rd.nextInt(1000)+1)%6 == 2)
                        {
                            ((ImageView) v).setImageResource(R.drawable.cat1);
                            v.setTag(TAG_MILK_ON);
                        }
                        else if((rd.nextInt(1000)+1)%6 == 3)
                        {
                            ((ImageView) v).setImageResource(R.drawable.cat2);
                            v.setTag(TAG_MILK_ON2);
                        }
                        else if((rd.nextInt(1000)+1)%6 == 4)
                        {
                            ((ImageView) v).setImageResource(R.drawable.mouse1);
                            v.setTag(TAG_MOUSE_ON);
                        }
                        else if((rd.nextInt(1000)+1)%6 == 5)
                        {
                            ((ImageView) v).setImageResource(R.drawable.mouse2);
                            v.setTag(TAG_MOUSE_ON2);
                        }
                    }
                }
            });
        }

        for (int i = 0; i < img_array.length; i++) {
            // i는 DThread 클래스 정의 시에 고양이 인덱스 번호이다.
            new Thread(new Main3Activity.DThread(i)).start();
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
            if((rd.nextInt(1000)+1)%6 == 0)
            {
                img_array[msg.arg1].setImageResource(R.drawable.catfish1);
                img_array[msg.arg1].setTag(TAG_FISH_ON);
            }
            else if((rd.nextInt(1000)+1)%6 == 1)
            {
                img_array[msg.arg1].setImageResource(R.drawable.catfish3);
                img_array[msg.arg1].setTag(TAG_FISH_ON2);
            }
            else if((rd.nextInt(1000)+1)%6 == 2)
            {
                img_array[msg.arg1].setImageResource(R.drawable.cat1);
                img_array[msg.arg1].setTag(TAG_MILK_ON);
            }
            else if((rd.nextInt(1000)+1)%6 == 3)
            {
                img_array[msg.arg1].setImageResource(R.drawable.cat2);
                img_array[msg.arg1].setTag(TAG_MILK_ON2);
            }
            else if((rd.nextInt(1000)+1)%6 == 4)
            {
                img_array[msg.arg1].setImageResource(R.drawable.mouse1);
                img_array[msg.arg1].setTag(TAG_MOUSE_ON);
            }
            else if((rd.nextInt(1000)+1)%6 == 5)
            {
                img_array[msg.arg1].setImageResource(R.drawable.mouse2);
                img_array[msg.arg1].setTag(TAG_MOUSE_ON2);
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
