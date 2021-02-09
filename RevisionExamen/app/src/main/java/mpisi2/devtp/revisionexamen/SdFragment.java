package mpisi2.devtp.revisionexamen;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SdFragment extends Fragment {

    private Context mContext;
    private LayoutInflater mi;
    Button btnSend;
    EditText etNom;
    ListView lvMessages;

    public SdFragment(){};

    public SdFragment(Context context, LayoutInflater layoutInflater){
        mContext = context;
        mi = layoutInflater;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.layout_st_fragment, container, false);

        btnSend = mView.findViewById(R.id.btnSend);
        etNom = mView.findViewById(R.id.etNom);
        lvMessages = mView.findViewById(R.id.lvMessages);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageInBackgroundServer(etNom.getText().toString().trim());
            }
        });
        return mView;
    }
    
    public void sendMessageInBackgroundServer(String valeur){
        
    }
}
