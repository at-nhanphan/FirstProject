package com.example.naunem.firstproject.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.naunem.firstproject.R;

import static com.example.naunem.firstproject.R.id.imgView;

/**
 * Created by naunem on 16/03/2017.
 */

public class CheckIntentActivity extends AppCompatActivity implements View.OnClickListener {

    Button mBtnCall;
    Button mBtnSms;
    Button mBtnSendMail;
    Button mBtnLaunchWeb;
    Button mBtnOpenChPlay;
    Button mBtnOpenGgMap;
    Button mBtnOpenGallery;
    Button mBtnOpenCamera;
    ImageView mImgView;

    public void init() {
        mBtnCall = (Button) findViewById(R.id.btnCall);
        mBtnSms = (Button) findViewById(R.id.btnSms);
        mBtnSendMail = (Button) findViewById(R.id.btnSendMail);
        mBtnLaunchWeb = (Button) findViewById(R.id.btnLaunchWeb);
        mBtnOpenChPlay = (Button) findViewById(R.id.btnOpenCHPlay);
        mBtnOpenGgMap = (Button) findViewById(R.id.btnOpenGgMap);
        mBtnOpenGallery = (Button) findViewById(R.id.btnShowImg);
        mBtnOpenCamera = (Button) findViewById(R.id.btnCamera);
        mImgView = (ImageView) findViewById(imgView);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_intent);

        init();

        mBtnCall.setOnClickListener(this);
        mBtnSms.setOnClickListener(this);
        mBtnSendMail.setOnClickListener(this);
        mBtnLaunchWeb.setOnClickListener(this);
        mBtnOpenChPlay.setOnClickListener(this);
        mBtnOpenGgMap.setOnClickListener(this);
        mBtnOpenGallery.setOnClickListener(this);
        mBtnOpenCamera.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCall:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0941717505"));
                startActivity(intent);
                break;
            case R.id.btnSms:
                String number = "0941717505";
                String content = "ahihi do ngoc";
                Uri uri = Uri.parse("smsto:" + number);
                Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                it.putExtra("sms_body", content);
                startActivity(it);
                break;
            case R.id.btnSendMail:
                String mailTo = "nhan.phan@asiantech.vn";
                Intent itSendMail = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + mailTo));
                itSendMail.putExtra(Intent.EXTRA_SUBJECT, "Test send mail");
                itSendMail.putExtra(Intent.EXTRA_TEXT, "My name is Nhan");
                startActivity(Intent.createChooser(itSendMail, "Send Email"));
                break;
            case R.id.btnLaunchWeb:
                Intent itLaunchWeb = new Intent(Intent.ACTION_VIEW);
                String url = "http://www.vnexpress.net";
                itLaunchWeb.setData(Uri.parse(url));
                startActivity(itLaunchWeb);
                break;
            case R.id.btnOpenCHPlay:
                final String appPackageChPlay = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageChPlay)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageChPlay)));
                }
                break;
            case R.id.btnOpenGgMap:
                final String appPackageGgMap = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageGgMap)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.streetview:cbll=46.414382,10.013988" + appPackageGgMap)));
                }
                break;
            case R.id.btnShowImg:
                Intent itShowImg = new Intent(Intent.ACTION_GET_CONTENT);
                itShowImg.setType("image/*");
                startActivityForResult(Intent.createChooser(itShowImg, "Select Picture"), 0);
                break;
            case R.id.btnCamera:
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 11);
                break;
        }
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK && null != data) {
            Uri selectedImageUri = data.getData();
            if (null != selectedImageUri) {
                mImgView.setImageURI(selectedImageUri);
            }
        }
        if (requestCode == 11 && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            mImgView.setImageBitmap(photo);
        }
    }
}
