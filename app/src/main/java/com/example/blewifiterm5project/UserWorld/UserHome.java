package com.example.blewifiterm5project.UserWorld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.example.blewifiterm5project.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class UserHome extends AppCompatActivity {

    private static final String TAG = "UserHome";
    private static ChipNavigationBar menu_bottom;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: userhome");
        setContentView(R.layout.activity_user_home);

        //Instantiate widgets
        menu_bottom = findViewById(R.id.userhome_navigation);

        menu_bottom.setItemSelected(0, true);

        if (savedInstanceState == null){
            menu_bottom.setItemSelected(R.id.checkin, true);
            fragmentManager = getSupportFragmentManager();
            CheckInCheckOutFragment checkInCheckOutFragment = new CheckInCheckOutFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.userhome_fragment_container, checkInCheckOutFragment)
                    .commit();
        }
        menu_bottom.showBadge(R.id.checkin);

        //Changing of fragments when using bottom nav bar
        menu_bottom.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch(id) {
                    case R.id.checkin:
                        fragment = new CheckInCheckOutFragment();
                        break;
                    case R.id.navigation:
                        fragment = new NavigationFragment();
                        break;
                    case R.id.profile:
                        fragment = new ProfileFragment();
                        break;
                }

                if (fragment != null){
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.userhome_fragment_container, fragment)
                            .commit();
                }
                else {
                    Log.e(TAG, "Error in creating fragment");
                }
            }
        });
    }


}