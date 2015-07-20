package tripbook.main;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Process;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class SignUpActivity extends Activity {
	
	//Animation anim;
	
	
	HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;
    Animation anim,rot;
	EditText etemail,pass,passagain;
	Button btsignup;
	String empty="";
	String email,pass1,user1;
	InputStream is=null;
	String result=null;
	String line=null;
	int code;
	private Menu optionMenu;


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		
		setContentView(R.layout.activity_sign_up_screen);
		
		/*
		//
		 mGoogleApiClient = new GoogleApiClient.Builder(this)
	        .addConnectionCallbacks((com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks) this)
	        .addOnConnectionFailedListener((com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener) this)
	        .addApi(Plus.API)
	        .addScope(Plus.SCOPE_PLUS_LOGIN)
	        .build();
		
		*/
		
		 getActionBar().setDisplayHomeAsUpEnabled(true);
		etemail=(EditText) findViewById(R.id.etEmail);
	
		pass=(EditText) findViewById(R.id.etPass);
		passagain=(EditText) findViewById(R.id.etPassag);
		rot=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
		//rot.setAnimationListener((AnimationListener)this);
		

		
		
	
		
		
		
		//
		//* Track whether the sign-in button has been clicked so that we know to resolve
		// * all issues preventing sign-in without waiting.
		 //*/
		

		/* Store the connection result from onConnectionFailed callbacks so that we can
		 * resolve them when the user clicks sign-in.
		 */
		

		/* A helper method to resolve the current ConnectionResult error. */
		
		
		
		
		
		
		
		
		
		
		
		pass.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
passagain.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
			}
		});
		
		
		btsignup=(Button) findViewById(R.id.btnSearch);
		btsignup.setOnClickListener(new View.OnClickListener() {
			@Override
            public void onClick(View v) {
				setBoolenSharPref("skippressed",false,getBaseContext());
				
				
            	String k1=pass.getText().toString();
            	String k2=etemail.getText().toString();
            	String k3=passagain.getText().toString();
            	if(!haveNetworkConnection())
            	{
            		Toast.makeText(SignUpActivity.this,"OOhh!!No Internet", Toast.LENGTH_SHORT).show();
            	}
            	else if((k1.equals("")||(k2.equals(""))||(k3.equals("")))){
            		//op
            		Toast.makeText(SignUpActivity.this,"Please Enter the Required Fields", Toast.LENGTH_SHORT).show();
            		//op.setTextColor(getResources().getColor(R.color.red));
            		//op.setText("Please Enter Required Field") ;
            		
            	}
            	else if(!emailValidator(etemail.getText().toString()))
            	{
            		Toast.makeText(SignUpActivity.this,"Invalid Email Addres",Toast.LENGTH_SHORT).show();
            	}
            
            	else if(!k1.equals(k3)){
            		Toast.makeText(SignUpActivity.this,"Password Mismatch",Toast.LENGTH_SHORT).show();
            	}
            	else{
               // dialog = ProgressDialog.show(SignUpActivity.this, "", 
                  //      "Please Wait...", true);
                //dialog.setCanceledOnTouchOutside(true);
                 setRefreshState(true);
                 btsignup.setClickable(false);
            		new Thread(new Runnable() {
                        public void run() {
                        	Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
                        	login();                          
                        }
                      }).start();               
            }}
        });
    }
	
	/*private void resolveSignInError() {
		  if (mConnectionResult.hasResolution()) {
		    try {
		      mIntentInProgress = true;
		      startIntentSenderForResult(mConnectionResult.getResolution().getIntentSender(),
		          RC_SIGN_IN, null, 0, 0, 0);
		    } catch (SendIntentException e) {
		      // The intent was canceled before it was sent.  Return to the default
		      // state and attempt to connect to get an updated ConnectionResult.
		      mIntentInProgress = false;
		      mGoogleApiClient.connect();
		    }
		  }}*/
	
	/*
	protected void onStart() {
	    super.onStart();
	    mGoogleApiClient.connect();
	  }
	
	protected void onStop() {
	    super.onStop();

	    if (mGoogleApiClient.isConnected()) {
	      mGoogleApiClient.disconnect();
	    }}
	
	
	public void onConnectionFailed(ConnectionResult result) {
		  if (!mIntentInProgress && result.hasResolution()) {
		    try {
		      mIntentInProgress = true;
		      startIntentSenderForResult(result.getResolution().getIntentSender(),
		          RC_SIGN_IN, null, 0, 0, 0);
		    } catch (SendIntentException e) {
		      // The intent was canceled before it was sent.  Return to the default
		      // state and attempt to connect to get an updated ConnectionResult.
		      mIntentInProgress = false;
		      mGoogleApiClient.connect();
		    }
		  }
		}

		public void onConnected(Bundle connectionHint) {
		  // We've resolved any connection errors.  mGoogleApiClient can be used to
		  // access Google APIs on behalf of the user.
		}
		
		protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
			  if (requestCode == RC_SIGN_IN) {
			    mIntentInProgress = false;

			    if (!mGoogleApiClient.isConnecting()) {
			      mGoogleApiClient.connect();
			    }
			  }
			}
		public void onConnectionSuspended(int cause) {
			  mGoogleApiClient.connect();
			}
		
		
	*/
	
     
    void login(){
        try{            
              
            httpclient=new DefaultHttpClient();
            httppost= new HttpPost("http://192.168.43.207/SEtest-Mobile/signup.php"); // make sure the url is correct.
            //add your data
            nameValuePairs = new ArrayList<NameValuePair>(3);
            // Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar, 
            nameValuePairs.add(new BasicNameValuePair("register-username",etemail.getText().toString().trim())); 
             // $Edittext_value = $_POST['Edittext_value'];
            nameValuePairs.add(new BasicNameValuePair("register-password",pass.getText().toString().trim())); 
           
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            //Execute HTTP Post Request
            response=httpclient.execute(httppost);
            response.getEntity().consumeContent();
          //m here....
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler).toString().replaceAll("\n","");
            System.out.println("Response : " + response); 
            runOnUiThread(new Runnable() {
                public void run() {
                	//op.getOnFocusChangeListener().onFocusChange(welcome, true);
                	
                	etemail.setText("Response: " + response);
                	 setRefreshState(false);
                     btsignup.setClickable(true);
                   // dialog.dismiss();
                }
            });
             
            if(response.toString().equals("1")){
                runOnUiThread(new Runnable() {
                    public void run() {
                   
                    	String p=pass.getText().toString();
                    	String e=etemail.getText().toString();
                    	setDefaults("namestore",e, getBaseContext());
    	                setDefaults("passstore",p, getBaseContext());
    	                setDefaults("email",e,getBaseContext());
                        Toast.makeText(SignUpActivity.this,"Success", Toast.LENGTH_SHORT).show();
                    }
                });
                 
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                SignUpActivity.this.finish();
                overridePendingTransition(R.anim.fade_in,0);
            }else{
                showAlert();                
            }
             
        }catch(Exception e){
          //  dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
    }
    public void showAlert(){
        SignUpActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                builder.setTitle("Signup Error.");
                builder.setMessage("Please try again.")  
                       .setCancelable(false)
                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                           }
                       });                     
                AlertDialog alert = builder.create();
                alert.setCanceledOnTouchOutside(true);
                
                alert.show();   
                
            }
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
				{
					etemail.setText(empty);	
				
					pass.setText(empty);
					passagain.setText(empty);
					//pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
					//pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
					//passagain.setTransformationMethod(PasswordTransformationMethod.getInstance());
					btsignup.startAnimation(rot);
					return true;}
				case R.id.action_skip:
					{//setBoolenSharPref("skippressed",true,getBaseContext());
						Intent i=new Intent(SignUpActivity.this,TakeDemo.class);
					startActivity(i);
					overridePendingTransition(R.anim.fade_in,0);
					SignUpActivity.this.finish();
					return true;
					}
				case android.R.id.home:
			        NavUtils.navigateUpFromSameTask(this);
			        return true;
			
				default:
					return super.onOptionsItemSelected(item);
				}
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
				public static class Validate{
					Validate(){
						
					}
				public final static boolean isValidEmail(CharSequence target) {
					  return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
					}
				}
				
				
				 public static boolean emailValidator(final String mailAddress) {

			            Pattern pattern;
			            Matcher matcher;

			            final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

			            pattern = Pattern.compile(EMAIL_PATTERN);
			            matcher = pattern.matcher(mailAddress);
			            return matcher.matches();

			        }
					
				 public static boolean usernameValidator(final String userName) {

			            Pattern pattern;
			            Matcher matcher;

			            final String USERNAME_PATTERN ="^(?i)[a-z0-9_-]{4,15}$";

			            pattern = Pattern.compile(USERNAME_PATTERN);
			            matcher = pattern.matcher(userName);
			            return matcher.matches();

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

				 /*
				@Override
				public void onDisconnected() {
					// TODO Auto-generated method stub
					
				}*/
				 
				 
}
