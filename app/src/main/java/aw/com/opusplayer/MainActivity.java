package aw.com.opusplayer;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;

import aw.com.controllers.OpusController;
import aw.com.events.OpusControllerEvent;
import aw.com.utils.Converters;
import aw.com.utils.FileUtilities;
import top.oply.opuslib.OpusEvent;

public class MainActivity extends AppCompatActivity {

    private ImageButton playPauseButton;
    private ProgressBar progressBar;
    private TextView durationText;
    private TextView currentPositionText;

    private OpusController opusController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        opusController = new OpusController(this);

        copySampleFiles();
        InitUI();
        EventBus.getDefault().register(this);
    }

    private void InitUI() {
        durationText = (TextView) findViewById(R.id.durationText);
        currentPositionText = (TextView) findViewById(R.id.currentPositionText);
        playPauseButton = (ImageButton) findViewById(R.id.playPauseButton);

        progressBar = (ProgressBar) findViewById(R.id.playerProgressBar);
        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Jump on the specific duration is not implemented yet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void copySampleFiles() {
        // we have 2 sample files here, we will copy those sample into local, because that's currently the only way for OpusLib to play it
        String sample1Path = Environment.getExternalStorageDirectory() + getString(R.string.opus_sample1_path);
        File file1 = new File(sample1Path);
        if (!file1.exists()) {
            try {
                FileUtilities.copyRAWtoSDCard(this, R.raw.sample1, sample1Path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String sample2Path = Environment.getExternalStorageDirectory() + getString(R.string.opus_sample2_path);
        File file2 = new File(sample2Path);
        if (!file2.exists()) {
            try {
                FileUtilities.copyRAWtoSDCard(this, R.raw.sample2, sample2Path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnOpusControllerEvent(OpusControllerEvent event) {
        switch (event.getOpusEventCode()) {

            case OpusEvent.PLAYING_STARTED: {
                playPauseButton.setImageResource(R.drawable.pause);
                durationText.setText(Converters.convertNumberToTimeDisplay(opusController.getProgressPercentage()));
                break;
            }
            case OpusEvent.PLAYING_FINISHED: {
                playPauseButton.setImageResource(R.drawable.play);
                break;
            }
            case OpusEvent.PLAYING_PAUSED: {
                playPauseButton.setImageResource(R.drawable.play);
                break;
            }
            case OpusEvent.PLAY_PROGRESS_UPDATE: {
                progressBar.setProgress(opusController.getProgressPercentage());
                currentPositionText.setText(Converters.convertNumberToTimeDisplay((int) opusController.getPlayer().getPosition()));
                break;
            }
        }
    }

    public void onPlayPauseClick(View v) {
        switch (opusController.getPlayerState()) {
            case NONE: {
                opusController.getPlayer().play(Environment.getExternalStorageDirectory() + "/OpusPlayer/sample2.opus");
                break;
            }
            case PLAYING: {
                opusController.getPlayer().pause();
                break;
            }
            case PAUSED: {
                opusController.getPlayer().resume();
                break;
            }
            case FINISHED: {
                break;
            }
        }
    }
}
