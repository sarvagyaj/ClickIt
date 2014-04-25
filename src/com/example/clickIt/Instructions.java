package com.example.clickIt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Instructions extends Activity {

	ImageButton back;
	TextView buttonText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.instructions);
		
		back = (ImageButton)findViewById(R.id.imageButton4);
		buttonText = (TextView)findViewById(R.id.textView2);
		
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				Intent intent = new Intent(Instructions.this, Homepage.class);
				startActivity(intent);
				
			}
		});
	}
	
	

}
