package mpisi2.devtp.revisionexamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements IOnFragmentEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        StFragment ff = new StFragment();
        SdFragment sf = new SdFragment();

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.StFragment, ff).commit();
        fm.beginTransaction().add(R.id.SdFragment, sf).commit();

    }

    @Override
    public void messageFromFragment(String nomF) {
        setTitle(nomF);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_interface, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.miNouveau:
                Toast.makeText(this, "item Nouveau", Toast.LENGTH_SHORT).show();
                break;
            case R.id.miSupprimer:
                Toast.makeText(this, "item Supprimer", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}