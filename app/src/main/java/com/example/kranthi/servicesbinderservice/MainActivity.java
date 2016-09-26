package com.example.kranthi.servicesbinderservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et;
    Integer input;
    MyBinderService myBinderService;
    Boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.result);

    }
    public void bindService(View view){
        Intent i = new Intent(this,MyBinderService.class);
        bindService(i, sc, Context.BIND_AUTO_CREATE);
        status = true;
        Toast.makeText(MainActivity.this, "Service Binded", Toast.LENGTH_SHORT).show();

    }
    public void unbindService(View view){
        if (status){
            unbindService(sc);
            Toast.makeText(MainActivity.this, "Service unbinded", Toast.LENGTH_SHORT).show();
            status = false;
        } else{
            Toast.makeText(MainActivity.this, "Service already unbinded", Toast.LENGTH_SHORT).show();
        }

    }
    public void findFactorial(View view){
        if (status){
            int num = Integer.parseInt(et.getText().toString());
            long result = myBinderService.factorialMethod(num);
            Toast.makeText(MainActivity.this, "Factorial value of " +num + " is:" +result, Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(MainActivity.this, "Please bind the service first", Toast.LENGTH_SHORT).show();
        }

    }
    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBinderService.LocalBinder binder = (MyBinderService.LocalBinder) iBinder;
            myBinderService = binder.getService();
            status = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {


        }
    };
}
