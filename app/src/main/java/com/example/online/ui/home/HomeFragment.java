package com.example.online.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.online.R;

public class HomeFragment extends Fragment {

    private AllCatagoriesViewModel allCatagoriesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        allCatagoriesViewModel =
                ViewModelProviders.of(this).get(AllCatagoriesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
  //      final TextView textView = root.findViewById(R.id.home);


        allCatagoriesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
              //  textView.setText(s);
            }
        });
        return root;

    }
}