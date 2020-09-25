package com.uuu;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Data;
import android.util.Log;

public class ContentProviderExerciseActivity extends Activity {
    /** Called when the activity is first created. */
   	private final String LogTag = "CPTest";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	/** Called when the activity is first created. */
 
 
    		// 設定查詢的統一資料識別源，也就是查詢Data表格
    		Uri contacts = Data.CONTENT_URI;
    		// 設定要傳回的欄位，id, data的型態和主要的資料
    		String[] selectedColumns = new String[] { Data._ID, Data.MIMETYPE,
    				Data.DATA1 };
            // 使用Activity下被管理的查詢

		//    		Cursor managedCursor = managedQuery(contacts, selectedColumns, null,  //old api
		//    				null, Data.DISPLAY_NAME + " ASC");


		try(Cursor managedCursor = getContentResolver().query(contacts, selectedColumns, null, //new api
				null, Data.DISPLAY_NAME + " ASC")){
			//迭代Cursor，並且將資料取出
			if (managedCursor.moveToFirst()) {
				//依照Cursor中回傳欄位的索引
				int idPos = managedCursor.getColumnIndex(Data._ID);
				int mimePos = managedCursor.getColumnIndex(Data.MIMETYPE);
				int data1Pos = managedCursor.getColumnIndex(Data.DATA1);
				do {
					// 取得每一筆Cursor中的欄位值
					long id = managedCursor.getLong(idPos);
					String mimeType = managedCursor.getString(mimePos);
					String data1 = managedCursor.getString(data1Pos);
					Log.w(LogTag, "The id is:" + id);
					Log.w(LogTag, "The MIMETYPE is: " + mimeType);
					Log.w(LogTag, "The primary data is:" + data1);
				} while (managedCursor.moveToNext());

			}

		}



    }
}