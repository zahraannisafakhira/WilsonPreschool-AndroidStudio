package com.example.wilsonpreschool;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlogFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    EditText mName, mEmail, mComment;
    Button mSaveBtn;
    ProgressDialog pd;
    FirebaseFirestore db;

    public BlogFragment() {
        // Required empty public constructor
    }

    public static BlogFragment newInstance(String param1, String param2) {
        BlogFragment fragment = new BlogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog, container, false);

        mName = view.findViewById(R.id.nameCom);
        mEmail = view.findViewById(R.id.emailCom);
        mComment = view.findViewById(R.id.descriptionCom);
        mSaveBtn = view.findViewById(R.id.saveButton);

        pd = new ProgressDialog(getActivity());
        db = FirebaseFirestore.getInstance();

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mName.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String comment = mComment.getText().toString().trim();
                uploadData(name, email, comment);
            }
        });

        return view;
    }

    private void uploadData(String name, String email, String comment) {
        pd.setTitle("Adding Data to Firestore");
        pd.show();
        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("name", name);
        doc.put("email", email);
        doc.put("comment", comment);

        db.collection("comments").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pd.dismiss();
                        Toast.makeText(getActivity(), "Uploaded...", Toast.LENGTH_SHORT).show();
                        clearForm();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void clearForm() {
        mName.setText("");
        mEmail.setText("");
        mComment.setText("");
    }
}
