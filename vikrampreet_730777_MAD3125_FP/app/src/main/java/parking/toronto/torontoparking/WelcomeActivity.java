package parking.toronto.torontoparking;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import parking.toronto.torontoparking.db.DBHelper;
import parking.toronto.torontoparking.fragments.AddTicketFragment;
import parking.toronto.torontoparking.fragments.HomeFragment;
import parking.toronto.torontoparking.fragments.InstructionViewFragment;
import parking.toronto.torontoparking.fragments.MapsActivity;
import parking.toronto.torontoparking.fragments.TicketReportFragment;

public class WelcomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView textViewUserEmail;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        UserManager.getInstance().mydbManager = new DBHelper(this);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        textViewUserEmail = header.findViewById(R.id.textViewUserEmail);

        if (UserManager.getInstance().userEmail != null)
            textViewUserEmail.setText(UserManager.getInstance().userEmail);

        onNavigationItemSelected(navigationView.getMenu().getItem(0));
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        Fragment fragment = null;
        String title = null;

        switch (id) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                title = getString(R.string.toronto_parking_system);
                break;

            case R.id.nav_add_ticket:
                fragment = new AddTicketFragment();
                title = getString(R.string.nav__title_add_ticket);
                break;

            case R.id.nav_loc:
                startActivity(new Intent(this, MapsActivity.class));
                break;

            case R.id.nav_inst:
                title = "Parking Instruction";
                fragment = new InstructionViewFragment();
                break;

            case R.id.nav_contact:
                contact();
                break;

            case R.id.nav_report:
                fragment = new TicketReportFragment();
                title = getString(R.string.nav__title_report);
                break;

            case R.id.nav_logout:
                closeAndOpenLogin();
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.containerView, fragment)
                    .commit();
        }
        if (title != null)
            setTitle(title);
        return true;
    }

    private void contact() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Need Help?")
                .setMessage("+1 123 456 7890 9:00 a.m - 8:00 p.m Email:admin@gmail.com ")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    private void closeAndOpenLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void openMainScreen() {
        if (navigationView != null)
            onNavigationItemSelected(navigationView.getMenu().getItem(0));

    }
}
