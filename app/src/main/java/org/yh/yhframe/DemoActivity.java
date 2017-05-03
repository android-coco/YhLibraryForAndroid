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

public class DemoActivity extends BaseActiciy
        implements NavigationView.OnNavigationItemSelectedListener
{
    View view;
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
    }

    @Override
    public void initWidget()
    {
        super.initWidget();

        changeFragment(R.id.content,new OneFragment());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //导航图标
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
    }
    //获取Back键的按下事件
    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
            changeFragment(R.id.content,new OneFragment());
            Snackbar.make(view, "nav_camera", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else if (id == R.id.nav_gallery)
        {
            changeFragment(R.id.content,new TwoFragment());
            Snackbar.make(view, "nav_camera", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else if (id == R.id.nav_slideshow)
        {
            changeFragment(R.id.content,new ThreeFragment());
            Snackbar.make(view, "nav_camera", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else if (id == R.id.nav_manage)
        {
            changeFragment(R.id.content,new FourFragment());
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
