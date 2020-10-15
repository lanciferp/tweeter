package edu.byu.cs.tweeter.view.main.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.android.material.tabs.TabLayout;
import edu.byu.cs.tweeter.R;


/**
 * Contains the minimum UI required to allow the user to login with a hard-coded user. Most or all
 * of this should be replaced when the back-end is implemented.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginSectionsPagerAdapter loginSectionsPagerAdapter = new LoginSectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.login_view_pager);
        viewPager.setAdapter(loginSectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.login_tabs);
        tabs.setupWithViewPager(viewPager);
    }

}
