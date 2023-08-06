package com.example.myfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
Fragment fragment;
FragmentManager fragmentManager;
FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        addFragment();
    }

    public void addFragment() {
        fragment=new Fragment_login ();
        fragmentManager =getSupportFragmentManager ();
        fragmentTransaction=fragmentTransaction.beginTransaction();
        fragmentTransaction.add ( R.id.fragmentContainer,fragment );
        fragmentTransaction.comit();
    }
    public void replaceFragment()
    {
        fragment new Fragme_register ();
        fragmentManager=getSupportFragmentManager ();
        fragmentTransaction=fragmentManager.beginTransaction ();
        fragmentTransaction.addToBackStack ( null );
        fragmentTransaction.replace ( R.id.fragmentContainer,fragment );

    }
}