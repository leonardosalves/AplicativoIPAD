package minhasanotacoes.leonardo.com.navegacaodrawer.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import minhasanotacoes.leonardo.com.navegacaodrawer.R;


public class CanalYoutubeFragment extends Fragment {

    private WebView mWebView;

    public CanalYoutubeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_canal_youtube, container, false);

        mWebView = (WebView) v.findViewById(R.id.youtube_canal);
        mWebView.loadUrl("https://www.youtube.com/channel/UCZiTsjKLAaL0USqm606gqlg");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        return v;

    }
}
