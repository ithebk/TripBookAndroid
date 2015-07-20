package tripbook.main;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivityK extends Activity{
    Button button;
    EditText addrText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        addrText = (EditText) findViewById(R.id.editText);
        //button.setOnClickListener(new listener());
    }

    public void dosomething(View v)
    {
        try {

            // Process text for network transmission
            String address = addrText.getText().toString();
            address = address.replace(' ', '+');
            Log.d("kkk",address);

            // Create Intent object for starting Google Maps application
            /*Intent geoIntent = new Intent(
                    android.content.Intent.ACTION_VIEW, Uri
                    .parse("geo:0,0?q=" + address));*/
            Uri gmmIntentUri = Uri.parse("google.navigation:q="+address);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
          

        } catch (Exception e) {

        }


    }





    public void function(View v)
    {
        Log.d("kkk","button was clicked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("kkk","onresume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("kkk","onstart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("kkk","onstop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("kkk","onpause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("kkk","ondestroy");
    }



}
