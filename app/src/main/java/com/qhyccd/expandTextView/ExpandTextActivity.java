package com.qhyccd.expandTextView;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.daquexian.flexiblerichtextview.FlexibleRichTextView;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener;
import com.qhyccd.R;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.RichTextConfig;
import com.zzhoujay.richtext.callback.ImageGetter;
import com.zzhoujay.richtext.callback.ImageLoadNotify;
import com.zzhoujay.richtext.ig.DefaultImageGetter;

import org.scilab.forge.jlatexmath.core.AjLatexMath;
import org.sufficientlysecure.htmltextview.HtmlAssetsImageGetter;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * @author tx
 * @date 2018/9/4
 */
public class ExpandTextActivity extends AppCompatActivity {
    private TextView tv;
    private ImageView iv;
    private RequestBuilder<PictureDrawable> requestBuilder;
    private FlexibleRichTextView richTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_text);
        AjLatexMath.init(this);
        RichText.initCacheDir(this);
        tv = findViewById(R.id.html_text);
        iv = findViewById(R.id.iv);
        richTv = findViewById(R.id.rich_tv);

        String ww = "\"<p><span>二元函数 <img style=\"width:auto;height:auto ;\" src=\"http://equation.kaoyanvip.cn/?tex=z%20%3D%20xy(3-x-y)%20\" alt=\"z = xy(3-x-y) \" class=\"formula-tex\"></span>的极值点是</p>\"";

        RichText.fromHtml(ww)
                .imageGetter(new MyImgGetter())
                .into(tv);

//        Glide.with(this).load("http://oss.kaoyanvip.cn/test/ZKDmMZ9nwpgvsVML9wVxpj.jpeg").into(iv);

        requestBuilder = Glide.with(this)
                .as(PictureDrawable.class)
                .transition(withCrossFade())
                .listener(new SvgSoftwareLayerSetter());

    }

    @Override
    protected void onStart() {
        super.onStart();
        String url = "http://equation.kaoyanvip.cn/?mml=%3Cmath%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F1998%2FMath%2FMathML%22%3E%3Cmi%3Eu%3C%2Fmi%3E%3Cmo%3E%2C%3C%2Fmo%3E%3Cmsup%3E%3Cmi%3Eo%3C%2Fmi%3E%3Cmn%3E2%3C%2Fmn%3E%3C%2Fmsup%3E%3C%2Fmath%3E";

        String url2 = "http://www.clker.com/cliparts/u/Z/2/b/a/6/android-toy-h.svg";
        Uri uri = Uri.parse(url);
        requestBuilder.load(uri).into(iv);

        GlideToVectorYou
                .init()
                .with(this)
                .load(Uri.parse(url), iv);
        String richText = "<img src=\"http://equation.kaoyanvip.cn/?mml=%3Cmath%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F1998%2FMath%2FMathML%22%3E%3Cmn%3E12%3C%2Fmn%3E%3Cmsqrt%2F%3E%3Cmn%3E45%3C%2Fmn%3E%3Cmfenced%20open%3D%22%7B%22%20close%3D%22%7D%22%3E%3Cmrow%3E%3Cmo%3E%26%23x2208%3B%3C%2Fmo%3E%3Cmo%3E%26%23x221E%3B%3C%2Fmo%3E%3Cmi%20mathvariant%3D%22normal%22%3E%26%23x3C0%3B%3C%2Fmi%3E%3Cmfenced%20open%3D%22%5B%22%20close%3D%22%5D%22%3E%3Cmfenced%20open%3D%22%7B%22%20close%3D%22%7D%22%3E%3Cmfenced%20open%3D%22%7C%22%20close%3D%22%7C%22%3E%3Cmfenced%3E%3Cmsup%3E%3Cmsub%3E%3Cmroot%3E%3Cmfrac%3E%3Cmrow%2F%3E%3Cmrow%2F%3E%3C%2Fmfrac%3E%3Cmrow%2F%3E%3C%2Fmroot%3E%3Cmrow%2F%3E%3C%2Fmsub%3E%3Cmrow%2F%3E%3C%2Fmsup%3E%3C%2Fmfenced%3E%3C%2Fmfenced%3E%3C%2Fmfenced%3E%3C%2Fmfenced%3E%3C%2Fmrow%3E%3C%2Fmfenced%3E%3C%2Fmath%3E\" alt=\"<math xmlns=&quot;http://www.w3.org/1998/Math/MathML&quot;><mn>12</mn><msqrt></msqrt><mn>45</mn><mfenced open=&quot;{&quot; close=&quot;}&quot;><mrow><mo>&amp;#x2208;</mo><mo>&amp;#x221E;</mo><mi mathvariant=&quot;normal&quot;>&amp;#x3C0;</mi><mfenced open=&quot;[&quot; close=&quot;]&quot;><mfenced open=&quot;{&quot; close=&quot;}&quot;><mfenced open=&quot;|&quot; close=&quot;|&quot;><mfenced><msup><msub><mroot><mfrac><mrow></mrow><mrow></mrow></mfrac><mrow></mrow></mroot><mrow></mrow></msub><mrow></mrow></msup></mfenced></mfenced></mfenced></mfenced></mrow></mfenced></math>\" class=\"wiris-tex\">";
        String q = "http://equation.kaoyanvip.cn/?mml=%3Cmath%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F1998%2FMath%2FMathML%22%3E%3Cmfrac%3E%3Cmn%3E1%3C%2Fmn%3E%3Cmn%3E2%3C%2Fmn%3E%3C%2Fmfrac%3E%3C%2Fmath%3E";
        String w = "<p>111111</p>";
        richTv.setText(w);

    }
}
