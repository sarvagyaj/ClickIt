package com.example.clickIt;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class OnGameOver extends Activity{
	 TextView tvReason ;
	static String reason;
	private static int totalScore=0;
	private int currentScore = 0;
	
	
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ongameover);
		
		tvReason = (TextView) findViewById(R.id.tvReason);
		tvReason.setText(reason);
		
		TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setText("Your score : "+MainActivity.score); 
		currentScore = MainActivity.score;
		SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
		 totalScore = preferences1.getInt("TotalScore", 0) + currentScore;
		
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		  SharedPreferences.Editor editor = preferences.edit();
		  editor.putInt("TotalScore", totalScore);
		  editor.commit();
		  
		  TextView score = (TextView) findViewById(R.id.tvtotalScore);
		  score.setText("Total Score : "+ totalScore);
		
		ImageButton btn = (ImageButton) findViewById(R.id.ibPlayAgain);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(OnGameOver.this, Homepage.class);
				startActivity(i);
			}
		});
		btn.setVisibility(0);
	}
}
