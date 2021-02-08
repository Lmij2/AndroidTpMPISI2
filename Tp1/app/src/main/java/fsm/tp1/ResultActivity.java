package fsm.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("ResultActivity");
        setContentView(R.layout.activity_result);

        TextView message = findViewById(R.id.tvMessage);
        Button btnRetour = findViewById(R.id.btnRetour);
        Button btnQuitter = findViewById(R.id.btnQuitter);

        Intent intentParent = getIntent();
        String nom = intentParent.getStringExtra("nom");
        String prenom = intentParent.getStringExtra("prenom");
        message.setText("nom et prenom : " + nom + " " + prenom);

        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
    }
}