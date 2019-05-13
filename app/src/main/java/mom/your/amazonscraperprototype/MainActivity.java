package mom.your.amazonscraperprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

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

                InputStream inputStream = getResources().openRawResource(R.raw.get_price);
                BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder total = new StringBuilder();
                try{
                    for (String line; (line = r.readLine()) != null; ) {
                        total.append(line).append('\n');
                    }
                }
                catch(Exception e){
                    throw new RuntimeException(e);
                }

                view.evaluateJavascript(
                    total.toString(),
                    new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                            TextView textView = MainActivity.this.findViewById(R.id.textView);
                            textView.setText("Scraped PRICE: $" + s);
                            Log.i(TAG, "test value: " + s);
                        }
                    }
                );
            }
        });

        webView.loadUrl("https://www.amazon.com/gp/aw/d/0151442339/ref=tmm_hrd_title_0?ie=UTF8&qid=1557678425&sr=8-3");
    }
}
