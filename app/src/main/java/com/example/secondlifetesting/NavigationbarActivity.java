package com.example.secondlifetesting;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class NavigationbarActivity extends AppCompatActivity {
    //Initialize variable
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationbar);

    //assign variable
    bottomNavigation = findViewById(R.id.bottom_navigation);


    bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));

    bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_search));
    bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_account));


    bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
        @Override
        public void onShowItem(MeowBottomNavigation.Model item) {
            //initialize fragments
            Fragment fragment = null;
            switch (item.getId()){
                case 1:
                    fragment = new HomeFragment();
                    break;
                case 2:
                    fragment = new SearchFragment();
                    break;
                case 3:
                    fragment = new MyAccountFragment();
                    break;

            }

            loadFragment(fragment);
        }
    });

bottomNavigation.show(1,true);

bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
    @Override
    public void onClickItem(MeowBottomNavigation.Model item) {

    }
});

bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
    @Override
    public void onReselectItem(MeowBottomNavigation.Model item) {

    }
});
    }

    private void loadFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }
}