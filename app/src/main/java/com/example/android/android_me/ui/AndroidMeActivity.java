/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    // Todo1 (1) Create layout file that displays one body part image named fragment_body_part.xml
          // This layout should contain a single ImageView

    // Todo2 (2) Create a new class called BodyPartFragment to display an image of an Android-Me body part
          // In this class, you'll need to implement an empty constructor and the onCreateView method

    // Todo3 (3) Show the first image in the list of the head images
          // Soon, you will update this image display code to show any image you want

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        if(savedInstanceState == null) {

            // Todo5 (5) Crete new BodyPartFragment  instance and display it using the FragmentManager
            BodyPartFragment headFragment = new BodyPartFragment();

            headFragment.setmImageIds(AndroidImageAssets.getHeads());
            headFragment.setmListIndex(2);

            // Use a FragmentManager and transaction to add the fragment to the screen
            FragmentManager fragmentManager = getFragmentManager();

            // Fragment transaction
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
    }
}
