package com.example.navdrawer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class espace_client extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_espace_client );
        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout2);
        findViewById(R.id.imagemenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer( GravityCompat.START);
            }
        });



        NavigationView navigationview = findViewById(R.id.navigationview1);
        navigationview.setItemIconTintList(null);
        NavController navController = Navigation.findNavController(this, R.id.navHostFragment1);

        NavigationUI.setupWithNavController(navigationview, navController);
        final TextView textTitle = findViewById(R.id.textTitle1);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                textTitle.setText(destination.getLabel());


            }
        });
    }
}