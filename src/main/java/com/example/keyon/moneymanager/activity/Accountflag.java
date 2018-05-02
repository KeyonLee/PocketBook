package com.example.keyon.moneymanager.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.keyon.moneymanager.R;
import com.example.keyon.moneymanager.dao.FlagDAO;
import com.example.keyon.moneymanager.model.Tb_flag;

public class Accountflag extends AppCompatActivity {
    EditText txtFlag;
    Button btnflagSaveButton;
    Button btnflagCancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
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
        setContentView(R.layout.accountflag);

        txtFlag = (EditText) findViewById(R.id.txtFlag);
        btnflagSaveButton = (Button) findViewById(R.id.btnflagSave);
        btnflagCancelButton = (Button) findViewById(R.id.btnflagCancel);
        btnflagSaveButton.setOnClickListener(new OnClickListener() {// 为保存按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String strFlag = txtFlag.getText().toString();
                if (!strFlag.isEmpty()) {
                    FlagDAO flagDAO = new FlagDAO(Accountflag.this);
                    Tb_flag tb_flag = new Tb_flag(
                            flagDAO.getMaxId() + 1, strFlag);
                    flagDAO.add(tb_flag);
                    Toast.makeText(Accountflag.this, "数据添加成功！",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Accountflag.this, "请输入便签！",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnflagCancelButton.setOnClickListener(new OnClickListener() {// 为取消按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                txtFlag.setText("");
                finish();
            }
        });
    }
}
