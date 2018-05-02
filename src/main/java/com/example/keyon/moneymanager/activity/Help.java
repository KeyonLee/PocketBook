package com.example.keyon.moneymanager.activity;

import com.example.keyon.moneymanager.R;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Help extends AppCompatActivity {
    EditText txtFlag;
    Button btnflagSaveButton;
    Button btnflagCancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.appicon);
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(0.13f, 0.13f);
        Bitmap resizeBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth,
                bitmapHeight, matrix, true);
        Drawable drawable = new BitmapDrawable(resizeBitmap);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(drawable);
        actionBar.setDisplayUseLogoEnabled(true);
        setContentView(R.layout.help);
        WebView webview=(WebView)findViewById(R.id.webView1);
        StringBuilder sb=new StringBuilder();
        sb.append("<div><strong style=\"font-size:20px;\">口袋账本<sup style=\"font-size:10px;\">①</sup></strong>使用帮助：</div>");
        sb.append("<ul>");
        sb.append("<li>点击“修改密码”可以修改登录密码，App初始没有密码。</li>");
        sb.append("<li>支出管理：点击“新增支出”可以添加支出信息；点击“我的支出”可以查看、修改或删除支出信息。</li>");
        sb.append("<li>收入管理：点击“新增收入”可以添加收入信息；点击“我的收入”可以查看、修改或删除收入信息。</li>");
        sb.append("<li>便签管理：点击“收支便签”可以添加便签信息；点击“数据管理”-“便签信息”可以查看、修改或删除便签信息。</li>");
        sb.append("<li>注销登录：点击“登出”可以注销本App。</li>");
        sb.append("</ul>");
        sb.append("<br>");
        sb.append("<div  style=\"font-size:12px;color: #696969;\">①该App由李宇坤开发&nbsp;&nbsp;&nbsp;&nbsp;©Keyon</div>");
        webview.setBackgroundColor(0xEEEEE0);
        webview.loadDataWithBaseURL(null, sb.toString(),"text/html","utf-8",null);//加载数据
        webview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(Help.this, "使用起来感觉不错？点击右上角打赏我吧  (～￣▽￣)～ ", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.wechat) {
            Intent intent = new Intent(Help.this, wechat.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.alipay) {
            Intent intent = new Intent(Help.this, alipay.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
