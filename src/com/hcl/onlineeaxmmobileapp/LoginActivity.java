package com.hcl.onlineeaxmmobileapp;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity
{
    EditText edemil,edpassword;
    Button loginbtn;
    TextView tvreg;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.log);
    	edemil=(EditText)findViewById(R.id.edmail);
    	edpassword=(EditText)findViewById(R.id.edpass);
    	tvreg=(TextView)findViewById(R.id.tvregid);
    	
    	loginbtn=(Button)findViewById(R.id.logbtn);
    	
    	loginbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				
				String email=edemil.getText().toString();
				String password=edpassword.getText().toString();
				
				String data="email="+email+"&password="+password;
				
				String url=WebResources.LOGIN_URL+"?"+data;
				
				
				new LoginTask().execute(url);
				
			}
		});
    	
    	
    	tvreg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent in=new Intent(LoginActivity.this, SignupActivity.class);
				startActivity(in);
				
			}
		});
    	
    }
	
    class LoginTask extends AsyncTask<String, Void, String>
    {
    	
		@Override
		protected void onPreExecute() {
					super.onPreExecute();
					
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
					
					
					Log.e("LoginActiivty onPostExecute", result);
					
					if(result!=null)
					{
						
						if(result.equalsIgnoreCase("not found")){
							
							Toast.makeText(LoginActivity.this, "Please Enter Valid Credentials", Toast.LENGTH_LONG).show();
						}
						
						else if(result!=null){
						
						try {
							
							JSONObject jobj=new JSONObject(result);
							
							String email=jobj.getString("email");
							
							
							
							
							String mobile=jobj.getString("mobile");
							String password=jobj.getString("password");
							String name=jobj.getString("name");
							String college=jobj.getString("college");
							
							
							SharedPreferences sp=getSharedPreferences("user",MODE_PRIVATE);
							Editor ed=sp.edit();
							
							ed.putString("Email", email);
							ed.putString("Password", password);
							ed.putString("Mobile", mobile);
							ed.putString("Name", name);
							ed.putString("College", college);
							ed.commit();
					
							Intent in=new Intent(LoginActivity.this, HomeActivity.class);
							startActivity(in);
							
						
							
							//ed.clear();
							
						} catch (Exception e) {
							Log.e("LoginActiivty onPostExecute", e.toString());
						}
						
						}
						
							
						
						
					}
					
					
					
					
		}
    	
    	
    }
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub	
		
			finish();
			}
	
}









