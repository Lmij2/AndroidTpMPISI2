package mpisi2.devtp.tp3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class InfoFragment extends Fragment {

    TextView tvNom, tvPrenom,tvFormation;
    Button btnSuivant, btnSupprimer;
    IOnEtudiantEventListener myListener;
    int indexCurrentEtudiant = 0;
    String nomEt, prenomEt, formationEt;

    static interface IOnEtudiantEventListener{
        public void onSupprimerClicked(int index);
        public void onSuivantClicked(int index);
    }

    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        tvNom = view.findViewById(R.id.tvNom);
        tvPrenom = view.findViewById(R.id.tvPrenom);
        tvFormation = view.findViewById(R.id.tvFormation);
        btnSuivant = view.findViewById(R.id.btnSuivant);
        btnSupprimer = view.findViewById(R.id.btnSupprimer);

        myListener = (IOnEtudiantEventListener)getActivity();

        Bundle data = getArguments();
        indexCurrentEtudiant = data.getInt("indexEtudiant", -1);
        nomEt = data.getString("nom");
        prenomEt = data.getString("prenom");
        formationEt = data.getString("formation");

        tvNom.setText("Nom: " + nomEt);
        tvPrenom.setText("Prenom: " + prenomEt);
        tvFormation.setText("Formation: " + formationEt);

        btnSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), "Index Info : " + indexCurrentEtudiant, Toast.LENGTH_LONG).show();
                myListener.onSupprimerClicked(indexCurrentEtudiant);
            }
        });
        btnSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), "Index Info : " + indexCurrentEtudiant, Toast.LENGTH_LONG).show();
                myListener.onSuivantClicked(indexCurrentEtudiant);
            }
        });
        return view;
    }

    public void afficherEtudiant(String nom, String prenom, String formation, int index){

        indexCurrentEtudiant = index;
        tvNom.setText("Nom: " + nom);
        tvPrenom.setText("Prenom: " + prenom);
        tvFormation.setText("Formation: " + formation);
    }

}