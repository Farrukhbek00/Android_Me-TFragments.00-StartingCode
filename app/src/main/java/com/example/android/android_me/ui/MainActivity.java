package com.example.android.android_me.ui;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.android_me_linear_layout) != null) {

            mTwoPane = true;

            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            GridView gridView = (GridView) findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            if (savedInstanceState == null) {

                FragmentManager fragmentManager = getFragmentManager();

                // Todo5 (5) Crete new BodyPartFragment  instance and display it using the FragmentManager
                BodyPartFragment headFragment = new BodyPartFragment();
                headFragment.setmImageIds(AndroidImageAssets.getHeads());
                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .commit();


                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setmImageIds(AndroidImageAssets.getBodies());
                fragmentManager.beginTransaction()
                        .add(R.id.body_container, bodyFragment)
                        .commit();

                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setmImageIds(AndroidImageAssets.getLegs());
                fragmentManager.beginTransaction()
                        .add(R.id.leg_container, legFragment)
                        .commit();
            }
        } else {
            mTwoPane = false;
        }
    }

    public void onImageSelected(int position) {

        // Toast.makeText(this, "Position clicked " + position, Toast.LENGTH_LONG).show();

        int bodyPartNumber = position / 12;

        int listIndex = position - 12 * bodyPartNumber;

        if (mTwoPane) {

            BodyPartFragment newFragment = new BodyPartFragment();

            switch (bodyPartNumber) {

//                case 0:
//                    // A head image has been clicked
//                    // Give the correct image resources to the new fragment
//                    newFragment.setImageIds(AndroidImageAssets.getHeads());
//                    newFragment.setListIndex(listIndex);
//                    // Replace the old head fragment with a new one
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.head_container, newFragment)
//                            .commit();
//                    break;
//                case 1:
//                    newFragment.setImageIds(AndroidImageAssets.getBodies());
//                    newFragment.setListIndex(listIndex);
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.body_container, newFragment)
//                            .commit();
//                    break;
//                case 2:
//                    newFragment.setImageIds(AndroidImageAssets.getLegs());
//                    newFragment.setListIndex(listIndex);
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.leg_container, newFragment)
//                            .commit();
//                    break;
//                default:
//                    break;
            }

        } else {

            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legIndex = listIndex;
                default:
                    break;
            }


            Bundle b = new Bundle();
            b.putInt("headIndex", headIndex);
            b.putInt("bodyIndex", bodyIndex);
            b.putInt("legIndex", legIndex);

            final Intent intent = new Intent(this, AndroidMeActivity.class);
            intent.putExtras(b);

            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
        }
    }
}
