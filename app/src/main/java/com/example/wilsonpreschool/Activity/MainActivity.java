package com.example.wilsonpreschool.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.wilsonpreschool.Adapter.SliderAdapter;
import com.example.wilsonpreschool.BlogFragment;
import com.example.wilsonpreschool.ContactFragment;
import com.example.wilsonpreschool.Domain.Programs;
import com.example.wilsonpreschool.Domain.SliderItems;
import com.example.wilsonpreschool.HomeFragment;
import com.example.wilsonpreschool.ProgramsFragment;
import com.example.wilsonpreschool.R;
import com.example.wilsonpreschool.RegistrationFragment;
import com.example.wilsonpreschool.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());



        binding.bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    replaceFragment(new HomeFragment());
                } else if (item.getItemId() == R.id.programs) {
                    replaceFragment(new ProgramsFragment());
                } else if (item.getItemId() == R.id.registration) {
                    replaceFragment(new RegistrationFragment());
                } else if (item.getItemId() == R.id.blog) {
                    replaceFragment(new BlogFragment());
                } else if (item.getItemId() == R.id.contact_us) {
                    replaceFragment(new ContactFragment());
                } else {
                    return false;
                }
                return true;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}