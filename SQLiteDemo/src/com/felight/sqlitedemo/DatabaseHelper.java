package com.felight.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "MyDbName";
	private static final int DB_VERSION = 1;

	private static final String CONTACTS_TABLE = "contacts";
	public static final String KEY_ROWID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_EMAIL = "email";
	private static final String CONTACTS_TABLE_CREATE_STATEMENT = "create table contacts "
			+ "(id integer primary key autoincrement, "
			+ "name text not null, " + "email text not null);";
	private static final String CONTACTS_TABLE_DROP_STATEMENT = "DROP TABLE IF EXISTS "
			+ CONTACTS_TABLE;

	private SQLiteDatabase db;
	private DatabaseHelper databaseHelper;

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CONTACTS_TABLE_CREATE_STATEMENT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(CONTACTS_TABLE_DROP_STATEMENT);
		onCreate(db);
	}

	public DatabaseHelper open() throws SQLException {
		db = getWritableDatabase();
		databaseHelper = this;
		return this;
	}

	public void close() {
		databaseHelper.close();
	}

	public long insertContact(String name, String email) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_EMAIL, email);
		return db.insert(CONTACTS_TABLE, null, initialValues);
	}

	// ---deletes a particular contact---
	public boolean deleteContact(long rowId) {
		return db.delete(CONTACTS_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	// ---deletes all contacts---
	public boolean deleteContacts() {
		return db.delete(CONTACTS_TABLE, null, null) > 0;
	}

	
	
			//Retrieves all the contacts
			public Cursor getAllContacts() {
				return db.query(CONTACTS_TABLE, new String[] { KEY_ROWID, KEY_NAME,
						KEY_EMAIL }, null, null, null, null, null);
			}
		
			//Retrieves a particular contact
			public Cursor getContact(long rowId) throws SQLException {
				Cursor mCursor = db.query(true, CONTACTS_TABLE, new String[] {
						KEY_ROWID, KEY_NAME, KEY_EMAIL }, KEY_ROWID + "=" + rowId,
						null, null, null, null, null);
				if (mCursor != null) {
					mCursor.moveToFirst();
				}
				return mCursor;
			}
			
			
			
			
			//Updates a Contact
			public boolean updateContact(long rowId, String name, String email) {
				ContentValues args = new ContentValues();
				args.put(KEY_NAME, name);
				args.put(KEY_EMAIL, email);
				return db.update(CONTACTS_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
			}

			
			
			
			
}
