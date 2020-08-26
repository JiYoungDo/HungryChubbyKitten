package com.CARRIEDO.catmilkgame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Objects;


public class RegisterActivity extends AppCompatActivity {

    EditText et_id, et_pw;
    Button check_btn, register_btn;
    ImageButton back_btn;

    Boolean checkID = false;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //DBHelper 객체 생성
        final String dbName = "Person.db";
        int dbVersion =1;
        final DBHelper dbHelper;
        dbHelper = new DBHelper(this,dbName,null,dbVersion);

        et_id= findViewById(R.id.register_et_id);
        et_pw= findViewById(R.id.register_et_pw);
        check_btn = findViewById(R.id.register_id_check_btn);
        register_btn = findViewById(R.id.register_id_register_btn);
        back_btn = findViewById(R.id.register_back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

       // String[] empty = new String[10];

        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idStr = et_id.getText().toString();
                String sql = "SELECT * FROM Person WHERE id ='"+idStr+"';";
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                if(idStr =="")
                {
                    Toast.makeText(getApplicationContext(),"사용 불가능한 형식입니다.",Toast.LENGTH_SHORT).show();
                }

                @SuppressLint("Recycle") Cursor cs = db.rawQuery(sql, null);
                // String tempid = cs.getString(0)+"a";
                if(cs != null && cs.moveToFirst()){
                    Toast.makeText(getApplicationContext(),"사용 불가능 합니다.",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getApplicationContext(), "사용 가능합니다.", Toast.LENGTH_SHORT).show();
                    checkID=true;
                }

            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idStr = et_id.getText().toString();
                String pwStr = et_pw.getText().toString();
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                if(checkID == true)
                {
                    String sql = String.format("INSERT INTO Person VALUES('%s','%s');",idStr,pwStr);
                    db.execSQL(sql);

                    // 저장 완료
                    Toast.makeText(getApplicationContext(), "회원가입을 축하드립니다.",Toast.LENGTH_LONG).show();
                    checkID =false;

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "중복 체크를 먼저 해주세요.",Toast.LENGTH_LONG).show();
                    et_id.setText(" ");
                    et_pw.setText(" ");
                }

            }
        });

    }
}
