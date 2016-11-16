package kr.ac.koreatech.swkang.ch14publicdirectory;

import android.annotation.TargetApi;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MobileProgramming";
    MediaPlayer mediaPlayer;
    File musicDir;

    class Mp3Filter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mp3"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        File maid = new File(musicDir.getAbsolutePath() + "/maid.mp3");
        if(maid.isFile()) {
            if(maid.canRead()) {
                Log.i(TAG, "this is a file and can be read");
            } else {
                Log.i(TAG, "this is a file and cannot be read");
            }
        }

        if(musicDir.isDirectory()) {

            Log.i(TAG, "state: " + Environment.getExternalStorageState(musicDir));
            if(musicDir.canRead()) {
                Log.i(TAG, "this is directory and can be read");
            } else {
                Log.i(TAG, "this is directory but cannot be read");
                Log.i(TAG, "name and path: " + musicDir.getName() + ", " + musicDir.getAbsolutePath());
            }
        }

        //File files[];
        //int num = 0;

        //try {
            //files = musicDir.listFiles();
            //files = musicDir.listFiles(new Mp3Filter());

            //String f[] = musicDir.list();
            //int n = f.length;
            //Log.i(TAG, "music directory file: " + new Integer(n).toString());

            //num = files.length;
            //for(int i = 0; i < num; i++) {
            //    Log.i(TAG, "music directory file: " + files[i].getName());
            //}
        //} catch(SecurityException e) {
        //    e.printStackTrace();
        //}


        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            //mediaPlayer.setDataSource(files[0].getAbsolutePath());
            mediaPlayer.setDataSource(musicDir.getAbsolutePath() + "/maid.mp3");
        } catch(IOException e) {
            e.printStackTrace();
        }

        try {
            mediaPlayer.prepare();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //mediaPlayer.release();
        mediaPlayer = null;
        Log.i("MobileProgramming", "onDestory()");
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.start:
                mediaPlayer.start();
                break;
            case R.id.pause:
                mediaPlayer.pause();
                break;
        }
    }

}
