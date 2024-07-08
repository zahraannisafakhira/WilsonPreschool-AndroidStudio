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
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText mName, mGender, mBirthdate, mAddress, mParName, mHomPhone, mParPhone;
    Button mSaveBtn;
    ProgressDialog pd;
    FirebaseFirestore db;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrationFragment newInstance(String param1, String param2) {
        RegistrationFragment fragment = new RegistrationFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        mName = view.findViewById(R.id.nameRes);
        mGender = view.findViewById(R.id.genderRes);
        mBirthdate = view.findViewById(R.id.birthdateRes);
        mAddress = view.findViewById(R.id.addressRes);
        mParName = view.findViewById(R.id.pnameRes);
        mHomPhone = view.findViewById(R.id.hphoneRes);
        mParPhone = view.findViewById(R.id.pphoneRes);
        mSaveBtn = view.findViewById(R.id.saveButton);

        pd = new ProgressDialog(getActivity());
        db = FirebaseFirestore.getInstance();

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mName.getText().toString().trim();
                String gender = mGender.getText().toString().trim();
                String birthdate = mBirthdate.getText().toString().trim();
                String address = mAddress.getText().toString().trim();
                String parentName = mParName.getText().toString().trim();
                String homePhone = mHomPhone.getText().toString().trim();
                String parentPhone = mParPhone.getText().toString().trim();
                uploadData(name, gender, birthdate, address, parentName, homePhone, parentPhone);
            }
        });

        return view;
    }

    private void uploadData(String name, String gender, String birthdate, String address, String parentName, String homePhone, String parentPhone) {
        pd.setTitle("Adding Data to Firestore");
        pd.show();
        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("name", name);
        doc.put("gender", gender);
        doc.put("dateofbirth", birthdate);
        doc.put("address", address);
        doc.put("parent", parentName);
        doc.put("phone", homePhone);
        doc.put("parentnum", parentPhone);

        db.collection("applicants").document(id).set(doc)
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
        mGender.setText("");
        mBirthdate.setText("");
        mAddress.setText("");
        mParName.setText("");
        mHomPhone.setText("");
        mParPhone.setText("");
    }
}