package com.felight.android.databases;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

public class DatabasesActivity extends Activity {

	DBAdapter db;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		db = new DBAdapter(this);

		//addContact();

		

		// getOneContact();
		 
		 
		 //updateContact();

		deleteContact();
		retrieveContacts();

		//deleteContacts();
	}

	private void addContact() {
		// --- add a contact ---
		db.open();
		long id = db.insertContact("Mahesh", "mahesh@email.com");
		 id = db.insertContact("Vinay", "vinay@email.com");
		db.close();
	}

	private void retrieveContacts() {
		db.open();
		Cursor c = db.getAllContacts();
		if (c.moveToFirst()) {
			do {
				displayContact(c);
			} while (c.moveToNext());
		} else
			Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
		db.close();
	}

	private void displayContact(Cursor c) {
		Toast.makeText(
				getBaseContext(),
				"id: " + c.getString(0) + "\n" + "Name: " + c.getString(1)
						+ "\n" + "Email: " + c.getString(2) + "\n",
				Toast.LENGTH_LONG).show();
	}

	private void getOneContact() {
		// ---get a contact---
		db.open();
		Cursor c = db.getContact(1);
		if (c.moveToFirst())
			displayContact(c);
		else
			Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
		db.close();

	}

	private void updateContact() {
		// ---update contact---
		db.open();
		if (db.updateContact(13, "mahesh felight", "mahesh@account.com"))
			Toast.makeText(this, "Update successful.", Toast.LENGTH_LONG)
					.show();
		else
			Toast.makeText(this, "Update failed.", Toast.LENGTH_LONG).show();
		db.close();

	}

	private void deleteContact() {
		db.open();
		if (db.deleteContact(1))
			Toast.makeText(this,
					"one record from the table deleted successfully.",
					Toast.LENGTH_LONG).show();
		else
			Toast.makeText(this, "Delete failed.", Toast.LENGTH_LONG).show();
		db.close();
	}

	private void deleteContacts() {
		db.open();
		if (db.deleteContacts())
			Toast.makeText(this,
					"all records from the table deleted successfully.",
					Toast.LENGTH_LONG).show();
		else
			Toast.makeText(this, "Delete failed.", Toast.LENGTH_LONG).show();
		db.close();
	}
}