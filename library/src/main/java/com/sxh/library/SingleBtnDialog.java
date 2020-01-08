package com.sxh.library;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxh.library.widge.RoundTextView;

public class SingleBtnDialog extends BaseSSDialog {

    private Builder mBuilder;

    public SingleBtnDialog(Builder builder) {
        mBuilder = builder;
        intiDialog();
    }


    @Override
    public void intiDialog() {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(mBuilder.mContext).inflate(R.layout.view_single_dialog, null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_dialog_title);
        TextView tv_content = (TextView) view.findViewById(R.id.tv_dialog_content);
        final EditText editText = (EditText) view.findViewById(R.id.editText);
        RoundTextView dialog_buttom1 = (RoundTextView) view.findViewById(R.id.btn_sure);
        ImageView img_close = (ImageView) view.findViewById(R.id.img_close);

        tv_title.setText(TextUtils.isEmpty(mBuilder.mTitle) ? "提示" : mBuilder.mTitle);
        tv_title.setTextColor(mBuilder.titleColor);
        if (TextUtils.isEmpty(mBuilder.mContent)) {
            tv_content.setVisibility(View.GONE);
        } else {
            tv_content.setText(mBuilder.mContent);
            if (mBuilder.contentClick != null) {
                tv_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBuilder.contentClick.onClick(v);
                    }
                });
            }
        }

        if (mBuilder.isShowETV) {
            editText.setVisibility(View.VISIBLE);
            editText.setHint(mBuilder.editTextHint == null ? "" : mBuilder.editTextHint);
        } else {
            editText.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(mBuilder.btnText)) {
            dialog_buttom1.setVisibility(View.GONE);
        } else {
            dialog_buttom1.setVisibility(View.VISIBLE);
            dialog_buttom1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mBuilder.btnClick != null) {
                        mBuilder.btnClick.onClick(v, editText.getText().toString());
                        if (mBuilder.btnClick.isClose) {
                            dismiss();
                        }
                    } else {
                        dismiss();
                    }
                }
            });
            dialog_buttom1.setText(mBuilder.btnText);
            dialog_buttom1.setTextColor(mBuilder.btnColor);
            dialog_buttom1.setBackgroungColor(mBuilder.btnBgcolor);
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


    public static class Builder  extends BaseBuilder{

        public Builder(Context context) {
            super(context, false);
        }
        public Builder(Context context, boolean cancelable) {
            super(context, cancelable);
        }

        private String mTitle;
        private int titleColor;
        private int btnBgcolor = Color.parseColor("#FC6565");

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

        private SpannableString mContent;
        private View.OnClickListener contentClick;

        public Builder setContent(SpannableString content) {
            mContent = content;
            return this;
        }

        public Builder setContentALL(SpannableString content, View.OnClickListener contentClick) {
            mContent = content;
            this.contentClick = contentClick;
            return this;
        }


        private String btnText;
        private int btnColor;
        private SureBtnClick btnClick;

        public Builder setSureBtn(String btnText, int btnColor, SureBtnClick btnClick) {
            this.btnText = btnText;
            this.btnColor = btnColor;
            this.btnClick = btnClick;
            return this;
        }

        public Builder setSureBtnBg(int bgColor){
            this.btnBgcolor = bgColor;
            return this;
        }


        private boolean isShowETV;
        private String editTextHint;

        public Builder setShowETV(boolean showETV, String editTextHint) {
            isShowETV = showETV;
            this.editTextHint = editTextHint;
            return this;
        }

        private DialogInterface.OnDismissListener mDismissListener;

        public Builder setDismissListener(DialogInterface.OnDismissListener dismissListener) {
            mDismissListener = dismissListener;
            return this;
        }

        public SingleBtnDialog build() {
            SingleBtnDialog dialog = new SingleBtnDialog(this);
            return dialog;
        }
    }

    public static abstract class SureBtnClick {
        public boolean isClose;

        public SureBtnClick(boolean isClose) {
            this.isClose = isClose;
        }

        public abstract void onClick(View v, String editTextStr);
    }
}
