package com.example.naunem.firstproject.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naunem.firstproject.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

/**
 * This class used to demo AsyncTask
 * Created by naunem on 04/04/2017.
 */

public class AsyncTaskActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvProgress;
    private ImageView mImgDownload;
    private ProgressDialog mProgressDialog;
    private int randNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asystask);

        mTvProgress = (TextView) findViewById(R.id.tvProgress);
        Button mBtnDownload = (Button) findViewById(R.id.btnDownload);
        mImgDownload = (ImageView) findViewById(R.id.imgDownload);
        mBtnDownload.setOnClickListener(this);
        mProgressDialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        String IMAGE_URL = "https://cdn.theatlantic.com/assets/media/img/photo/2015/11/images-from-the-2016-sony-world-pho/s01_130921474920553591/main_900.jpg?1448476701";
        new MyAsyncTask().execute(IMAGE_URL);
    }

    private class MyAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mTvProgress.setText("Downloading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setProgress(0);
            mProgressDialog.setCancelable(true);
            mProgressDialog.setMessage("Downloading...");
            mProgressDialog.setMax(100);
            mProgressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            int count;
            Random random = new Random();
            randNumber = random.nextInt(1000);
            try {
                URL url = new URL(params[0]);
                URLConnection connection = url.openConnection();
                int lengthOfFile = connection.getContentLength();
                InputStream inputStream = new BufferedInputStream(url.openStream(), 8192);
                OutputStream outputStream = new FileOutputStream("/sdcard/picture" + String.valueOf(randNumber) + ".jpg");

                byte[] datas = new byte[1024];
                long total = 0;
                while ((count = inputStream.read(datas)) != -1) {
                    total += count;
                    publishProgress(String.valueOf(total * 100 / lengthOfFile));
                    outputStream.write(datas, 0, count);
                }
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            } catch (MalformedURLException e) {
                Log.e("Error", "doInBackground: ", e);
            } catch (IOException e) {
                Log.e("IOException", "doInBackground: ", e);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            mProgressDialog.setProgress(Integer.parseInt(values[0]));
        }

        @Override
        protected void onPostExecute(String url) {
            super.onPostExecute(url);
            mProgressDialog.dismiss();
            mTvProgress.setText("Done!");
            String imagePath = Environment.getExternalStorageDirectory().toString() + "/picture" + String.valueOf(randNumber) + ".jpg";
            Log.d("ggggg", "onPostExecute: " + imagePath);
            mImgDownload.setImageDrawable(Drawable.createFromPath(imagePath));
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(imagePath))));
            Toast.makeText(AsyncTaskActivity.this, "Download Complete", Toast.LENGTH_SHORT).show();
        }
    }
}
