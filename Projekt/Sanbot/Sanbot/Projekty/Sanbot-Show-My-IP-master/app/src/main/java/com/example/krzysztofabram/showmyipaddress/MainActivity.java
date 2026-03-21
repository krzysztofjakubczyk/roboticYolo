package com.example.krzysztofabram.showmyipaddress;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qihancloud.opensdk.base.TopBaseActivity;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends TopBaseActivity {

    /*@BindView(R.id.button)
    Button mButton;

    @BindView(R.id.ip_address)
    TextView mIpAddress;*/

    private Button mButton;
    private TextView mIpAddress;

    private String IPaddress;
    private Boolean IPValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBodyView(R.layout.activity_main);

        ButterKnife.bind(this);

        getWindow().setFlags(128, 128);

        mIpAddress = (TextView) findViewById(R.id.ip_address);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetwordDetect();
            }
        });
    }

    @Override
    protected void onMainServiceConnected() {

    }

    //Check the internet connection.
    private void NetwordDetect() {
        boolean WIFI = false;
        boolean MOBILE = false;

        ConnectivityManager CM = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfo = CM.getAllNetworkInfo();

        for (NetworkInfo netInfo : networkInfo) {
            if (netInfo.getTypeName().equalsIgnoreCase("WIFI"))
                if (netInfo.isConnected())
                    WIFI = true;

            if (netInfo.getTypeName().equalsIgnoreCase("MOBILE"))
                if (netInfo.isConnected())
                    MOBILE = true;
        }

        if(WIFI == true) {
            IPaddress = GetDeviceipWiFiData();
            mIpAddress.setText(IPaddress);
        }

        if(MOBILE == true) {
            IPaddress = GetDeviceipMobileData();
            mIpAddress.setText(IPaddress);
        }

    }


    public String GetDeviceipMobileData(){
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements();) {
                NetworkInterface networkinterface = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = networkinterface.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("Current IP", ex.toString());
        }
        return null;
    }

    public String GetDeviceipWiFiData() {
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);

        @SuppressWarnings("deprecation")
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        return ip;
    }

}
