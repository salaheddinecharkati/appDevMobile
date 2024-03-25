package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    Inscription dbHelper;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        dbHelper = new Inscription(this);
        Spinner spinner = findViewById(R.id.sp);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity2.this, "Selected item"+item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Casablanca");
        arrayList.add("Marrakech");
        arrayList.add("Rabat");
        arrayList.add("Tanger");
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,arrayList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);}
    public void retour(View view) {
        // Créer une intention pour lancer l'activité d'inscription (MainActivity2)
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent); // Lancer l'activité d'inscription
    }

    public void cree_compte(View view){
        EditText editTextEmail = findViewById(R.id.ET1);

        EditText editTextMotpasse = findViewById(R.id.ET3);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        String email = editTextEmail.getText().toString();

        String mot_de_passe = editTextMotpasse.getText().toString();
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
        Spinner spinnerVille = findViewById(R.id.sp);
        String ville = spinnerVille.getSelectedItem().toString();

        String userType = selectedRadioButton.getText().toString();
        if (email.isEmpty() || ville.isEmpty() || mot_de_passe.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        // Vérifier que le mot de passe contient au moins 5 caractères
        if (mot_de_passe.length() < 5) {
            Toast.makeText(this, "Le mot de passe doit contenir au moins 5 caractères", Toast.LENGTH_SHORT).show();
            return;
        }


        long result = dbHelper.insererUtilisateur(email, ville, mot_de_passe,userType);

        if (result != -1) {
            Toast.makeText(this, "Création du compte réussie", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Échec de la création du compte", Toast.LENGTH_SHORT).show();
          }
}

}
