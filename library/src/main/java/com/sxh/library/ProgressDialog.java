package com.sxh.library;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.sxh.library.widge.CircleProgressBar;

public class ProgressDialog extends BaseSSDialog {

    private ProgressDialog.Builder mBuilder;
    private CircleProgressBar mProgressBar;


    private ProgressDialog(Builder builder) {
        mBuilder = builder;
        intiDialog();
    }

    @Override
    public void intiDialog() {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(mBuilder.mContext).inflate(R.layout.view_progress_dialog, null);

        mProgressBar = view.findViewById(R.id.progressBar);
        mProgressBar.setProgressColor(mBuilder.mProgressColor);

        TextView tv_content = view.findViewById(R.id.tv_content);

        tv_content.setText(mBuilder.mContent);

        mDialog = new Dialog(mBuilder.mContext, R.style.myDialog2);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setCanceledOnTouchOutside(false); // 设置是否禁止按空白区域取消
        mDialog.setCancelable(false); // 设置是否禁止返回键取消
        mDialog.setContentView(view);
        mDialog.setOnDismissListener(mBuilder.mDismissListener);
        // 设置是否可以取消
        mDialog.setCanceledOnTouchOutside(mBuilder.mCancelable); // 设置是否禁止按空白区域取消
        mDialog.setCancelable(mBuilder.mCancelable); // 设置是否禁止返回键取消
        if (!mBuilder.mCancelable) {
            mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_BACK) {
                        return true; // 禁止搜索键取消
                    } else {
                        return false; // 默认返回 false
                    }
                }
            });
        }
    }

    @Override
    public void show() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    /**
     * 传百分之多少
     * @param percent 百分之多少
     */
    public void downloadProcess(int percent) {
        int progress = percent * 360 / 100;
        mProgressBar.update(progress, percent + "%");
    }

    public static class Builder  extends BaseBuilder{

        private String mContent;
        private int mProgressColor = Color.parseColor("#03A9F4");

        private DialogInterface.OnDismissListener mDismissListener;

        public Builder(Context context) {
            super(context, false);
        }
        public Builder(Context context, boolean cancelable) {
            super(context, cancelable);
        }

        public Builder setContent(String content) {
            mContent = content;
            return this;
        }

        public Builder setMainColor(int color) {
            mProgressColor = color;
            return this;
        }

        public Builder setDismissListener(DialogInterface.OnDismissListener dismissListener) {
            mDismissListener = dismissListener;
            return this;
        }
        public ProgressDialog build() {
            ProgressDialog dialog = new ProgressDialog(this);
            return dialog;
        }

    }

}
