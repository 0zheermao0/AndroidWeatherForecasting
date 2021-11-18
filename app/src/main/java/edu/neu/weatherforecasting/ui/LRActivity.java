package edu.neu.weatherforecasting.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.FrameLayout;

import java.util.Objects;

import edu.neu.weatherforecasting.R;
import edu.neu.weatherforecasting.ui.fragment.LoginFragment;
import edu.neu.weatherforecasting.ui.fragment.RegisterFragment;

public class LRActivity extends AppCompatActivity {

    private FrameLayout fl_container;
    private Fragment loginFragment;
    private Fragment registerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_r);
//        Objects.requireNonNull(this.getSupportActionBar()).hide();

        fl_container = findViewById(R.id.fl_container);
        loginFragment = new LoginFragment();
        registerFragment = new RegisterFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container, loginFragment, "login").commitAllowingStateLoss();
//        getSupportFragmentManager().beginTransaction().add(R.id.fl_container, registerFragment, "register").commitAllowingStateLoss();
    }
}