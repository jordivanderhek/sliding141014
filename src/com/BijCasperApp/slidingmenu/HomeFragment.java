package com.BijCasperApp.slidingmenu;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;

import info.androidhive.listviewfeed.R;
import com.BijCasperApp.slidingmenu.FeedListAdapter;
import com.BijCasperApp.slidingmenu.AppController;
import com.BijCasperApp.slidingmenu.FeedItem;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HomeFragment extends Fragment {
	
	private static final String TAG = FeedMainActivity.class.getSimpleName();
	private ListView listView;
	private FeedListAdapter Adapter;
	private List<FeedItem> feedItems;
	private String URL_FEED = "http://www.jordivanderhek.nl/nieuws/nieuws.json";
	
	public HomeFragment(){}
	
	@SuppressLint("NewApi")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		Log.d("HomeFragment", "in oncreate");
		
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
    
        // issues fixed here!
        listView = (ListView) rootView.findViewById(R.id.list);
        Log.d("HomeFragment", "past list");

		feedItems = new ArrayList<FeedItem>();

		Adapter = new FeedListAdapter(getActivity(), feedItems);
		//  issues v2 also fixed
		listView.setAdapter(Adapter);
		
		
		// zet balk kleur
		getActivity().getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
		// set icon ontzichtbaar
		getActivity().getActionBar().setIcon(
				   new ColorDrawable(getResources().getColor(android.R.color.transparent)));

			// making fresh volley request and getting json
			JsonObjectRequest jsonReq = new JsonObjectRequest(Method.GET,
					URL_FEED, null, new Response.Listener<JSONObject>() {

						@Override
						public void onResponse(JSONObject response) {
							
							Log.d("HomeFragment", "into JsonObjectRequest");
							VolleyLog.d(TAG, "Response: " + response.toString());
							if (response != null) {
								parseJsonFeed(response);
							}
						}
					}, new Response.ErrorListener() {

						@Override
						public void onErrorResponse(VolleyError error) {
							VolleyLog.d(TAG, "Error: " + error.getMessage());
						}
					});

			// Adding request to volley request queue
			AppController.getInstance().addToRequestQueue(jsonReq);
			return rootView;
		}


	/**
	 * Parsing json reponse and passing the data to feed view list adapter
	 * */
	private void parseJsonFeed(JSONObject response) {
		try {
			JSONArray feedArray = response.getJSONArray("feed");
			Log.d("HomeFragment", "in parseJsonFeed");

			for (int i = 0; i < feedArray.length(); i++) {
				JSONObject feedObj = (JSONObject) feedArray.get(i);

				FeedItem item = new FeedItem();
				item.setId(feedObj.getInt("id"));
				item.setName(feedObj.getString("name"));

				// Image might be null sometimes
				String image = feedObj.isNull("image") ? null : feedObj
						.getString("image");
				item.setImge(image);
				item.setStatus(feedObj.getString("status"));
				item.setProfilePic(feedObj.getString("profilePic"));
				item.setTimeStamp(feedObj.getString("timeStamp"));

				// url might be null sometimes
				String feedUrl = feedObj.isNull("url") ? null : feedObj
						.getString("url");
				item.setUrl(feedUrl);

				feedItems.add(item);
			}

			// notify data changes to list adapater
			Adapter.notifyDataSetChanged();
			Log.d("HomeFragment", "notifyDataSetChanged");
		} catch (JSONException e) {
			e.printStackTrace();
			Log.d("HomeFragment", "ran catch");
		}
		Log.d("HomeFragment", "ran full code");
	}      
}
