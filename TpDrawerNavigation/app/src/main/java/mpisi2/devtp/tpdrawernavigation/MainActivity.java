package mpisi2.devtp.tpdrawernavigation;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        item.setChecked(true);
                        int id = item.getItemId();
                        for(int i = 0; i < navigationView.getMenu().size(); i++){
                            if(navigationView.getMenu().getItem(i).getItemId() != id){
                                navigationView.getMenu().getItem(i).setChecked(false);
                            }
                        }
                        if (id == R.id.nav_camera) {
                            // Handle the camera action
                            Toast.makeText(getApplicationContext(), "camera clicked", Toast.LENGTH_SHORT).show();
                        } else if (id == R.id.nav_gallery) {
                        } else if (id == R.id.nav_slideshow) {
                        } else if (id == R.id.nav_manage) {
                        } else if (id == R.id.nav_phone) {
                        }
                        DrawerLayout drawer = findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                        //true to display the item as selected
                        return false;
                    }
                });
    }
}