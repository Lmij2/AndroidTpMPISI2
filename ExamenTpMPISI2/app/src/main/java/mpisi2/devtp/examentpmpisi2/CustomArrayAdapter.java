package mpisi2.devtp.examentpmpisi2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Vector;

public class CustomArrayAdapter extends ArrayAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList listLivres;
    TextView tvAuteur, tvTitre;
    public CustomArrayAdapter(Context mContext, LayoutInflater mi, ArrayList livres){
        super(mContext, R.layout.list_row, livres);
        context = mContext;
        inflater = mi;
        listLivres = livres;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentView = inflater.inflate(R.layout.list_row, parent, false);

        tvTitre = currentView.findViewById(R.id.tvTitreLv);
        tvAuteur = currentView.findViewById(R.id.tvAuteurLv);

        Vector currentLivre = (Vector) listLivres.get(position);

        tvTitre.setText(currentLivre.get(1).toString());
        tvAuteur.setText(currentLivre.get(2).toString());

        return  currentView;
    }
}
