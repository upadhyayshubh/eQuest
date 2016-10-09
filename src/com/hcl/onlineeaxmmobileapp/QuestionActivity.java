package com.hcl.onlineeaxmmobileapp;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;


import com.hcl.db.MYDBHelper;



import com.hcl.onlineexammobileapp.dto.ExamData;
import com.hcl.resource.WebResources;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class QuestionActivity extends Activity 
{

	Button startbtn;
	ArrayList<ExamData>elist=new ArrayList<ExamData>();
	int count;
	MYDBHelper helper;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startexam);
		
		helper = new MYDBHelper(this, MYDBHelper.DATABASE, null,MYDBHelper.VERSION);
		
		helper.datadelete();
		
		startbtn=(Button)findViewById(R.id.startbtnid);
		
		SharedPreferences sp=getSharedPreferences("user",MODE_PRIVATE);
		String data=sp.getString("Category", "java");
		String url=WebResources.GET_ALL_QUESTIONS+"?cat="+data;
		new GetAllQuestionTask().execute(url);
		

		
		
		startbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent in4=new Intent(QuestionActivity.this , ExamActivity.class);
				startActivity(in4);
				
			}
		});
		
	}
	
	public class GetAllQuestionTask extends AsyncTask<String, Void, String>{
		
			ProgressDialog pd;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd=new ProgressDialog(QuestionActivity.this);
			pd.setMessage("Please Wait...");
			pd.setTitle("Loading Questions");
			pd.show();
			
		}
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String weburl=params[0];
			String result="";
			
			HttpGet get=new HttpGet(weburl);
			
			try {
				HttpClient client=new DefaultHttpClient();
				HttpResponse response=client.execute(get);
				HttpEntity entity=response.getEntity();
				
				InputStream is=entity.getContent();
				
				int x=is.read();
				
				while(x!=-1)
				{
					result =result+(char)x;
					x=is.read();
				}
				
				
				
			} catch (Exception e) {
				result=e.toString();
			}
			
			
			
			return result;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.cancel();
			
			if(result!=null){
				try {
						JSONArray jarr=new JSONArray(result);
					
					for(int i=0;i<jarr.length();i++)
					{
						JSONObject jobj=jarr.getJSONObject(i);
						String qno=jobj.getString("qno");
					    String ques=jobj.getString("ques");
					    String ans1=jobj.getString("ans1");
					    String ans2=jobj.getString("ans2");
					    String ans3=jobj.getString("ans3");
					    String ans4=jobj.getString("ans4");
					    String rightans=jobj.getString("rightans");
					    String category=jobj.getString("category");
					    
					    ExamData e=new ExamData(qno, ques, ans1, ans2, ans3, ans4, rightans, category);
					    elist.add(e);
					    	
					    
					    
					    
					}
					count=elist.size();
					SharedPreferences sp=getSharedPreferences("user",MODE_PRIVATE);
					Editor ed=sp.edit();
					ed.putInt("COUNT", count);
					
					ed.commit();
					int initial;
				 
					for(initial=0;initial<count;initial++)
					{
						
					ExamData quesdata=elist.get(initial);
					
					String qno=String.valueOf(initial+1);
					String ques=quesdata.getQues();
					String ans1=quesdata.getAns1();
					String ans2=quesdata.getAns2();
					String ans3=quesdata.getAns3();
					String ans4=quesdata.getAns4();
					String rightans=quesdata.getRightans();
					String category=quesdata.getCategory();
					
					//Toast.makeText(QuestionActivity.this,qno+" "+ques+" "+ans1+" "+ans2+" "+ans3+" "+ans4 , Toast.LENGTH_LONG).show();
					
					helper.addQues(qno, ques, ans1, ans2, ans3, ans4, rightans, category);
					
					
					
					
					
					}
					
				/*	Cursor rs=helper.getData(1);
					rs.moveToFirst();
					
					int qno=1;
					String ques=rs.getString(rs.getColumnIndex("ques")); 
					String ans1=rs.getString(rs.getColumnIndex("ans1"));
					String ans2=rs.getString(rs.getColumnIndex("ans2"));
					String ans3=rs.getString(rs.getColumnIndex("ans3"));
					String ans4=rs.getString(rs.getColumnIndex("ans4"));
					
					Toast.makeText(QuestionActivity.this,qno+" "+ques+" "+ans1+" "+ans2+" "+ans3+" "+ans4 , Toast.LENGTH_LONG).show();
					
					if (!rs.isClosed()) 
			        {
			         rs.close();  
			        }

					
				*/
					
				} catch (Exception e) {
					// TODO: handle exception
					
					
				}
					
				
				
				
			}
			
		}
		
		
		
	}
	
	
}
