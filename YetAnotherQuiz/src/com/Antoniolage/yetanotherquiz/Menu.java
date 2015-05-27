package com.Antoniolage.yetanotherquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Menu extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		Log.d("menu", "Tudo certo aqui");	
	TextView tv= (TextView)findViewById(R.id.score);
		

	Intent intent=getIntent();
	int score= intent.getIntExtra("Rightone",0);
	tv.setText("Respostas certas: "+score);
	
	}
	public void onPause()
	{
		super.onPause();
		finish();
	}
	@Override
	public void onBackPressed()// optional depending on your needs
	{
	    finish(); 
	    // super.onBackPressed(); 
	}
	
	public void StartQuiz(View view){
	Log.d("Menu","Iniciando quiz");
	startActivity(new Intent(Menu.this, GameActivity.class));
	finish();
	}

	}
