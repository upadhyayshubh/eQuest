package com.hcl.onlineeaxmmobileapp;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.hcl.resource.WebResources;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity  extends Activity
{
	EditText edemail,edmobile,edname,edpassword,edcollege;
	Button savebtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				
				setContentView(R.layout.reg);
				
				edemail=(EditText)findViewById(R.id.edemailid);
				edmobile=(EditText)findViewById(R.id.edmobileid);
				edpassword=(EditText)findViewById(R.id.edpasswordid);
				edname=(EditText)findViewById(R.id.ednameid);
				edcollege=(EditText)findViewById(R.id.edcollegeid);
				
				savebtn=(Button)findViewById(R.id.savebtnid);
				
				
				savebtn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
					
						String email=edemail.getText().toString();
						String mobile=edmobile.getText().toString();
						String password=edpassword.getText().toString();
						String name=edname.getText().toString();
						String college=edcollege.getText().toString();
						
						
						String data="email="+email+"&mobile="+mobile+"&password="+password+"&name="+name+"&college="+college;
				
						String url=WebResources.SIGNUP_URL+"?"+data;
						
						new SignUpTask().execute(url);
						
						
					}
				});
	}
	
	
	class SignUpTask extends AsyncTask<String, Void, String>
	{
		ProgressDialog pd;
		@Override
		protected void onPreExecute() {
					super.onPreExecute();
					pd=new ProgressDialog(SignupActivity.this);
					pd.setMessage("Please Wait...");
					
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
					
					Log.e("SignupActivity onPostExecute", result);
					
					if(result!=null)
					{
						if(result.equals("done"))
						{
							Toast.makeText(SignupActivity.this, "SIGNUP DONE", 2).show();
						}
						
					}
		}
		
		
		
	}
	
}













