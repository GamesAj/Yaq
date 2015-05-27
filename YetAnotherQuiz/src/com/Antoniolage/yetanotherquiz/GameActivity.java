package com.Antoniolage.yetanotherquiz;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends Activity {
	
public boolean isCorrect;
public int botaon;
public int score;
public int life;

public String questions;
public TextView question;
public TextView resultado;
public Button answer1;
public Button answer2;
public Button answer3;
int[] lquestion=new int[40];

final CountDownTimer cdt=	new CountDownTimer(10000, 1000) {

    public void onTick(long millisUntilFinished) {
        resultado.setText("tempo restante: " + millisUntilFinished / 1000 +"s");
    }

    public void onFinish() {
     GameOver();
    }
 };

static int[] shuffleArray(int[] ar)
{
  Random rnd = new Random();
  for (int i = ar.length - 1; i > 0; i--)
  {
    int index = rnd.nextInt(i+1);
    // Simple swap
    int a = ar[index];
    ar[index] = ar[i];
    ar[i] = a;
   // Log.d("suffled","a["+i+"] ="+ar[i]);
  }
  return ar;
}

public String[] answers;
public	String[] Perguntas= new String[40];
public  String[] Respostas= new String[40];
public static QuestionsDbHelper QuestionsDB=null;
private String correctAnswer="Essa aqui ta certa";
public void onPause(){
cdt.cancel();
super.onPause();
finish();
}
	@Override
 	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		score=0;
		life=1;	
		question = (TextView) findViewById(R.id.pergunta);
		resultado= (TextView)findViewById(R.id.textView1);
		
		answer1= (Button) findViewById(R.id.resp1);
		answer2= (Button) findViewById(R.id.resp2);
		answer3= (Button) findViewById(R.id.resp3);
		QuestionsDB= new QuestionsDbHelper(this);
		QuestionsDB.CreateDatabase();
		Setpergunta();
		setStuff();
	}
	public void GameOver(){
		startActivity(new Intent(GameActivity.this, Menu.class).putExtra("Rightone", score));
		finish();
	}
	public void IsCorrect(View view){
		Button btn= (Button) view;
		if(btn.getText().toString().equalsIgnoreCase(correctAnswer)){
			//resultado.setText("Resposta certa!");
			//Resultanim();
			score+=1;
			cdt.cancel();
			setStuff();

		}else{ 
		if(score<=0){
			score=0;
		}
		GameOver();
		cdt.cancel();
		}
	}
	public void setStuff(){
	int[] p= ERd();
	int a=score;
	int[] o= getRandom();
	if(score<lquestion.length){
		cdt.start();
    question.setText(getQuestion(lquestion[a]));
    answer1.setText(getAnswer(p[o[0]]));
	answer2.setText(getAnswer(p[o[1]]));
	answer3.setText(getAnswer(p[o[2]]));
	correctAnswer= getAnswer(lquestion[a]);
}else{
GameOver();
}
	}
	public int[] ERd(){
			Random rd= new Random();
			int[] a={lquestion[score],0,0};
			while ((a[0]==a[1])|(a[0]==a[2])|(a[1]==a[2])){
			//	a[0]=rd.nextInt(Perguntas.length-1);
				a[1]=rd.nextInt(Perguntas.length-1);
				a[2]=rd.nextInt(Perguntas.length-1);
			}
		a=	shuffleArray(a);
			return a;
		}
	public int[] getRandom(){
	int max =3;

	Random rd= new Random();
	int[] a ={0,0,0};
	while ((a[0]==a[1])|(a[0]==a[2])|(a[1]==a[2])){
		a[0]=rd.nextInt(max);
		a[1]=rd.nextInt(max);
		a[2]=rd.nextInt(max);
	}
	
	return a;
}
	public String getQuestion(int c){
		
		String ask=Perguntas[c];
		return ask;
	}
	public String getAnswer(int c){
		String asw= Respostas[c];
		
		String resp = asw;
			
		return resp;
	}
	private String[] Stuff(int i){

		Questions question=	QuestionsDB.GetQuestion(i);
		String q=	question.getPergunta();
		String c=	question.getResposta();

		String[] ok = {q,c};
		
		return ok;
		
		}
	public void Setpergunta (){
		for(int i=1;i<=40;i++){
			Perguntas[i-1]=Stuff(i)[0];
			Respostas[i-1]=Stuff(i)[1];
			lquestion[i-1]=i-1;
			Log.d("array","["+lquestion[i-1]+"]");
		}
		
		lquestion=shuffleArray(lquestion);
		 for (int i = 0; i < lquestion.length; i++)
		    {
		      System.out.println("lquestio["+i+"] ="+lquestion[i]);
		    		
		    }
		    System.out.println();
		  
		
		return;
				}
	}