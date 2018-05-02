package com.example.keyon.moneymanager.activity;

import java.util.List;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keyon.moneymanager.R;
import com.example.keyon.moneymanager.dao.OutaccountDAO;
import com.example.keyon.moneymanager.model.Tb_outaccount;

public class Outaccountinfo extends AppCompatActivity {
    public static final String FLAG = "id";
    ListView lvinfo;
    String strType = "";

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
        setContentView(R.layout.outaccountinfo);
        lvinfo = (ListView) findViewById(R.id.lvoutaccountinfo);

        ShowInfo(R.id.btnoutinfo);

        lvinfo.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String strInfo = String.valueOf(((TextView) view).getText());
                String strid = strInfo.substring(0, strInfo.indexOf('|'));
                Intent intent = new Intent(Outaccountinfo.this, InfoManage.class);
                intent.putExtra(FLAG, new String[] { strid, strType });
                finish();
                startActivity(intent);
            }
        });
    }

    private void ShowInfo(int intType) {
        String[] strInfos = null;
        ArrayAdapter<String> arrayAdapter = null;
        strType = "btnoutinfo";
        OutaccountDAO outaccountinfo = new OutaccountDAO(Outaccountinfo.this);
        List<Tb_outaccount> listoutinfos = outaccountinfo.getScrollData(0,
                (int) outaccountinfo.getCount());
        strInfos = new String[listoutinfos.size()];
        int i = 0;
        for (Tb_outaccount tb_outaccount : listoutinfos) {
            strInfos[i] = tb_outaccount.getid() + "|" + tb_outaccount.getType()
                    + " " + String.valueOf(tb_outaccount.getMoney()) + "元     "
                    + tb_outaccount.getTime();
            i++;
        }
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strInfos);
        lvinfo.setAdapter(arrayAdapter);
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        ShowInfo(R.id.btnoutinfo);
    }
}