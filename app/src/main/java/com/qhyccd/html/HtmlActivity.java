package com.qhyccd.html;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.qhyccd.R;
import com.tencent.smtt.sdk.WebSettings;

import wendu.dsbridge.DWebView;
import wendu.dsbridge.OnReturnValue;

/**
 * @author tx
 * @date 2018/11/1
 */
public class HtmlActivity extends AppCompatActivity {

    private WebView webView;
    //设函数<img src="http://equation.kaoyanvip.cn/?mml=%3Cmath%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F1998%2FMath%2FMathML%22%3E%3Cmi%3Ef%3C%2Fmi%3E%3Cmfenced%3E%3Cmrow%3E%3Cmi%3Ex%3C%2Fmi%3E%3Cmo%3E%2C%3C%2Fmo%3E%3Cmi%3Ey%3C%2Fmi%3E%3C%2Fmrow%3E%3C%2Fmfenced%3E%3Cmo%3E%3D%3C%2Fmo%3E%3Cmfenced%20open%3D%22%7B%22%20close%3D%22%22%3E%3Cmtable%20columnalign%3D%22left%22%3E%3Cmtr%3E%3Cmtd%3E%3Cmi%3Ex%3C%2Fmi%3E%3Cmi%3Ey%3C%2Fmi%3E%3Cmi%3Esin%3C%2Fmi%3E%3Cmfrac%3E%3Cmn%3E1%3C%2Fmn%3E%3Cmsqrt%3E%3Cmsup%3E%3Cmi%3Ex%3C%2Fmi%3E%3Cmn%3E2%3C%2Fmn%3E%3C%2Fmsup%3E%3Cmo%3E%2B%3C%2Fmo%3E%3Cmsup%3E%3Cmi%3Ey%3C%2Fmi%3E%3Cmn%3E2%3C%2Fmn%3E%3C%2Fmsup%3E%3C%2Fmsqrt%3E%3C%2Fmfrac%3E%3Cmo%3E%2C%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmsup%3E%3Cmi%3Ex%3C%2Fmi%3E%3Cmn%3E2%3C%2Fmn%3E%3C%2Fmsup%3E%3Cmo%3E%2B%3C%2Fmo%3E%3Cmsup%3E%3Cmi%3Ey%3C%2Fmi%3E%3Cmn%3E2%3C%2Fmn%3E%3C%2Fmsup%3E%3Cmo%3E%26%23x2260%3B%3C%2Fmo%3E%3Cmn%3E0%3C%2Fmn%3E%3Cmo%3E%2C%3C%2Fmo%3E%3C%2Fmtd%3E%3C%2Fmtr%3E%3Cmtr%3E%3Cmtd%3E%3Cmn%3E0%3C%2Fmn%3E%3Cmo%3E%2C%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmsup%3E%3Cmi%3Ex%3C%2Fmi%3E%3Cmn%3E2%3C%2Fmn%3E%3C%2Fmsup%3E%3Cmo%3E%2B%3C%2Fmo%3E%3Cmsup%3E%3Cmi%3Ey%3C%2Fmi%3E%3Cmn%3E2%3C%2Fmn%3E%3C%2Fmsup%3E%3Cmo%3E%3D%3C%2Fmo%3E%3Cmn%3E0%3C%2Fmn%3E%3Cmo%3E%2C%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3C%2Fmtd%3E%3C%2Fmtr%3E%3C%2Fmtable%3E%3C%2Fmfenced%3E%3C%2Fmath%3E" alt="<math xmlns=&quot;http://www.w3.org/1998/Math/MathML&quot;><mi>f</mi><mfenced><mrow><mi>x</mi><mo>,</mo><mi>y</mi></mrow></mfenced><mo>=</mo><mfenced open=&quot;{&quot; close=&quot;&quot;><mtable columnalign=&quot;left&quot;><mtr><mtd><mi>x</mi><mi>y</mi><mi>sin</mi><mfrac><mn>1</mn><msqrt><msup><mi>x</mi><mn>2</mn></msup><mo>+</mo><msup><mi>y</mi><mn>2</mn></msup></msqrt></mfrac><mo>,</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><msup><mi>x</mi><mn>2</mn></msup><mo>+</mo><msup><mi>y</mi><mn>2</mn></msup><mo>&amp;#x2260;</mo><mn>0</mn><mo>,</mo></mtd></mtr><mtr><mtd><mn>0</mn><mo>,</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><msup><mi>x</mi><mn>2</mn></msup><mo>+</mo><msup><mi>y</mi><mn>2</mn></msup><mo>=</mo><mn>0</mn><mo>,</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo></mtd></mtr></mtable></mfenced></math>" class="wiris-tex">则<img src="http://equation.kaoyanvip.cn/?mml=%3Cmath%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F1998%2FMath%2FMathML%22%3E%3Cmi%3Ef%3C%2Fmi%3E%3Cmo%3E(%3C%2Fmo%3E%3Cmi%3Ex%3C%2Fmi%3E%3Cmo%3E%2C%3C%2Fmo%3E%3Cmi%3Ey%3C%2Fmi%3E%3Cmo%3E)%3C%2Fmo%3E%3C%2Fmath%3E" alt="<math xmlns=&quot;http://www.w3.org/1998/Math/MathML&quot;><mi>f</mi><mo>(</mo><mi>x</mi><mo>,</mo><mi>y</mi><mo>)</mo></math>" class="wiris-tex">在点<img src="http://equation.kaoyanvip.cn/?mml=%3Cmath%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F1998%2FMath%2FMathML%22%3E%3Cmo%3E(%3C%2Fmo%3E%3Cmn%3E0%3C%2Fmn%3E%3Cmo%3E%2C%3C%2Fmo%3E%3Cmn%3E0%3C%2Fmn%3E%3Cmo%3E)%3C%2Fmo%3E%3C%2Fmath%3E" alt="<math xmlns=&quot;http://www.w3.org/1998/Math/MathML&quot;><mo>(</mo><mn>0</mn><mo>,</mo><mn>0</mn><mo>)</mo></math>" class="wiris-tex">处
    private String txt = "设函数<img src=\"http://equation.kaoyanvip.cn/?mml=%3Cmath%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F1998%2FMath%2FMathML%22%3E%3Cmi%3Ef%3C%2Fmi%3E%3Cmfenced%3E%3Cmrow%3E%3Cmi%3Ex%3C%2Fmi%3E%3Cmo%3E%2C%3C%2Fmo%3E%3Cmi%3Ey%3C%2Fmi%3E%3C%2Fmrow%3E%3C%2Fmfenced%3E%3Cmo%3E%3D%3C%2Fmo%3E%3Cmfenced%20open%3D%22%7B%22%20close%3D%22%22%3E%3Cmtable%20columnalign%3D%22left%22%3E%3Cmtr%3E%3Cmtd%3E%3Cmi%3Ex%3C%2Fmi%3E%3Cmi%3Ey%3C%2Fmi%3E%3Cmi%3Esin%3C%2Fmi%3E%3Cmfrac%3E%3Cmn%3E1%3C%2Fmn%3E%3Cmsqrt%3E%3Cmsup%3E%3Cmi%3Ex%3C%2Fmi%3E%3Cmn%3E2%3C%2Fmn%3E%3C%2Fmsup%3E%3Cmo%3E%2B%3C%2Fmo%3E%3Cmsup%3E%3Cmi%3Ey%3C%2Fmi%3E%3Cmn%3E2%3C%2Fmn%3E%3C%2Fmsup%3E%3C%2Fmsqrt%3E%3C%2Fmfrac%3E%3Cmo%3E%2C%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmsup%3E%3Cmi%3Ex%3C%2Fmi%3E%3Cmn%3E2%3C%2Fmn%3E%3C%2Fmsup%3E%3Cmo%3E%2B%3C%2Fmo%3E%3Cmsup%3E%3Cmi%3Ey%3C%2Fmi%3E%3Cmn%3E2%3C%2Fmn%3E%3C%2Fmsup%3E%3Cmo%3E%26%23x2260%3B%3C%2Fmo%3E%3Cmn%3E0%3C%2Fmn%3E%3Cmo%3E%2C%3C%2Fmo%3E%3C%2Fmtd%3E%3C%2Fmtr%3E%3Cmtr%3E%3Cmtd%3E%3Cmn%3E0%3C%2Fmn%3E%3Cmo%3E%2C%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmsup%3E%3Cmi%3Ex%3C%2Fmi%3E%3Cmn%3E2%3C%2Fmn%3E%3C%2Fmsup%3E%3Cmo%3E%2B%3C%2Fmo%3E%3Cmsup%3E%3Cmi%3Ey%3C%2Fmi%3E%3Cmn%3E2%3C%2Fmn%3E%3C%2Fmsup%3E%3Cmo%3E%3D%3C%2Fmo%3E%3Cmn%3E0%3C%2Fmn%3E%3Cmo%3E%2C%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3Cmo%3E%26%23xA0%3B%3C%2Fmo%3E%3C%2Fmtd%3E%3C%2Fmtr%3E%3C%2Fmtable%3E%3C%2Fmfenced%3E%3C%2Fmath%3E\" alt=\"<math xmlns=&quot;http://www.w3.org/1998/Math/MathML&quot;><mi>f</mi><mfenced><mrow><mi>x</mi><mo>,</mo><mi>y</mi></mrow></mfenced><mo>=</mo><mfenced open=&quot;{&quot; close=&quot;&quot;><mtable columnalign=&quot;left&quot;><mtr><mtd><mi>x</mi><mi>y</mi><mi>sin</mi><mfrac><mn>1</mn><msqrt><msup><mi>x</mi><mn>2</mn></msup><mo>+</mo><msup><mi>y</mi><mn>2</mn></msup></msqrt></mfrac><mo>,</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><msup><mi>x</mi><mn>2</mn></msup><mo>+</mo><msup><mi>y</mi><mn>2</mn></msup><mo>&amp;#x2260;</mo><mn>0</mn><mo>,</mo></mtd></mtr><mtr><mtd><mn>0</mn><mo>,</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><msup><mi>x</mi><mn>2</mn></msup><mo>+</mo><msup><mi>y</mi><mn>2</mn></msup><mo>=</mo><mn>0</mn><mo>,</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo><mo>&amp;#xA0;</mo></mtd></mtr></mtable></mfenced></math>\" class=\"wiris-tex\">则<img src=\"http://equation.kaoyanvip.cn/?mml=%3Cmath%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F1998%2FMath%2FMathML%22%3E%3Cmi%3Ef%3C%2Fmi%3E%3Cmo%3E(%3C%2Fmo%3E%3Cmi%3Ex%3C%2Fmi%3E%3Cmo%3E%2C%3C%2Fmo%3E%3Cmi%3Ey%3C%2Fmi%3E%3Cmo%3E)%3C%2Fmo%3E%3C%2Fmath%3E\" alt=\"<math xmlns=&quot;http://www.w3.org/1998/Math/MathML&quot;><mi>f</mi><mo>(</mo><mi>x</mi><mo>,</mo><mi>y</mi><mo>)</mo></math>\" class=\"wiris-tex\">在点<img src=\"http://equation.kaoyanvip.cn/?mml=%3Cmath%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F1998%2FMath%2FMathML%22%3E%3Cmo%3E(%3C%2Fmo%3E%3Cmn%3E0%3C%2Fmn%3E%3Cmo%3E%2C%3C%2Fmo%3E%3Cmn%3E0%3C%2Fmn%3E%3Cmo%3E)%3C%2Fmo%3E%3C%2Fmath%3E\" alt=\"<math xmlns=&quot;http://www.w3.org/1998/Math/MathML&quot;><mo>(</mo><mn>0</mn><mo>,</mo><mn>0</mn><mo>)</mo></math>\" class=\"wiris-tex\">处";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);
        webView = findViewById(R.id.webView);
//        WebSettings settings = webView.getSettings();
//        settings.setJavaScriptEnabled(true);
//        settings.setDomStorageEnabled(true);
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);
//        settings.setBuiltInZoomControls(false);
//        settings.setSupportZoom(false);
//        settings.setDisplayZoomControls(false);
        webView.loadUrl("file:///android_asset/test.html");

//        webView.addJavascriptObject(new JsApi(), null);
//
//        HtmlData data = new HtmlData();
//        data.html = txt;
//        webView.callHandler("nativeToJs", new Object[]{new Gson().toJson(data)}, new OnReturnValue<String>() {
//            @Override
//            public void onValue(String o) {
//                Log.i("》》》》》  ","o======== "+o);
//            }
//        });
    }
}
