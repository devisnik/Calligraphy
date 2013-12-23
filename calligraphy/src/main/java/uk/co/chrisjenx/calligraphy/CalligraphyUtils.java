package uk.co.chrisjenx.calligraphy;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by chris on 20/12/2013
 * Project: Calligraphy
 */
public final class CalligraphyUtils {

    private static final int[] R_styleable_TextView = new int[]{
        /* 0 */android.R.attr.fontFamily,
    };
    private static final int TextView_fontFamily = 0;

    private final Context context;
    private final TypefaceUtils typefaceUtils;

    public boolean applyFontToTextView(final TextView textView, final Typeface typeface) {
        if (textView == null || typeface == null) return false;
        textView.setTypeface(typeface);
        return true;
    }

    public boolean applyFontToTextView(final TextView textView, final String filePath) {
        if (textView == null) return false;
        final Typeface typeface = typefaceUtils.load(filePath);
        return applyFontToTextView(textView, typeface);
    }

    public void applyFontToTextView(final TextView textView, final CalligraphyConfig config) {
        if (textView == null || config == null) return;
        if (!config.isFontSet()) return;
        applyFontToTextView(textView, config.getFontPath());
    }

    public void applyFontToTextView(final TextView textView, final CalligraphyConfig config, final String textViewFont) {
        if (textView == null || config == null) return;
        if (!TextUtils.isEmpty(textViewFont) && applyFontToTextView(textView, textViewFont)) {
            return;
        }
        applyFontToTextView(textView, config);
    }

    /**
     * Pulls out the fontFamily from the attributes to see if the user has set a custom font
     *
     * @return
     */
    String pullFontFamily(AttributeSet attrs) {
        if (attrs == null) return null;
        final TypedArray a = context.obtainStyledAttributes(attrs, R_styleable_TextView);
        // Use the thickness specified, zero being the default
        final String fontFamily = a.getString(TextView_fontFamily);
        a.recycle();

        return fontFamily;
    }

    CalligraphyUtils(Context context, TypefaceUtils typefaceUtils) {
        if (context == null)
            throw new IllegalArgumentException("context must not be null!");
        this.context = context;
        this.typefaceUtils = typefaceUtils;
    }
}
