package tripbook.main;


import java.util.ArrayList;

import tripbook.main.R;
import tripbook.main.adapter.NavDrawerListAdapter;
import tripbook.main.model.NavDrawerItem;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

public class MainActivity extends Activity {
//	SharedPreferences sharedpreferences = getSharedPreferences
	//     (StartActivity.MyPREFERENCES, Context.MODE_PRIVATE);
//    Editor editor = sharedpreferences.edit();
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	 ProgressDialog dialog = null;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		

		
		
		
		
		
		
		
		
		
		
		

		mTitle = mDrawerTitle = getTitle();

		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		// Home
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		// Explore
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		
		//navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1), true, "20+"));
		//Itinary Planner
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
		//Tripdiary
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
		
		//About
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
		
		
		// Network
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
		
		
//Add PHOTOS
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		
		
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        if (null != searchView) {
            searchView.setSearchableInfo(searchManager
                    .getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(true);
            
        }

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // this is your adapter that will be filtered
            	
            	//cardArrayAdapter.getFilter().filter(newText.toString());
            	
            //	Toast.makeText(getApplicationContext(),newText, Toast.LENGTH_SHORT).show();
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                //Here u can get the value "query" which is entered in the search box.
            	//Toast.makeText(getApplicationContext(),query, Toast.LENGTH_SHORT).show();
            	 String address = query;
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
            	
            	return true;
            		
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);

    	
    	
    	
        
		
		
		
		
		return true;
		
		
	}

	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		
		case R.id.action_settings:
			
			return true;
		case R.id.action_search:
		
		//	System.out.println(k);
			return true;
		case R.id.action_signout:
			//editor.clear();
		      //editor.commit();
			removeSharedPref(getBaseContext());
			Intent i=new Intent(MainActivity.this,StartActivity.class);
			startActivity(i);
			overridePendingTransition(R.anim.fade_in,0);
			MainActivity.this.finish();
			return true;
		case R.id.action_editinfo:
			Intent intent = new Intent(this, SecondActivity.class);
		      startActivity(intent);
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		
		
		
		MenuItem user=menu.findItem(R.id.action_user);
		MenuItem signout=menu.findItem(R.id.action_signout);
		MenuItem userinfo=menu.findItem(R.id.action_editinfo);
		try{
		if(getBoolSharPref("skippressed",getBaseContext())==true){
			userinfo.setVisible(false);
			user.setTitle("Guest");
			signout.setIcon(getResources().getDrawable(R.drawable.useropy));
			signout.setTitle("Login/SignUp");
		}
		
		else
		{String k=getDefaults("namestore", getBaseContext());
			StringBuilder sb = new StringBuilder(k);
			char c = sb.charAt(0);
			sb.setCharAt(0,Character.toUpperCase(c));
		user.setTitle(sb.toString());}
		}
		catch (Exception e){
			e.printStackTrace();
			
		}
		
		
		return super.onPrepareOptionsMenu(menu);
		
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			
			fragment = new HomeFragment();
			break;
		case 1:
			
		{
			
                	fragment=new Explore();
              
			
			break;
		}
			
			
		case 2:
			fragment = new ItineraryPlanner();
			break;
		case 3:
			fragment = new TripDiary();
			break;
		case 4:
			fragment=new Reviews();
			break;
		case 6:
			fragment = new About();
			break;

		case 5:
			startActivity(new Intent(MainActivity.this,AddPost.class));
			break;
		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
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
	public static void removeSharedPref(Context context)
	{
		 SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		    SharedPreferences.Editor editor = prefs.edit();
		    editor.clear();
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
