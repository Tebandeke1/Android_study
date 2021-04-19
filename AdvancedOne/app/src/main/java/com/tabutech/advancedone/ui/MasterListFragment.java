package com.tabutech.advancedone.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.tabutech.advancedone.Data.AndroidImageAssets;
import com.tabutech.advancedone.R;

public class MasterListFragment  extends Fragment {


    //creating an interface object
    OnImageClickListener mCallBack;

    //creating a listener
    public interface OnImageClickListener{
        void onImageSelected(int position);
    }

    //overriding onAttach method to make sure the container activity implemented the callback


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            mCallBack = (OnImageClickListener)context;
        }catch (ClassCastException e){
            throw  new ClassCastException(context.toString()+"Must implement callback");
        }
    }

    public MasterListFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_master_list_fragment,container,false);

        //Get a reference to the GridView in the fragment_master_list xml layout file
        GridView view  = rootView.findViewById(R.id.images_grid_view);

        //set the adapter
        //this adapter takes in the context and all the ArrayList Images
        MasterListAdapter adapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAllBodyParts());

        //set the adapter to the grid view
        view.setAdapter(adapter);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallBack.onImageSelected(position);
            }
        }
    );

        return rootView;
    }
}
