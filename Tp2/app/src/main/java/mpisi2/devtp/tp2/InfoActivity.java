package mpisi2.devtp.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    TextView nom, prenom, formation;
    int indexEtudiant = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        Button btnOk = findViewById(R.id.btnOk);
        Button btnSupprimer = findViewById(R.id.btnSupprimer);

        nom = findViewById(R.id.tvNom);
        prenom = findViewById(R.id.tvPrenom);
        formation = findViewById(R.id.tvFormation);

        Intent data = getIntent();

        String nomStr = data.getStringExtra("nom");
        String prenomStr = data.getStringExtra("prenom");
        String formationStr = data.getStringExtra("formation");
        indexEtudiant = data.getIntExtra("indexEtudiant", -1);

        nom.setText(nomStr);
        prenom.setText(prenomStr);
        formation.setText(formationStr);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnOnclick(view, 1);
            }
        });

        btnSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnOnclick(view, 2);
            }
        });
    }

    private void btnOnclick(View view, int choice) {

        Intent intent = new Intent();
        if(choice == 1)
            setResult(MainActivity.RESULT_OK_FROM_INFO, intent);
        if(choice == 2){
            intent.putExtra("indexEtudiant", indexEtudiant);
            setResult(MainActivity.RESULT_SUPPRIMER, intent);
        }
        finish();
    }

}