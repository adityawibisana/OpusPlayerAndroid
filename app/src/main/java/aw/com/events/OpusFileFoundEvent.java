package aw.com.events;

import java.io.File;

/**
 * Created by Adit on 4/9/2017.
 */

public class OpusFileFoundEvent {
    private File opusFile;

    public File getOpusFile() {
        return opusFile;
    }

    public void setOpusFile(File opusFile) {
        this.opusFile = opusFile;
    }
}
