package com.example.animatedstepper;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.Navigation;


public class FragmentA extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View nextToB = view.findViewById(R.id.nextToB);

                nextToB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController(v).navigate(R.id.fragmentAtoB);
                        Stepper stepper = getActivity().findViewById(R.id.Stepper);
                        if (stepper != null) {
                            stepper.forward();
                        }
                    }
                });
    }
}