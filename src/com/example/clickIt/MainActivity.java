package com.example.clickIt;

import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	Random r;
	static Integer score = 0;
	ImageView  testImageView, bcImageView, clickImageView, wrongBall;
	TextView scoreValue;
	int height, width;
	Timer timer,boundarycheckTimer, givTimer;
	RunTask obj;
	BoundaryCheck objBC;
	GenerateImageView objGIV;
	static int checkSign=0,img_width;
	static int lastXCoord=0;
	int speedChecker = 0;
	int[][] imgCoordinates = new int[14][2];
	RelativeLayout relLayout;
	ImageView imageView;
	static int imageId = 1;
	int numberOfImages=1;
	int[][] initialImgCoordinates = new int[14][2];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		relLayout=(RelativeLayout) findViewById(R.id.relativeLayout1);
		//relLayout.setOrientation(RelativeLayout.VERTICAL);
		scoreValue =(TextView) findViewById(R.id.scoreValue);
        
		r = new Random();
		score = 0;
		scoreValue.setText(score.toString());
		firstCall();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		Display display = getWindowManager().getDefaultDisplay();
		DisplayMetrics ds = new DisplayMetrics();
		display.getMetrics(ds);
		//Point size = new Point();
		//display.getSize(size);
		Window w = this.getWindow();
		width = w.getDecorView().getWidth();
		height = w.getDecorView().getHeight();
		//width = ds.widthPixels;
		//height = ds.heightPixels;
		
		Toast.makeText(getBaseContext(), width+" "+height, Toast.LENGTH_LONG).show();
	}
	
	public void firstCall()
	{
		if(timer != null)
		{
		     timer.cancel();
		}
		timer = new Timer();
		obj = new RunTask();
		timer.schedule(obj,1000,100);
		
		if(boundarycheckTimer != null)
		{
			boundarycheckTimer.cancel();
		}
		
		objBC = new BoundaryCheck();
		boundarycheckTimer = new Timer();
		boundarycheckTimer.schedule(objBC,1000,100);
		
		if(givTimer != null)
		{
			givTimer.cancel();
		}
		
		objGIV = new GenerateImageView();
		givTimer = new Timer();
		givTimer.schedule(objGIV,1000,1000);
	}

	class RunTask extends TimerTask
	{
		Display display = getWindowManager().getDefaultDisplay();
		@Override
		public void run()
		{
				runOnUiThread(new Runnable()
				{
					@Override 
					public void run()
					{
						
							//iv1.setY(iv1.getY()+5.0f+ speed);
							for(int i= 1 ;i<numberOfImages;i++)
							{
								testImageView = (ImageView) findViewById(i);
								testImageView.setY(testImageView.getY()+5.0f);
							}
						}
					
				});
		}
	}
	
	class BoundaryCheck extends TimerTask
	{
		@Override
		public void run()
		{
				runOnUiThread(new Runnable()
				{
					@Override
					public void run()
					{
						
						for(int i= 1; i< numberOfImages ; i++)
						{
							bcImageView = (ImageView) findViewById(i);
							bcImageView.getLocationOnScreen(imgCoordinates[i]);
							if(imgCoordinates[i][1] >= height)
							{
								if(i%3==0)
								{
									bcImageView.setX(generateRandomXCoordinate());
									bcImageView.setY(0.0f);
								}
								else
									onGameOverIntent();
							}
							
						}
						
					}
				});
		}
	}
	
	public void onGameOverIntent()
	{
		OnGameOver.reason = "You Missed the Strawberry" ;
		onExitMainActivity();
	}
	
	class GenerateImageView extends TimerTask
	{
		@Override
		public void run()
		{
			
			runOnUiThread(new Runnable()
			{
				
				@Override
				public void run()
				{
					int xCoord;
					xCoord = generateRandomXCoordinate();
					System.out.println(xCoord);
					
					newImageView();
					imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
					initialImgCoordinates[imageId][0]=xCoord;
					//imageView.setMaxHeight(height/200);
					imageView.setX(xCoord);
					imageView.setY(0.0f);
					imageView.setId(imageId);
					imageId++;
					if(numberOfImages < 13)
					{
						if(numberOfImages%3 == 0)
						{
							imageView.setImageResource(R.drawable.strawberry_jelly);
							imageView.setOnClickListener(new View.OnClickListener() {
							
							@Override
							public void onClick(View v) {
									// TODO Auto-generated method stub
									wrongImageClickEvent();
								}
							});
						}
						else
						{
							imageView.setImageResource(R.drawable.strawberry);
							imageView.setOnClickListener(new View.OnClickListener() {
							
								@Override
								public void onClick(View v) {
								// TODO Auto-generated method stub
									img_width = imageView.getWidth();
									imageClickEvent(v.getId());
								}
							});
						}
			        //adding view to layout
						imageView.getLayoutParams().height = height/5;
						imageView.getLayoutParams().width = width/5;
						
						relLayout.addView(imageView);
						setContentView(relLayout);
						numberOfImages++;
					}
					else
					{
						givTimer.cancel();
					}
				}
			});
		}
	}
	public void newImageView()
	{
		imageView = new ImageView(this);
	}
	public void imageClickEvent(int id)
	{		
		score++;
		scoreValue.setText(score.toString());
		
		clickImageView = (ImageView) findViewById(id);
		clickImageView.setX(generateRandomXCoordinate());
		clickImageView.setY(0.0f);
	}
	public int generateRandomXCoordinate()
	{
		int i;
		
		if ( numberOfImages == 1)
		{
			lastXCoord = r.nextInt(width/2);
			return lastXCoord;
		}
		else if(checkSign == 0)
		{
			checkSign = 1;
			//i = r.nextInt(width-width/2);
			while(true)
			{
				i = r.nextInt(width/2)+r.nextInt(width/2)-img_width;
				if(i <= lastXCoord+img_width && i>=lastXCoord)
					continue;
				else
				{
					lastXCoord = i;
					return lastXCoord;
				}
			}
	 
		}
		else
		{
			checkSign = 0;
			while(true)
			{
				i = r.nextInt(width/2);
				if(i <= lastXCoord+img_width && i>=lastXCoord)
					continue;
				else
				{
					lastXCoord = i;
					return lastXCoord;
				}
			}
			
		}
		
	}
	public void wrongImageClickEvent()
	{
		OnGameOver.reason = "You clicked not on fruit but on jelly :(";
		onExitMainActivity();
	}
	
	public void onExitMainActivity()
	{
		ImageView onExitImageView;
		timer.cancel();
		objBC.cancel();
		objGIV.cancel();
		
		//testImageView =null;
		for(int i=1; i<numberOfImages;i++)
		{
			onExitImageView = (ImageView) findViewById(i);
			onExitImageView.setVisibility(ImageView.GONE);
			//testImageView = null;
		}
		
		Arrays.fill(imgCoordinates, null);
		numberOfImages = 1;
		relLayout = null;
		bcImageView = null;
		testImageView = null;
		imageId=1;
		clickImageView = null;
		wrongBall = null;
		
		Intent i =new Intent(getBaseContext(), OnGameOver.class );
		startActivity(i);
	}
}