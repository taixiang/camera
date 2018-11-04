package com.qhyccd.html;

import android.util.Log;
import android.webkit.JavascriptInterface;

import wendu.dsbridge.CompletionHandler;

/**
 * Created by tx on 2018/11/3.
 */

public class JsApi {

    @JavascriptInterface
    public void jsToNative(Object msg, CompletionHandler<String> handler) {
        handler.complete(msg+"回调给js");
    }
}
