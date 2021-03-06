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

public class FlagManage extends AppCompatActivity {
    EditText txtFlag;
    Button btnEdit, btnDel;
    String strid;

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
        setContentView(R.layout.flagmanage);
        txtFlag = (EditText) findViewById(R.id.txtFlagManage);
        btnEdit = (Button) findViewById(R.id.btnFlagManageEdit);
        btnDel = (Button) findViewById(R.id.btnFlagManageDelete);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        strid = bundle.getString(Showinfo.FLAG);
        final FlagDAO flagDAO = new FlagDAO(FlagManage.this);
        txtFlag.setText(flagDAO.find(Integer.parseInt(strid)).getFlag());

        btnEdit.setOnClickListener(new OnClickListener() {// 为修改按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Tb_flag tb_flag = new Tb_flag();
                tb_flag.setid(Integer.parseInt(strid));
                tb_flag.setFlag(txtFlag.getText().toString());
                flagDAO.update(tb_flag);
                Toast.makeText(FlagManage.this, "修改成功！",
                        Toast.LENGTH_SHORT).show();
                finish();
                Intent intentSI = new Intent(FlagManage.this, Showinfo.class);
                startActivity(intentSI);
            }
        });

        btnDel.setOnClickListener(new OnClickListener() {// 为删除按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                flagDAO.detele(Integer.parseInt(strid));
                Toast.makeText(FlagManage.this, "删除成功！",
                        Toast.LENGTH_SHORT).show();
                finish();
                Intent intentSI = new Intent(FlagManage.this, Showinfo.class);
                startActivity(intentSI);
            }
        });
    }

}
