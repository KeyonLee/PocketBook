package com.example.keyon.moneymanager.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.example.keyon.moneymanager.R;
import com.example.keyon.moneymanager.dao.FlagDAO;
import com.example.keyon.moneymanager.dao.InaccountDAO;
import com.example.keyon.moneymanager.dao.OutaccountDAO;
import com.example.keyon.moneymanager.model.Tb_flag;
import com.example.keyon.moneymanager.model.Tb_inaccount;
import com.example.keyon.moneymanager.model.Tb_outaccount;

public class Showinfo extends AppCompatActivity {
    public static final String FLAG = "id";
    Button btnoutinfo, btnininfo, btnflaginfo;
    ListView lvinfo;
    String strType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.icon48px);
        actionBar.setDisplayUseLogoEnabled(true);
        setContentView(R.layout.showinfo);

        lvinfo = (ListView) findViewById(R.id.lvinfo);
        btnoutinfo = (Button) findViewById(R.id.btnoutinfo);
        btnininfo = (Button) findViewById(R.id.btnininfo);
        btnflaginfo = (Button) findViewById(R.id.btnflaginfo);


        btnoutinfo.setOnClickListener(new OnClickListener() {// 为支出信息按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ShowInfo(R.id.btnoutinfo);
            }
        });

        btnininfo.setOnClickListener(new OnClickListener() {// 为收入信息按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ShowInfo(R.id.btnininfo);
            }
        });
        btnflaginfo.setOnClickListener(new OnClickListener() {// 为便签信息按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ShowInfo(R.id.btnflaginfo);
            }
        });

        lvinfo.setOnItemClickListener(new OnItemClickListener(){// 为ListView添加项单击事件
            // 重写onItemClick方法
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String strInfo = String.valueOf(((TextView) view).getText());
                String strid = strInfo.substring(0, strInfo.indexOf('|'));
                Intent intent = null;
                if (strType == "btnflaginfo") {
                    intent = new Intent(Showinfo.this, FlagManage.class);
                    intent.putExtra(FLAG, strid);
                    startActivity(intent);
                }
            }
        });
    }

    private void ShowInfo(int intType) {
        String[] strInfos = null;
        ArrayAdapter<String> arrayAdapter = null;
        Intent intent = null;
        switch (intType) {
            case R.id.btnoutinfo:
                strType = "outinfo";
                intent = new Intent(Showinfo.this, TotalChart.class);
                intent.putExtra("passType", strType);
                startActivity(intent);
                break;
            case R.id.btnininfo:

                strType = "ininfo";
                intent = new Intent(Showinfo.this, TotalChart.class);
                intent.putExtra("passType", strType);
                startActivity(intent);
                break;
            case R.id.btnflaginfo:
                strType = "btnflaginfo";
                FlagDAO flaginfo = new FlagDAO(Showinfo.this);
                List<Tb_flag> listFlags = flaginfo.getScrollData(0,
                        (int) flaginfo.getCount());
                strInfos = new String[listFlags.size()];
                int n = 0;
                for (Tb_flag tb_flag : listFlags) {
                    strInfos[n] = tb_flag.getid() + " | " + tb_flag.getFlag();
                    if (strInfos[n].length() > 15)
                        strInfos[n] = strInfos[n].substring(0, 15) + "……";
                    n++;
                }

                arrayAdapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, strInfos);
                lvinfo.setAdapter(arrayAdapter);
                break;
        }
    }

}