package aw.com.opusplayer;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.IOException;

import aw.com.utils.FileUtilities;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        copySampleFiles(); 
    }

    private void copySampleFiles() {
        // we have 2 sample files here, we will copy those sample into local, because that's currently the only way for OpusLib play it

        String sample1Path = Environment.getExternalStorageDirectory()+"/OpusPlayer/sample1.opus";
        File file1 = new File(sample1Path);
        if(!file1.exists()) {
            try {
                FileUtilities.copyRAWtoSDCard(this, R.raw.sample1, sample1Path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String sample2Path = Environment.getExternalStorageDirectory()+"/OpusPlayer/sample2.opus";
        File file2 = new File(sample2Path);
        if(!file2.exists()) {
            try {
                FileUtilities.copyRAWtoSDCard(this, R.raw.sample2, sample2Path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
