package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.larswerkman.holocolorpicker.ColorPicker;

public class color extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Search setting of color
        SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String value = preferences.getString("color_selected", "null");
        if(value!="null"){
            TextView textView=findViewById(R.id.color_show);
            int color_value= Color.parseColor(value);
            TextView textView1=findViewById(R.id.color_show);
            textView1.setBackgroundColor(color_value);
        }
        ColorPicker picker = (ColorPicker) findViewById(R.id.picker);
        picker.setShowOldCenterColor(false);

        Button btn_back=(Button)findViewById(R.id.button_back);
        btn_back.setOnClickListener(btn_backListner);

        Button btn_select=(Button)findViewById(R.id.button_select);
        btn_select.setOnClickListener(btn_selectListner);
    }
    //Back button listner
    private Button.OnClickListener btn_backListner=
            new Button.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Animation animation = AnimationUtils.loadAnimation(color.this,R.anim.scale_up);
                    v.startAnimation(animation);
                    finish();
                }
            };

    public static String decimalToRGBHex(int decimalValue) {
        int red = (decimalValue >> 16) & 0xFF;
        int green = (decimalValue >> 8) & 0xFF;
        int blue = decimalValue & 0xFF;

        return String.format("#%02X%02X%02X", red, green, blue);
    }
    //select color button listner
    private Button.OnClickListener btn_selectListner=
            new Button.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Animation animation = AnimationUtils.loadAnimation(color.this,R.anim.scale_up);
                    v.startAnimation(animation);
                    Animation animation2 = AnimationUtils.loadAnimation(color.this,R.anim.scale_down);
                    v.startAnimation(animation2);

                    ColorPicker picker = (ColorPicker) findViewById(R.id.picker);
                    picker.setOldCenterColor(picker.getColor());
                    TextView textView=findViewById(R.id.color_show);
                    int color_value= Color.parseColor(decimalToRGBHex(picker.getColor()));
                    textView.setBackgroundColor(color_value);

                    SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("color_selected", decimalToRGBHex(picker.getColor()));
                    editor.apply();

                    Toast toast=Toast.makeText(getApplicationContext(),"Color Selected",Toast.LENGTH_SHORT);
                    toast.show();
                }
            };

}
