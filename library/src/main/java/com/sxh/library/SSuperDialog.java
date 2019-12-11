package com.sxh.library;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class SSuperDialog {

    public static final int TYPE_CENTER = 100;
    public static final int TYPE_BOTTOM = 111;
    public static final int TYPE_TOP = 222;


    private Dialog mDialog;
    private Builder mBuilder;

    private SSuperDialog(Builder builder) {
        mBuilder = builder;
        intiDialog();
    }

    private void intiDialog() {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(mBuilder.mContext).inflate(R.layout.view_dialog, null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_dialog_title);
        TextView tv_content = (TextView) view.findViewById(R.id.tv_dialog_content);
        TextView dialog_buttom1 = (TextView) view.findViewById(R.id.tv_dialog_buttom1);
        TextView dialog_buttom2 = (TextView) view.findViewById(R.id.tv_dialog_buttom2);
        View line_dialog_control = view.findViewById(R.id.line_dialog_control);

        tv_title.setText(TextUtils.isEmpty(mBuilder.mTitle) ? "提示" : mBuilder.mTitle);
        tv_title.setTextColor(mBuilder.titleColor);
        if (TextUtils.isEmpty(mBuilder.mContent)) {
            tv_content.setVisibility(View.GONE);
        } else {
            tv_content.setText(mBuilder.mContent);
            tv_content.setTextColor(mBuilder.contentColor);
        }

        if (TextUtils.isEmpty(mBuilder.leftText)) {
            line_dialog_control.setVisibility(View.GONE);
            dialog_buttom1.setVisibility(View.GONE);
        } else {
            line_dialog_control.setVisibility(View.VISIBLE);
            dialog_buttom1.setVisibility(View.VISIBLE);
            dialog_buttom1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mBuilder.leftClick != null) {
                        mBuilder.leftClick.onClick(v);
                    }
                    dismiss();
                }
            });
            dialog_buttom1.setText(mBuilder.leftText);
            dialog_buttom1.setTextColor(mBuilder.leftColor);
        }

        if (TextUtils.isEmpty(mBuilder.rightText)) {
            line_dialog_control.setVisibility(View.GONE);
            dialog_buttom2.setVisibility(View.GONE);
        } else {
            line_dialog_control.setVisibility(View.VISIBLE);
            dialog_buttom2.setVisibility(View.VISIBLE);
            dialog_buttom2.setText(mBuilder.rightText);
            dialog_buttom2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mBuilder.rightClick != null) {
                        mBuilder.rightClick.onClick(v);
                    }
                    dismiss();
                }
            });
            dialog_buttom2.setTextColor(mBuilder.rightColor);
        }

        mDialog = new Dialog(mBuilder.mContext, R.style.myDialog2);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
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

    public void show() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    public void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }


    public static class Builder  extends BaseBuilder{


        private int mStyle;

        public Builder(Context context, int style) {
            super(context, false);
            mStyle = style;
        }
        public Builder(Context context) {
            super(context, false);
            mStyle = TYPE_CENTER;
        }
        public Builder(Context context, boolean cancelable) {
            super(context, cancelable);
            mStyle = TYPE_CENTER;
        }
        public Builder(Context context, boolean cancelable, int style) {
            super(context, cancelable);
            mStyle = style;
        }

        private String mTitle;
        private int titleColor;

        private String mContent;
        private int contentColor;

        private String leftText;
        private int leftColor;

        private String rightText;
        private int rightColor;

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

        public Builder setContent(String content) {
            mContent = content;
            this.contentColor = Color.parseColor("#666666");
            return this;
        }

        public Builder setContentAndColor(String content, int contentColor) {
            mContent = content;
            this.contentColor = contentColor;
            return this;
        }

        public Builder setLeftBtn(String leftText, int leftColor, View.OnClickListener leftClick) {
            this.leftText = leftText;
            this.leftColor = leftColor;
            this.leftClick = leftClick;
            return this;
        }

        public Builder setRightBtn(String rightText, int rightColor, View.OnClickListener rightClick) {
            this.rightText = rightText;
            this.rightColor = rightColor;
            this.rightClick = rightClick;
            return this;
        }

        private View.OnClickListener leftClick;
        private View.OnClickListener rightClick;

        private DialogInterface.OnDismissListener mDismissListener;

        public Builder setDismissListener(DialogInterface.OnDismissListener dismissListener) {
            mDismissListener = dismissListener;
            return this;
        }

        public SSuperDialog build() {
            SSuperDialog dialog = new SSuperDialog(this);
            return dialog;
        }
    }
}
