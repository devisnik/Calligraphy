package uk.co.chrisjenx.calligraphy;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by chris on 19/12/2013
 * Project: Calligraphy
 */
class CalligraphyLayoutInflater extends LayoutInflater {

    private static final int[] R_styleable_TextView = new int[]{
        /* 0 */android.R.attr.fontFamily,
    };
    private static final int TextView_fontFamily = 0;

    private static final String[] sClassPrefixList = {
            "android.widget.",
            "android.webkit."
    };
    private static final String sTextViewClassName = TextView.class.getSimpleName();
    private static final String sButtonClassName = Button.class.getSimpleName();
    private final CalligraphyUtils calligraphyUtils;

    protected CalligraphyLayoutInflater(LayoutInflater original, Context context, CalligraphyUtils calligraphyUtils) {
        super(original, context);
        this.calligraphyUtils = calligraphyUtils;
    }

    /**
     * Override onCreateView to instantiate names that correspond to the
     * widgets known to the Widget factory. If we don't find a match,
     * call through to our super class.
     */
    @Override
    protected View onCreateView(String name, AttributeSet attrs) throws ClassNotFoundException {
        for (String prefix : sClassPrefixList) {
            try {
                View view = createView(name, prefix, attrs);
                if (view != null) {
                    textViewFilter(view, name, attrs);
                    return view;
                }
            } catch (ClassNotFoundException e) {
                // In this case we want to let the base class take a crack
                // at it.
            }
        }

        return super.onCreateView(name, attrs);
    }

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return new CalligraphyLayoutInflater(this, newContext, calligraphyUtils);
    }

    private final void textViewFilter(final View view, final String name, final AttributeSet attrs) {
        if (view == null) return;
        if (sTextViewClassName.equals(name) || sButtonClassName.equals(name)) {
            String textViewFont = pullFontFamily(attrs, view.getContext());
            calligraphyUtils.applyFontToTextView((TextView) view, textViewFont);
        }
    }

    /**
     * Pulls out the fontFamily from the attributes to see if the user has set a custom font
     *
     * @return
     */
    private String pullFontFamily(AttributeSet attrs, Context context) {
        if (attrs == null) return null;
        final TypedArray a = context.obtainStyledAttributes(attrs, R_styleable_TextView);
        // Use the thickness specified, zero being the default
        final String fontFamily = a.getString(TextView_fontFamily);
        a.recycle();

        return fontFamily;
    }

}
