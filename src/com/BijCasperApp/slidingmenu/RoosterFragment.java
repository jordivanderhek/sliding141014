package com.BijCasperApp.slidingmenu;

import info.androidhive.listviewfeed.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class RoosterFragment extends Fragment {
	
	WebView myWebView;
	
	public RoosterFragment(){}
	
	@SuppressLint("NewApi")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_rooster, container, false);
        
     // zet balk kleur
     		getActivity().getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
     		// set icon ontzichtbaar
     		getActivity().getActionBar().setIcon(
     				   new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        
        WebView myWebView = (WebView)rootView.findViewById(R.id.webView1);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("http://www.jordivanderhek.com/testsight/rooster/");
        
         
        return rootView;
    }
}