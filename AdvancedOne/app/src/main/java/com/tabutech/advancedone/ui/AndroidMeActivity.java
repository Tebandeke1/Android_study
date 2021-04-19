package com.tabutech.advancedone.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.tabutech.advancedone.Data.AndroidImageAssets;
import com.tabutech.advancedone.R;

public class AndroidMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        if(savedInstanceState == null) {


            //this was for testing
            //Toast.makeText(this, "Position "+getIntent().getIntExtra("position",0), Toast.LENGTH_SHORT).show();

            BodyPartFragment headFragment = new BodyPartFragment();

            //this is for head body parts
            headFragment.setImageIds(AndroidImageAssets.getHeads());

            // Get the correct index to access in the array of head images from the intent
            // Set the default value to 0
            
            int getHead = getIntent().getIntExtra("headIndex",0);
            headFragment.setListIndex(getHead);


            //use fragmentManager and transaction to add fragment to the screen
            FragmentManager manager = getSupportFragmentManager();

            manager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .commit();
            //////////////////////////////////////////////////////////////////////////

            //this is for bodyBodyParts
            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setImageIds(AndroidImageAssets.getBodies());

            //here we are getting the body part
            int getBody = getIntent().getIntExtra("bodyIndex",0);
            bodyFragment.setListIndex(getBody);

            manager.beginTransaction()
                    .add(R.id.body_container, bodyFragment)
                    .commit();

            //////////////////////////////////////////////////////////////////////////

            //this is for legBodyPart
            BodyPartFragment legFragment = new BodyPartFragment();

            legFragment.setImageIds(AndroidImageAssets.getLegs());

            int getLeg = getIntent().getIntExtra("legIndex",0);
            legFragment.setListIndex(getLeg);

            manager.beginTransaction()
                    .add(R.id.leg_container, legFragment)
                    .commit();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}