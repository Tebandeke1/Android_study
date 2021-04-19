package com.tabutech.advancedone.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.tabutech.advancedone.Data.AndroidImageAssets;
import com.tabutech.advancedone.R;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {


    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    //this checks if a layout is in a two pane mode,a single pane is a phone screen layout while
    // a two pane mode is a tablet screen layout

    private boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //as of the two pane display, git rid ot the next button
        //determine if your creating a two pane layout or a single pane layout
        if (findViewById(R.id.android_me_linear_layout) != null){
            mTwoPane = true;

            //first set the next button visibility to GONE in a two pane layout
            Button nextButton = findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);


            //this also changes the Grid view space and columns to two

            GridView view = findViewById(R.id.images_grid_view);
            view.setNumColumns(2);

            //creating new body parts fragment
            if(savedInstanceState == null) {

                BodyPartFragment headFragment = new BodyPartFragment();

                //this is for head body parts
                headFragment.setImageIds(AndroidImageAssets.getHeads());

                // Get the correct index to access in the array of head images from the intent
                // Set the default value to 0

                int getHead = getIntent().getIntExtra("headIndex",0);
                headFragment.setListIndex(getHead);

                //this is for legBodyPart
                BodyPartFragment legFragment = new BodyPartFragment();

                legFragment.setImageIds(AndroidImageAssets.getLegs());

                int getLeg = getIntent().getIntExtra("legIndex",0);
                legFragment.setListIndex(getLeg);

                //this is for bodyBodyParts
                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setImageIds(AndroidImageAssets.getBodies());

                //here we are getting the body part
                int getBody = getIntent().getIntExtra("bodyIndex",0);

                bodyFragment.setListIndex(getBody);
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

        }else {
            // We're in single-pane mode and displaying fragments on a phone in separate activities
            mTwoPane = false;
        }
    }

    @Override
    public void onImageSelected(int position) {
        //Toast.makeText(this, "Position "+position, Toast.LENGTH_SHORT).show();


        // Based on where a user has clicked, store the selected list index for the head, body, and leg BodyPartFragments

        // bodyPartNumber will be = 0 for the head fragment, 1 for the body, and 2 for the leg fragment
        // Dividing by 12 gives us these integer values because each list of images resources has a size of 12
        int bodyPartNumber = position/12;


        // Store the correct list index no matter where in the image list has been clicked
        // This ensures that the index will always be a value between 0-11
        int listIndex = position - 12*bodyPartNumber;

        //check if a layout is in a single pane cases or two pane case

        if(mTwoPane){

            //this  part is for a two pane case that prevents a layout from creating a new activity
            BodyPartFragment newFragment = new BodyPartFragment();

            // Set the currently displayed item for the correct body part fragment
            switch (bodyPartNumber){

                case 0:
                    //here a head part has been clicked
                    //give the correct image resource to the new fragment
                    newFragment.setImageIds(AndroidImageAssets.getHeads());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container,newFragment)
                            .commit();
                    break;

                case 1:
                    //here a body part has been clicked
                    //give the correct image resource to the new fragment
                    newFragment.setImageIds(AndroidImageAssets.getHeads());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container,newFragment)
                            .commit();
                    break;

                case 2:
                    //here a leg part has been clicked
                    //give the correct image resource to the new fragment
                    newFragment.setImageIds(AndroidImageAssets.getHeads());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container,newFragment)
                            .commit();
                    break;
                default:
                    break;
            }

        }else {
            // Set the currently displayed item for the correct body part fragment
            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legIndex = listIndex;
                    break;
                default:
                    break;
            }

            // Put this information in a Bundle and attach it to an Intent that will launch an AndroidMeActivity
            Bundle b = new Bundle();
            b.putInt("headIndex", headIndex);
            b.putInt("bodyIndex", bodyIndex);
            b.putInt("legIndex", legIndex);
            b.putInt("position",listIndex);


            //attach the bundle to the  intent
            final Intent intent = new Intent(this, AndroidMeActivity.class);
            intent.putExtras(b);


            // The "Next" button launches a new AndroidMeActivity
            Button nxtButton = findViewById(R.id.next_button);
            nxtButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(intent);
                }
            });

        }

    }
}