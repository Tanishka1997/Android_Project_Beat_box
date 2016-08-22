package com.example.tanishka.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanishka on 24-05-2016.
 */
public class BeatBox {
 public static final String TAG="BeatBox";
    public static final String SOUNDS_FOLDER="sample_sounds";
    private AssetManager mAssets;
    private SoundPool mSoundPool;
    private List<Sound> assetSound=new ArrayList<>();
    private static final int MAX_SOUNDS=5;
    public List<Sound> getAssetSound() {
        return assetSound;
    }


    public BeatBox(Context context)
    {
        mAssets=context.getAssets();
        mSoundPool=new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC,0);
        loadSounds();
    }

    private void loadSounds() {
        String soundNames[] = new String[0];
        try {

            soundNames=mAssets.list(SOUNDS_FOLDER);

        }
       catch (IOException e) {
           Log.e(TAG,"Could not list assests",e);
        }
      for(String filename:soundNames){
           String assetPath=SOUNDS_FOLDER+"/"+filename;
           Sound sound=new Sound(assetPath);
          try {
              load(sound);
          } catch (IOException e) {
              e.printStackTrace();
          }
          assetSound.add(sound);

      }
    }
   private void load(Sound sound) throws  IOException{
       AssetFileDescriptor afd=mAssets.openFd(sound.getmAssetPath());
       int SoundId=mSoundPool.load(afd,1);
       sound.setmSoundId(SoundId);
   }
   public void play(Sound sound){
       Integer soundId=sound.getmSoundId();
       if(soundId==null)
           return;
       mSoundPool.play(soundId,1.0f,1.0f,1,0,1.0f);
   }
  public void release(){
      mSoundPool.release();
  }
}
