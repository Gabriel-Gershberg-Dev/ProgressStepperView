package com.example.animatedstepper;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.Navigation;

import com.example.stepperlib.Stepper;


public class FragmentC extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_c, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View nextToD = view.findViewById(R.id.nextToD);

                nextToD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController(v).navigate(R.id.fragmentCtoD);
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