package com.sxh.library;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxh.library.widge.RoundTextView;

public class CustomViewDialog extends BaseSSDialog {

    private Builder mBuilder;

    private CustomViewDialog(Builder builder) {
        mBuilder = builder;
        intiDialog();
    }


    @Override
    public void intiDialog() {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(mBuilder.mContext).inflate(R.layout.view_custom_dialog, null);
        TextView tv_title = view.findViewById(R.id.tv_dialog_title);
        FrameLayout fl_custom =  view.findViewById(R.id.fl_custom);
        RoundTextView dialog_buttom1 = view.findViewById(R.id.btn_sure);
        ImageView img_close = view.findViewById(R.id.img_close);

        tv_title.setText(TextUtils.isEmpty(mBuilder.mTitle) ? "提示" : mBuilder.mTitle);
        tv_title.setTextColor(mBuilder.titleColor);

        fl_custom.removeAllViews();
        fl_custom.addView(mBuilder.customView);

        if (TextUtils.isEmpty(mBuilder.btnText)) {
            dialog_buttom1.setVisibility(View.GONE);
        } else {
            dialog_buttom1.setVisibility(View.VISIBLE);
            dialog_buttom1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mBuilder.btnClick != null) {
                        mBuilder.btnClick.onClick(v);

                    } else {
                        dismiss();
                    }
                }
            });
            dialog_buttom1.setText(mBuilder.btnText);
            dialog_buttom1.setTextColor(mBuilder.btnColor);
        }

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mDialog = new Dialog(mBuilder.mContext, R.style.myDialog2);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(view);
        mDialog.setOnDismissListener(mBuilder.mDismissListener);
    }

    public static class Builder {
        private Context mContext;

        public Builder(Context context) {
            mContext = context;
        }

        private String mTitle;
        private int titleColor;

        public Builder setTitle(String title) {
            mTitle = title;
            this.titleColor = Color.parseColor("#333333");
            return this;
        }

        public Builder setTitleAndColor(String title, int titleColor) {
            mTitle = title;
            this.titleColor = titleColor;
            return this;
        }

        private View customView;

        public Builder setCustomView(View customView) {
            this.customView = customView;
            return this;
        }

        private String btnText;
        private int btnColor;
        private View.OnClickListener btnClick;

        public Builder setSureBtn(String btnText, int btnColor, View.OnClickListener btnClick) {
            this.btnText = btnText;
            this.btnColor = btnColor;
            this.btnClick = btnClick;
            return this;
        }

        private DialogInterface.OnDismissListener mDismissListener;

        public Builder setDismissListener(DialogInterface.OnDismissListener dismissListener) {
            mDismissListener = dismissListener;
            return this;
        }

        public CustomViewDialog build() {
            return  new CustomViewDialog(this);
        }
    }
}
