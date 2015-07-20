package tripbook.main;

import java.io.IOException;
import java.util.List;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import tripbook.main.R;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class HomeFragment extends Fragment {
	
	 List<Address> addresses;
	MapView mMapView;
	private GoogleMap googleMap;
	private Button btSearch;
	private EditText etfrom;
	
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
         //hhhdh
        
      //  map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
        //        .getMap();
        
        etfrom=(EditText) rootView.findViewById(R.id.etfrom);
        btSearch=(Button) rootView.findViewById(R.id.btnSearch);
        mMapView = (MapView) rootView.findViewById(R.id.map1);
        mMapView.onCreate(savedInstanceState);
        btSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			
				try {

		            // Process text for network transmission
		            String address = etfrom.getText().toString();
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


				// TODO Auto-generated method stub
				
			}
		});

        mMapView.onResume();// needed to get the map to display immediately
        
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        googleMap = mMapView.getMap();
        
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        
        
        
        
       
      
        LocationManager mng = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Location location = mng.getLastKnownLocation(mng.getBestProvider(new Criteria(), false));
        if(location!=null){
        	
        double lat = location.getLatitude();
        double lon = location.getLongitude();
            
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 10);
      googleMap.animateCamera(cameraUpdate);	
        }
       
       
       
        
        return rootView;
    }

	/*
	
	public void showAlert(){
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
            	//op.setTextColor(getResources().getColor(R.color.red));
                //op.setText("Username or Password Wrong");
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                
               
                builder.setMessage("Please Enter Some place Name.")  
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
            }
        });
    }	*/
@Override
public void onResume() {
    super.onResume();
    mMapView.onResume();
}

@Override
public void onPause() {
    super.onPause();
    mMapView.onPause();
}
	

@Override
public void onDestroy() {
    super.onDestroy();
    mMapView.onDestroy();
}

@Override
public void onLowMemory() {
    super.onLowMemory();
    mMapView.onLowMemory();
}

}
