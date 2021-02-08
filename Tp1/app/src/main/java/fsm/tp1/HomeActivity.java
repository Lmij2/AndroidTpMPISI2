package fsm.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    EditText nom, prenom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("HomeActivity");
        setContentView(R.layout.activity_home);

        Button btnSuivant = findViewById(R.id.btnSuivant);
        Button btnAnnuler = findViewById(R.id.btnAnnuler);
        nom = findViewById(R.id.edtNom);
        prenom = findViewById(R.id.edtPrenom);

        //Toast.makeText(this, "ttt", Toast.LENGTH_LONG).show();

        btnSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnOnclick(view);
            }
        });

        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void btnOnclick(View v){
        Intent mIntent = new Intent(this, ResultActivity.class);
        mIntent.putExtra("nom", nom.getText().toString());
        mIntent.putExtra("prenom", prenom.getText().toString());
        startActivity(mIntent);
    }
}