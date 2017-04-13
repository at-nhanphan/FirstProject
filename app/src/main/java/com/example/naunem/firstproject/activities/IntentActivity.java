package com.example.naunem.firstproject.activities;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.naunem.firstproject.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

/**
 * IntentActivity class
 * Created by naunem on 16/03/2017.
 */

@EActivity(R.layout.activity_check_intent)
public class IntentActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0;
    public static final int REQUEST_CODE_CAMERA = 11;

    @Click(R.id.btnCall)
    void clickCall() {
        Intent itCall = new Intent(Intent.ACTION_DIAL);
        itCall.setData(Uri.parse("tel:0941717505"));
        startActivity(itCall);
    }

    @Click(R.id.btnSms)
    void clickSms() {
        String number = "0941717505";
        String content = "ahihi do ngoc";
        Uri uri = Uri.parse("smsto:" + number);
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", content);
        startActivity(it);
    }

    @Click(R.id.btnSendMail)
    void clickSendMail() {
        String mailTo = "nhan.phan@asiantech.vn";
        Intent itSendMail = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + mailTo));
        itSendMail.putExtra(Intent.EXTRA_SUBJECT, "Test send mail");
        itSendMail.putExtra(Intent.EXTRA_TEXT, "My name is Nhan");
        startActivity(Intent.createChooser(itSendMail, "Send Email"));
    }

    @Click(R.id.btnLaunchWeb)
    void clickLaunchWeb() {
        Intent itLaunchWeb = new Intent(Intent.ACTION_VIEW);
        String url = "http://www.vnexpress.net";
        itLaunchWeb.setData(Uri.parse(url));
        startActivity(itLaunchWeb);
    }

    @Click(R.id.btnOpenCHPlay)
    void clickOpenCHPlay() {
        final String appPackageChPlay = getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageChPlay)));
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageChPlay)));
        }
    }

    @Click(R.id.btnOpenGgMap)
    void clickOpenGgMap() {
        final String appPackageGgMap = getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageGgMap)));
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.streetview:cbll=46.414382,10.013988" + appPackageGgMap)));
        }
    }

    @Click(R.id.btnShowImg)
    void clickShowImg() {
        Intent itShowImg = new Intent(Intent.ACTION_GET_CONTENT);
        itShowImg.setType("image/*");
        startActivityForResult(Intent.createChooser(itShowImg, "Select Picture"), 0);
    }

    @Click(R.id.btnCamera)
    void clickCamera() {
        Intent itCamera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(itCamera, 11);
    }

    @ViewById(R.id.imgView)
    ImageView mImgView;

    @OnActivityResult(REQUEST_CODE)
    void onResult(int resultCode, Intent data) {
        // Gallery
        if (resultCode == RESULT_OK && null != data) {
            Uri selectedImageUri = data.getData();
            if (null != selectedImageUri) {
                mImgView.setImageURI(selectedImageUri);
            }
        }
    }

    @OnActivityResult(REQUEST_CODE_CAMERA)
    void onResultCamera(int resultCode, Intent data) {
        // Camera
        if (resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            mImgView.setImageBitmap(photo);
        }
    }
}
