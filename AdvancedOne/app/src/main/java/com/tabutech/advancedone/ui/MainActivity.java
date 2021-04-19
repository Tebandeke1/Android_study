package com.tabutech.advancedone.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tabutech.advancedone.R;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {


    private int headIndex;
    private int bodyIndex;
    private int legIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "Position "+position, Toast.LENGTH_SHORT).show();


        // Based on where a user has clicked, store the selected list index for the head, body, and leg BodyPartFragments

        // bodyPartNumber will be = 0 for the head fragment, 1 for the body, and 2 for the leg fragment
        // Dividing by 12 gives us these integer values because each list of images resources has a size of 12
        int bodyPartNumber = position/12;


        // Store the correct list index no matter where in the image list has been clicked
        // This ensures that the index will always be a value between 0-11
        int listIndex = position - 12*bodyPartNumber;

        // Set the currently displayed item for the correct body part fragment
        switch (bodyPartNumber){
            case 1:headIndex = listIndex;
            break;
            case 2:bodyIndex = listIndex;
            break;
            case 3:legIndex = listIndex;
            break;
            default: break;
        }

        // Put this information in a Bundle and attach it to an Intent that will launch an AndroidMeActivity
        Bundle b = new Bundle();
        b.putInt("headIndex",headIndex);
        b.putInt("bodyIndex",bodyIndex);
        b.putInt("legIndex",legIndex);


        //attach the bundle to the  intent
        final Intent intent = new Intent(this,AndroidMeActivity.class);
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