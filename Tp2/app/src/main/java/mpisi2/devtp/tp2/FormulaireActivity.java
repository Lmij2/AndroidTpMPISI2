package mpisi2.devtp.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FormulaireActivity extends AppCompatActivity {

    EditText nom, prenom, formation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);


        Button btnOk = findViewById(R.id.btnOk);
        Button btnAnnuler = findViewById(R.id.btnAnnuler);

        nom = findViewById(R.id.etNom);
        prenom = findViewById(R.id.etPrenom);
        formation = findViewById(R.id.etFormation);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnOnclick(view, 1);
            }
        });

        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnOnclick(view, 2);
            }
        });

    }

    public void btnOnclick(View v, int message){

        Intent intent = new Intent();

        String nomStr = nom.getText().toString();
        String prenomStr = prenom.getText().toString();
        String formationStr = formation.getText().toString();

        if(message == 1){
            intent.putExtra("message",nomStr + " " + prenomStr + ", formation : " + formationStr );
            intent.putExtra("nom", nomStr);
            intent.putExtra("prenom", prenomStr);
            intent.putExtra("formation", formationStr);
            setResult(RESULT_OK, intent);
        }
        else {
            intent.putExtra("message", "Annuler");
            setResult(RESULT_CANCELED, intent);
        }
        finish();
    }
}