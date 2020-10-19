package edu.byu.cs.tweeter.view.main.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;
import edu.byu.cs.tweeter.presenter.StoryPresenter;
import edu.byu.cs.tweeter.view.asyncTasks.GetStoryTask;
import edu.byu.cs.tweeter.view.main.profile.ProfileActivity;
import edu.byu.cs.tweeter.view.util.ImageUtils;

public class StoryFragment extends Fragment implements StoryPresenter.View {
    private static final String LOG_TAG = "StoryFragment";
    private static final String USER_KEY = "UserKey";
    private static final String AUTH_TOKEN_KEY = "AuthTokenKey";

    private static final int LOADING_DATA_VIEW = 0;
    private static final int ITEM_VIEW = 1;

    private static final int PAGE_SIZE = 10;

    private User user;
    private AuthToken authToken;
    private StoryPresenter presenter;

    private StoryRecyclerViewAdapter storyRecyclerViewAdapter;

    /**
     * Creates an instance of the fragment, and puts the user who is logged in in the arguements
     *
     * @param user      the user who's story is being loaded
     * @param authToken the auth token for this users session
     * @return the fragment
     */
    public static StoryFragment newInstance(User user, AuthToken authToken) {
        StoryFragment fragment = new StoryFragment();

        Bundle args = new Bundle(2);
        args.putSerializable(USER_KEY, user);
        args.putSerializable(AUTH_TOKEN_KEY, authToken);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        //noinspection ConstantConditions
        user = (User) getArguments().getSerializable(USER_KEY);
        authToken = (AuthToken) getArguments().getSerializable(AUTH_TOKEN_KEY);

        presenter = new StoryPresenter(this);

        RecyclerView storyRecyclerView = view.findViewById(R.id.listRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        storyRecyclerView.setLayoutManager(layoutManager);
        storyRecyclerViewAdapter = new StoryRecyclerViewAdapter();
        storyRecyclerView.setAdapter(storyRecyclerViewAdapter);
        storyRecyclerView.addOnScrollListener(new StoryRecyclerViewPaginationScrollListener(layoutManager));

        return view;
    }

    protected SpannableString setMentions(Status status){
        SpannableString ss = new SpannableString(status.getMessage());
        for (User user : status.getMentions()) {
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    Toast.makeText(widget.getContext(), "worked" + status.getLastName(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getActivity(), ProfileActivity.class);
                    intent.putExtra(ProfileActivity.CURRENT_USER_KEY, user);
                    intent.putExtra(ProfileActivity.AUTH_TOKEN_KEY, authToken);
                    startActivity(intent);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                }
            };
            int startSpan = -1;
            int endSpan = 0;
            String message = status.getMessage();
            for (int i = 0; i < message.length(); ++i){
                if (startSpan != -1 && message.toCharArray()[i] == ' ' || i == message.length() - 1){
                    endSpan = ++i;
                    break;
                }
                if (message.toCharArray()[i] == '@'){
                    startSpan = i;
                }
            }
            ss.setSpan(clickableSpan, startSpan, endSpan, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        }
        return ss;
    }

    private class StoryHolder extends RecyclerView.ViewHolder {
        private final ImageView statusUserImage;
        private final TextView statusUserAlias;
        private final TextView userName;
        private final TextView timePosted;
        private final TextView message;

        StoryHolder(@NonNull View itemView, int viewType) {
            super(itemView);

            if (viewType == ITEM_VIEW) {
                statusUserImage = itemView.findViewById(R.id.statusUserImage);
                statusUserAlias = itemView.findViewById(R.id.statusUserAlias);
                userName = itemView.findViewById(R.id.statusUserName);
                timePosted = itemView.findViewById(R.id.statusTime);
                message = itemView.findViewById(R.id.statusMessage);

            } else {
                statusUserImage = null;
                statusUserAlias = null;
                userName = null;
                timePosted = null;
                message = null;
            }
        }

        void bindStatus(Status status) {
            statusUserImage.setImageDrawable(ImageUtils.drawableFromByteArray(status.getImageBytes()));
            statusUserAlias.setText(status.getAlias());
            userName.setText(status.getName());
            timePosted.setText(status.getTimePosted().toString());
            SpannableString ss = setMentions(status);
            message.setText(ss);
            message.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private class StoryRecyclerViewAdapter extends RecyclerView.Adapter<StoryHolder> implements GetStoryTask.Observer {

        private final List<Status> statuses = new ArrayList<>();
        private edu.byu.cs.tweeter.model.domain.Status lastStatus;

        private boolean hasMorePages;
        private boolean isLoading = false;

        /**
         * Creates an instance and loads the first page of following data.
         */
        StoryRecyclerViewAdapter() {
            loadMoreItems();
        }

        /**
         * Adds new users to the list from which the RecyclerView retrieves the statuses it displays
         * and notifies the RecyclerView that items have been added.
         *
         * @param newStatuses the users to add.
         */
        void addItems(List<Status> newStatuses) {
            int startInsertPosition = statuses.size();
            statuses.addAll(newStatuses);
            this.notifyItemRangeInserted(startInsertPosition, newStatuses.size());
        }

        void addItem(Status status) {
            statuses.add(status);
            this.notifyItemChanged(statuses.size() - 1);
        }

        void removeItem(Status status) {
            int position = statuses.indexOf(status);
            statuses.remove(position);
            this.notifyItemChanged(position);
        }

        @NonNull
        @Override
        public StoryFragment.StoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(StoryFragment.this.getContext());
            View view;

            if (viewType == LOADING_DATA_VIEW) {
                view = layoutInflater.inflate(R.layout.loading_row, parent, false);

            } else {
                view = layoutInflater.inflate(R.layout.status_row, parent, false);
            }

            return new StoryFragment.StoryHolder(view, viewType);
        }

        @Override
        public void onBindViewHolder(@NonNull StoryFragment.StoryHolder storyHolder, int position) {
            if (!isLoading) {
                storyHolder.bindStatus(statuses.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return statuses.size();
        }

        @Override
        public int getItemViewType(int position) {
            return (position == statuses.size() - 1 && isLoading) ? LOADING_DATA_VIEW : ITEM_VIEW;
        }

        void loadMoreItems() {
            isLoading = true;
            addLoadingFooter();

            GetStoryTask getStoryTask = new GetStoryTask(presenter, this);
            StoryRequest request = new StoryRequest(user, PAGE_SIZE, lastStatus);
            getStoryTask.execute(request);
        }

        @Override
        public void statusesRetrieved(StoryResponse storyResponse) {
            List<Status> statuses = storyResponse.getStatuses();

            lastStatus = (statuses.size() > 0) ? statuses.get(statuses.size() - 1) : null;
            hasMorePages = storyResponse.getHasMorePages();

            isLoading = false;
            removeLoadingFooter();
            storyRecyclerViewAdapter.addItems(statuses);
        }

        @Override
        public void handleException(Exception exception) {
            Log.e(LOG_TAG, exception.getMessage(), exception);
            removeLoadingFooter();
            Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
        }

        private void addLoadingFooter() {
            addItem(new Status(null, "message", null, new User("Dummy", "User", "")));
        }

        private void removeLoadingFooter() {
            removeItem(statuses.get(statuses.size() - 1));
        }
    }

    private class StoryRecyclerViewPaginationScrollListener extends RecyclerView.OnScrollListener {
        private final LinearLayoutManager layoutManager;

        StoryRecyclerViewPaginationScrollListener(LinearLayoutManager layoutManager) {
            this.layoutManager = layoutManager;
        }

        @Override
        public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!storyRecyclerViewAdapter.isLoading && storyRecyclerViewAdapter.hasMorePages) {
                if ((visibleItemCount + firstVisibleItemPosition) >=
                        totalItemCount && firstVisibleItemPosition >= 0) {
                    storyRecyclerViewAdapter.loadMoreItems();
                }
            }
        }
    }
}












