package com.example.keyon.moneymanager.activity;

import com.example.keyon.moneymanager.R;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class Help extends AppCompatActivity {
    EditText txtFlag;
    Button btnflagSaveButton;
    Button btnflagCancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.icon48px);
        actionBar.setDisplayUseLogoEnabled(true);
        setContentView(R.layout.help);
        WebView webview=(WebView)findViewById(R.id.webView1);
        StringBuilder sb=new StringBuilder();
        sb.append("<div><strong style=\"font-size:20px;\">口袋账本<sup style=\"font-size:10px;\">①</sup></strong>使用帮助：</div>");
        sb.append("<ul>");
        sb.append("<li>修改密码：选择“修改密码”模块可以修改登录密码，App初始没有密码。</li>");
        sb.append("<li>支出管理：选择“新增支出”模块可以添加支出信息；选择“我的支出”模块可以查看、修改或删除支出信息。</li>");
        sb.append("<li>收入管理：选择“新增收入”模块可以添加收入信息；选择“我的收入”模块可以查看、修改或删除收入信息。</li>");
        sb.append("<li>便签管理：选择“收支便签”模块可以添加便签信息；选择“数据管理”模块中的“便签信息”按钮可以查看、修改或删除便签信息。</li>");
        sb.append("<li>注销登录：选择“登出”模块可以注销本App。</li>");
        sb.append("</ul>");
        sb.append("<br>");
        sb.append("<div  style=\"font-size:12px;color: #696969;\">①该App由李宇坤开发&nbsp;&nbsp;&nbsp;&nbsp;©Keyon</div>");
        webview.setBackgroundColor(0xEEEEE0);
        webview.loadDataWithBaseURL(null, sb.toString(),"text/html","utf-8",null);//加载数据
    }
}
