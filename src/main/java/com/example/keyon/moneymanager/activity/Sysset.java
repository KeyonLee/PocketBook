package com.example.keyon.moneymanager.activity;

import com.example.keyon.moneymanager.R;

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
import com.example.keyon.moneymanager.dao.PwdDAO;
import com.example.keyon.moneymanager.model.Tb_pwd;

public class Sysset extends AppCompatActivity {
    EditText txtpwd;
    Button btnSet, btnsetCancel;

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
        setContentView(R.layout.sysset);

        txtpwd = (EditText) findViewById(R.id.txtPwd);
        btnSet = (Button) findViewById(R.id.btnSet);
        btnsetCancel = (Button) findViewById(R.id.btnsetCancel);

        btnSet.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                PwdDAO pwdDAO = new PwdDAO(Sysset.this);
                Tb_pwd tb_pwd = new Tb_pwd(txtpwd.getText().toString());
                if (pwdDAO.getCount() == 0) {
                    pwdDAO.add(tb_pwd);
                } else {
                    pwdDAO.update(tb_pwd);
                }
                Toast.makeText(Sysset.this, "设置成功！", Toast.LENGTH_SHORT)
                        .show();
                finish();
            }
        });

        btnsetCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                txtpwd.setText("");
                txtpwd.setHint("请输入密码");
                finish();
            }
        });
    }
}
