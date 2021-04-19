package com.tabutech.advancedone.ui;

import android.content.Context;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class MasterListAdapter extends BaseAdapter {


    private Context context;
    private List<Integer> mImageIds;


    public MasterListAdapter(Context context, List<Integer> mImageIds){
        this.context = context;
        this.mImageIds = mImageIds;
    }

    //this one returns the number of items the list will display
    @Override
    public int getCount() {
        return mImageIds.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    //this one creates new image view for each item referenced by the adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;

        if (convertView == null){
            //if imageView is not recycled , this creates a new image view to hold an image
            imageView = new ImageView(context);

            //define the layout parameters
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);

        }else {
            imageView = (ImageView)convertView;
        }
        //set the image resource and return the newly created image

        imageView.setImageResource(mImageIds.get(position));
        return imageView;
    }
}

