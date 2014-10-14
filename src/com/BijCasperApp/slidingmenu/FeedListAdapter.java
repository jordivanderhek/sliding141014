package com.BijCasperApp.slidingmenu;

import info.androidhive.listviewfeed.R;
import com.BijCasperApp.slidingmenu.FeedImageView;
import com.BijCasperApp.slidingmenu.AppController;
import com.BijCasperApp.slidingmenu.FeedItem;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class FeedListAdapter extends BaseAdapter {	
	
	private Activity activity;
	private LayoutInflater inflater;
	private List<FeedItem> feedItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public FeedListAdapter(Activity activity, List<FeedItem> feedItems) {
		Log.d("FeedListAdapter", "in FeedListAdapter");
		this.activity = activity;
		Log.d("FeedListAdapter", "in FeedListAdapter2");
		this.feedItems = feedItems;
		Log.d("FeedListAdapter", "in FeedListAdapter3");
		
	}

	@Override
	public int getCount() {
		Log.d("FeedListAdapter", "in getcount");
		return feedItems.size();
		
	}

	@Override
	public Object getItem(int location) {
		Log.d("FeedListAdapter", "in getItem");
		return feedItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		Log.d("FeedListAdapter", "in getItemId");
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d("FeedListAdapter", "making feed");
		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Log.d("FeedListAdapter", "after inflater");
		if (convertView == null)
			convertView = inflater.inflate(R.layout.feed_item, parent, false);
		Log.d("FeedListAdapter", "after layout");
		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		Log.d("FeedListAdapter", "after imageloader");
		TextView name = (TextView) convertView.findViewById(R.id.name);
		TextView timestamp = (TextView) convertView
				.findViewById(R.id.timestamp);
		TextView statusMsg = (TextView) convertView
				.findViewById(R.id.txtStatusMsg);
		TextView url = (TextView) convertView.findViewById(R.id.txtUrl);
		NetworkImageView profilePic = (NetworkImageView) convertView
				.findViewById(R.id.profilePic);
		FeedImageView feedImageView = (FeedImageView) convertView
				.findViewById(R.id.feedImage1);

		FeedItem item = feedItems.get(position);
		Log.d("FeedListAdapter", "after get position");
		name.setText(item.getName());
		Log.d("FeedListAdapter", "after get name");
		// Check for empty status message
		if (!TextUtils.isEmpty(item.getStatus())) {
			statusMsg.setText(item.getStatus());
			statusMsg.setVisibility(View.VISIBLE);
		} else {
			// status is empty, remove from view
			statusMsg.setVisibility(View.GONE);
		}

		// Check for timestamp
				if (!TextUtils.isEmpty(item.getTimeStamp())) {
					timestamp.setText(item.getTimeStamp());
					timestamp.setVisibility(View.VISIBLE);
				} else {
					// status is empty, remove from view
					timestamp.setVisibility(View.GONE);
				}

		
		// Checking for null feed url
		if (item.getUrl() != null) {
			url.setText(Html.fromHtml("<a href=\"" + item.getUrl() + "\">"
					+ item.getUrl() + "</a> "));

			// Making url clickable
			url.setMovementMethod(LinkMovementMethod.getInstance());
			url.setVisibility(View.VISIBLE);
		} else {
			// url is null, remove from the view
			url.setVisibility(View.GONE);
		}

		// user profile pic
		profilePic.setImageUrl(item.getProfilePic(), imageLoader);

		// Feed image
		if (item.getImge() != null) {
			feedImageView.setImageUrl(item.getImge(), imageLoader);
			feedImageView.setVisibility(View.VISIBLE);
			feedImageView
					.setResponseObserver(new FeedImageView.ResponseObserver() {
						@Override
						public void onError() {
						}

						@Override
						public void onSuccess() {
						}
					});
		} else {
			feedImageView.setVisibility(View.GONE);
		}
		Log.d("FeedListAdapter", "FeedListAdapter ran fully");
		return convertView;
	}

}
