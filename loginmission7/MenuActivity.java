package com.example.loginmission7;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.logging.Level;

public class MenuActivity extends AppCompatActivity {
    public static final int RESPONSE_CODE_OK = 200;
    private Intent mData;
    private String mA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent receivedIntent = getIntent();
        String username = receivedIntent.getStringExtra("username");
        String password = receivedIntent.getStringExtra("password");

        Toast.makeText(this, "username : " + username + ", password : " + password, Toast.LENGTH_LONG).show();

        Button menu01Button = findViewById(R.id.btnLocationSelector);
        menu01Button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("menu", "지역을 선택하세요.");
                getIntent().setData(Uri.parse(mA));
                receivedIntent.filterEquals(mData);

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        Button menu02Button = findViewById(R.id.btnRankingSearch);
        menu02Button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("menu", "자신의 랭크를 검색하세요.");
                resultIntent.putExtra("message", "result message is OK!");

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        Button menu03Button = findViewById(R.id.btnSelectPerson);
        menu03Button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("menu", "맞짱 뜰 상대를 결정하세요.");
                resultIntent.putExtra("message", "result message is OK!");

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}