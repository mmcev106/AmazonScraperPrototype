package mom.your.amazonscraperprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = this.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                Log.i(TAG, "in onPageFinished");
                view.evaluateJavascript(
                    "(function() { return document.evaluate(\"//th[contains(text(), 'ISBN-10')]\", document, null, XPathResult.ANY_TYPE, null).iterateNext().nextElementSibling.textContent.trim() })();",
                    new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                            TextView textView = MainActivity.this.findViewById(R.id.textView);
                            textView.setText("Scraped ISBN: " + s);
                            Log.i(TAG, "test values: " + s);
                        }
                    }
                );
            }
        });

        webView.loadUrl("https://www.amazon.com/Imperial-Earth/dp/9994734040/ref=tmm_pap_swatch_0?_encoding=UTF8&qid=1557678425&sr=8-3");
    }
}
