package com.example.navdrawer.contart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.navdrawer.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class contrat extends Fragment {

    private static final String TAG = "contrat";

    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private TextView textViewData;
Button load;
Button add;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Notebook");
    private DocumentReference noteRef = db.document("Notebook/My First Note");


    public contrat() {
        // Required empty public constructor
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){

        super.onViewCreated ( view, savedInstanceState );
    editTextTitle = view.findViewById(R.id.edit_text_title);
    editTextDescription = view.findViewById(R.id.edit_text_description);
    textViewData = view.findViewById(R.id.text_view_data);
    load=view.findViewById ( R.id.load );
       add =view.findViewById ( R.id.add);
       add.setOnClickListener ( new View.OnClickListener () {
                                            @Override
                                            public void onClick(View view) {
                                                String title = editTextTitle.getText().toString();
                                                String description = editTextDescription.getText().toString();

                                                Note note = new Note(title, description);

                                                notebookRef.add(note);
                                            }
                                        } );
load.setOnClickListener ( new View.OnClickListener () {
    @Override
    public void onClick(View view) {
        notebookRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot> () {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = "";

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Note note = documentSnapshot.toObject(Note.class);
                            note.setDocumentId(documentSnapshot.getId());

                            String documentId = note.getDocumentId();
                            String title = note.getTitle();
                            String description = note.getDescription();

                            data += "ID: " + documentId
                                    + "\nTitle: " + title + "\nDescription: " + description + "\n\n";
                        }

                        textViewData.setText(data);

    }
} );

                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        notebookRef.addSnapshotListener(requireActivity (), new EventListener<QuerySnapshot> () {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }

                String data = "";

                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Note note = documentSnapshot.toObject(Note.class);
                    note.setDocumentId(documentSnapshot.getId());

                    String documentId = note.getDocumentId();
                    String title = note.getTitle();
                    String description = note.getDescription();

                    data += "ID: " + documentId
                            + "\nTitle: " + title + "\nDescription: " + description + "\n\n";
                }

                textViewData.setText(data);
            }
        });
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate ( R.layout.fragment_contrat, container, false );
    }
}