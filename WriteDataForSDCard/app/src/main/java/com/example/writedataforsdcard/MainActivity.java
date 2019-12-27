package com.example.writedataforsdcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void writeMethod(View view) {
        File filePath = Environment.getExternalStorageDirectory();
//        File filePath = new File("/storage/sdcard");
        File file = new File(filePath,"cljz.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write("cljz.com".getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
