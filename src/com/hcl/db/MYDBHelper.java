package com.hcl.db;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class MYDBHelper extends SQLiteOpenHelper
{
	public final static int VERSION=1;
	public final static String DATABASE="onlineexam";
	
	Context context;

	public MYDBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		
		this.context=context;

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		
		db.execSQL("create table questions(qno varchar(20)  , ques varchar(20), ans1 varchar(20) ,ans2 varchar(20) ,ans3 varchar(20) ,ans4 varchar(20) ,rightans varchar(20) ,category varchar(20), attempted varchar(20) ,marks varchar(20)) ");
		
	}

	
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS questions");
		onCreate(db);
	}

	public void addQues(String qno, String ques, String ans1, String ans2, String ans3, String ans4, String rightans, String category){
		SQLiteDatabase db = this.getWritableDatabase(); 
		ContentValues contentValues = new ContentValues();
		String attempted="not attempted";
		String marks="1";
	      contentValues.put("qno", qno);
	      contentValues.put("ques", ques);
	      contentValues.put("ans1", ans1);
	      contentValues.put("ans2", ans2);
	      contentValues.put("ans3", ans3);
	      contentValues.put("ans4", ans4);
	      contentValues.put("rightans", rightans);
	      contentValues.put("category", category);
	      contentValues.put("attempted", attempted);
	      contentValues.put("marks", marks);
	      
	      
	      
	      db.insert("questions", null, contentValues);
	     
	}
	
	public Cursor getData(int number) {
		
		
		SQLiteDatabase db = this.getReadableDatabase();
	
	      Cursor res =  db.rawQuery("select * from questions where qno="+number, null );
	      return res;
	}
	
	public void datadelete(){
	SQLiteDatabase db=this.getWritableDatabase();
	db.execSQL("DELETE FROM questions");
	}
	
	
	public void attemptupdate(String quesno, int attempted) {
		// TODO Auto-generated method stub
		if(attempted==1){
		SQLiteDatabase db=this.getWritableDatabase();
		db.execSQL("update questions set attempted='right answer' where qno="+quesno);
		db.close();
		}
		
		else if(attempted==2){
			SQLiteDatabase db=this.getWritableDatabase();
			db.execSQL("update questions set attempted='wrong answer' where qno="+quesno);
			db.close();
		}
		
	}

	public void updateselected(String quesno, int sel) {
		// TODO Auto-generated method stub
		if(sel==1){
			SQLiteDatabase db=this.getWritableDatabase();
			db.execSQL("update questions set marks='1' where qno="+quesno);
			db.close();
			}
			
			else if(sel==2){
				SQLiteDatabase db=this.getWritableDatabase();
				db.execSQL("update questions set marks='2' where qno="+quesno);
				db.close();
			}
			else if(sel==3){
				SQLiteDatabase db=this.getWritableDatabase();
				db.execSQL("update questions set marks='3' where qno="+quesno);
				db.close();
			}
			else if(sel==4){
				SQLiteDatabase db=this.getWritableDatabase();
				db.execSQL("update questions set marks='4' where qno="+quesno);
				db.close();
			}
		
	}
	

	
	
}







