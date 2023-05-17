package com.example.grocart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CartActivity extends AppCompatActivity {
    private MaterialToolbar toolbar;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initViews();
        initBottomNavView();
        setSupportActionBar(toolbar);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new FirstCartFragment());
        transaction.commit();
    }

    private void initBottomNavView() {
        bottomNavigationView.setSelectedItemId(R.id.cart);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 int Id = item.getItemId();
                     if (Id == R.id.search) {
                         Intent searchIntent = new Intent(CartActivity.this, SearchActivity.class);
                         searchIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                         startActivity(searchIntent);
                     }
                    else if (Id == R.id.home) {
                         Intent homeIntent = new Intent(CartActivity.this, MainActivity.class);
                         homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                         startActivity(homeIntent);
                     }
                    else if (Id == R.id.cart) {

                     }
                return false;
            }
        });
    }

    private void initViews(){
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView);
        toolbar = (MaterialToolbar) findViewById(R.id.toolbar);
    }
}