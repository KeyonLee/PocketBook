package com.example.keyon.moneymanager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.keyon.moneymanager.R;

public class wechat extends AppCompatActivity {

    ImageView WeChat;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wechat);

        WeChat = (ImageView)findViewById(R.id.wechat) ;
        WeChat.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });
    }
}
