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
public class EncontreUmaIgrejaFragment extends Fragment {

    private WebView mWebView;

    public EncontreUmaIgrejaFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_encontre_uma_igreja, container, false);

        mWebView = (WebView) v.findViewById(R.id.encontre_igreja);
        mWebView.loadUrl("http://186.215.128.10:8080/mapa/index.php");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        return v;
    }
}
