package aw.com.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Adit on 4/6/2017.
 */

public class FileUtilities {

    public static void copyRAWtoSDCard(Context context, int id, String path) throws IOException {
        InputStream in = context.getResources().openRawResource(id);

        File folder = new File(Environment.getExternalStorageDirectory() + "/OpusPlayer");
        if(!folder.exists()) {
            folder.mkdir();
        }


        FileOutputStream out = new FileOutputStream(path);
        byte[] buff = new byte[1024];
        int read = 0;
        try {
            while ((read = in.read(buff)) > 0) {
                out.write(buff, 0, read);
            }
        } finally {
            in.close();
            out.close();
        }
    }
}
