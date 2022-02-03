package com.interview.chittoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        setUpViewPager();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Exit the app?")
                .setCancelable(true)
                .setPositiveButton("OK", (dialog, id2) -> finish())
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_logoff){
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(task -> {
                        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    });
            return true;
        }else return false;
    }

    private void setUpViewPager(){
        ViewPager2 viewPager = findViewById(R.id.viewPager2);
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) ->
        {
            switch (position) {
                case 0: default: tab.setText("Certificate");break;
                case 1: tab.setText("Profile");break;
                case 2: tab.setText("Chat");break;
                case 3: tab.setText("Score");break;
            }
        }).attach();
        viewPager.setCurrentItem(1);
    }
}