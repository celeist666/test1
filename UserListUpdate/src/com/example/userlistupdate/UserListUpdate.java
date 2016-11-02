package com.example.userlistupdate;

import com.example.userlistupdate.UserDatabaseHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class UserListUpdate extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_list_update);
		Intent i = getIntent();
		final int _id = Integer.parseInt(i.getStringExtra("_id"));
		final String name = i.getStringExtra("name");
		final String city = i.getStringExtra("city");
		final String age = i.getStringExtra("age");
		Button btn1 = (Button)findViewById(R.id.button1);
		Button btn2 = (Button)findViewById(R.id.button2);
		final EditText edit1 = (EditText) findViewById(R.id.et1);
		final EditText edit2 = (EditText) findViewById(R.id.et2);
		final EditText edit3 = (EditText) findViewById(R.id.et3);
		
		edit1.setText(name);
		edit2.setText(city);
		edit3.setText(age);

		
		UserDatabaseHelper helper = new UserDatabaseHelper(this);
        final SQLiteDatabase sqlitedb = helper.getWritableDatabase();
		
		final Cursor cursor = sqlitedb.rawQuery("SELECT * FROM users ", null);
        
//        ListView list = (ListView) findViewById(R.id.ListView01);

        String[] data_columns = new String[] {"name", "city", "age"};
        int[] widgets = new int[] {R.id.TextView01, R.id.TextView02, R.id.TextView03};
        
        
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sqlitedb.delete("users","_id" + "='" +_id+"'" , null);
				Intent i = new Intent(getApplicationContext(), UserList.class);
				startActivity(i);
				finish();
			}
		});
		
		
		
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String name1= edit1.getText().toString();
				String city1= edit2.getText().toString();
				int age1= Integer.parseInt(edit3.getText().toString());
				
				ContentValues values = new ContentValues();
				values.put("name", name1);
		        values.put("city", city1);
		        values.put("age", age1);
		        sqlitedb.update("users", values, "_id" + "='" +_id+"'", null);
		        
//		        		database.update(       s, "id" + "='" + currentActiveId + "'", null) > 0 && database.update(
//		                        TABLE_CHILDREN, newargs, "id" + "='" + id + "'", null) > 0);
				
				
//				sqlitedb.execSQL("UPDATE users SET (name, city, age) = VALUES (name1, city1, age1) WHERE _id = "+_id+";");
//		           db.execSQL("INSERT INTO users " + "(name, city, age)" + "VALUES ('Zang', 'seoul', 40);");
				
//				 values = new ContentValues();
//			        values.put("name", "King");
//			        values.put("city", "Chungbuk");
//			        values.put("age", 45);
//			        sqlitedb2.insert("users", null, values);
//			        
//			        //2. SQLiteDatabase 객체의 update 메소드를 통해 데이터 수정
//			        values = new ContentValues();
//			        values.put("city", "Inchyeon");
//			        sqlitedb2.update("users", values, "name = 'Hong'", null);
				
				
				Intent i = new Intent(getApplicationContext(), UserList.class);
				
//				i.putExtra("name1", edit1.getText());
//				i.putExtra("city1", edit2.getText());
//				i.putExtra("age1", edit3.getText());
//				i.putExtra("_id1", _id);
				startActivity(i);
				finish();
			}
		});
		
	}
	
}
