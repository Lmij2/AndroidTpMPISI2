package mpisi2.devtp.tp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class InfoActivity extends AppCompatActivity implements InfoFragment.IOnEtudiantEventListener {

    ArrayList<Vector> listEtudiant;
    LinearLayout llInfoContainer;
    FragmentTransaction ft;
    InfoFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        listEtudiant = new ArrayList<>();
        Vector<String> vector = new Vector<>();
        vector.add("nomEt1");
        vector.add("prenomEt1");
        vector.add("formationEt1");
        listEtudiant.add(vector);
        vector = new Vector<>();
        vector.add("nomEt2");
        vector.add("prenomEt2");
        vector.add("formationEt2");
        listEtudiant.add(vector);

        fragment = new InfoFragment();

        Vector etudiant = new Vector();
        Bundle params = new Bundle();
        params = new Bundle();
        etudiant = listEtudiant.get(0);
        params.putString("nom", etudiant.get(0).toString());
        params.putString("prenom", etudiant.get(1).toString());
        params.putString("formation", etudiant.get(2).toString());
        params.putInt("indexEtudiant", 0);
        fragment.setArguments(params);

        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.llInfoContainer, fragment);
        ft.commit();
        //initFragment();

    }

    public void initFragment(){

        Toast.makeText(this, "Index : // " + listEtudiant.size(), Toast.LENGTH_LONG).show();
        if(listEtudiant.size() > 0){
            Vector etudiant = listEtudiant.get(0);
            fragment.afficherEtudiant(etudiant.get(0).toString(), etudiant.get(1).toString(), etudiant.get(2).toString(), 0);
        }
        else {
            fragment.afficherEtudiant("aucun etudiant","aucun etudiant", "aucun etudiant", -1);
        }

    }
    @Override
    public void onSupprimerClicked(int index) {

        Toast.makeText(this, "Index del : " + index + " // " + listEtudiant.size(), Toast.LENGTH_LONG).show();
        if(listEtudiant.size() > 0)
            listEtudiant.remove(index);
        initFragment();
    }

    @Override
    public void onSuivantClicked(int index) {
        Toast.makeText(this, "Index suiv : " + index + " // " + listEtudiant.size(), Toast.LENGTH_LONG).show();
        if(listEtudiant.size() > index) {
            if(index == listEtudiant.size() - 1) {
                index = 0;
            }
            else {
                index++;
            }
            Vector etudiant = listEtudiant.get(index);
            fragment.afficherEtudiant(etudiant.get(0).toString(), etudiant.get(1).toString(), etudiant.get(2).toString(), index);
        }
        else
            initFragment();

    }
}