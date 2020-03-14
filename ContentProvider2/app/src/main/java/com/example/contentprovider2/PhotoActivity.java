package com.example.contentprovider2;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author admin
 */
public class PhotoActivity extends AppCompatActivity {
    private static final String TAG = "PhotoActivity";
    private int LOAD_ID = 0x0085;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ContentResolver contentResolver = getContentResolver();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor query = contentResolver.query(uri, null, null, null, null, null);
        String[] columnNames = query.getColumnNames();
        while (query.moveToNext()) {
            Log.d(TAG, "----------------------------");
            for (String columnName : columnNames) {
                Log.d(TAG, columnName + " ==== " + query.getString(query.getColumnIndex(columnName)));
            }
            Log.d(TAG, "----------------------------");
        }
        query.close();

    }

   /* private void loadManger() {
        LoaderManager loaderManager = LoaderManager.getInstance(this);
        loaderManager.initLoader(LOAD_ID, null, new LoaderManager.LoaderCallbacks<Cursor>() {

            @NonNull
            @Override
            public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
                if (id == LOAD_ID) {
                    return new CursorLoader(PhotoActivity.this,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            new String[] {"_data","_display_name","date_added"},null,null,
                            "date_added DESC"
                            );
                }
                return null;
            }

            @Override
            public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
                if (cursor != null) {
                    String[] culumnsNames = cursor.getColumnNames();

                    while (cursor.moveToNext()) {
                        Log.d(TAG,"----------------------------");
                        for(String columnName : culumnsNames) {
                            Log.d(TAG,columnName + " ==== " + cursor.getString(cursor.getColumnIndex(columnName)));
                        }
                        Log.d(TAG,"----------------------------");
                    }
                    cursor.close();

                }
            }

            @Override
            public void onLoaderReset(@NonNull Loader<Cursor> loader) {

            }
        });
    }*/
}