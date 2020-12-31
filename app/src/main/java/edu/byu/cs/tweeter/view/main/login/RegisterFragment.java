package edu.byu.cs.tweeter.view.main.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;
import edu.byu.cs.tweeter.presenter.RegisterPresenter;
import edu.byu.cs.tweeter.view.asyncTasks.RegisterTask;
import edu.byu.cs.tweeter.view.main.main.MainActivity;

public class RegisterFragment extends Fragment implements RegisterPresenter.View, RegisterTask.Observer {

    private RegisterPresenter presenter;
    private static final String LOG_TAG = "RegisterFragment";
    private Toast registerToast;

    public static RegisterFragment newInstance(){
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        EditText username = view.findViewById(R.id.registerusername);
        EditText password = view.findViewById(R.id.registerpassword);
        EditText firstName = view.findViewById(R.id.registerfirstname);
        EditText lastName = view.findViewById(R.id.registerlastname);
        Button registerButton = view.findViewById(R.id.registerButton);
        TextView cameraText = view.findViewById(R.id.cameraText);
        cameraText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(cameraIntent);
            }
        });

        presenter = new RegisterPresenter(this);

        registerButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Makes a login request. The user is hard-coded, so it doesn't matter what data we put
             * in the LoginRequest object.
             *
             * @param view the view object that was clicked.
             */
            @Override
            public void onClick(View view) {
                registerToast = Toast.makeText(getContext(), "Registering", Toast.LENGTH_LONG);
                registerToast.show();

                if(username.getText().toString().equals("")){
                    Toast.makeText(getContext(), "You did not enter a username", Toast.LENGTH_SHORT).show();
                    return;
                } else if(password.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "You did not enter a password", Toast.LENGTH_SHORT).show();
                    return;
                } else if(firstName.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "You did not enter a First Name", Toast.LENGTH_SHORT).show();
                    return;
                } else if(lastName.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "You did not enter a Last Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                // It doesn't matter what values we put here. We will be logged in with a hard-coded dummy user.
                RegisterRequest registerRequest = new RegisterRequest(username.getText().toString(), password.getText().toString(), firstName.getText().toString(),
                        lastName.getText().toString(), "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
                RegisterTask registerTask = new RegisterTask(presenter, RegisterFragment.this);
                registerTask.execute(registerRequest);
            }
        });

        return view;
    }

    @Override
    public void registerSuccessful(RegisterResponse registerResponse) {
        Intent intent = new Intent(getContext(), MainActivity.class);

        intent.putExtra(MainActivity.CURRENT_USER_KEY, registerResponse.getUser());
        intent.putExtra(MainActivity.AUTH_TOKEN_KEY, registerResponse.getAuthToken());

        startActivity(intent);
    }

    @Override
    public void registerUnsuccessful(RegisterResponse registerResponse) {
        Toast.makeText(getContext(), "Failed to login. " + registerResponse.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void handleException(Exception exception) {
        Log.e(LOG_TAG, exception.getMessage(), exception);
        Toast.makeText(getContext(), "Failed to login because of exception: " + exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}
