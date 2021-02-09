package mpisi2.devtp.revisionexamen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter {

    ArrayList listValues;
    LayoutInflater inflater;
    ImageView imageView;
    TextView from, message;
    public MyAdapter(@NonNull Context context, LayoutInflater mInflater, ArrayList myList) {
        super(context,-1, myList);
        listValues = myList;
        inflater = mInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentView = null;
        currentView = inflater.inflate(R.layout.message_model, parent, false);

        imageView = currentView.findViewById(R.id.imgMessage);
        from = currentView.findViewById(R.id.tvFrom);
        message = currentView.findViewById(R.id.tvMessage);

        imageView.setImageResource(R.mipmap.ic_launcher);
        from.setText(listValues.get(position).toString().split(" ")[0]);
        message.setText(listValues.get(position).toString().split(" ")[1]);

        return  currentView;
    }
}
