package org.yh.yhframe;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.yh.library.YHFragment;
import org.yh.yhframe.base.BaseActiciy;

public class DemoActivity extends BaseActiciy
        implements NavigationView.OnNavigationItemSelectedListener
{
    View view;
    YHFragment oneFragment = null;
    YHFragment twoFragment = null;
    YHFragment threeFragment = null;
    YHFragment fourFragment = null;
    DrawerLayout drawer = null;
    @Override
    public void setRootView()
    {
        setContentView(R.layout.activity_demo);
        view = findViewById(R.id.drawer_layout);
    }

    @Override
    public void initData()
    {
        super.initData();
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();
        fourFragment = new FourFragment();
    }

    @Override
    public void initWidget()
    {
        super.initWidget();

        changeFragment(R.id.content,oneFragment);
        FloatingActionButton fab = bindView(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = bindView(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //导航图标
        //toolbar.setNavigationIcon(R.mipmap.ic_launcher);

        NavigationView navigationView =bindView(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
    }
    //获取Back键的按下事件
    @Override
    public void onBackPressed()
    {

        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings)
//        {
//            return true;
//        }
//        invalidateOptionsMenu();
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {
            changeFragment(R.id.content,oneFragment);
            Snackbar.make(view, "nav_camera", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else if (id == R.id.nav_gallery)
        {
            changeFragment(R.id.content,twoFragment);
            Snackbar.make(view, "nav_camera", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else if (id == R.id.nav_slideshow)
        {
            changeFragment(R.id.content,threeFragment);
            Snackbar.make(view, "nav_camera", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else if (id == R.id.nav_manage)
        {
            changeFragment(R.id.content,fourFragment);
            Snackbar.make(view, "nav_manage", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else if (id == R.id.nav_share)
        {
            Snackbar.make(view, "nav_share", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else if (id == R.id.nav_send)
        {
            Snackbar.make(view, "nav_send", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onMenuClick()
    {
        super.onMenuClick();
        Snackbar.make(view, "onMenuClick", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    protected void onBackClick()
    {
        super.onBackClick();
        Snackbar.make(view, "onBackClick", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
