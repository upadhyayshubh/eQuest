package com.hcl.onlineeaxmmobileapp;

import com.hcl.db.MYDBHelper;
import com.hcl.resource.WebResources;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {
	
	MYDBHelper helper;
	TextView tvtotal,tvobtained;
	int count,total=0,obtained=0,negative;
	Cursor rs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		tvtotal=(TextView)findViewById(R.id.tvtotalmarksid);
		tvobtained=(TextView)findViewById(R.id.tvmarksobtainedid);
		
		SharedPreferences sp=getSharedPreferences("user",MODE_PRIVATE);
		count=sp.getInt("COUNT", 2);
		
		total=WebResources.marks*count;
		
		helper = new MYDBHelper(this, MYDBHelper.DATABASE, null,MYDBHelper.VERSION);
		
		for(int initial=1;initial<=count;initial++){
		
		rs=helper.getData(initial);
		rs.moveToFirst();
		
		
	
		String attempted=rs.getString(rs.getColumnIndex("attempted"));
		
		if(attempted.equalsIgnoreCase("right answer")){
			obtained=obtained+WebResources.marks;
		}
		else if(attempted.equalsIgnoreCase("wrong answer")){
			
			obtained=obtained-WebResources.negativemarks;
		}
		
		if (!rs.isClosed()) 
        {
         rs.close();  
        }

		
		}
		String tot=String.valueOf(total);
		String obt=String.valueOf(obtained);
		
		tvtotal.setText(tot);
		tvobtained.setText(obt);
		
		helper.datadelete();
	}
@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
	this.startActivity(new Intent(ResultActivity.this,HomeActivity.class));

}
}
