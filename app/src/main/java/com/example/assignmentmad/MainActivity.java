package com.example.assignmentmad;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import com.example.assignmentmad.ui.gallery.GalleryFragment;
import com.example.assignmentmad.ui.home.HomeFragment;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity{

    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawer;
    NavController navController;
    TextView tv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fb_share);
        fab.setLabelTextColor(getResources().getColor(R.color.white));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "New Complaint Clicked", Snackbar.LENGTH_LONG)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        }).show();

            }
        });
        FloatingActionButton fab2 = findViewById(R.id.corona_tiger);
        fab2.setLabelTextColor(getResources().getColor(R.color.white));
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Become a Corona Relief Tiger Clicked", Snackbar.LENGTH_LONG)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        }).show();

            }
        });
        drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        //Inflate the header
        View header = navigationView.inflateHeaderView(R.layout.nav_header_main);
        ImageView imageCancel = header.findViewById(R.id.cancelbutton);
        imageCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawers();
            }
        });
        navigationView.setItemIconTintList(null);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send,R.id.send,R.id.share,R.id.nav_update)
                .setDrawerLayout(drawer)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            drawer.closeDrawers();

                if(item.getItemId() == R.id.share)
                {

                    Intent shareintent = new Intent();
                    shareintent.setAction(Intent.ACTION_SEND);
                    shareintent.putExtra(Intent.EXTRA_TEXT,"https://drive.google.com/file/d/1qgGRKK8_e5JjkoVVlOWp8JjvWazThHuf/view?usp=sharing");
                    shareintent.setType("text/plain");
                    startActivity(Intent.createChooser(shareintent,"share via"));
                }
                else if(item.getItemId() == R.id.send){
                    Intent i = getOpenGmailIntent("muhammadmuneebkhan810@gmail.com", "Hello Sir ! \n");
                    startActivity(i);
                }
                else if(item.getItemId() == R.id.nav_gallery){
                    navController.popBackStack(R.id.mobile_navigation, true);
                    Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment).navigate(R.id.nav_gallery);

                }
                else if(item.getItemId() == R.id.nav_home){
                    navController.popBackStack(R.id.mobile_navigation, true);
                    Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment).navigate(R.id.nav_home);

                }
                else if(item.getItemId() == R.id.team){
                    navController.popBackStack(R.id.mobile_navigation, true);
                    Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment).navigate(R.id.nav_send);

                }
                else if(item.getItemId() == R.id.Contact_us){
                    navController.popBackStack(R.id.mobile_navigation, true);
                    Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment).navigate(R.id.nav_share);

                }
                else if(item.getItemId() == R.id.nav_slideshow){
                    navController.popBackStack(R.id.mobile_navigation, true);
                    Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment).navigate(R.id.nav_slideshow);

                }
                else if(item.getItemId() == R.id.nav_tools){
                    navController.popBackStack(R.id.mobile_navigation, true);
                    Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment).navigate(R.id.nav_tools);

                }
                else if(item.getItemId() == R.id.update){
                    navController.popBackStack(R.id.mobile_navigation, true);
                    Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment).navigate(R.id.nav_update);

                }
                return false;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
         navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public static Intent getOpenGmailIntent(String mail, String subject) {

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", mail, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        return Intent.createChooser(emailIntent, null);
    }

    @Override
    public void onBackPressed() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MainActivity.this,SweetAlertDialog.ERROR_TYPE);
        sweetAlertDialog.setTitleText("Are you Want to Exit ?");
        sweetAlertDialog.setConfirmText("Yes");
        sweetAlertDialog.setCancelText("No");
        sweetAlertDialog.showCancelButton(true);
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
               System.exit(0);
            }
        }).show();

    }
}
