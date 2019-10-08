package com.sxh.library;


import android.app.Dialog;

public abstract class BaseSSDialog {

    public Dialog mDialog;

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

    public abstract void intiDialog();
}
