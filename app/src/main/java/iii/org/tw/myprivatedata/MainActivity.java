package iii.org.tw.myprivatedata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;

public class MainActivity extends AppCompatActivity {
    private TelephonyManager tmgr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        tmgr = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
    }
}
