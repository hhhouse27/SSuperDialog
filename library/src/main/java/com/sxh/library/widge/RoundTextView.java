package com.sxh.library.widge;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;

import com.sxh.library.R;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * 支持圆角的TextView
 * Created by stephen on 2017/12/18.
 */
public class RoundTextView extends AppCompatTextView {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public RoundTextView(Context context) {
        this(context, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public RoundTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public RoundTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundTextView, defStyleAttr, 0);

        if (attributes != null) {

            int rtvBorderWidth = attributes.getDimensionPixelSize(R.styleable.RoundTextView_rtvBorderWidth, 0);
            int rtvBorderColor = attributes.getColor(R.styleable.RoundTextView_rtvBorderColor, Color.BLACK);
            float rtvRadius = attributes.getDimension(R.styleable.RoundTextView_rtvRadius, 0);
            int rtvBgColor = attributes.getColor(R.styleable.RoundTextView_rtvBgColor, Color.WHITE);
            attributes.recycle();

            GradientDrawable gd = new GradientDrawable();//创建drawable
            gd.setColor(rtvBgColor);
            gd.setCornerRadius(rtvRadius);
            if (rtvBorderWidth > 0) {
                gd.setStroke(rtvBorderWidth, rtvBorderColor);
            }

            this.setBackground(gd);
        }
    }

    public void setBackgroungColor(@ColorInt int color) {
        GradientDrawable myGrad = (GradientDrawable) getBackground();
        myGrad.setColor(color);
    }
}