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

public class VoorwaardenFragment extends Fragment {
	
	public VoorwaardenFragment(){}
	
	@SuppressLint("NewApi")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_voorwaarden, container, false);
        
     // zet balk kleur
     		getActivity().getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
     		// set icon ontzichtbaar
     		getActivity().getActionBar().setIcon(
     				   new ColorDrawable(getResources().getColor(android.R.color.transparent)));
         
        return rootView;
    }
}
