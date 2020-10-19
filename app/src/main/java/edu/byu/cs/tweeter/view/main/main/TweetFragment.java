package edu.byu.cs.tweeter.view.main.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Date;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;
import edu.byu.cs.tweeter.presenter.TweetPresenter;
import edu.byu.cs.tweeter.view.asyncTasks.TweetTask;

public class TweetFragment extends DialogFragment implements TweetPresenter.View, TweetTask.Observer {

    EditText tweetText;
    Button sendTweet;

    public static final String CURRENT_USER_KEY = "CurrentUser";
    public static final String AUTH_TOKEN_KEY = "AuthTokenKey";
    private TweetPresenter presenter;

    private User user;
    private AuthToken authToken;

    public static TweetFragment newInstance (User user, AuthToken authToken) {
        TweetFragment frag = new TweetFragment();
        Bundle args = new Bundle();
        args.putSerializable(CURRENT_USER_KEY, user);
        args.putSerializable(AUTH_TOKEN_KEY, authToken);
        frag.setArguments(args);
        return frag;
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login_popup, container, false);
        //noinspection ConstantConditions
        user = (User) getArguments().getSerializable(CURRENT_USER_KEY);
        authToken = (AuthToken) getArguments().getSerializable(AUTH_TOKEN_KEY);
        presenter = new TweetPresenter(this);

        sendTweet = v.findViewById(R.id.tweetButton);
        sendTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Status status = new Status(new Date(1,1,1), tweetText.getText().toString(), null, user);

                TweetRequest request = new TweetRequest(status);
                TweetTask task = new TweetTask(presenter, TweetFragment.this);
                task.execute(request);
            }
        });
        return v;
    }

    @Override
    public void tweetSuccessful(TweetResponse response) {
        this.dismiss();
    }

    @Override
    public void tweetFailed(TweetResponse response) {
        this.dismiss();
    }

    @Override
    public void handleException(Exception ex) {
        this.dismiss();
    }
}
