package com.example.myapplication;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.larswerkman.holocolorpicker.ColorPicker;

import java.io.IOException;

public class upload extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Button btn_back=(Button)findViewById(R.id.button_back);
        btn_back.setOnClickListener(btn_backListner);

        Button btn_select=(Button)findViewById(R.id.select);
        btn_select.setOnClickListener(btn_selectListner);

        imageButton = findViewById(R.id.imageView);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onImageButtonClick(v);
            }
        });
    }

    public void onImageButtonClick(View view) {
        // Open gallery to choose a photo
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                imageButton.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //Back button listner
    private Button.OnClickListener btn_backListner=
            new Button.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Animation animation = AnimationUtils.loadAnimation(upload.this,R.anim.scale_up);
                    v.startAnimation(animation);
                    finish();
                }
            };

    private Button.OnClickListener btn_selectListner=
            new Button.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Animation animation = AnimationUtils.loadAnimation(upload.this,R.anim.scale_up);
                    v.startAnimation(animation);
                    Animation animation2 = AnimationUtils.loadAnimation(upload.this,R.anim.scale_down);
                    v.startAnimation(animation2);

                    Toast toast=Toast.makeText(getApplicationContext(),"Photo Selected",Toast.LENGTH_SHORT);
                    toast.show();
                }
            };
}