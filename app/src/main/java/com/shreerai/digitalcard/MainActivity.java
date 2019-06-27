package com.shreerai.digitalcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreerai.digitalcard.activityAdvertisement.Browse;
import com.shreerai.digitalcard.contacts.ContactsFragment;
import com.shreerai.digitalcard.DetailActivity.DetailsActivity;
import com.shreerai.digitalcard.activityFriendRequest.FriendRequestActivity;
import com.shreerai.digitalcard.profileFragment.Profile;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ViewPager viewPager_v;
    private TabLayout tabLayout_v;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference mDatabaseReference;
    TextView comapany_v;
    TextView name_v;
    TextView facebook_v;
    TextView twitter_v;
    TextView position_v;
    String current_userid_V;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        comapany_v = (TextView) headerView.findViewById(R.id.companyId);
        name_v = headerView.findViewById(R.id.name);
        position_v = headerView.findViewById(R.id.position);
        navigationView.setNavigationItemSelectedListener(this);
        init();
        current_userid_V = user.getUid();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(current_userid_V);
        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String firstname_V = dataSnapshot.child("firstname").getValue().toString();
                String lastname_V = dataSnapshot.child("lastname").getValue().toString();
                String position = dataSnapshot.child("position").getValue().toString();
                String company_V = dataSnapshot.child("company").getValue().toString();
                name_v.setText(firstname_V + " " + lastname_V);
                position_v.setText(position);
                comapany_v.setText(company_V);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Profile(), "Profile");
        adapter.addFragment(new ContactsFragment(), "Contacts");
        adapter.addFragment(new Browse(), "Browse");
        viewPager.setAdapter(adapter);
    }

    void init() {
        viewPager_v = findViewById(R.id.viewpager_Home);
        tabLayout_v = findViewById(R.id.tabs_Home);
        setUpViewPager(viewPager_v);
        tabLayout_v.setupWithViewPager(viewPager_v);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(MainActivity.this, DetailsActivity.class));

        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(MainActivity.this, FriendRequestActivity.class));


        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public static String FACEBOOK_URL = "https://www.facebook.com/profile.php?id=100020375474481";
    public static String FACEBOOK_PAGE_ID = "YourPageName";


}
