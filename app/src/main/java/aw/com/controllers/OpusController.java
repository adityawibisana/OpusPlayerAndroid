package aw.com.controllers;

import android.content.Context;
import android.content.IntentFilter;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import aw.com.events.OpusControllerEvent;
import aw.com.events.OpusMessageEvent;
import aw.com.events.PlaylistClickedEvent;
import aw.com.opusplayer.OpusReceiver;
import aw.com.opusplayer.R;
import aw.com.utils.Converters;
import top.oply.opuslib.OpusEvent;
import top.oply.opuslib.OpusPlayer;

/**
 * Created by Adit on 4/9/2017.
 */

public class OpusController {
    private Context context;
    private OpusPlayer opusPlayer;
    private OpusPlayerState playerState;
    private int progressPercentage;
    private String displayedDuration;
    private String displayedProgress;
    private String lastPlayedFile;

    public OpusPlayer getPlayer() {
        return opusPlayer;
    }

    public OpusPlayerState getPlayerState() {
        return playerState;
    }

    public int getProgressPercentage() {
        return progressPercentage;
    }

    public String getDisplayedDuration() {
        return displayedDuration;
    }

    public String getDisplayedProgress() {
        return displayedProgress;
    }

    public String getLastPlayedFile() {
        return lastPlayedFile;
    }

    public enum OpusPlayerState {
        NONE,
        PLAYING,
        PAUSED,
        FINISHED
    }

    public OpusController(Context context) {
        opusPlayer = OpusPlayer.getInstance();
        playerState = OpusPlayerState.NONE;

        opusPlayer.setEventSender(new OpusEvent(context));
        OpusReceiver receiver = new OpusReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(OpusEvent.ACTION_OPUS_UI_RECEIVER);
        context.registerReceiver(receiver, filter);

        this.context = context;
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnOpusMessageEvent(OpusMessageEvent event) {
        switch (event.getOpusEventCode()) {
            case OpusEvent.PLAYING_STARTED: {
                playerState = OpusPlayerState.PLAYING;
                displayedDuration = Converters.convertNumberToTimeDisplay((int)opusPlayer.getDuration());
                break;
            }
            case OpusEvent.PLAYING_FINISHED: {
                playerState = OpusPlayerState.FINISHED;
                break;
            }
            case OpusEvent.PLAYING_PAUSED: {
                playerState = OpusPlayerState.PAUSED;
                break;
            }
            case OpusEvent.PLAY_PROGRESS_UPDATE: {
                progressPercentage = (int) ((double) opusPlayer.getPosition() / opusPlayer.getDuration() * 100);
                displayedProgress = Converters.convertNumberToTimeDisplay((int) opusPlayer.getPosition());
                break;
            }
            case OpusEvent.PLAYING_FAILED: {
                Toast.makeText(context, context.getString(R.string.unable_play_opus_file), Toast.LENGTH_SHORT).show();
                break;
            }
        }

        OpusControllerEvent oce = new OpusControllerEvent();
        oce.setOpusEventCode(event.getOpusEventCode());
        EventBus.getDefault().post(oce);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnPlaylistClickedEvent(PlaylistClickedEvent event) {
        if (event.getSelectedFilePath()!=null) {
            getPlayer().toggle(event.getSelectedFilePath());
            lastPlayedFile = event.getSelectedFilePath();
        }
    }
}
