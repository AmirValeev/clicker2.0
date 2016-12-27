package com.example.clicker20;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class SettingWallpaper extends AppCompatActivity {

    Button fireBtn,waterBtn,ironBtn,backBtn,woodBtn;
    int numbOfPic;
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_wallpaper);
        fireBtn = (Button) findViewById(R.id.fireBtn);
        waterBtn = (Button) findViewById(R.id.waterBton);
        ironBtn = (Button) findViewById(R.id.mettalBtn);
        backBtn = (Button) findViewById(R.id.backButton);
        woodBtn = (Button) findViewById(R.id.woodbtn);
          res = getResources();
        int white = res.getColor(R.color.white);

        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_setting_wallpaper);

        int wall = getIntent().getIntExtra("WALLOFSETTING",0);
          numbOfPic = wall;

        switch (wall){
            case 1: layout.setBackgroundResource(R.drawable.fonfire);
                backBtn.setBackgroundResource(R.drawable.fireanotherswapbtn);
                backBtn.setTextColor(white);
                break;
            case 2: layout.setBackgroundResource(R.drawable.fonwater);
                backBtn.setBackgroundResource(R.drawable.wateranotherbtnswap);
                break;
           case 3: layout.setBackgroundResource(R.drawable.metall_fon_carapiny_poverhnost_18408_1920x1080);
                  backBtn.setBackgroundResource(R.drawable.ironbtnswapdefault);
               break;
            case 4: layout.setBackgroundResource(R.drawable.woodfon);
                backBtn.setTextColor(white);
                backBtn.setBackgroundResource(R.drawable.wooddiffbtnswap);
        }


       View.OnClickListener Listn = (new View.OnClickListener(){
            @Override
           public void onClick(View v) {
                int white = res.getColor(R.color.white);
           switch (v.getId()){
               case R.id.fireBtn: numbOfPic=1;
                   layout.setBackgroundResource(R.drawable.fonfire);
                   backBtn.setBackgroundResource(R.drawable.fireanotherswapbtn);
                   backBtn.setTextColor(white);
                   break;

               case R.id.waterBton: numbOfPic=2;
                  layout.setBackgroundResource(R.drawable.fonwater);
                   backBtn.setBackgroundResource(R.drawable.wateranotherbtnswap);
                   break;

                case R.id.mettalBtn: numbOfPic=3;
                  layout.setBackgroundResource(R.drawable.metall_fon_carapiny_poverhnost_18408_1920x1080);
                    backBtn.setBackgroundResource(R.drawable.ironbtnswapdefault);
                    break;
               case R.id.woodbtn: numbOfPic = 4;
                  layout.setBackgroundResource(R.drawable.woodfon);
                   backBtn.setBackgroundResource(R.drawable.wooddiffbtnswap);
                   backBtn.setTextColor(white);

          }
           }
       }

        );
        fireBtn.setOnClickListener(Listn);
        waterBtn.setOnClickListener(Listn);
       ironBtn.setOnClickListener(Listn);
        woodBtn.setOnClickListener(Listn);



    }
    public void backToMain (View view){
       Intent intent = new Intent(this,MainActivity.class);

        intent.putExtra("WALL",numbOfPic);
        startActivity(intent);
    }
}
