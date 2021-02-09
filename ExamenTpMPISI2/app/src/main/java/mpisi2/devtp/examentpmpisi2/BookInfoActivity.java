package mpisi2.devtp.examentpmpisi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Vector;

public class BookInfoActivity extends AppCompatActivity {

    TextView tvId, tvTitre, tvAuteur, tvEditeur, tvDatePub, tvIsbn, tvNbrPage;
    Button btnOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        setTitle("Info Livre");

        Intent data = getIntent();

        Vector livreSelected = new Vector();

        livreSelected.add(data.getStringExtra("id"));
        livreSelected.add(data.getStringExtra("titre"));
        livreSelected.add(data.getStringExtra("auteur"));
        livreSelected.add(data.getStringExtra("editeur"));
        livreSelected.add(data.getStringExtra("datePublication"));
        livreSelected.add(data.getStringExtra("isbn"));
        livreSelected.add(data.getStringExtra("nbrPage"));

        tvId = findViewById(R.id.tvID);
        tvTitre = findViewById(R.id.tvTitre);
        tvAuteur = findViewById(R.id.tvAuteur);
        tvEditeur = findViewById(R.id.tvEditeur);
        tvDatePub = findViewById(R.id.tvDatePub);
        tvIsbn = findViewById(R.id.tvIsbn);
        tvNbrPage = findViewById(R.id.tvNbrPage);
        btnOk = findViewById(R.id.btnOk);

        tvId.setText("Id : " + livreSelected.get(0).toString());
        tvTitre.setText("Titre : " + livreSelected.get(1).toString());
        tvAuteur.setText("Auteur : " + livreSelected.get(2).toString());
        tvEditeur.setText("Editeur : " + livreSelected.get(3).toString());
        tvDatePub.setText("Date De Publication : " + livreSelected.get(4).toString());
        tvIsbn.setText("ISBN : " + livreSelected.get(5).toString());
        tvNbrPage.setText("Nombre de Pages : " + livreSelected.get(6).toString());

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}