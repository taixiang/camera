package com.qhyccd.scrollviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.qhyccd.R;

/**
 * @author tx
 * @date 2018/8/10
 */
public class TestFragment extends Fragment {

    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        webView = view.findViewById(R.id.webView);
        return view;
    }


    public void setData(){
        webView.loadUrl("http://www.kaoyanvip.cn/");
    }

}
