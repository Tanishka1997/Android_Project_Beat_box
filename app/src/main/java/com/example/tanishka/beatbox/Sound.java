package com.example.tanishka.beatbox;

/**
 * Created by Tanishka on 25-05-2016.
 */
public class Sound {
    String mAssetPath;
    String mName;
    private Integer mSoundId;
    public String getmAssetPath() {
        return mAssetPath;
    }

    public Integer getmSoundId() {
        return mSoundId;
    }

    public void setmSoundId(Integer mSoundId) {
        this.mSoundId = mSoundId;
    }


    public String getmName() {
        return mName;
    }


    public Sound(String assetPath){
     mAssetPath=assetPath;
        String components[]=assetPath.split("/");
        String fileName=components[components.length-1];
       mName= fileName.replace(".wav","");
    }

}
