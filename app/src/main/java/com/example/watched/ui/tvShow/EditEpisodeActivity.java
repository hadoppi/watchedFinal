package com.example.watched.ui.tvShow;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.example.watched.R;
import com.example.watched.database.entity.EpisodeEntity;
import com.example.watched.database.entity.TvShowEntity;
import com.example.watched.ui.BaseActivity;
import com.example.watched.util.OnAsyncEventListener;
import com.example.watched.viewmodel.tvShow.EpisodeViewModel;
import com.example.watched.viewmodel.tvShow.TvShowViewModel;

public class EditEpisodeActivity extends BaseActivity {

    private static final String TAG = "EditAccountActivity";

    private EpisodeEntity episode;
    private String name;
    private int numberEpisode;
    private String TvShow;
    private boolean isEditMode;
    private Toast toast;
    private EditText episodeName;
    private EditText episodeNumber;

    private EpisodeViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_edit_episode, frameLayout);
        String TvShow = getIntent().getStringExtra("tvShow");
        navigationView.setCheckedItem(position);
        episode=new EpisodeEntity();
        episodeName = findViewById(R.id.episodeName);
        episodeName.requestFocus();
        episodeNumber = findViewById(R.id.episodeNumber);
        episodeNumber.requestFocus();

        Button saveBtn = findViewById(R.id.createAccountButton);
        saveBtn.setOnClickListener(view -> {
            saveChanges(episodeName.getText().toString(), Integer.parseInt(episodeNumber.getText().toString()), TvShow);
            onBackPressed();
            toast.show();
        });


        if (name == null) {
            setTitle(getString(R.string.title_activity_create_episode));
            toast = Toast.makeText(this, getString(R.string.episode_created), Toast.LENGTH_LONG);
            isEditMode = false;
        } else {
            setTitle(getString(R.string.title_activity_edit_episode));
            saveBtn.setText(R.string.action_update);
            toast = Toast.makeText(this, getString(R.string.show_edited), Toast.LENGTH_LONG);
            isEditMode = true;
        }
        if (isEditMode) {
            EpisodeViewModel.Factory factory = new EpisodeViewModel.Factory(
                    getApplication(), episode.getId());
            viewModel = ViewModelProviders.of(this, factory).get(EpisodeViewModel.class);

            viewModel.getEpisode().observe(this, episodeEntity -> {
                if (episodeEntity != null) {
                    episode = episodeEntity;
                    episodeName.setText(episode.getName());
                    episodeNumber.setText(episode.getNumberEpisode());
                    episodeEntity.setTvShow(TvShow);
                }
            });
        }
    }

    private void saveChanges(String EpisodeName, int numberEpisode, String tvShow) {
        if (isEditMode) {
            if (!"".equals(EpisodeName)) {
                episode.setName(EpisodeName);
                episode.setNumberEpisode(numberEpisode);
                episode.setTvShow(tvShow);

                viewModel.updateEpisode(episode, new OnAsyncEventListener() {
                    @Override
                    public void onSuccess() {
                        Log.d(TAG, "updateAccount: success");
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Log.d(TAG, "updateAccount: failure", e);
                    }
                });
            }
        } else {

            EpisodeEntity newEpisode = new EpisodeEntity();
            newEpisode.setName(EpisodeName);
            newEpisode.setNumberEpisode(numberEpisode);
            newEpisode.setTvShow(tvShow);
            EpisodeViewModel.Factory factory = new EpisodeViewModel.Factory(
                    getApplication(), newEpisode.getId());
            viewModel = ViewModelProviders.of(this, factory).get(EpisodeViewModel.class);
            viewModel.createEpisode(newEpisode, new OnAsyncEventListener() {
                @Override
                public void onSuccess() {
                    Log.d(TAG, "createAccount: success");
                }

                @Override
                public void onFailure(Exception e) {
                    Log.d(TAG, "createAccount: failure", e);
                }
            });
        }
    }
}
