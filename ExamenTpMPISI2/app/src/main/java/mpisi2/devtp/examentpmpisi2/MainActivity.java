package mpisi2.devtp.examentpmpisi2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    ArrayList listLivres;
    CustomArrayAdapter adapter;
    ListView lvLivres;

    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Liste des Livres");
        lvLivres = findViewById(R.id.lvLivres);
        listLivres = new ArrayList();

        sqLiteDatabase = openOrCreateDatabase("myExamDB", SQLiteDatabase.OPEN_READWRITE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS livres ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "titre VARCHAR, auteur VARCHAR,"
                + "editeur VARCHAR, datePublication VARCHAR,"
                + "isbn VARCHAR, nbrPage VARCHAR);");


        Cursor resultSet = sqLiteDatabase.rawQuery("SELECT * FROM livres", null);

        if (resultSet.moveToFirst()) {
            //lire une ligne et verifier s’il y en a d’autres
            do {
                String id = resultSet.getString(0);
                String titre = resultSet.getString(1);
                String auteur = resultSet.getString(2);
                String editeur = resultSet.getString(3);
                String datePublication = resultSet.getString(4);
                String isbn = resultSet.getString(5);
                String nbrPage = resultSet.getString(6);

                Vector currentLivre = new Vector();
                currentLivre.add(id);
                currentLivre.add(titre);
                currentLivre.add(auteur);
                currentLivre.add(editeur);
                currentLivre.add(datePublication);
                currentLivre.add(isbn);
                currentLivre.add(nbrPage);

                listLivres.add(currentLivre);

            } while (resultSet.moveToNext());
        }
        resultSet.close();


        adapter = new CustomArrayAdapter(getApplicationContext(), getLayoutInflater(), listLivres);

        lvLivres.setAdapter(adapter);

        lvLivres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Vector livreSelected = (Vector)  listLivres.get(i);
                Intent intent = new Intent(getApplicationContext(), BookInfoActivity.class);
                intent.putExtra("id", livreSelected.get(0).toString());
                intent.putExtra("titre", livreSelected.get(1).toString());
                intent.putExtra("auteur", livreSelected.get(2).toString());
                intent.putExtra("editeur", livreSelected.get(3).toString());
                intent.putExtra("datePublication", livreSelected.get(4).toString());
                intent.putExtra("isbn", livreSelected.get(5).toString());
                intent.putExtra("nbrPage", livreSelected.get(6).toString());
                startActivity(intent);
            }

        });

        lvLivres.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Vector livreSelected = (Vector)  listLivres.get(i);
                sqLiteDatabase.delete("livres", "id=" + livreSelected.get(0), null);
                Toast.makeText(getApplicationContext(), "id=" + livreSelected.get(0), Toast.LENGTH_SHORT).show();
                listLivres.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuAddLivre:
                startActivityForResult(new Intent(MainActivity.this, AddBookActivity.class), 0);
                break;
            case R.id.menuQuitter:
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Vector livreFromAddBook = new Vector();

            livreFromAddBook.add(data.getStringExtra("id"));
            livreFromAddBook.add(data.getStringExtra("titre"));
            livreFromAddBook.add(data.getStringExtra("auteur"));
            livreFromAddBook.add(data.getStringExtra("editeur"));
            livreFromAddBook.add(data.getStringExtra("datePublication"));
            livreFromAddBook.add(data.getStringExtra("isbn"));
            livreFromAddBook.add(data.getStringExtra("nbrPage"));

            ContentValues values = new ContentValues();
            values.put("titre", livreFromAddBook.get(1).toString());
            values.put("auteur", livreFromAddBook.get(2).toString());
            values.put("editeur", livreFromAddBook.get(3).toString());
            values.put("datePublication", livreFromAddBook.get(4).toString());
            values.put("isbn", livreFromAddBook.get(5).toString());
            values.put("nbrPage", livreFromAddBook.get(6).toString());

            long id = sqLiteDatabase.insert("livres", null, values);
            if (id == -1) {
                Toast.makeText(this, "Erreur d'insertion dans la base"
                        , Toast.LENGTH_SHORT).show();
            }
            else {
                Vector vectorAjouter = new Vector();
                vectorAjouter.add("" + id);
                vectorAjouter.add(livreFromAddBook.get(1).toString());
                vectorAjouter.add(livreFromAddBook.get(2).toString());
                vectorAjouter.add(livreFromAddBook.get(3).toString());
                vectorAjouter.add(livreFromAddBook.get(4).toString());
                vectorAjouter.add(livreFromAddBook.get(5).toString());
                vectorAjouter.add(livreFromAddBook.get(6).toString());

                listLivres.add(vectorAjouter);
                adapter.notifyDataSetChanged();
            }


        }
        else if(resultCode == RESULT_CANCELED){
            Toast.makeText(getApplicationContext(), "Ajouter Cancel", Toast.LENGTH_SHORT).show();
        }
    }
}