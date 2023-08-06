package com.example.navdrawer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class declarataion extends Fragment {
    private static final String TAG = "declaration";

    private static final String KEY_nom = "nom";
    private static final String KEY_prenom = "prenom";
    private static final String KEY_adresse = "adresse";
    private static final String KEY_matrecule = "matrecule";
    private static final String KEY_Email = "Email";
    private static final String KEY_Date = "Date";
    private static final String KEY_message = "message";


    private EditText editTextnom;
    private EditText editTextprenom;
    private EditText editTextadresse;
    private EditText editTextmatrecule;
    private EditText editTextEmail;
    private EditText editTextDate;
    private EditText editTextmessage;

      Button saveNote;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();



    public declarataion() {

    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){

        super.onViewCreated ( view, savedInstanceState );



        editTextnom =  view.findViewById(R.id.edit_text_nom);
        editTextprenom = view.findViewById(R.id.edit_text_prenom);
        editTextadresse = view.findViewById(R.id.edit_text_adresse);
        editTextmatrecule = view.findViewById(R.id.edit_text_matricule);
        editTextEmail = view.findViewById(R.id.edit_text_Email);
        editTextDate =view.findViewById(R.id.edit_text_Date);
        editTextmessage = view.findViewById(R.id.edit_text_message);
saveNote=view.findViewById ( R.id.saveNote );
saveNote.setOnClickListener ( new View.OnClickListener () {
    @Override
    public void onClick(View v) {




        String nom = editTextnom.getText().toString();
        String prenom = editTextprenom.getText().toString();
        String adresse = editTextadresse.getText().toString();
        String matrecule = editTextmatrecule.getText().toString();
        String Email = editTextEmail.getText().toString();
        String Date = editTextDate.getText().toString();
        String message = editTextmessage.getText().toString();

        Map<String, Object> note = new HashMap<> ();
        note.put(KEY_nom, nom);
        note.put(KEY_prenom,prenom);
        note.put(KEY_adresse, adresse);
        note.put(KEY_matrecule,matrecule);
        note.put(KEY_Email, Email);
        note.put(KEY_Date,Date);
        note.put(KEY_message,message);

        db.collection("Notebook").document("My First Note").set(note)
                .addOnSuccessListener(new OnSuccessListener<Void> () {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(requireContext (), "Note saved", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener () {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(requireContext (), "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }

});

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate ( R.layout.fragment_declarataion, container, false );
    }
}