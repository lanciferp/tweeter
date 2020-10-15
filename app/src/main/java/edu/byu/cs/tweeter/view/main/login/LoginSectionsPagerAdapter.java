package edu.byu.cs.tweeter.view.main.login;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.view.main.PlaceholderFragment;


public class LoginSectionsPagerAdapter  extends FragmentStatePagerAdapter {

    private static final int LOGIN_FRAGMENT_POSITION = 0;
    private static final int REGISTER_FRAGMENT_POSITION = 1;

    private final Context mContext;
    @StringRes
    private static final int[] TAB_TITLES = new int[]{ R.string.loginTabTitle, R.string.registerTabTitle};


    public LoginSectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context.getApplicationContext();
    }

    @Override
    public Fragment getItem(int position) {

        if (position == REGISTER_FRAGMENT_POSITION) {
            return RegisterFragment.newInstance();
        } else if (position == LOGIN_FRAGMENT_POSITION) {
            return LoginFragment.newInstance();
        } else{
            return PlaceholderFragment.newInstance(position + 1);
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}
