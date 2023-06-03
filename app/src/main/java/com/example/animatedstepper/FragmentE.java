package com.example.animatedstepper;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.Navigation;
import androidx.core.content.ContextCompat;

import com.example.stepperlib.Stepper;


public class FragmentE extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_e, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View progress = view.findViewById(R.id.progress);

                progress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Stepper stepper = getActivity().findViewById(R.id.Stepper);
                        if (stepper != null) {
                            stepper.progress(3);
                            stepper.addOnCompleteListener(new Runnable() {
                                @Override
                                public void run() {
                                    View stepperView = getActivity().findViewById(R.id.StepperView);
                                    if (stepperView != null) {
                                        stepperView.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.success_gradient));
                                    }
                                }
                            });
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
                            stepper.stop();
                            stepper.back();
                        }
                    }
                });

        View progressStop = view.findViewById(R.id.progressStop);

                progressStop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Stepper stepper = getActivity().findViewById(R.id.Stepper);
                        if (stepper != null) {
                            stepper.stop();
                        }
                    }
                });
    }
}