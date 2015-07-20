package tripbook.main;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
 
public class SecondActivity extends Activity
{
  protected WebView webView;
   
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.testweb);
     
    webView = ((WebView)findViewById(R.id.webView));
     
    // Initialize the WebView
    webView.getSettings().setSupportZoom(true);
    webView.getSettings().setBuiltInZoomControls(true);
    webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
    webView.setScrollbarFadingEnabled(true);
    webView.getSettings().setLoadsImagesAutomatically(true);
 
    // Load the URLs inside the WebView, not in the external web browser
    webView.setWebViewClient(new WebViewClient());
 
    webView.loadUrl("http://barat.co.nf/mobile/test");
  }
 
  @Override
  public File getCacheDir()
  {
    // NOTE: this method is used in Android 2.1
     
    return getApplicationContext().getCacheDir();
  }
}
