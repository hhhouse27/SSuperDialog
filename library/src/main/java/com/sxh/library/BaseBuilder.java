package com.sxh.library;

import android.content.Context;

public class BaseBuilder {
    protected Context mContext;
    protected boolean mCancelable;
    public BaseBuilder(Context context, boolean cancelable) {
        mContext = context;
        mCancelable = cancelable;
    }

}
