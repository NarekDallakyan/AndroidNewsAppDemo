package am.ith.app.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import am.ith.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class YoutubePlayerFragment extends YouTubePlayerSupportFragment {

    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;

    private View view;
    private Context context;
    public String youtube_ID = "";
    private Button button_play;
    private YouTubePlayerView youTubePlay;

    public void setYoutube_ID(String youtube_ID) {
        this.youtube_ID = youtube_ID;
    }

    public YoutubePlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_youtube_player, container, false);
        context = getContext();
        Toast.makeText(context, youtube_ID, Toast.LENGTH_SHORT).show();
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(youtube_ID);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        button_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                youTubePlay.initialize("AIzaSyDL45Up3GHsNUnhrLnc_9-_y-wtKzb-mwQ",onInitializedListener);
            }
        });
        return view;
    }

    private void findView() {

        button_play = view.findViewById(R.id.youtubePlayBtnID);
        youTubePlay = view.findViewById(R.id.youtubePlayID);


    }

}
