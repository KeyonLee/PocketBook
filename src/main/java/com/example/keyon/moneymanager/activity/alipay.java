package com.example.keyon.moneymanager.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.keyon.moneymanager.R;

public class alipay extends AppCompatActivity {

    ImageView AliPay;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alipay);

        AliPay = (ImageView)findViewById(R.id.alipay) ;
        AliPay.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                Toast.makeText(alipay.this, "吱口令已复制到剪切板，打开支付宝即可给我打赏哟  (～￣▽￣)～ ", Toast.LENGTH_LONG).show();
                ClipData mClipData = ClipData.newPlainText("吱口令已复制到剪切板，打开支付宝即可给我打赏哟", "#吱口令#长按复制此条消息，打开支付宝给我转账zYnk8B17rs");
                cm.setPrimaryClip(mClipData);
            }
        });
    }
}
