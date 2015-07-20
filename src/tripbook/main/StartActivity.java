package tripbook.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public  class StartActivity extends Activity implements AnimationListener{
	
	
	// private static final String TAG=StartActivity.class.getName();
	    private static ArrayList<Activity> activities=new ArrayList<Activity>();
	    String path="http://192.168.43.62/signinmob.php";
	    RelativeLayout rt;
	    TextView op;
	    Button b,btnsu;
	    EditText et,pass;
	    HttpPost httppost;
	    StringBuffer buffer;
	    HttpResponse response;
	    HttpClient httpclient;
	    List<NameValuePair> nameValuePairs;
	    ProgressDialog dialog = null;
	    Animation anim,rot;
	    private Menu optionMenu;
	    public static final String namestore = "nameKey"; 
	    public static final String passstore = "passwordKey";
	    public static final String MyPREFERENCES = "MyPrefs" ;
	    SharedPreferences sharedpreferences;
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		
		//....Test
		/*Button testbt=(Button) findViewById(R.id.test);
		testbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//Intent intentmap=new Intent(StartActivity.this,MapActivity.class);
				//startActivity(intentmap);
				
			}
		});*/

		
		
		op=(TextView) findViewById(R.id.op);
		 // load the animation
        anim = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation);
        rot=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
     // set animation listener
        rot.setAnimationListener(this);
        anim.setAnimationListener(this);
       
        
		activities.add(this);
		rt=(RelativeLayout) findViewById(R.id.rel);
		
		b=(Button) findViewById(R.id.btnSingIn);
		btnsu=(Button) findViewById(R.id.btnSearch);
		et=(EditText)findViewById(R.id.etUserName);
		pass=(EditText)findViewById(R.id.etPass);
		//btskip=(Button)findViewById(R.id.btskip);
		//et.startAnimation(anim);
		//pass.startAnimation(anim);
		
		
		et.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				et.clearAnimation();
				return false;
			}
		});



		
		btnsu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// start the animation
				
				Intent i=new Intent(StartActivity.this,SignUpActivity.class);
				startActivity(i);
				//StartActivity.this.finish();
				overridePendingTransition(R.anim.fade_in,0);
				
			}
		});
		
		
		
		
		
		
		
		
		
	/*	
		btskip.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent i=new Intent(StartActivity.this,MainActivity.class);
				startActivity(i);
				StartActivity.this.finish();
				overridePendingTransition(R.anim.fade_in,0);
			}
			
		});
		*/
		
		
		
		
		 b.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	setBoolenSharPref("skippressed",false,getBaseContext());
	            	String k=et.getText().toString();
	            	String k1=pass.getText().toString();
	            	if(!haveNetworkConnection())
	            	{
	            		Toast.makeText(StartActivity.this,"No Internet Cannection", Toast.LENGTH_SHORT).show();
	            	}
	            	else if(k.equals("")||(k1.equals(""))){
	            		//op
	            	
	            		op.setTextColor(getResources().getColor(R.color.red));
	            		op.setText("Please Enter Required Field") ;
	            		
	            	}
	            	else{
	            		
	              // dialog = ProgressDialog.show(StartActivity.this, "", 
	                   //     "Validating user...", true);
	                //dialog.setCanceledOnTouchOutside(true);
	               b.setClickable(false);
	               btnsu.setClickable(false);
	            		setRefreshState(true);
	                 new Thread(new Runnable() {
	                        public void run() {
	                            login();
	                            
	                        }
	                      }).start();             
	            }}
	        });
	    }
	     
	   
	

		void login(){
	        try{            
	        	
	            httpclient=new DefaultHttpClient();
	            httppost= new HttpPost(path); // make sure the url is correct.
	            //add your data
	            nameValuePairs = new ArrayList<NameValuePair>(2);
	            // Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar, 
	            nameValuePairs.add(new BasicNameValuePair("login-username",et.getText().toString().trim()));  // $Edittext_value = $_POST['Edittext_value'];
	            nameValuePairs.add(new BasicNameValuePair("login-password",pass.getText().toString().trim())); 
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            //Execute HTTP Post Request
	            response=httpclient.execute(httppost);
	            response.getEntity();
	           
	            ResponseHandler<String> responseHandler = new BasicResponseHandler();
	            final String response = httpclient.execute(httppost, responseHandler).toString().replaceAll("\n","");
	            
	           // Toast.makeText(StartActivity.this,response.toString(), Toast.LENGTH_LONG).show();
	            System.out.println("Response : " + response); 
	            runOnUiThread(new Runnable() {
	                public void run() {
	                	//op.getOnFocusChangeListener().onFocusChange(welcome, true);
	                	op.setTextColor(Color.parseColor("#007FFF"));
	                	op.setText("Response: " + response);
	                	setRefreshState(false);
	                	b.setClickable(true);
	 	               btnsu.setClickable(true);
	                	// dialog.dismiss();
	                }
	            });
	             
	            if(response.equals("1")){
	                runOnUiThread(new Runnable() {
	                    public void run() {
	                        Toast.makeText(StartActivity.this,"Login Success", Toast.LENGTH_SHORT).show();
	                    }
	                });
	                //Editor editor = sharedpreferences.edit();
	                String u = et.getText().toString();
	                String p = pass.getText().toString();
	                //editor.putString(namestore, u);
	                //editor.putString(passstore, p);
	                //editor.commit();
	                setDefaults("namestore",u, getBaseContext());
	                setDefaults("passstore",p, getBaseContext());
	                startActivity(new Intent(StartActivity.this,MainActivity.class));
	                StartActivity.this.finish();
	                overridePendingTransition(R.anim.fade_in,0);
	            }else{
	            	
	            	
	            	
	                showAlert();                
	            }
	             
	        }catch(Exception e){
	            dialog.dismiss();
	            System.out.println("Exception : " + e.getMessage());
	        }
	    }
	    public void showAlert(){
	        StartActivity.this.runOnUiThread(new Runnable() {
	            public void run() {
	            	op.setTextColor(getResources().getColor(R.color.red));
	                op.setText("Username or Password Wrong");
	                /*AlertDialog.Builder builder = new AlertDialog.Builder(StartActivity.this);
	                builder.setTitle("Login Error.");
	               
	                builder.setMessage("User not Found.")  
	                       .setCancelable(false)
	                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	                           public void onClick(DialogInterface dialog, int id) {
	                           }
	                       });                     
	                AlertDialog alert = builder.create();
	                
	               // op.startAnimation(anim);
	               
	               
	                //et.setHint("Invalid");
	                //et.setHintTextColor(getResources().getColor(R.color.red));
	                //pass.setText("");
	               // pass.setHint("Invalid");
	                //et.setHintTextColor(getResources().getColor(R.color.red));
	                //pass.setHintTextColor(getResources().getColor(R.color.red));
	                alert.show();
	              // builder.setCancelable(true);
	                alert.setCanceledOnTouchOutside(true);
	                	           */ }
	        });
	    }
	    
	    @Override
		public boolean onCreateOptionsMenu(Menu menu) {
			this.optionMenu=menu;
	    	getMenuInflater().inflate(R.menu.menu1, menu);
			return true;
		}

		
		
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// toggle nav drawer on selecting action bar app icon/title
			
			// Handle action bar actions click
			switch (item.getItemId()) {
			case R.id.action_refresh:
				
				//btskip.setText("Skip");
				pass.setText("");
				et.setText("");
				op.setText("");
				et.setHint(getResources().getString(R.string.enter_username));
				pass.setHint(getResources().getString(R.string.enter_password));
				//btskip.startAnimation(rot);
				btnsu.startAnimation(rot);
				b.startAnimation(rot);
				return true;
			case R.id.action_skip:
				//setBoolenSharPref("skippressed",true,getBaseContext());
				Intent i=new Intent(StartActivity.this,TakeDemo.class);
				startActivity(i);
				overridePendingTransition(R.anim.fade_out,R.anim.fade_in);
				//StartActivity.this.finish();
				return true;
		
			default:
				return super.onOptionsItemSelected(item);
			}
		}
		   @Override
		    public void onAnimationEnd(Animation animation) {
		      
		 
		    }
		 
		    @Override
		    public void onAnimationRepeat(Animation animation) {
		        // TODO Auto-generated method stub
		 
		    }
		 
		    @Override
		    public void onAnimationStart(Animation animation) {
		        // TODO Auto-generated method stub
		 
		    }
		    private boolean haveNetworkConnection() {
		        boolean haveConnectedWifi = false;
		        boolean haveConnectedMobile = false;

		        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
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




			
			public void setRefreshState(final boolean refreshing)
			
			{
				
				if(optionMenu!=null){
					
					final MenuItem refreshItem=optionMenu.findItem(R.id.action_refresh);
					if(refreshItem!=null)
					{
						
						if(refreshing){refreshItem.setActionView(R.layout.refresh);}
						else{refreshItem.setActionView(null);}
					}
				}
				
			}
			@Override
			   protected void onResume() {
			  /*    sharedpreferences=getSharedPreferences(MyPREFERENCES, 
			      Context.MODE_PRIVATE);
			      if (sharedpreferences.contains(namestore))
			      {
			      if(sharedpreferences.contains(passstore)){
			         //
			    	  Intent i = new Intent(this,MainActivity.class);
			         startActivity(i);
			         StartActivity.this.finish();
			      }
			      }*/
				Boolean s=getBoolSharPref("skippressed",getBaseContext());
			      String k=getDefaults("namestore", getBaseContext());
			      if((k!=null)||(s==true)){ Intent i = new Intent(this,MainActivity.class);
			         startActivity(i);
			         StartActivity.this.finish();}
			      
			      super.onResume();
			   }
			public static void setDefaults(String key, String value, Context context) {
			    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
			    SharedPreferences.Editor editor = prefs.edit();
			    editor.putString(key, value);
			    editor.commit();
			}
			public static String getDefaults(String key, Context context) {
			    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
			    return preferences.getString(key, null);
			}
			public static void setBoolenSharPref(String key,boolean val,Context context){
				SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
			    SharedPreferences.Editor editor = prefs.edit();
			    editor.putBoolean(key,val);
			    editor.commit();
				
			}
			public static boolean getBoolSharPref(String key,Context context)
			{
				 SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
				/* Boolean tmp = null;
				 tmp = preferences.getBoolean("key", tmp.getBoolean(""));
				 if(tmp==true){return true;}
				 else{
					 return false;
				 }*/
				 
				return preferences.getBoolean(key, false);
				 
				
				
			}
			
		   
		

}
