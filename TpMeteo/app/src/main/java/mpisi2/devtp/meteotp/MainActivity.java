package mpisi2.devtp.meteotp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button btnOk;
    TextView tvTitleMeteo,tvVille, tvTemperature, tvTemperatureMax, tvTemperatureMin, tvHumidite, tvVitesseVent, tvCompteur;
    EditText edtNomVille;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCompteur = findViewById(R.id.tvCompteur);
        tvTitleMeteo = findViewById(R.id.tvTitleMeteo);
        tvVille = findViewById(R.id.tvVille);
        tvTemperature = findViewById(R.id.tvTemperature);
        tvTemperatureMax = findViewById(R.id.tvTemperatureMax);
        tvTemperatureMin = findViewById(R.id.tvTemperatureMin);
        tvHumidite = findViewById(R.id.tvHumidite);
        tvVitesseVent = findViewById(R.id.tvVitesseVent);
        edtNomVille = findViewById(R.id.edtNomVille);

        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Thread thread = new Thread() {
                        public void run() {
                            methodeLongue();
                        }
                    };
                    thread.start();
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(), "Internet Error3 " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void methodeLongue() {
        getMeteo(edtNomVille.getText().toString());
    }

    private void getMeteo(String ville){
        try {
            String strUrl = "http://api.openweathermap.org/data/2.5/weather?q=" +
                    ville + "&units=metric&APPID=de73ad8666ad7164cb899ed30c7031f6";
            URL url = new URL(strUrl);
            Log.d("mainFrame", "Internet Connected22222 " + url.toString());
            InputStream is =  url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String text = "";
            String line;
            while ((line = br.readLine()) != null) {
                text += line;
            }
            is.close();
            String finalText = text;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //Response
                        JSONObject json = new JSONObject(finalText);
                        tvVille.setText("Ville : " + json.getString("name"));
                        JSONObject mainMeteo = json.getJSONObject("main");
                        tvTemperature.setText("Température C : " + mainMeteo.getString("temp"));
                        tvTemperatureMin.setText("Température C (min) : " + mainMeteo.getString("temp_min"));
                        tvTemperatureMax.setText("Température C (max) : " + mainMeteo.getString("temp_max"));
                        tvHumidite.setText("Humidité : " + mainMeteo.getString("humidity"));
                        JSONObject windMeteo = json.getJSONObject("wind");
                        tvVitesseVent.setText("Vitesse du vent (m/s) : " + windMeteo.getString("speed"));
                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Internet Connected1111111 " + ville, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            Log.d("error", e.toString());
            e.printStackTrace();
        }
    }

}