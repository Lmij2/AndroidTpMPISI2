package mpisi2.devtp.tp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Vector;

class CustomArrayAdapter extends ArrayAdapter {
    Context mContext;
    ArrayList<Vector> listEtudiant;
    LayoutInflater inflater;
    CustomArrayAdapter(Context context, ArrayList<Vector> arrayList, LayoutInflater layoutInflater){
        super(context, -1, arrayList);
        mContext = context;
        listEtudiant = arrayList;
        inflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.list_row, parent, false);

        ImageView imgView = view.findViewById(R.id.image);
        TextView tvNom = view.findViewById(R.id.tvNom);
        TextView tvFormation = view.findViewById(R.id.tvFormation);
        Vector etudiant = (Vector) listEtudiant.get(position);

        String nom= (String) etudiant.get(0);
        String prenom = (String) etudiant.get(1);
        String formation = (String) etudiant.get(2);

        tvNom.setText(nom + " " + prenom);
        tvFormation.setText(formation);
        imgView.setImageResource(R.mipmap.ic_launcher);

        return view;
    }
}
