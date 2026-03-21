package com.sanbot.demo.app.common.picture.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.sanbot.demo.R;
import com.sanbot.demo.app.BaseActivity;
import com.sanbot.lib.view.ZoomImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;


/**
 * 本地图片浏览
 *
 * @author youngbin
 */
public class PictureDetailActivity extends BaseActivity implements ZoomImageView.OnPhotoTapListener, ZoomImageView.OnViewTapListener {

    private static final String TAG = "PictureDetailActivity";

    private ZoomImageView mImageView;
    private ProgressBar mProgressBar;
    private String mPath;

    public static void startActivity(Activity activity, String path) {
        Intent intent = new Intent(activity, PictureDetailActivity.class);
        intent.putExtra("path", path);
        activity.startActivity(intent);
    }


    @Override
    protected void initView() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_picture_detail);
        mImageView = (ZoomImageView) findViewById(R.id.picture_detail_iv);
        mProgressBar = (ProgressBar) findViewById(R.id.picture_detail_pb);
    }

    @Override
    protected void initListener() {
        mImageView.setOnPhotoTapListener(this);
        mImageView.setOnViewTapListener(this);
    }

    @Override
    protected void initReceiver() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        mPath = intent.getStringExtra("path");

        Picasso.with(this).load(new File(mPath)).into(mImageView, new Callback() {
            @Override
            public void onSuccess() {
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                mProgressBar.setVisibility(View.GONE);
                mImageView.setImageResource(R.mipmap.ic_launcher);
            }
        });
    }

    @Override
    protected void saveData(Bundle outState) {

    }

    @Override
    public void onPhotoTap(View view, float x, float y) {
        finish();
    }

    @Override
    public void onViewTap(View view, float x, float y) {
        finish();
    }

}
