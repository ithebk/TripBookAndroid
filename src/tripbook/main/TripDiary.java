package tripbook.main;
import tripbook.main.R;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class TripDiary extends Fragment {
	public static String path="http://192.168.43.207/SEtest-Mobile/tripdiary.php"; 
	private WebView browser;
	 public Button refreshweb;
	 private ProgressBar progressBarT4 = null;
	public TextView load;
	Animation anim;
	private Bundle webViewBundle;
	
	
	ProgressDialog dialog = null;
	public TripDiary(){}
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		
 
		 View rootView = inflater.inflate(R.layout.fragment_whats_trending, container, false);
		// View footerView = ((LayoutInflater)this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.listfooter, null, false);
		 //this.getListView().addFooterView(footerView);
		 progressBarT4 = (ProgressBar) rootView.findViewById(R.id.progressBar1web);
		 load = (TextView)rootView.findViewById(R.id.loadin);
        browser = (WebView)rootView.findViewById(R.id.webView1);
        refreshweb=(Button) rootView.findViewById(R.id.buttonrefresh);
        anim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.animation);
        //load.startAnimation(anim);
        //getActivity().anim.setAnimationListener(this);
	      browser.setWebViewClient(new MyBrowser());
	  
	   
		

			// TODO Auto-generated method stub
			browser.getSettings().setLoadsImagesAutomatically(true);
		      browser.getSettings().setJavaScriptEnabled(true);
		      browser.getSettings().setSupportZoom(false);
		     // browser.getSettings().setBuiltInZoomControls(true);
		      browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		      browser.setScrollbarFadingEnabled(true);
		      browser.getSettings().setLoadsImagesAutomatically(true);
		     
		    
		      if(!haveNetworkConnection())
		    	  
		      {		//load.setTextColor(getResources().getColor(R.color.yellow));
		      load.setBackgroundColor(getResources().getColor(R.color.red));
		    	  load.setText("No Internet Connection");
		      		
		      		progressBarT4.setVisibility(View.GONE);
		    	  refreshweb.setVisibility(View.VISIBLE);
		      		refreshweb.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							Intent i=new Intent(getActivity(),MainActivity.class);
						
							getActivity().finish();
							startActivity(i);
							
						}
					});
		    	  Toast.makeText(getActivity(),"No Internet Cannection", Toast.LENGTH_SHORT).show();
		    	  
		      
		      
		      }
		      else{
		    	  
		    	  browser.setWebChromeClient(new WebChromeClient() {
		    	        public void onProgressChanged(WebView view, int progress) {
		    	            progressBarT4.setVisibility(View.VISIBLE);
		    	           
		    	            progressBarT4.setProgress(progress);
		    	           
		    	            if (progress == 100) {
		    	            	load.setVisibility(View.GONE);
//		    	            	/load.clearAnimation();
		    	                progressBarT4.setVisibility(View.GONE); // Make the bar disappear after URL is loaded
		    	            }
		    	        }
		    	    });
		    	  load.setVisibility(View.VISIBLE);
		    	    progressBarT4.setVisibility(View.VISIBLE);
		    	  
		    	    if (webViewBundle == null) {
		    	    	browser.loadUrl(path);}
		    	    else {
		    	        browser.restoreState(webViewBundle);
		    	    }
		    	    
		    	    
		    	    
		    	  
		    	 /*dialog = ProgressDialog.show(getActivity(), "", 
		                  "Please Wait",true);
		    	  dialog.setCancelable(true);
		    	  dialog.setCanceledOnTouchOutside(true);*/
		         
		    	  
		      }
		  
        return rootView;
        
    }
	
	 @Override
	    public void onPause() {
	    super.onPause();
	 
	    webViewBundle = new Bundle();
	    browser.saveState(webViewBundle);
	    }
	
  /*  @Override
	public void onDestroy()
    {
      // Clear the cache (this clears the WebViews cache for the entire application)
      browser.clearCache(true);
       
      super.onDestroy();
    }
    
    public File getCacheDir()
    {
      // NOTE: this method is used in Android 2.1
       
      return getActivity().getApplicationContext().getCacheDir();
    }*/
   
	
	public boolean haveNetworkConnection() {
	    boolean haveConnectedWifi = false;
	    boolean haveConnectedMobile = false;

	    ConnectivityManager cm = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
	    for (NetworkInfo ni : netInfo) {
	        if (ni.getTypeName().equalsIgnoreCase("WIFI"))
	            if (ni.isConnected())
	                haveConnectedWifi = true;
	        if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
	            if (ni.isConnected())
	                haveConnectedMobile = true;
	    }
	    return haveConnectedWifi || haveConnectedMobile;
	}
	

	

	   private class MyBrowser extends WebViewClient {
	     
		 //  protected final String TAG = null;

		@Override
		   public void onPageStarted(WebView view, String url, Bitmap favicon) {
		   // TODO Auto‐generated method stub
		   super. onPageStarted(view, url, favicon) ;
		   
		   }
		   
		  @Override
	      public boolean shouldOverrideUrlLoading(WebView view, String url) {
			  
			// load.setVisibility(View.VISIBLE);
			  
			  
if(!haveNetworkConnection())
		    	  
		      {		//load.setTextColor(getResources().getColor(R.color.yellow));
		      load.setBackgroundColor(getResources().getColor(R.color.red));
		    	  load.setText("No Internet Connection");
		      		
		      		progressBarT4.setVisibility(View.GONE);
		    	  refreshweb.setVisibility(View.VISIBLE);
		      		refreshweb.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							Intent i=new Intent(getActivity(),MainActivity.class);
						
							getActivity().finish();
							startActivity(i);
							
						}
					});
		    	  Toast.makeText(getActivity(),"No Internet Cannection", Toast.LENGTH_SHORT).show();
		    	  
		      
		      
		      }
else{
			  
			  browser.setWebChromeClient(new WebChromeClient() {
				//  private long starttime = 0;
	    	        public void onProgressChanged(WebView view, int progress) {
	    	        	progressBarT4.setVisibility(View.VISIBLE);
		    	           
	    	            progressBarT4.setProgress(progress);
	    	        	//Log.v(TAG, "progressChanged: " + progress);
	    	        	//if(progress == 10) starttime = System.currentTimeMillis();
	    	        	//long secondsSinceStart = (System.currentTimeMillis() - starttime) / 1000;


	    	        /*	 if(progress < 100) {
	    	        	        Log.v(TAG, "seconds since start: " + secondsSinceStart);
	    	        	        if(secondsSinceStart > 5){
	    	        	            view.stopLoading();
	    	        	            Toast.makeText(getActivity(),"Timeout", Toast.LENGTH_SHORT).show();
	    	        	            Log.d(TAG, "TIMEOUT -> Stopped.");
	    	        	        }
	    	        	    } */
	    	           
	    	            if (progress == 100) {
	    	            	load.setVisibility(View.GONE);
//	    	            	/load.clearAnimation();
	    	                progressBarT4.setVisibility(View.GONE); // Make the bar disappear after URL is loaded
	    	            }
	    	        }
	    	    });
			 // load.setText("Loading please wait");
			  //load.setBackgroundColor(getResources().getColor(R.id.loadin));
			  //load.setVisibility(View.VISIBLE);
	    	    progressBarT4.setVisibility(View.VISIBLE);
			  
			  view.loadUrl(url);
	         
	      }return true;}
	      
	      @Override
	      public void onPageFinished(WebView view, String url) {
	      // TODO Auto‐generated method stub
	      super.onPageFinished(view,url) ;
	      
	    //  dialog.cancel();
	      }
	      
	      
	     
	      
	      }




	
	   
	      
	   }

