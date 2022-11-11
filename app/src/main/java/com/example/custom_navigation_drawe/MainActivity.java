package com.example.custom_navigation_drawe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout_id);
        MaterialToolbar toolbar = findViewById(R.id.materialToolbar);
        NavigationView navigationView = findViewById(R.id.navigationView);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id) {
                    case R.id.nav_home:
                        replaceFragment(new HomeFragment());
                        break;

                    case R.id.nav_add_expense:
                        replaceFragment(new Add_Expense_Fragment());
                        Toast.makeText(MainActivity.this,"Add Expense is Click", Toast.LENGTH_SHORT ).show();
                        break;

                    case R.id.nav_expense_list:
                        replaceFragment(new Expense_list_Fragment());
                        Toast.makeText(MainActivity.this,"Expense List is Click", Toast.LENGTH_SHORT ).show();
                        break;

                    case R.id.nav_message:
                        replaceFragment(new MessageFragment());
                        break;


                    case R.id.nav_sync:
                        replaceFragment(new SyncFragment());
                        Toast.makeText(MainActivity.this, "Sync Is Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_trash:
                        replaceFragment(new TrashFragment());
                        Toast.makeText(MainActivity.this, "TrashFragment Is Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_setting:
                        replaceFragment(new SettingFragment());
                        Toast.makeText(MainActivity.this, "SettingFragment Is Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_login:
                        replaceFragment(new LogingFragment());
                        Toast.makeText(MainActivity.this, "LogingFragment Is Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_share:
                        replaceFragment(new ShareFragment());
                        Toast.makeText(MainActivity.this, "ShareFragment Is Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_rate:
                        replaceFragment(new RateFragment());
                        Toast.makeText(MainActivity.this, "RateFragment Is Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        return true;
                }
                return true;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}