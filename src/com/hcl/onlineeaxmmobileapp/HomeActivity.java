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
import com.hcl.onlineexammobileapp.dto.Category;
import com.hcl.onlineexammobileapp.dto.Student;
import com.hcl.resource.WebResources;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HomeActivity extends Activity
{
	ListView lv;
	ArrayAdapter<Category>sadap;
	ArrayList<Category>slist=new ArrayList<Category>();

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.home);
		
		lv=(ListView)findViewById(R.id.slistid);
		
		String url=WebResources.GET_ALL_CATEGORY_URL;
		
		new GetAllStudentTask().execute(url);
		
		

		
		
	}
	
	class GetAllStudentTask extends AsyncTask<String, Void, String>
	{

		ProgressDialog pd;
		@Override
		protected void onPreExecute() {
					super.onPreExecute();
					pd=new ProgressDialog(HomeActivity.this);
					pd.setMessage("Please Wait...");
					pd.setTitle("Loading Exam Categories");
					pd.show();
		}
		@Override
		protected String doInBackground(String... params) {
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
	
			super.onPostExecute(result);
			pd.cancel();
			
			if(result!=null)
			{
				try {
					
					JSONArray jarr=new JSONArray(result);
					
					for(int i=0;i<jarr.length();i++)
					{
						JSONObject jobj=jarr.getJSONObject(i);
						
						String cid=jobj.getString("cid");
						String cname=jobj.getString("cname");
						String total=jobj.getString("total");
						
						
						Category c=new Category(cid, cname, total);
						
						slist.add(c);
						
						
					}
					sadap=new ArrayAdapter<Category>(HomeActivity.this, android.R.layout.simple_list_item_1, slist);
					
					lv.setAdapter(sadap);
					
					lv.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							// TODO Auto-generated method stub
							
							
							Category c=slist.get(arg2);
							
							String cat=c.getCname();
							SharedPreferences sp=getSharedPreferences("user",MODE_PRIVATE);
							Editor ed=sp.edit();
							ed.putString("Category", cat);
							
							ed.commit();
							
							
							Intent in1=new Intent(HomeActivity.this , QuestionActivity.class);
							startActivity(in1);
							
							
						}
						
					
					});
					
					
					
					
					
				} catch (Exception e) {
					Log.e("error in json", e.toString());
				}
				
				
				
			}
			
		}
		
		
		
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent in7=new Intent(HomeActivity.this, LoginActivity.class);
		in7.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		in7.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		in7.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		startActivity(in7);
	}
}












