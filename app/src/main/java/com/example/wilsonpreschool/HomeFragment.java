package com.example.wilsonpreschool;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.example.wilsonpreschool.Adapter.SliderAdapter;
import com.example.wilsonpreschool.Domain.SliderItems;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ViewPager2 viewPagerSlider;
    private ProgressBar progressBarBanner;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPagerSlider = view.findViewById(R.id.viewPagerSlider);
        progressBarBanner = view.findViewById(R.id.progressBarBanner);

        initBanner();
        return view;
    }

    private void initBanner() {
        progressBarBanner.setVisibility(View.VISIBLE); // Show the progress bar initially

        // Populate the ArrayList with data from Firebase
        ArrayList<SliderItems> items = new ArrayList<>();
        // Add items to the items ArrayList

        setupBanner(items);
    }

    private void setupBanner(ArrayList<SliderItems> items) {
        SliderAdapter sliderAdapter = new SliderAdapter(items, viewPagerSlider);
        viewPagerSlider.setAdapter(sliderAdapter);
        viewPagerSlider.setClipToPadding(false);
        viewPagerSlider.setClipChildren(false);
        viewPagerSlider.setOffscreenPageLimit(3);
        viewPagerSlider.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        viewPagerSlider.setPageTransformer(compositePageTransformer);

        progressBarBanner.setVisibility(View.GONE); // Hide the progress bar when data is loaded
    }
}