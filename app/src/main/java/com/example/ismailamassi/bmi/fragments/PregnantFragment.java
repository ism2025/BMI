package com.example.ismailamassi.bmi.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ismailamassi.bmi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PregnantFragment extends Fragment {


    public PregnantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pregnant, container, false);
    }

}
