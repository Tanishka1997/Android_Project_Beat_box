package com.example.tanishka.beatbox;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_beat_box);
        FragmentManager fragmentManager=getSupportFragmentManager();
       Fragment beat_box_fragment= fragmentManager.findFragmentByTag("beat_box");
        if(beat_box_fragment==null)
        {
            fragmentManager.beginTransaction().replace(R.id.frame, BeatBoxFragment.newInstance(),"beat_box").commit();
        }
    }
}
