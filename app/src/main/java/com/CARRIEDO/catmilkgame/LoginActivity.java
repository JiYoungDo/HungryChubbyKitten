package com.CARRIEDO.catmilkgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText id,pwd;
    Button btn;
    TextView register;
    String loginId, loginPwd;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        mp = MediaPlayer.create(this,R.raw.bgm);
        mp.setLooping(true);
        mp.start();


        //DBHelper 객체 생성
        final String dbName = "Person.db";
        int dbVersion =1;
        final DBHelper dbHelper;
        dbHelper = new DBHelper(this,dbName,null,dbVersion);

        id = findViewById(R.id.login_et_id);
        pwd = findViewById(R.id.login_et_pw);
        btn = findViewById(R.id.login_btn);

        TextView tv_title = findViewById(R.id.login_tv_title);
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        tv_title.startAnimation(anim);

        register = findViewById(R.id.login_register_tv);
        // 회원가입 텍스트 누르면 회원가입 창으로
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });


        String idStr = id.getText().toString();
        String pwStr = pwd.getText().toString();


        SharedPreferences auto = getSharedPreferences("idStr", Activity.MODE_PRIVATE);
        // sharedPreferences에 아무런 정보도 없으므로 값을 저장할 키들을 생성한다.
        // getString의 첫 번째 인자는 저잗될 키, 두 번째 인자는 값이다.
        // 처음에는 값이 없어서 키값은 원하는 것으로 하고, 값을 null을 준다.
        loginId = auto.getString("inputId",null);
        loginPwd = auto.getString("inputPwd",null);

        if(loginId != null && loginPwd != null)
        {
            Toast.makeText(LoginActivity.this, loginId+ "님 자동로그인 입니다.",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, StartActivity.class);
            startActivity(intent);
            finish();
        }
        else if(loginId == null && loginPwd ==null)
        {
            final String sql = "SELECT * FROM Person WHERE id ='"+idStr+"'AND pw='"+pwStr+"';";
            final SQLiteDatabase db = dbHelper.getReadableDatabase();
            auto = getSharedPreferences("idStr",Activity.MODE_PRIVATE);
            final SharedPreferences.Editor autoLogin = auto.edit();

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cursor cs = db.rawQuery(sql, null);
                    if( cs != null )
                    {
                        // 있을 때

                        // 아이디가 cat이고, 비밀번호가 milk 일 경우 SharedPreference.Editor을 통해
                        // auto의 loginId와 loginPwd에 값을 저장한다.
                        autoLogin.putString("inputId",id.getText().toString());
                        autoLogin.putString("inputPwd",pwd.getText().toString());
                        //commit을 해주어야 저장이 된다.
                        autoLogin.commit();
                        Toast.makeText(LoginActivity.this,id.getText().toString()+
                                "님 환영합니다.",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,StartActivity.class);
                        startActivity(intent);

                        finish();

                    }
                }
            });

        }

        else {
            // 데이터 베이스에 없을 때
            Toast.makeText(getApplicationContext(),"회원 정보가 없습니다. 회원 가입 해주세요.",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.release();
    }
}
