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
import com.example.keyon.moneymanager.R;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.keyon.moneymanager.dao.InaccountDAO;
import com.example.keyon.moneymanager.model.Tb_inaccount;

public class AddInaccount extends AppCompatActivity {
    protected static final int DATE_DIALOG_ID = 0;
    EditText txtInMoney, txtInTime, txtInHandler, txtInMark;
    Spinner spInType;
    Button btnInSaveButton;
    Button btnInCancelButton;
    private int mYear;
    private int mMonth;
    private int mDay;

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
        setContentView(R.layout.addinaccount);
        txtInMoney = (EditText) findViewById(R.id.txtInMoney);
        txtInTime = (EditText) findViewById(R.id.txtInTime);
        txtInHandler = (EditText) findViewById(R.id.txtInHandler);
        txtInMark = (EditText) findViewById(R.id.txtInMark);
        spInType = (Spinner) findViewById(R.id.spInType);
        btnInSaveButton = (Button) findViewById(R.id.btnInSave);
        btnInCancelButton = (Button) findViewById(R.id.btnInCancel);

        txtInTime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                showDialog(DATE_DIALOG_ID);
            }
        });

        btnInSaveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String strInMoney = txtInMoney.getText().toString();
                if (!strInMoney.isEmpty()) {
                    InaccountDAO inaccountDAO = new InaccountDAO(
                            AddInaccount.this);
                    Tb_inaccount tb_inaccount = new Tb_inaccount(
                            inaccountDAO.getMaxId() + 1, Double
                            .parseDouble(strInMoney), txtInTime
                            .getText().toString(), spInType
                            .getSelectedItem().toString(),
                            txtInHandler.getText().toString(),
                            txtInMark.getText().toString());
                    inaccountDAO.add(tb_inaccount);
                    Toast.makeText(AddInaccount.this, "添加成功！",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddInaccount.this, "请输入收入金额！",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnInCancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                txtInMoney.setText("");
                txtInMoney.setHint("0.00");
                txtInTime.setText("");
                txtInTime.setHint("2018-04-01");
                txtInHandler.setText("");
                txtInMark.setText("");
                spInType.setSelection(0);
                finish();
            }

        });

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        updateDisplay();
    }

    @Override
    protected Dialog onCreateDialog(int id){
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
                        mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
            };

    private void updateDisplay() {
        txtInTime.setText(new StringBuilder().append(mYear).append("-")
                .append(mMonth + 1).append("-").append(mDay));
    }
}
