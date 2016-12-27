package com.example.clicker20;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Instructions extends AppCompatActivity {
    int numbOfPic;
    Button btn;
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_instructions);
        btn = (Button) findViewById(R.id.button7);
        res = getResources();
        int white =res.getColor(R.color.white);
        int wall = getIntent().getIntExtra("WALLOFINSTRUC",0);
        numbOfPic = wall;

        switch (numbOfPic){
            case 1: layout.setBackgroundResource(R.drawable.fonfire);
                btn.setBackgroundResource(R.drawable.fireanotherswapbtn);
                btn.setTextColor(white);
                break;
            case 2: layout.setBackgroundResource(R.drawable.fonwater);
                  btn.setBackgroundResource(R.drawable.wateranotherbtnswap);
                break;
            case 3: layout.setBackgroundResource(R.drawable.metall_fon_carapiny_poverhnost_18408_1920x1080);
                btn.setBackgroundResource(R.drawable.ironbtnswapdefault);
                break;
            case 4: layout.setBackgroundResource(R.drawable.woodfon);
                btn.setBackgroundResource(R.drawable.wooddiffbtnswap);
                btn.setTextColor(white);
        }

    }
        public void back(View view){
        Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("WALL",numbOfPic);
        startActivity(intent);
    }
    }

