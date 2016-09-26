package com.example.kranthi.servicesbinderservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by kranthi on 9/25/2016.
 */
public class MyBinderService extends Service{
    private final IBinder mBinder = new LocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalBinder extends Binder{

        public MyBinderService getService(){
            return MyBinderService.this;
        }

    }
    public long factorialMethod(int x){
        long fact = 1;
        for (int i=1;i<=x;i++){
            fact = fact * i;
        }
        return fact;
    }
}
