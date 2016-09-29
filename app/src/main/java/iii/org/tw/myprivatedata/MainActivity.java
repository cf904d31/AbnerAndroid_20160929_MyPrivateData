package iii.org.tw.myprivatedata;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private TelephonyManager tmgr;
    private AccountManager amgr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT > 23) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_PHONE_STATE},
                            1);
            }
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_SMS},
                        1);
            }
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.GET_ACCOUNTS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.GET_ACCOUNTS},
                        1);
            }
        }

        tmgr = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        String imei = tmgr.getDeviceId();
        String imsi = tmgr.getSubscriberId();

        Log.d("Abner",imei);
        Log.d("Abner",imsi);

        amgr = (AccountManager) getSystemService(ACCOUNT_SERVICE);
        Account[] account = amgr.getAccounts();
        for (Account a : account) {
            String accountName = a.name;
            String accountType = a.type;
            Log.d("Abner",accountName + ":" + accountType);
        }
        tmgr.listen(new MyPhoneStateListener(),PhoneStateListener.LISTEN_CALL_STATE);

    }

    private class MyPhoneStateListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            if (state == TelephonyManager.CALL_STATE_IDLE) {

            } else if (state == TelephonyManager.CALL_STATE_OFFHOOK) {

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void b1(View v) {
        ContentResolver contentResolver = getContentResolver();
        String name = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
        String number = ContactsContract.CommonDataKinds.Phone.NUMBER;
        Cursor c = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                ,new String[]{name,number},null,null,name);
        if (c.moveToNext()) {
            String dname = c.getString(c.getColumnIndex(name));
            String dnumber = c.getString(c.getColumnIndex(number));
            Log.d("Abner",dname + ":" + dnumber);
        }
    }
    public void b2(View v) {

    }
    public void b3(View v) {

    }
}
