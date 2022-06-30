package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout choose = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_main,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//		builder.setView(LayoutInflater.from(this).inflate(R.layout.choose_purchase_way,null));
        builder.setView(choose);
        builder.setTitle("Choose Payment Method");
//		builder.setTitle("请选择支付方式");

        AlertDialog alertDialog = builder.create();

        Button Button1 = (Button) choose.findViewById(R.id.ib_select1Pay);
        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LogUtils.e("AlertDialog","select 1");
                Toast.makeText(MainActivity.this, "你点击了1按钮", Toast.LENGTH_SHORT).show();
            }
        });
        Button Button2 = (Button) choose.findViewById(R.id.ib_select2Pay);
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LogUtils.e("AlertDialog","select 2");
                Toast.makeText(MainActivity.this, "你点击了2按钮", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.show();
//		alertDialog.getWindow().setLayout(600,800);
//上面的方法也可以修改alertdialog 的大小，但是我只需要宽度固定，所以采用下面的方法。
        WindowManager.LayoutParams  lp= alertDialog.getWindow().getAttributes();
        lp.width=850;//定义宽度
//		lp.height=800;//定义高度
        alertDialog.getWindow().setAttributes(lp);
    }
}