package com.example.naunem.firstproject.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.models.User;
import com.example.naunem.firstproject.models.UserDatabase;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

/**
 * LayoutAddEditActivity class
 * Created by nhan on 21/03/2017.
 */

@EActivity(R.layout.activity_add_edit_user)
public class LayoutAddEditActivity extends AppCompatActivity {
    @ViewById(R.id.tvTitle)
    TextView mTvTitle;
    @ViewById(R.id.imgAvatar)
    ImageView mImgAvatar;
    @ViewById(R.id.edtName)
    EditText mEdtName;
    @ViewById(R.id.edtAge)
    EditText mEdtAge;
    @ViewById(R.id.edtGender)
    EditText mEdtGender;
    @ViewById(R.id.btnAdd)
    Button mBtnAddEdit;
    @ViewById(R.id.btnRemove)
    Button mBtnRemove;
    @Extra
    String mValue;
    @Extra
    User mUser;
    private UserDatabase mUserDatabase = new UserDatabase(this);
    private String mPath;

    @AfterViews
    void init() {
        if (!TextUtils.isEmpty(mValue) && mValue.equals("add")) {
            mTvTitle.setText(R.string.textView_text_add);
            mBtnAddEdit.setText(R.string.textView_text_add);
            mBtnRemove.setVisibility(View.GONE);
        } else {
            mTvTitle.setText(R.string.textView_text_edit);
            Picasso.with(this)
                    .load(mUser.getImage())
                    .fit()
                    .error(R.drawable.ic_boy)
                    .into(mImgAvatar);
            mEdtName.setText(mUser.getName());
            mEdtAge.setText(mUser.getAge());
            mEdtGender.setText(mUser.getGender());
            mBtnAddEdit.setText(R.string.textView_text_edit);
            mBtnRemove.setVisibility(View.VISIBLE);
        }
    }

    @Click(R.id.imgAvatar)
    void chooseImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Choose a photo"), 5);
    }

    @OnActivityResult(5)
    void onResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri mSelectedImageUri = data.getData();
            mPath = mSelectedImageUri.toString();
            Picasso.with(this)
                    .load(mPath)
                    .fit()
                    .into(mImgAvatar);
        }
    }

    @Click(R.id.btnAdd)
    void onClickButtonAdd() {
        if (mValue.equals("add")) { // add
            if (!TextUtils.isEmpty(mPath)) {
                User user = new User();
                user.setName(mEdtName.getText().toString());
                user.setAge(mEdtAge.getText().toString());
                user.setGender(mEdtGender.getText().toString());
                user.setImage(mPath);
                mUserDatabase.insertUser(user);
            }
        } else { // edit
            User user = new User(mUser.getId(), mPath, mEdtName.getText().toString(), mEdtAge.getText().toString(), mEdtGender.getText().toString());
            mUserDatabase.updateUser(user);
        }
        ListUserActivity_.intent(this).start();
        finish();
    }

    @Click(R.id.btnRemove)
    void removeUser() {
        mUserDatabase.deleteUser(mUser.getId());
        ListUserActivity_.intent(this).start();
        finish();
    }
}
