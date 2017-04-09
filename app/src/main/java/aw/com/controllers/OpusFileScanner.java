package aw.com.controllers;

import android.os.Environment;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import aw.com.events.OpusFileFoundEvent;

/**
 * Created by Adit on 4/9/2017.
 */

public class OpusFileScanner extends Thread {

    public void walkdir(File dir) {

        File[] listFile;
        listFile = dir.listFiles();

        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    walkdir(listFile[i]);
                } else {
                    if (listFile[i].getName().toLowerCase().endsWith(".opus")){

                        OpusFileFoundEvent offe = new OpusFileFoundEvent();
                        offe.setOpusFile(listFile[i]);
                        EventBus.getDefault().post(offe);
                    }
                }
            }
        }
    }

    public void recursiveScan(File f) {
        File[] file = f.listFiles();
        for (File ff : file) {
            if (ff.isDirectory()) recursiveScan(f);
            if (ff.isFile() && ff.getPath().endsWith(".opus")) {
                OpusFileFoundEvent offe = new OpusFileFoundEvent();
                offe.setOpusFile(ff);

                EventBus.getDefault().post(ff);
            }
        }
    }

    @Override
    public void run() {
        walkdir(Environment.getExternalStorageDirectory());
    }
}
