package com.qhyccd.expandTextView;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichTextConfig;
import com.zzhoujay.richtext.ig.DefaultImageGetter;

/**
 * @author tx
 * @date 2018/9/11
 */
public class MyImgGetter extends DefaultImageGetter {

    @Override
    public Drawable getDrawable(ImageHolder holder, RichTextConfig config, TextView textView) {
        return super.getDrawable(holder, config, textView);

    }
}
