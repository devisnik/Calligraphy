package uk.co.chrisjenx.calligraphy;

import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by chris on 20/12/2013
 * Project: Calligraphy
 */
public final class CalligraphyUtils {

    private final TypefaceUtils typefaceUtils;
    private final String defaultFont;

    public boolean applyFontToTextView(final TextView textView, final Typeface typeface) {
        if (textView == null || typeface == null) {
            return false;
        }
        textView.setTypeface(typeface);
        return true;
    }

    private boolean applyFont(final TextView textView, final String filePath) {
        if (textView == null || filePath == null) {
            return false;
        }
        final Typeface typeface = typefaceUtils.load(filePath);
        return applyFontToTextView(textView, typeface);
    }

    public void applyDefaultFontToTextView(final TextView textView) {
        if (textView == null) {
            return;
        }
        applyFontToTextView(textView, defaultFont);
    }

    public void applyFontToTextView(final TextView textView, final String preferredFont) {
        if (textView == null) {
            return;
        }
        if (!applyFont(textView, preferredFont)) {
            applyDefaultFontToTextView(textView);
        }
    }

    CalligraphyUtils(TypefaceUtils typefaceUtils, String defaultFont) {
        if (typefaceUtils == null) {
            throw new IllegalArgumentException("typefaceUtils must not be null!");
        }
        this.typefaceUtils = typefaceUtils;
        this.defaultFont = defaultFont;
    }
}
