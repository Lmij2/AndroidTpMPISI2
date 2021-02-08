package mpisi2.devtp.tp2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    ArrayList<Vector> mListEtudiant;
    ArrayAdapter mAdapter;

    public static int RESULT_SUPPRIMER = 2020;
    public static int RESULT_OK_FROM_INFO = 2019;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListEtudiant = new ArrayList<Vector>();
        Button btnAjouter = findViewById(R.id.btnAjouter);
        ListView lvEtudians = findViewById(R.id.lvEtudiants);

        Vector etudiant = new Vector();
        etudiant.add("myNameIs");
        etudiant.add("mysurNameIs");
        etudiant.add("myFormation");
        mListEtudiant.add(etudiant);
        etudiant = new Vector();
        etudiant.add("myNameIs1");
        etudiant.add("mysurNameIs1");
        etudiant.add("myFormation1");
        mListEtudiant.add(etudiant);
        etudiant = new Vector();
        etudiant.add("myNameIs2");
        etudiant.add("mysurNameIs2");
        etudiant.add("myFormation2");
        mListEtudiant.add(etudiant);

        mAdapter = new CustomArrayAdapter(this,mListEtudiant,
                getLayoutInflater());

        lvEtudians.setAdapter(mAdapter);
        lvEtudians.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Vector etudiant = (Vector) adapterView.getItemAtPosition(index);
                gotoDetailEtudiant(etudiant, index);
            }
        });
        lvEtudians.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int index, long l) {

                Vector etudiant = (Vector) adapterView.getItemAtPosition(index);

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.item_dialog);
                Button btnSupprimer = dialog.findViewById(R.id.btnSupprimer);
                Button btnDetail = dialog.findViewById(R.id.btnDetail);
                btnSupprimer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListEtudiant.remove(index);//remove the etudiant who has the index
                        mAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                btnDetail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gotoDetailEtudiant(etudiant, index);
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return true;
            }
        });
        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnOnclick(view);
            }
        });
    }

    public void gotoDetailEtudiant(Vector etudiant, int index){

        Intent mIntent = new Intent(MainActivity.this, InfoActivity.class);
        mIntent.putExtra("nom", etudiant.get(0).toString());
        mIntent.putExtra("prenom", etudiant.get(1).toString());
        mIntent.putExtra("formation", etudiant.get(2).toString());
        mIntent.putExtra("indexEtudiant", index);
        startActivityForResult(mIntent, 0);
    }
    public void btnOnclick(View v){
        Intent mIntent = new Intent(this, FormulaireActivity.class);
        startActivityForResult(mIntent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String message = data.getStringExtra("message");

            String nom = data.getStringExtra("nom");
            String prenom = data.getStringExtra("prenom");
            String formation = data.getStringExtra("formation");
            Toast.makeText(this, message + " // " + nom, Toast.LENGTH_LONG).show();
            Vector etudiant = new Vector();
            etudiant.add(nom);
            etudiant.add(prenom);
            etudiant.add(formation);
            mListEtudiant.add(etudiant);

            mAdapter.notifyDataSetChanged();

        } else if (resultCode == RESULT_CANCELED) {
            String message = data.getStringExtra("message");
            Toast.makeText(this, message + " is aclsdfq", Toast.LENGTH_SHORT).show();
        } else if (resultCode == RESULT_SUPPRIMER) {

            //get the index of that etudiant from InfoActivity if button Delete Clicked
            int indexEtudiant = data.getIntExtra("indexEtudiant", -1); //put -1 as default value

            //verifier si l'index est une valeur correcte
            if(indexEtudiant > -1)
                mListEtudiant.remove(indexEtudiant);//remove the etudiant who has the index == indexEtudiant

            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.miNouveau:
                ajouterEtudiant();
                break;
            case R.id.miSupprimer:
                supprimerEtudiant();
                break;
            case R.id.miQuitter:
                finish();
                break;
        }
        return true;

    }

    private void ajouterEtudiant(){
        Intent mIntent = new Intent(this, FormulaireActivity.class);
        startActivityForResult(mIntent, 0);
    }
    private void supprimerEtudiant(){
        mListEtudiant.clear();
        mAdapter.notifyDataSetChanged();
        /*
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog md = new DatePickerDialog(MainActivity.this);
            md.show();
        }
        TimePickerDialog md1 = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Toast.makeText(MainActivity.this, timePicker.getHour() + " // " + i + " // " + i1, Toast.LENGTH_LONG).show();
                }
            }
        }, 12, 52, true);
        md1.show();*/
    }
}