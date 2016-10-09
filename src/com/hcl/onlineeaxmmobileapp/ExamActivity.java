package com.hcl.onlineeaxmmobileapp;

import com.hcl.db.MYDBHelper;
import com.hcl.onlineexammobileapp.dto.Category;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ExamActivity extends Activity {

	MYDBHelper helper;
	TextView ques1id;
	RadioButton ans1id,ans2id,ans3id,ans4id;
	Button nextbtn,prevbtn,submitbtn;
	int initial=1;
	int count,rbselect;
	Cursor rs;
	String selectedans,rbstr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questions);
		
		SharedPreferences sp=getSharedPreferences("user",MODE_PRIVATE);
		count=sp.getInt("COUNT", 2);
		//String cat=sp.getString("Category", "java");
		
		
		ques1id=(TextView)findViewById(R.id.ques1id);
		ans1id=(RadioButton)findViewById(R.id.ans1id);
		ans2id=(RadioButton)findViewById(R.id.ans2id);
		ans3id=(RadioButton)findViewById(R.id.ans3id);
		ans4id=(RadioButton)findViewById(R.id.ans4id);
		
		nextbtn=(Button)findViewById(R.id.nextbtnid);
		prevbtn=(Button)findViewById(R.id.prevbtnid);
		submitbtn=(Button)findViewById(R.id.submitbtnid);
		
		
		
		
		helper = new MYDBHelper(this, MYDBHelper.DATABASE, null,MYDBHelper.VERSION);
		
		rs=helper.getData(initial);
		rs.moveToFirst();
		
		int qno=initial;
		String ques= "Q-"+qno+": "+ rs.getString(rs.getColumnIndex("ques")); 
		String ans1=rs.getString(rs.getColumnIndex("ans1"));
		String ans2=rs.getString(rs.getColumnIndex("ans2"));
		String ans3=rs.getString(rs.getColumnIndex("ans3"));
		String ans4=rs.getString(rs.getColumnIndex("ans4"));
		
		selectedans=rs.getString(rs.getColumnIndex("ans1"));

		
		ques1id.setText(ques);
		ans1id.setText(ans1);
		ans2id.setText(ans2);
		ans3id.setText(ans3);
		ans4id.setText(ans4);
		
			prevbtn.setVisibility(prevbtn.GONE);
		submitbtn.setVisibility(submitbtn.GONE);
		
		if (!rs.isClosed()) 
        {
         rs.close();  
        }
		
		
		nextbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String quesno=String.valueOf(initial);
				
				rs=helper.getData(initial);
				rs.moveToFirst();
				
				String ans=rs.getString(rs.getColumnIndex("rightans"));
				 
				rbstr=rs.getString(rs.getColumnIndex("marks"));
				if(rbstr.equalsIgnoreCase("1")){
					selectedans=rs.getString(rs.getColumnIndex("ans1"));
				}
				 
				if(selectedans.equalsIgnoreCase(ans)){
				
				helper.attemptupdate(quesno, 1);
				
				//Toast.makeText(ExamActivity.this, "Right answer", Toast.LENGTH_LONG).show();
				
				}
				
				else if(selectedans.equalsIgnoreCase(ans)==false){
					helper.attemptupdate(quesno, 2);
					//Toast.makeText(ExamActivity.this, "Wrong answer", Toast.LENGTH_LONG).show();
				}
				
				if (!rs.isClosed()) 
		        {
		         rs.close();  
		        }

				
				
				
				
				initial=initial+1;
				
				
				if(initial!=count){
				rs=helper.getData(initial);
				rs.moveToFirst();
				
				int qno=initial;
				String ques= "Q-"+qno+": "+ rs.getString(rs.getColumnIndex("ques")); 
				String ans1=rs.getString(rs.getColumnIndex("ans1"));
				String ans2=rs.getString(rs.getColumnIndex("ans2"));
				String ans3=rs.getString(rs.getColumnIndex("ans3"));
				String ans4=rs.getString(rs.getColumnIndex("ans4"));
				
				 rbstr=rs.getString(rs.getColumnIndex("marks"));
					
					
					
					if(rbstr.equalsIgnoreCase("1")){
						ans1id.setChecked(true);
					}
					else if(rbstr.equalsIgnoreCase("2")){
						ans2id.setChecked(true);
					}
					else if(rbstr.equalsIgnoreCase("3")){
						ans3id.setChecked(true);
					}
					else if(rbstr.equalsIgnoreCase("4")){
						ans4id.setChecked(true);
					}
					
					
				ques1id.setText(ques);
				ans1id.setText(ans1);
				ans2id.setText(ans2);
				ans3id.setText(ans3);
				ans4id.setText(ans4);
				
				
				
				prevbtn.setVisibility(prevbtn.VISIBLE);
				}
				
				else if(initial==count){
					
					rs=helper.getData(initial);
					rs.moveToFirst();
					
					int qno=initial;
					String ques= "Q-"+qno+": "+ rs.getString(rs.getColumnIndex("ques")); 
					String ans1=rs.getString(rs.getColumnIndex("ans1"));
					String ans2=rs.getString(rs.getColumnIndex("ans2"));
					String ans3=rs.getString(rs.getColumnIndex("ans3"));
					String ans4=rs.getString(rs.getColumnIndex("ans4"));
					
					 rbstr=rs.getString(rs.getColumnIndex("marks"));
						
						
						
						if(rbstr.equalsIgnoreCase("1")){
							ans1id.setChecked(true);
						}
						else if(rbstr.equalsIgnoreCase("2")){
							ans2id.setChecked(true);
						}
						else if(rbstr.equalsIgnoreCase("3")){
							ans3id.setChecked(true);
						}
						else if(rbstr.equalsIgnoreCase("4")){
							ans4id.setChecked(true);
						}
					
					ques1id.setText(ques);
					ans1id.setText(ans1);
					ans2id.setText(ans2);
					ans3id.setText(ans3);
					ans4id.setText(ans4);
					
					
					
					prevbtn.setVisibility(prevbtn.VISIBLE);
					submitbtn.setVisibility(submitbtn.VISIBLE);
					nextbtn.setVisibility(nextbtn.GONE);
					
					
				}
				
			
				
				
			}
		});
		
		
		submitbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				String quesno=String.valueOf(initial);
				
				rs=helper.getData(initial);
				rs.moveToFirst();
				
				rbstr=rs.getString(rs.getColumnIndex("marks"));
				if(rbstr.equalsIgnoreCase("1")){
					selectedans=rs.getString(rs.getColumnIndex("ans1"));
				}
				
				String ans=rs.getString(rs.getColumnIndex("rightans"));
				
				if(selectedans.equalsIgnoreCase(ans)){
				
				helper.attemptupdate(quesno, 1);
				
				//Toast.makeText(ExamActivity.this, "Right answer", Toast.LENGTH_LONG).show();
				
				}
				
				else if(selectedans.equalsIgnoreCase(ans)==false){
					helper.attemptupdate(quesno, 2);
					//Toast.makeText(ExamActivity.this, "Wrong answer", Toast.LENGTH_LONG).show();
				}
				
				if (!rs.isClosed()) 
		        {
		         rs.close();  
		        }
				
				Intent in5=new Intent(ExamActivity.this, ResultActivity.class);
				startActivity(in5);
				
				
			}
		});
		

		prevbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initial=initial-1;
				if(initial==1){
					rs=helper.getData(initial);
					rs.moveToFirst();
					
					int qno=initial;
					String ques= "Q-"+qno+": "+ rs.getString(rs.getColumnIndex("ques")); 
					String ans1=rs.getString(rs.getColumnIndex("ans1"));
					String ans2=rs.getString(rs.getColumnIndex("ans2"));
					String ans3=rs.getString(rs.getColumnIndex("ans3"));
					String ans4=rs.getString(rs.getColumnIndex("ans4"));
					
					 rbstr=rs.getString(rs.getColumnIndex("marks"));
						
						
						
						if(rbstr.equalsIgnoreCase("1")){
							ans1id.setChecked(true);
						}
						else if(rbstr.equalsIgnoreCase("2")){
							ans2id.setChecked(true);
						}
						else if(rbstr.equalsIgnoreCase("3")){
							ans3id.setChecked(true);
						}
						else if(rbstr.equalsIgnoreCase("4")){
							ans4id.setChecked(true);
						}
					
					ques1id.setText(ques);
					ans1id.setText(ans1);
					ans2id.setText(ans2);
					ans3id.setText(ans3);
					ans4id.setText(ans4);
					
				
					
					submitbtn.setVisibility(submitbtn.GONE);
					nextbtn.setVisibility(nextbtn.VISIBLE);
					prevbtn.setVisibility(prevbtn.GONE);
					
				}
				
				else if(initial>1){
				
				rs=helper.getData(initial);
				rs.moveToFirst();
				
				int qno=initial;
				String ques= "Q-"+qno+": "+ rs.getString(rs.getColumnIndex("ques")); 
				String ans1=rs.getString(rs.getColumnIndex("ans1"));
				String ans2=rs.getString(rs.getColumnIndex("ans2"));
				String ans3=rs.getString(rs.getColumnIndex("ans3"));
				String ans4=rs.getString(rs.getColumnIndex("ans4"));
				  
				  
				 rbstr=rs.getString(rs.getColumnIndex("marks"));
				
				
				
				if(rbstr.equalsIgnoreCase("1")){
					ans1id.setChecked(true);
				}
				else if(rbstr.equalsIgnoreCase("2")){
					ans2id.setChecked(true);
				}
				else if(rbstr.equalsIgnoreCase("3")){
					ans3id.setChecked(true);
				}
				else if(rbstr.equalsIgnoreCase("4")){
					ans4id.setChecked(true);
				}
				 
				 
				
				
				ques1id.setText(ques);
				ans1id.setText(ans1);
				ans2id.setText(ans2);
				ans3id.setText(ans3);
				ans4id.setText(ans4);	
				
			
				submitbtn.setVisibility(submitbtn.GONE);
				nextbtn.setVisibility(nextbtn.VISIBLE);
				}
				
				
			}
		});
		
		
		ans1id.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String Quesno=String.valueOf(initial);
				
				helper.updateselected(Quesno,1);
				
				//Toast.makeText(ExamActivity.this,Quesno , Toast.LENGTH_LONG).show();
				rs=helper.getData(initial);
				rs.moveToFirst();
				selectedans=rs.getString(rs.getColumnIndex("ans1"));
				
				
				
				
				if (!rs.isClosed()) 
		        {
		         rs.close();  
		        }
				
				
				
			}
		});
		
ans2id.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		String Quesno=String.valueOf(initial);
				
				helper.updateselected(Quesno,2);
				//Toast.makeText(ExamActivity.this,Quesno , Toast.LENGTH_LONG).show();
				
				rs=helper.getData(initial);
				rs.moveToFirst();
				selectedans=rs.getString(rs.getColumnIndex("ans2"));
				
				if (!rs.isClosed()) 
		        {
		         rs.close();  
		        }
				
				
				
			}
		});
ans3id.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		String Quesno=String.valueOf(initial);
		
		helper.updateselected(Quesno,3);
		//Toast.makeText(ExamActivity.this,Quesno , Toast.LENGTH_LONG).show();
		rs=helper.getData(initial);
		rs.moveToFirst();
		selectedans=rs.getString(rs.getColumnIndex("ans3"));
		
		if (!rs.isClosed()) 
        {
         rs.close();  
        }
		
		
		
	}
});

ans4id.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		String Quesno=String.valueOf(initial);
		
		helper.updateselected(Quesno,4);
		
		//Toast.makeText(ExamActivity.this,Quesno , Toast.LENGTH_LONG).show();
				
		rs=helper.getData(initial);
		rs.moveToFirst();
		selectedans=rs.getString(rs.getColumnIndex("ans4"));
		
		if (!rs.isClosed()) 
        {
         rs.close();  
        }
		
		
		
	}
});

		
	}
	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		this.startActivity(new Intent(ExamActivity.this,HomeActivity.class));  

	}
}
