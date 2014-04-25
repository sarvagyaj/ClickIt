package com.example.bikerace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class OnGameOver extends Activity{
	 TextView tvReason ;
	static String reason;
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ongameover);
		
		tvReason = (TextView) findViewById(R.id.tvReason);
		tvReason.setText(reason);
		
		TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setText("Your score : "+MainActivity.score);
		
		ImageButton btn = (ImageButton) findViewById(R.id.ibPlayAgain);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(OnGameOver.this, Homepage.class);
				startActivity(i);
			}
		});
	}
}
