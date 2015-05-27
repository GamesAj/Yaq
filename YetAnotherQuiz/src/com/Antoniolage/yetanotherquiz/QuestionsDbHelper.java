package com.Antoniolage.yetanotherquiz;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class QuestionsDbHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "yaquiz.db";
	private static final String DB_PATH= "/data/data/com.Antoniolage.yetanotherquiz/databases/";
	private static final int SCHEMA_VERSION =1;

	public SQLiteDatabase dbSqlite;
	
	private final Context Mycontext;
	
	public QuestionsDbHelper(Context context) {
		super(context, DB_NAME, null, SCHEMA_VERSION);
		this.Mycontext= context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}
public void CreateDatabase(){
	createDB();
} 

private void createDB(){
	boolean dbExists=DBExists(); 
	if(!dbExists){
		Log.d("DBHelper", "Copying DB");
		this.getReadableDatabase();
		copyDBFromResource();
	}
}

private void copyDBFromResource() {
	Log.d("DBHelper", "Trying to copy DB");
InputStream inputStream =null;
OutputStream outputStream=null;
String dbFilePath= DB_PATH+DB_NAME;
try{
	inputStream= Mycontext.getAssets().open(DB_NAME);
	
	outputStream=new FileOutputStream(dbFilePath);
	
	byte[] Buffer= new byte[1024];
	int lenght;
	while ((lenght=inputStream.read(Buffer))>0){
		outputStream.write(Buffer, 0, lenght);
	}
	outputStream.flush();
	outputStream.close();
	inputStream.close();
	Log.d("DB Helper", "DB Criada");
}catch(IOException e){
	Log.e("DB ERROR", "Erro ao criar DB");
}

}

private boolean DBExists() {
	SQLiteDatabase Db= null;
	String DB_FULL_PATH= DB_PATH+DB_NAME;
	try{
	Db=	SQLiteDatabase.openDatabase(DB_FULL_PATH, null,
                SQLiteDatabase.OPEN_READWRITE);
     Db.setVersion(1);
	}catch(SQLiteException e){Log.e("DB ERROR", "Database nao exite");}
	if(Db!=null){
		Db.close();
		Log.d("DB Helper","DB Existe");
	}
	return Db != null ? true : false;
}
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	// TODO Auto-generated method stub
	
}

public Questions GetQuestion(int id){
	String[] Columns= new String[] {"_id","pergunta","assunto","resposta"};
	SQLiteDatabase db = this.getReadableDatabase();
	Cursor c= db.query("YaQuestions", Columns,"_id"+"=?",new String[] { String.valueOf(id) },null, null, null);
	 try {if (c != null)
	        c.moveToFirst(); 	
	      Questions q=new Questions(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2),c.getString(3));
	
	return q;}finally{
		c.close();
	}
}

public Questions GetAnswer(int id){
	String[] Columns= new String[] {"_id","pergunta","assunto","resposta"};
	SQLiteDatabase db = this.getReadableDatabase();
	Cursor c= db.query("YaQuestions", Columns,"_id"+"=?",new String[] { String.valueOf(id) },null, null, null);
	if (c != null)
        c.moveToFirst(); 	
       Questions a=new Questions(c.getString(2),c.getString(3));
return a;
}
}