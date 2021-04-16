package com.tabutech.advancedone.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.tabutech.advancedone.R;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment  extends Fragment {

    //this is for a log massage
    private static final String TAG = "BodyPartFragment";


    //these strings are for saved instances
    private static String savedImage;
    private static String savedIndex;
    private List<Integer> imageIds;
    private int mListIndex;

    //mandatory constructor for instantiating the fragment
    public BodyPartFragment(){

    }

    //this inflates the fragment layout and sets any image resource


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {


        //this helps in bringing out the saved state variables
        if (savedInstanceState != null){
            imageIds = savedInstanceState.getIntegerArrayList(savedImage);
            mListIndex = savedInstanceState.getInt(savedIndex);
        }

        //inflate the android_me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part,container,false);

        //also get a reference to the image view
        final ImageView imageView = rootView.findViewById(R.id.body_part_image_view);

        //set the image resource to display
       // imageView.setImageResource(AndroidImageAssets.getHeads().get(0));

        if (imageIds != null){
            imageView.setImageResource(imageIds.get(mListIndex));
            //here were adding a click listener to the fragments

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListIndex < imageIds.size()-1){
                        mListIndex++;
                    }else {
                        mListIndex = 0;
                    }
                    imageView.setImageResource(imageIds.get(mListIndex));
                }
            });

        }else {
            //this messo is showed when the imageIds are null
            Log.d(TAG,"Failed to show the images");
        }

        //return root view
        return  rootView;
    }

    public void setImageIds(List<Integer> imageIds) {
        this.imageIds = imageIds;
    }

    public void setmListIndex(int mListIndex) {
        this.mListIndex = mListIndex;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putIntegerArrayList(savedImage, (ArrayList <Integer>) imageIds);
        outState.putInt(savedIndex,mListIndex);

    }
}
