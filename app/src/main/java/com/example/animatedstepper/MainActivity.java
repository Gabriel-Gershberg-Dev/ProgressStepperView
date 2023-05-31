package com.example.animatedstepper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavHostFragment.findNavController(getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)).navigateUp();
    }
}