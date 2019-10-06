package com.google.firebase.locationfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isServiceOk())
        {
            init();
        }
    }

    private void init() {
        Button button=findViewById(R.id.map_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean isServiceOk() {
        Log.d(TAG, "isServiceOk: checking google service version ");

        int available = GoogleApiAvailability.getInstance().
                isGooglePlayServicesAvailable(MainActivity.this);

        if (available == ConnectionResult.SUCCESS) {
            Log.d(TAG, "isServiceOk:google service version working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Log.d(TAG, "isServiceOk: an error is occur but we can fix it ");

            Dialog dialog = GoogleApiAvailability.getInstance().
                    getErrorDialog(MainActivity.this,
                            available, ERROR_DIALOG_REQUEST);
            dialog.show();

        } else {
            Toast.makeText(this, "you can't make map request",
                    Toast.LENGTH_SHORT).show();
        }
        return false;

    }
}
