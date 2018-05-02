package com.example.keyon.moneymanager.activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.keyon.moneymanager.R;
import com.example.keyon.moneymanager.dao.PwdDAO;

public class Login extends AppCompatActivity{
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
        setContentView(R.layout.password);

        final PasswordInputView passwordInputView = (PasswordInputView) findViewById(R.id.passwordInputView);
//        final EditText txtlogin = (EditText) findViewById(R.id.txtLogin);
        Button btnlogin = (Button) findViewById(R.id.btnLogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                PwdDAO pwdDAO = new PwdDAO(Login.this);
                if(pwdDAO.getCount()==0||pwdDAO.find().getPassword().isEmpty()){
                    if(passwordInputView.getText().toString().isEmpty()){
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this,"您未设密码，请直接登录",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if(pwdDAO.find().getPassword().equals(passwordInputView.getText().toString())){
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this,"请输入正确的密码",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                passwordInputView.setText("");
            }
        });

        Button btnclose = (Button) findViewById(R.id.btnClose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();// 退出当前程序
            }
        });
    }

}
