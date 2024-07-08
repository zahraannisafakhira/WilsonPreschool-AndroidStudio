//package com.example.wilsonpreschool;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.ProgressDialog;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//import java.util.HashMap;
//import java.util.Objects;
//import java.util.UUID;
//
//public class BlogActivity extends AppCompatActivity {
//
//    EditText mTitleEt, mDescriptionEt;
//    Button mSaveBtn;
//
//    ProgressDialog pd;
//
//    FirebaseFirestore db;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_blog);
//
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("Add Data");
//
//
//        mTitleEt = findViewById(R.id.titleEt);
//        mDescriptionEt = findViewById(R.id.descriptionEt);
//        mSaveBtn = findViewById(R.id.saveButton);
//
//        pd = new ProgressDialog(this);
//
//        db = FirebaseFirestore.getInstance();
//
//        mSaveBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                String title = mTitleEt.getText().toString().trim();
//                String description = mDescriptionEt.getText().toString().trim();
//
//                uploadData(title, description);
//            }
//        });
//
//    }
//
//    private void uploadData(String title, String description) {
//        pd.setTitle("Adding Data to Firestore");
//        pd.show();
//        String id = UUID.randomUUID().toString();
//
//        Map<String, Objects> doc = new HashMap<>();
//        doc.put("id", id);
//        doc.put("title", title);
//        doc.put("description", description);
//
//        db.collection("Documents").document(id).set(doc)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        pd.dismiss();
//                        Toast.makeText(BlogActivity.this, "Uploaded...", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        pd.dismiss();
//                        Toast.makeText(BlogActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//    }
//}