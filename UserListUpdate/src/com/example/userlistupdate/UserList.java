package com.example.userlistupdate;


import android.app.Activity;
import com.example.userlistupdate.UserDatabaseHelper;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;

public class UserList extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
       
        // SQLiteOpenHelper의 확장 구현을 통해 데이터베이스 생성
        UserDatabaseHelper helper = new UserDatabaseHelper(this);
        SQLiteDatabase sqlitedb = helper.getWritableDatabase();
        
        Button btn1 = (Button)findViewById(R.id.button1);
        
        // 쿼리문을 통해 데이터 추가
       
        // 쿼리문을 통해 데이터 수정
       // sqlitedb.execSQL("UPDATE users " + "SET city = 'Chungnam' " + "WHERE name = 'Hong';");
       
        // 쿼리문을 통해 데이터 조회
        final Cursor cursor = sqlitedb.rawQuery("SELECT * FROM users ", null);
        
        ListView list = (ListView) findViewById(R.id.ListView01);

        String[] data_columns = new String[] {"_id", "name", "city", "age"};
        
        int[] widgets = new int[] {R.id.TextView00, R.id.TextView01, R.id.TextView02, R.id.TextView03};

        btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), UserListAdd.class);
				startActivity(i);
				finish();
			}
		});
        
//        btn2.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent i = new Intent(getApplicationContext(), UserListAdd.class);
//				startActivity(i);
//				finish();
//			}
//		});
        
        // 리스트 어댑터를 이용 리스트 뷰에 출력
        if (cursor != null ){
            startManagingCursor(cursor);

            ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.dbview, cursor, data_columns, widgets);

            list.setAdapter(adapter);
        }
//		
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Cursor item = (Cursor)list.getAdapter().getItem(position);
//				int _id = item.getInt(item.getColumnIndex("_id"));
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), UserListUpdate.class);
				i.putExtra("name", cursor.getString(cursor.getColumnIndex("name")));
				i.putExtra("city", cursor.getString(cursor.getColumnIndex("city")));
				i.putExtra("age", cursor.getString(cursor.getColumnIndex("age")));
				i.putExtra("_id", cursor.getString(cursor.getColumnIndex("_id")));
				startActivity(i);
				finish();
			}
        	
		});
        // 데이터베이스 종료
        sqlitedb.close();
        
    }
    
    
}