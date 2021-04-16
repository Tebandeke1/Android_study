package com.tabutech.advancedone.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.tabutech.advancedone.Data.AndroidImageAssets;
import com.tabutech.advancedone.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {

            BodyPartFragment headFragment = new BodyPartFragment();

            //this is for head body parts
            headFragment.setImageIds(AndroidImageAssets.getHeads());
            headFragment.setmListIndex(0);

            //this is for legBodyPart
            BodyPartFragment legFragment = new BodyPartFragment();

            legFragment.setImageIds(AndroidImageAssets.getLegs());
            legFragment.setmListIndex(0);

            //this is for bodyBodyParts
            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setImageIds(AndroidImageAssets.getBodies());
            bodyFragment.setmListIndex(0);
            //use fragmentManager and transaction to add fragment to the screen
            FragmentManager manager = getSupportFragmentManager();

            manager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .commit();

            manager.beginTransaction()
                    .add(R.id.body_container, bodyFragment)
                    .commit();
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