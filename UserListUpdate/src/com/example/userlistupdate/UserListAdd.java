package com.example.userlistupdate;

import android.app.Activity;
import com.example.userlistupdate.UserDatabaseHelper;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class UserListAdd extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_list_add);
		
		UserDatabaseHelper helper = new UserDatabaseHelper(this);
        final SQLiteDatabase sqlitedb = helper.getWritableDatabase();

		Button btn1 = (Button)findViewById(R.id.button1);
		Button btn2 = (Button)findViewById(R.id.button2);
		
		final EditText et1 = (EditText)findViewById(R.id.et1);
		final EditText et2 = (EditText)findViewById(R.id.et2);
		final EditText et3 = (EditText)findViewById(R.id.et3);
		
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), UserList.class);
				startActivity(i);
				finish();
			}
		});
		
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), UserList.class);
				ContentValues values = new ContentValues();
		        values.put("name", et1.getText().toString());
		        values.put("city", et2.getText().toString());
		        values.put("age", Integer.parseInt(et3.getText().toString()));
		        sqlitedb.insert("users", null, values);
		        sqlitedb.close();

				startActivity(i);
				finish();
			}
		});
	}
	
	
	
}
