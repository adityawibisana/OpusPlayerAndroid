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
        messageEvent.setOpusEventCode(type);

        EventBus.getDefault().post(messageEvent);
    }
}
