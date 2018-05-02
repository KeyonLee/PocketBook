package com.example.keyon.moneymanager.activity;

import java.util.Map;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import com.example.keyon.moneymanager.R;
import com.example.keyon.moneymanager.dao.InaccountDAO;
import com.example.keyon.moneymanager.dao.OutaccountDAO;

public class TotalChart extends AppCompatActivity {
    private float[] money=new float[]{600,1000,600,300,1500};
    private int[] color=new int[]{Color.GREEN,Color.YELLOW,Color.RED,Color.MAGENTA,Color.BLUE};
    private final int WIDTH = 30;
    private final int OFFSET = 15;
    private int x =70;
    private int y=329;
    private int height=220;
    String[] type=null;
    private String passType="";
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
        setContentView(R.layout.chart);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        passType=bundle.getString("passType");
        Resources res=getResources();
        if("outinfo".equals(passType)){
            type=res.getStringArray(R.array.outtype);

        }else if("ininfo".equals(passType)){
            type=res.getStringArray(R.array.intype);
        }


        FrameLayout ll=(FrameLayout)findViewById(R.id.canvas);
        ll.addView(new MyView(this));

    }
    public class MyView extends View{
        public MyView(Context context) {
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.scale(3,3);
            canvas.drawColor(0xEEEEE0);
            Paint paint=new Paint();
            paint.setAntiAlias(true);

            /***********绘制坐标轴**********************/
            paint.setStrokeWidth(1);
            paint.setColor(Color.BLACK);
            canvas.drawLine(50, 330, 300, 330, paint);
            canvas.drawLine(50, 100, 50, 330, paint);

            /***********绘制柱型**********************/
            paint.setStyle(Style.FILL);
            int left=0;
            money=getMoney(passType);
            float max=maxMoney(money);
            for(int i=0;i<money.length;i++){
                paint.setColor(color[i]);
                left=x+i*(OFFSET+WIDTH);
                canvas.drawRect(left, y-height/max*money[i], left+WIDTH, y, paint);
            }

            /***********绘制纵轴的刻度**********************/
            paint.setColor(Color.BLACK);
            int tempY=0;
            for(int i=0;i<11;i++){
                tempY=y-height+height/10*i+1;
                canvas.drawLine(47,tempY , 50, tempY, paint);
                paint.setTextSize(12);
                canvas.drawText(String.valueOf((int)(max/10*(10-i))), 15,tempY+5, paint);	//绘制纵轴题注
            }

            /***********绘制说明文字**********************/
            paint.setColor(Color.BLACK);
            paint.setTextSize(21);

            /******************绘制标题*********************************/
            if("outinfo".equals(passType)){
                canvas.drawText("口袋账本-支出统计图", 40,55, paint);
            }else if("ininfo".equals(passType)){
                canvas.drawText("口袋账本-收入统计图", 40,55, paint);
            }
            paint.setTextSize(16);	//设置字体大小

            String str_type="";
            for(int i=0;i<type.length;i++){
                str_type+=type[i]+"   ";
            }
            canvas.drawText(str_type, 68,350, paint);
        }
    }
    //计算最大金额
    float maxMoney(float[] money){
        float max=money[0];
        for(int i=0;i<money.length-1;i++){
            if(max<money[i+1]){
                max=money[i+1];
            }
        }
        return max;
    }

    float[] getMoney(String flagType){
        Map mapMoney=null;
        System.out.println(flagType);
        if("ininfo".equals(flagType)){
            InaccountDAO inaccountinfo = new InaccountDAO(TotalChart.this);
            mapMoney=inaccountinfo.getTotal();
        }else if("outinfo".equals(flagType)){
            OutaccountDAO outaccountinfo = new OutaccountDAO(TotalChart.this);
            mapMoney=outaccountinfo.getTotal();
        }
        int size=type.length;
        float[] money1=new float[size];
        for(int i=0;i<size;i++){
            money1[i]=( mapMoney.get(type[i])!=null?((Float) mapMoney.get(type[i])):0);
        }
        return money1;
    }
}
