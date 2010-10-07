package com.ajepst.callme;

import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class PicturePicker extends Activity {
	private static final String TAG = "PicturePicker";
	private Context myself;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.d(TAG, "start picturepicker");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picturepicker);
myself = this;
		String[] projection = new String[] {
				ContactsContract.Contacts._ID,
				ContactsContract.Contacts.LOOKUP_KEY,
				ContactsContract.Contacts.DISPLAY_NAME,
				ContactsContract.Contacts.PHOTO_ID, };
		
		Uri contacts = ContactsContract.Contacts.CONTENT_URI;
		Cursor cur = managedQuery(contacts, projection, "Starred = 1",
				null, null);
		DisplayContact[] bitmaps = getColumnData(cur);
		Log.d(TAG, "about to make imageadapter");
        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, bitmaps));
        gridview.setOnItemClickListener( new OnItemClickListener() {
        	@Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
        		DisplayContact contact = (DisplayContact)gridview.getItemAtPosition(position);
        		Long contactId = contact.get_id();
        		
        		Cursor phones = getContentResolver().query( 
        			      ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
        			      null, 
        			      ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactId, 
        			      null, null); 
        		String phoneNumber = null;
        			    while (phones.moveToNext()) { 
        			     phoneNumber = phones.getString( 
        			       phones.getColumnIndex( 
        			         ContactsContract.CommonDataKinds.Phone.NUMBER)); 
        			     if (phoneNumber != null)
        			    	 break;
        			    } 
        			    phones.close();
        			    String url = createTelUrl(phoneNumber);
    			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
    			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    			myself.startActivity(intent);
            }
        });
    }
    
    private String createTelUrl(String number) {
        /*  if (String.isEmpty(number)) {
              return null;
          }*/

          StringBuilder buf = new StringBuilder("tel:");
          buf.append(number);
          return buf.toString();
      }
   
    private DisplayContact[] getColumnData(Cursor cur){ 
        ArrayList<DisplayContact> contacts = new ArrayList<DisplayContact>();
    	Log.d(TAG, "getting data");
    	if (cur.moveToFirst()) {

            String name; 
            Long id;
           // String phoneNumber; 
            int nameColumn = cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME); 
            int picColumn = cur.getColumnIndex(ContactsContract.Contacts.PHOTO_ID);
            int idColumn = cur.getColumnIndex(ContactsContract.Contacts._ID);
            String imagePath; 
        
            do {
                // Get the field values
                name = cur.getString(nameColumn);
                imagePath = cur.getString(picColumn);
                id =  cur.getLong(idColumn);
               Log.d(TAG, "starred name: " + name);
               Log.d(TAG, "image: " + imagePath);
               Uri contentDirUri = ContactsContract.Contacts.CONTENT_URI;
			Uri contactUri = ContentUris.withAppendedId(contentDirUri, id);
               ContentResolver resolver = getContentResolver();
               InputStream photoDataStream = Contacts.openContactPhotoInputStream(resolver,contactUri);
               Bitmap photo = BitmapFactory.decodeStream(photoDataStream);
               if (photo != null){
            	  DisplayContact newContact = new DisplayContact();
            	  newContact.set_bitmap(photo);
            	  newContact.set_id(id);
            	  newContact.set_name(name);
            	  contacts.add(newContact);
               }
            	   
            } while (cur.moveToNext());
            Log.d(TAG, "exiting listmaker");
            return  contacts.toArray( new DisplayContact[ contacts.size() ]);
        }
		return null;
    }

}
