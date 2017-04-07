package aw.com.opusplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import aw.com.events.OpusMessageEvent;
import top.oply.opuslib.OpusEvent;

/**
 * Created by Adit on 4/7/2017.
 */

public class OpusReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        int type = bundle.getInt(OpusEvent.EVENT_TYPE, 0);

        OpusMessageEvent messageEvent = new OpusMessageEvent();


        switch (type) {
            case OpusEvent.CONVERT_FINISHED:
                break;
            case OpusEvent.CONVERT_FAILED:
                break;
            case OpusEvent.CONVERT_STARTED:
                break;
            case OpusEvent.RECORD_FAILED:
                break;
            case OpusEvent.RECORD_FINISHED:
                break;
            case OpusEvent.RECORD_STARTED:
                break;
            case OpusEvent.RECORD_PROGRESS_UPDATE:
                break;
            case OpusEvent.PLAY_PROGRESS_UPDATE:
                break;
            case OpusEvent.PLAY_GET_AUDIO_TRACK_INFO:
                break;
            case OpusEvent.PLAYING_FAILED:
                break;
            case OpusEvent.PLAYING_FINISHED:
                break;
            case OpusEvent.PLAYING_PAUSED:
                break;
            case OpusEvent.PLAYING_STARTED:
                messageEvent.setOpusEventCode(OpusEvent.PLAYING_STARTED);
                break;
            default:
                Log.d("OpusReceiver onReceive", intent.toString() + "Invalid request,discarded");
                break;
        }

        EventBus.getDefault().post(messageEvent);
    }
}
