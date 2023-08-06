package com.example.navdrawer;

import Chat_BOT.chat_bot;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomSheetDialog bottomSheetDialog;
    BottomAppBar bottomAppBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        bottomAppBar = findViewById(R.id.bottomAppBar3);

        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.setting) {
                    Toast.makeText(MainActivity.this, " appel d'urgence ", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog = new BottomSheetDialog(MainActivity.this, R.style.BottomSheetTheme);
                    View sheetview = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottomsheetlayout, null);
                    sheetview.findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(MainActivity.this, "Share is Clicked", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData( Uri.parse("tel:123457895"));
                            startActivity(intent);
                        }
                    });
                    sheetview.findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(MainActivity.this, " comment is Clicked", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:123457895"));
                            startActivity(intent);
                        }
                    });
                    sheetview.findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(MainActivity.this, "programing is Clicked", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:123457895"));
                            startActivity(intent);
                        }
                    });
                    sheetview.findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(MainActivity.this, "programing is Clicked", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:123457895"));
                            startActivity(intent);
                        }
                    });

                    bottomSheetDialog.setContentView(sheetview);
                    bottomSheetDialog.show();
                }
                if (item.getItemId() == R.id.romoraquage) {
                    Toast.makeText(MainActivity.this, " romoquage ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:123457895"));
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.notfication) {
                    Toast.makeText(MainActivity.this, " notification ", Toast.LENGTH_SHORT).show();

                    //startActivity(new Intent(getApplicationContext(), Notificationn.class));

                }


                return false;
            }
        });
        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout1);
        findViewById(R.id.imagemenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer( GravityCompat.START);

            }
        });



        NavigationView navigationview = findViewById(R.id.navigationview6);
        navigationview.setItemIconTintList(null);
        NavController navController = Navigation.findNavController(this, R.id.navHostFragment6);

        NavigationUI.setupWithNavController(navigationview, navController);
        final TextView textTitle = findViewById(R.id.textTitle2);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                textTitle.setText(destination.getLabel());


            }
        });

    }

    public void process(View view) {
        Intent intent = null;
        if (view.getId() == R.id.bottomAppBar3) {
            intent = new Intent(Intent.ACTION_VIEW);

            startActivity(new Intent(getApplicationContext(), chat_bot.class));
        }
    }}
