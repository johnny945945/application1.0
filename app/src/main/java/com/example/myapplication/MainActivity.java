package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //color button
        Button btn_color = (Button) findViewById(R.id.button_color);
        btn_color.setOnClickListener(btn_colorListner);
        //upload button
        Button btn_upload = (Button) findViewById(R.id.button_upload);
        btn_upload.setOnClickListener(btn_uploadListner);

        ImageButton btn_check = (ImageButton) findViewById(R.id.button_bluetooth);
        btn_check.setOnClickListener(btn_checkListner);


    }
    //color button listener
    private Button.OnClickListener btn_colorListner=
            new Button.OnClickListener(){
                public void onClick(View v) {
                    Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.scale_up);
                    v.startAnimation(animation1);
                    Intent intent=new Intent();
                    intent.setClass(MainActivity.this,color.class);
                    startActivity(intent);

                }
            };
    //upload button listener
    private Button.OnClickListener btn_uploadListner=
            new Button.OnClickListener(){
                public void onClick(View v) {
                    Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.scale_up);
                    v.startAnimation(animation);
                    Intent intent=new Intent();
                    intent.setClass(MainActivity.this,upload.class);
                    startActivity(intent);
                }
            };
    private Button.OnClickListener btn_checkListner=
            new Button.OnClickListener(){
                public void onClick(View v) {
                    Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.scale_over);
                    v.startAnimation(animation1);
//                    Animation animation2 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.scale_down);
//                    AnimationSet animationSet=new AnimationSet(false);
//                    animation1.setDuration(200);
//                    animation1.setRepeatCount(5);
//                    animation2.setStartOffset(200);
//                    animation2.setDuration(200);
//                    animation2.setRepeatCount(5);
//                    animationSet.addAnimation(animation1);
//                    animationSet.addAnimation(animation2);
//                    animationSet.setFillAfter(true);
//                    v.setAnimation(animationSet);
//                    v.startAnimation(animationSet);
                }
            };

}