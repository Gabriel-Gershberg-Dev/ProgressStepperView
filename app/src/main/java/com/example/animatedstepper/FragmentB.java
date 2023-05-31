package com.example.animatedstepper;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.Navigation;


public class FragmentB extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View nextToC = view.findViewById(R.id.nextToC);

                nextToC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController(v).navigate(R.id.fragmentBtoC);
                        Stepper stepper = getActivity().findViewById(R.id.Stepper);
                        if (stepper != null) {
                            stepper.forward();
                        }
                    }
                });

        View backArrow = view.findViewById(R.id.backArrow);

                backArrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController(v).popBackStack();
                        Stepper stepper = getActivity().findViewById(R.id.Stepper);
                        if (stepper != null) {
                            stepper.back();
                        }
                    }
                });
    }
}