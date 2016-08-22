package com.example.tanishka.beatbox;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BeatBoxFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BeatBoxFragment extends Fragment {
    private BeatBox mBeatBox;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public BeatBoxFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment BeatBoxFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BeatBoxFragment newInstance() {
        BeatBoxFragment fragment = new BeatBoxFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setRetainInstance(true);
        mBeatBox=new BeatBox(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_beat_box2, container, false);
        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.fragment_beat_box_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recyclerView.setAdapter(new SoundAdapter(mBeatBox.getAssetSound()));
        return  view;
    }
  private  class SoundHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private Button mbutton;
      Sound mSound;
      public SoundHolder(View itemView) {
          super(itemView);
       mbutton=(Button) itemView.findViewById(R.id.list_item_sound_button);
       mbutton.setOnClickListener(this);
      }
    public void bind(Sound sound){
        mSound=sound;
        mbutton.setText(mSound.getmName());
    }

      @Override
      public void onClick(View v) {
          mBeatBox.play(mSound);
      }
  }
  private  class  SoundAdapter extends RecyclerView.Adapter<SoundHolder>{
    List <Sound> my_sound;

      public SoundAdapter(List<Sound> sound) {
          my_sound=sound;
      }

      @Override
      public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
        View v=  layoutInflater.inflate(R.layout.list_item_sound,parent,false);
          return new SoundHolder(v);
      }

      @Override
      public void onBindViewHolder(SoundHolder holder, int position) {
           Sound sound=my_sound.get(position);
           holder.bind(sound);
      }

      @Override
      public int getItemCount() {
          return my_sound.size();
      }
  }

    @Override
    public void onDestroy() {

        super.onDestroy();
        mBeatBox.release();
    }
}
