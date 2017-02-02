package com.example.dennis.androidfinal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    public static final String PREFS_NAME = "PrefsFile";

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;
    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 11;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    //private EditText mPasswordView;
    private Spinner monthSpinner;
    private Spinner daySpinner;
    private Spinner yearSpinner;

    // unusued references
    private View mProgressView;
    private View mLoginFormView;

    // ArrayLists of Months, Days, Years (also has preview text at the end)
    List<String> spinnerMonths = new ArrayList<String>() {{
        add("January");
        add("February");
        add("March");
        add("April");
        add("May");
        add("June");
        add("July");
        add("August");
        add("September");
        add("October");
        add("November");
        add("December");
        add("Month");
    }};

    List<String> spinnerDays = new ArrayList<String>() {{
        add("1");
        add("2");
        add("3");
        add("4");
        add("5");
        add("6");
        add("7");
        add("8");
        add("9");
        add("10");
        add("11");
        add("12");
        add("13");
        add("14");
        add("15");
        add("16");
        add("17");
        add("18");
        add("19");
        add("20");
        add("21");
        add("22");
        add("23");
        add("24");
        add("25");
        add("26");
        add("27");
        add("28");
        add("29");
        add("30");
        add("31");
        add("Day");
    }};

    List<String> spinnerYears = new ArrayList<String>() {{
        add("2010");
        add("2011");
        add("2012");
        add("2013");
        add("2014");
        add("2015");
        add("2016");
        add("2017");
        add("2018");
        add("2019");
        add("2020");
        add("Year");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("Month Value", monthSpinner.getSelectedItem().toString());
                Boolean monthBlank = monthSpinner.getSelectedItem().toString().equals("Month");
                if (monthBlank) {
                    TextView errorText = (TextView)monthSpinner.getSelectedView();
                    errorText.setError("no month selected");
                    errorText.setTextColor(Color.RED);
                    errorText.setText(null);
                    Toast.makeText(getApplicationContext(), "You did not fill out the month field.", Toast.LENGTH_SHORT).show();
                }
                //Log.d("Day Value", daySpinner.getSelectedItem().toString());
                Boolean dayBlank = daySpinner.getSelectedItem().toString().equals("Day");
                if (dayBlank) {
                    TextView errorText = (TextView)daySpinner.getSelectedView();
                    errorText.setError("no day selected");
                    errorText.setTextColor(Color.RED);
                    errorText.setText(null);
                    Toast.makeText(getApplicationContext(), "You did not fill out the day field.", Toast.LENGTH_SHORT).show();
                }
                //Log.d("Year Value", yearSpinner.getSelectedItem().toString());
                Boolean yearBlank = yearSpinner.getSelectedItem().toString().equals("Year");
                if (yearBlank) {
                    TextView errorText = (TextView)yearSpinner.getSelectedView();
                    errorText.setError("no year selected");
                    errorText.setTextColor(Color.RED);
                    errorText.setText(null);
                    Toast.makeText(getApplicationContext(), "You did not fill out the year field.", Toast.LENGTH_SHORT).show();
                }
                if (!monthBlank && !dayBlank && !yearBlank) {
                    saveToDB(view);
                }
            }
        });

       // mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String editTextValue = settings.getString("editTextValue", "none");

        EditText editText = (EditText)findViewById(R.id.email);
        editText.setText(editTextValue);

        // Spinner view for months
        monthSpinner = (Spinner)findViewById(R.id.month_spinner);
        ArrayAdapter<String> monthSpinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerMonths) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount()));
                }
                return v;
            }
            @Override
            public int getCount() {
                return super.getCount()-1;
            }
        };
        monthSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthSpinnerArrayAdapter);
        monthSpinner.setSelection(monthSpinnerArrayAdapter.getCount());
        monthSpinner.setPrompt("Select the current month.");

        // Spinner view for days
        daySpinner = (Spinner)findViewById(R.id.day_spinner);
        ArrayAdapter<String> daySpinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerDays) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount()));
                }
                return v;
            }
            @Override
            public int getCount() {
                return super.getCount()-1;
            }
        };
        daySpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(daySpinnerArrayAdapter);
        daySpinner.setSelection(daySpinnerArrayAdapter.getCount());
        daySpinner.setPrompt("Select the current day of the month.");

        // Spinner view for years
        yearSpinner = (Spinner)findViewById(R.id.year_spinner);
        ArrayAdapter<String> yearSpinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerYears) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount()));
                }
                return v;
            }
            @Override
            public int getCount() {
                return super.getCount()-1;
            }
        };
        yearSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearSpinnerArrayAdapter);
        yearSpinner.setSelection(yearSpinnerArrayAdapter.getCount());
        yearSpinner.setPrompt("Select the current year.");
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        EditText editText = (EditText)findViewById(R.id.email);
        editor.putString("editTextValue", editText.getText().toString());
        editor.commit();

    }

    public void saveToDB(View view) {
        DatabaseHelper mDbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        EditText editText = (EditText)findViewById(R.id.email);
        Spinner spinner1 = (Spinner)findViewById(R.id.month_spinner);
        Spinner spinner2 = (Spinner)findViewById(R.id.day_spinner);
        Spinner spinner3 = (Spinner)findViewById(R.id.year_spinner);
        String compid = editText.getText().toString();
        String month = spinner1.getSelectedItem().toString();
        String day = spinner2.getSelectedItem().toString();
        String year = spinner3.getSelectedItem().toString();
        values.put("compid", compid);
        values.put("month", month);
        values.put("day", day);
        values.put("year", year);

        long newRowId;
        newRowId = db.insert(
                "my_table",
                null,
                values)
        ;


        //String[] projection = {
        //        "compid",
        //        "month",
        //        "day",
        //        "year"
        //};

        //String sortOrder =
        //        "email" + " DESC";

        //Cursor cursor = db.query(
        //        "my_table",
        //       projection,
        //        null,
        //        null,
        //        null,
        //        null,
        //        sortOrder
        //);

        //cursor.moveToFirst();
        //while(cursor.moveToNext()) {
       //     String currID = cursor.getString(
        //            cursor.getColumnIndexOrThrow("email")
        //    );
        //    Log.i("DBData", currID);
        //}

        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  },
                    MY_PERMISSION_ACCESS_FINE_LOCATION );

        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();

        boolean cancel = false;
        View focusView = null;



        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            //showProgress(true);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
           // showProgress(false);

            if (success) {
                login(findViewById(android.R.id.content));
            } else {
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            //showProgress(false);
        }

        public void login(View view) {
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}

