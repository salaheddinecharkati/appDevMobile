package com.example.myapplication;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextMotDePasse;
    private Button buttonConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editTextText);
        editTextMotDePasse = findViewById(R.id.editTextText2);
        buttonConnexion = findViewById(R.id.button);

        buttonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifierConnexion();
            }
        });
    }
    public void inscrire(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    private void verifierConnexion() {
        String email = editTextEmail.getText().toString().trim();
        String motDePasse = editTextMotDePasse.getText().toString().trim();

        // Vérifier si les champs sont vides
        if (email.isEmpty() || motDePasse.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        // Vérifier les données dans la base de données
        Inscription dbHelper = new Inscription(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {Inscription.COLUMN_EMAIL, Inscription.COLUMN_MOT_DE_PASSE};
        String selection = Inscription.COLUMN_EMAIL + "=? AND " + Inscription.COLUMN_MOT_DE_PASSE + "=?";
        String[] selectionArgs = {email, motDePasse};
        Cursor cursor = db.query(Inscription.TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            // Connexion réussie, ouvrir l'activité MainActivity3
            Intent intent = new Intent(MainActivity.this, MainActivity3.class);
            startActivity(intent);
        } else {
            // Email ou mot de passe incorrect
            Toast.makeText(this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();
    }

}
