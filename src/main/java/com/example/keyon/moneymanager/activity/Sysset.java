package com.example.keyon.moneymanager.activity;

import com.example.keyon.moneymanager.R;

import android.content.Intent;
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
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.icon48px);
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
