package mpisi2.devtp.examentpmpisi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddBookActivity extends AppCompatActivity {

    EditText etTitre, etAuteur, etEditeur, etDatePub, etIsbn, etNbrPage;
    Button btnAjouter, btnAnnuler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        setTitle("Ajouter Livre");

        etTitre = findViewById(R.id.etTitre);
        etAuteur = findViewById(R.id.etAuteur);
        etEditeur = findViewById(R.id.etEditeur);
        etDatePub = findViewById(R.id.etDatePub);
        etIsbn = findViewById(R.id.etIsbn);
        etNbrPage = findViewById(R.id.etNbrPage);

        btnAjouter = findViewById(R.id.btnAjouter);
        btnAnnuler = findViewById(R.id.btnAnnuler);

        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                intent.putExtra("id", "0");
                intent.putExtra("titre", etTitre.getText().toString());
                intent.putExtra("auteur", etAuteur.getText().toString());
                intent.putExtra("editeur", etEditeur.getText().toString());
                intent.putExtra("datePublication", etDatePub.getText().toString());
                intent.putExtra("isbn", etIsbn.getText().toString());
                intent.putExtra("nbrPage", etNbrPage.getText().toString());

                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent();
                setResult(RESULT_CANCELED, mIntent);
                finish();
            }
        });

    }
}