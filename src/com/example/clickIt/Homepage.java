package com.example.clickIt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Homepage extends Activity
{

	ImageButton play,instructions,exit;
	//MediaPlayer backgroundMusic;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gamehomepage);
		
		play = (ImageButton)findViewById(R.id.imageButton1);
		instructions = (ImageButton)findViewById(R.id.imageButton2);
		exit = (ImageButton) findViewById(R.id.imageButton3);
		
		play.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				Intent intent = new Intent(Homepage.this,com.example.clickIt.MainActivity.class);
				startActivity(intent);
			}
		});
		
		instructions.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				Intent intent = new Intent(Homepage.this, Instructions.class);
				startActivity(intent);
			}
		});
		
		exit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Splash.backgroundMusic.release();
				Splash.backgroundMusic = null;
				finish();
			}
		});
						
	}

}
