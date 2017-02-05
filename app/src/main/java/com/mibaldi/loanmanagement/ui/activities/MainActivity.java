package com.mibaldi.loanmanagement.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mibaldi.loanmanagement.R;
import com.mibaldi.loanmanagement.base.baseMosby.activity.BaseMVPActivity;
import com.mibaldi.loanmanagement.di.HasComponent;
import com.mibaldi.loanmanagement.ui.presenters.mainActivity.DaggerMainActivityComponent;
import com.mibaldi.loanmanagement.ui.presenters.mainActivity.MainActivityComponent;
import com.mibaldi.loanmanagement.ui.presenters.mainActivity.MainActivityPresenter;
import com.mibaldi.loanmanagement.ui.views.MainActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class MainActivity extends BaseMVPActivity<MainActivityPresenter, MainActivityView>
        implements MainActivityView, HasComponent<MainActivityComponent>, NavigationView.OnNavigationItemSelectedListener {

    private MainActivityComponent mainActivityComponent;


    ImageView userPicture;
    TextView userName;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initializeInjector();
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        setupNavigationDrawer();
        presenter.init(this);
    }

    private void setupNavigationDrawer() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        userPicture = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.iv_user_picture);
        userName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_username);
        btnLogout = (Button) navigationView.getHeaderView(0).findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logout(){
        presenter.logout();
    }
    @OnClick(R.id.btn_createdebtor)
    public void createDebtor(){
        presenter.createDebtor();
    }

    @NonNull
    @Override
    public MainActivityPresenter createPresenter() {
        return mainActivityComponent.presenter();
    }

    @Override
    public MainActivityComponent getComponent() {
        return mainActivityComponent;
    }

    public void initializeInjector() {
        this.mainActivityComponent = DaggerMainActivityComponent.builder().loanManagementApplicationComponent(getInjector()).build();
    }


    // View methods

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showLogout() {
        btnLogout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showUserInfo(String username, String picture) {
        userName.setText(username);
        Glide.with(this).load(picture).into(userPicture);
    }
}
