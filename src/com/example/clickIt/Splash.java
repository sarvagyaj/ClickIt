package com.example.clickIt;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;


public class Splash extends Activity
{

	static MediaPlayer backgroundMusic;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		backgroundMusic = MediaPlayer.create(Splash.this, R.raw.mymusic);
		backgroundMusic.start();
		
		
		Thread timer = new Thread()
		{
			public void run()
			{
				try
				{
					sleep(7000);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
				finally
				{
					Intent intent = new Intent("com.example.clickIt.HOMEPAGE");
					startActivity(intent);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}


	
	
	
	
}
