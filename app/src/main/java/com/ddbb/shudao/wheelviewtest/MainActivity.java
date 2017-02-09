package com.ddbb.shudao.wheelviewtest;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("wheel dialog", "indicate I can see log");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<String> dataList = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H");

        textView = (TextView) findViewById(R.id.text);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: open dialog

                Log.d("wheel dialog", "in click");

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);  //先得到构造器

                //  载入布局
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.wheel_select, null);
                builder.setView(view);

                Log.d("wheel dialog", "set layout success");

                final WheelView wheelView = (WheelView) view.findViewById(R.id.wheel_view);

                wheelView.setWheelAdapter(new ArrayWheelAdapter(MainActivity.this)); // 文本数据源
                wheelView.setSkin(WheelView.Skin.Holo); // Holo皮肤
                wheelView.setWheelData(dataList);  // 数据集合
                wheelView.setSelection(4); // 设置初始选项

                Log.d("wheel dialog", "set wheelView success");


                //  确认按钮
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("wheel dialog", "get position: " + wheelView.getCurrentPosition());
                        textView.setText(String.valueOf(wheelView.getCurrentPosition()));
                        // dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });

                //  显示
                builder.create().show();
            }
        });
    }
}
