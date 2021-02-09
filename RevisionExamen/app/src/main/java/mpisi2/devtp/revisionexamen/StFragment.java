package mpisi2.devtp.revisionexamen;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class StFragment extends Fragment {

    private Context mContext;
    private LayoutInflater mi;
    Button btnSend;
    EditText etNom;
    ListView lvMessages;

    IOnFragmentEventListener myParent;
    ArrayList allMessages;
    MyAdapter myAdapter;

    public StFragment(){};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.layout_st_fragment, container, false);

        btnSend = mView.findViewById(R.id.btnSend);
        etNom = mView.findViewById(R.id.etNom);
        lvMessages = mView.findViewById(R.id.lvMessages);
        myParent = (IOnFragmentEventListener) getActivity();
        allMessages = new ArrayList();

        allMessages.add("st helloWorld");
        allMessages.add("st how are you");

        myAdapter = new MyAdapter(getContext(), getLayoutInflater(), allMessages);


        lvMessages.setAdapter(myAdapter);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageInBackgroundServer(etNom.getText().toString().trim());
                myParent.messageFromFragment("First Fragement");
            }
        });

        lvMessages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("StFramgent");

                builder.setItems(new String[]{"Btn"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), "btn Dialog", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

        return mView;
    }

    public void sendMessageInBackgroundServer(String valeur){

    }
}
