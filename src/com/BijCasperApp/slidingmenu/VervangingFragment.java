package com.BijCasperApp.slidingmenu;

import info.androidhive.listviewfeed.R;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class VervangingFragment extends Fragment {

	EditText forminputnaam;
	EditText forminputemail;
	EditText forminputdate;
	EditText forminputvervang;
	EditText forminputopmerking;
	Button sender;
	
	public VervangingFragment(){}
	
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {


		
		
        View rootView = inflater.inflate(R.layout.fragment_vervanging, container, false);
        
        final EditText forminputnaam = (EditText) rootView.findViewById(R.id.forminputnaam);
        final EditText forminputemail   = (EditText) rootView.findViewById(R.id.forminputemail);
        final EditText forminputdate = (EditText) rootView.findViewById(R.id.forminputdate);
        final EditText forminputvervang = (EditText) rootView.findViewById(R.id.forminputvervang);
        final EditText forminputopmerking = (EditText) rootView.findViewById(R.id.forminputopmerking);
        Button sender = (Button) rootView.findViewById(R.id.sender);
        
        forminputnaam.requestFocus();
        
        
        
        
        sender.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				// show alert
	  	        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
	  	        alertDialog.setTitle("controleer de gegevens!");
	  	        alertDialog.setMessage(	"Uw Naam: \n" + forminputnaam.getText().toString() + 
	  	        						"\n\n Uw Email: \n" + forminputemail.getText().toString() + 
	  	        						"\n\n Datum van verval: \n" + forminputdate.getText().toString() + 
	  	        						"\n\n Vervanger: \n" + forminputvervang.getText().toString() + 
	  	        						"\n\n Opmerking: \n" + forminputopmerking.getText().toString());
	  	        
	  	      alertDialog.setButton( Dialog.BUTTON_POSITIVE, "verstuur", new DialogInterface.OnClickListener() {
	  	        public void onClick(DialogInterface dialog, int which) {
	  	        	Intent mEmail = new Intent(Intent.ACTION_SEND);
	  	        	mEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{ "jordivdhek@gmail.com"});
	  	        	mEmail.putExtra(Intent.EXTRA_SUBJECT, "melding van vervanging voor: " + forminputnaam.getText().toString() + " op: " + forminputdate.getText().toString());
	  	        	mEmail.putExtra(Intent.EXTRA_TEXT, "Beste, \n\n" +
	  	        			forminputnaam.getText().toString() + " zal op " + forminputdate.getText().toString() + " vervangen worden door " + forminputvervang.getText().toString() + ". \n"
	  	        			+ "Wanneer Zij/Hij een opmerking heeft achtergelaten staat hij hier: \"" + forminputopmerking.getText().toString() + 
	  	        			" \"\n\n" + "Wanneer hier nog vragen over zijn graag op dit email adres: " + forminputemail.getText().toString() + " om vedere verklaring vragen! \n" +
	  	        			"\nMet vriendelijke groeten, \n" + forminputnaam.getText().toString() + "\nVanuit de Personeels app"
	  	        			
	  	        			);
	  	        	// prompts to choose email client
	  	        	mEmail.setType("message/rfc822");
	  	        	startActivity(Intent.createChooser(mEmail, "Choose an email client to send your"));
	  	        }});

	  	     alertDialog.setButton( Dialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener()    {
	  	       public void onClick(DialogInterface dialog, int which) {
	  	    	 dialog.cancel();
	  	       }});
	  	      
	  	        alertDialog.show();
				 
			   return;
	  	    }});
        
     
     // zet balk kleur
     		getActivity().getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
     		// set icon ontzichtbaar
     		getActivity().getActionBar().setIcon(
     				   new ColorDrawable(getResources().getColor(android.R.color.transparent)));
     		
        return rootView;
    }
}
