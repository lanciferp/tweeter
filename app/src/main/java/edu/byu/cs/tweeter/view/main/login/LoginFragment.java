package edu.byu.cs.tweeter.view.main.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.presenter.LoginPresenter;
import edu.byu.cs.tweeter.view.asyncTasks.LoginTask;
import edu.byu.cs.tweeter.view.main.main.MainActivity;

public class LoginFragment extends Fragment implements LoginPresenter.View, LoginTask.Observer {

    private LoginPresenter presenter;
    private static final String LOG_TAG = "LoginFragment";
    private Toast loginInToast;

    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        EditText username = view.findViewById(R.id.loginusername);
        EditText password = view.findViewById(R.id.loginpassword);
        Button loginButton = view.findViewById(R.id.loginButton);
        presenter = new LoginPresenter(this);

        loginButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Makes a login request. The user is hard-coded, so it doesn't matter what data we put
             * in the LoginRequest object.
             *
             * @param view the view object that was clicked.
             */
            @Override
            public void onClick(View view) {
                loginInToast = Toast.makeText(getContext(), "Logging In", Toast.LENGTH_LONG);
                loginInToast.show();

                if(username.getText().toString().equals("")){
                    Toast.makeText(getContext(), "You did not enter a username", Toast.LENGTH_SHORT).show();
                    return;
                } else if(password.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "You did not enter a password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // It doesn't matter what values we put here. We will be logged in with a hard-coded dummy user.
                LoginRequest loginRequest = new LoginRequest(username.getText().toString(), password.getText().toString());
                LoginTask loginTask = new LoginTask(presenter, LoginFragment.this);
                loginTask.execute(loginRequest);
            }
        });

        return view;
    }

    @Override
    public void loginSuccessful(LoginResponse loginResponse) {
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtra(MainActivity.CURRENT_USER_KEY, loginResponse.getUser());
        intent.putExtra(MainActivity.AUTH_TOKEN_KEY, loginResponse.getAuthToken());
        startActivity(intent);
    }

    @Override
    public void loginUnsuccessful(LoginResponse loginResponse) {
        Toast.makeText(getContext(), "Failed to login. " + loginResponse.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void handleException(Exception exception) {
        Log.e(LOG_TAG, exception.getMessage(), exception);
        Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}