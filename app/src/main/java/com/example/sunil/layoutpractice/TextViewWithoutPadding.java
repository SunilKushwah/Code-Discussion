package com.example.sunil.layoutpractice;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.TextView;
import com.example.sunil.layoutpractice.FontCache;

/**
 * Created by Sunil on 23-03-2017.
 */

public class TextViewWithoutPadding extends android.support.v7.widget.AppCompatTextView{

        public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

        private final Paint mPaint = new Paint();

        private final Rect mBounds = new Rect();

        public TextViewWithoutPadding(Context context) {
            super(context);
        }

        public TextViewWithoutPadding(Context context, AttributeSet attrs) {
            super(context, attrs);
            applyCustomFont(context, attrs);
        }

        public TextViewWithoutPadding(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            applyCustomFont(context, attrs);
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            final String text = calculateTextParams();

            final int left = mBounds.left;
            final int bottom = mBounds.bottom;
            mBounds.offset(mBounds.left, -mBounds.top);
            mPaint.setAntiAlias(true);
            mPaint.setColor(getCurrentTextColor());
            canvas.drawText(text, -left, mBounds.bottom - bottom, mPaint);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            calculateTextParams();
            setMeasuredDimension(mBounds.width() + 1, -mBounds.top + 1);
        }

        private String calculateTextParams() {
            final String text = getText().toString();
            final int textLength = text.length();
            mPaint.setTextSize(getTextSize());
            mPaint.getTextBounds(text, 0, textLength, mBounds);
            if (textLength == 0) {
                mBounds.right = mBounds.left;
            }
            return text;
        }
    private void applyCustomFont(Context context, AttributeSet attrs) {
        TypedArray attributeArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.CustomFontTextView);

        String fontName = attributeArray.getString(R.styleable.CustomFontTextView_font);
        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);

        Typeface customFont = selectTypeface(context, fontName, textStyle);
        setTypeface(customFont);

        attributeArray.recycle();
    }
    private Typeface selectTypeface(Context context, String fontName, int textStyle) {
        if (fontName.contentEquals(context.getString(R.string.font_name_fontawesome))) {
            return FontCache.getTypeface("fontawesome.ttf", context);
        } else if (fontName.contentEquals(context.getString(R.string.font_name_source_sans_pro))) {
              /*
              information about the TextView textStyle:
              http://developer.android.com/reference/android/R.styleable.html#TextView_textStyle
              */
            switch (textStyle) {
                case Typeface.BOLD: // bold
                    return FontCache.getTypeface("HVD Fonts - BrandonText-Light.otf", context);

                case Typeface.ITALIC: // italic
                    return FontCache.getTypeface("SourceSansPro-Italic.ttf", context);

                case Typeface.BOLD_ITALIC: // bold italic
                    return FontCache.getTypeface("SourceSansPro-BoldItalic.ttf", context);

                case Typeface.NORMAL: // regular
                default:
                    return FontCache.getTypeface("SourceSansPro-Regular.ttf", context);
            }
        } else {
            // no matching font found
            // return null so Android just uses the standard font (Roboto)
            return null;
        }
    }
}
