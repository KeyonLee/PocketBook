package com.example.keyon.moneymanager.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.keyon.moneymanager.R;
import com.example.keyon.moneymanager.dao.OutaccountDAO;
import com.example.keyon.moneymanager.model.Tb_outaccount;

import java.util.Calendar;

public class AddOutaccount extends AppCompatActivity {
    protected static final int DATE_DIALOG_ID = 0;
    EditText txtMoney, txtTime, txtAddress, txtMark;
    Spinner spType;
    Button btnSaveButton;
    Button btnCancelButton;

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
        setContentView(R.layout.addoutaccount);
        txtMoney = (EditText) findViewById(R.id.txtMoney);
        txtTime = (EditText) findViewById(R.id.txtTime);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
        txtMark = (EditText) findViewById(R.id.txtMark);
        spType = (Spinner) findViewById(R.id.spType);
        btnSaveButton = (Button) findViewById(R.id.btnSave);
        btnCancelButton = (Button) findViewById(R.id.btnCancel);

        txtTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                showDialog(DATE_DIALOG_ID);
            }
        });

        btnSaveButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String strMoney = txtMoney.getText().toString();
                if (!strMoney.isEmpty()) {
                    OutaccountDAO outaccountDAO = new OutaccountDAO(AddOutaccount.this);
                    Tb_outaccount tb_outaccount = new Tb_outaccount(
                            outaccountDAO.getMaxId() + 1, Double
                            .parseDouble(strMoney), txtTime
                            .getText().toString(), spType
                            .getSelectedItem().toString(),
                            txtAddress.getText().toString(), txtMark
                            .getText().toString());
                    outaccountDAO.add(tb_outaccount);
                    Toast.makeText(AddOutaccount.this, "添加成功！",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddOutaccount.this, "请输入支出金额！",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                txtMoney.setText("");
                txtMoney.setHint("0.00");
                txtTime.setText("");
                txtTime.setHint("2011-01-01");
                txtAddress.setText("");
                txtMark.setText("");
                spType.setSelection(0);
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

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDisplay();
        }
    };

    private void updateDisplay() {
        txtTime.setText(new StringBuilder().append(mYear).append("-")
                .append(mMonth + 1).append("-").append(mDay));
    }
}
