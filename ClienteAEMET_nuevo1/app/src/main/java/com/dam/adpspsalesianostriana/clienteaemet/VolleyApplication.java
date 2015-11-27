package com.dam.adpspsalesianostriana.clienteaemet;

import android.app.Application;

import com.android.volley.RequestQueue;

public class VolleyApplication extends Application{

    public static RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
    }
}
