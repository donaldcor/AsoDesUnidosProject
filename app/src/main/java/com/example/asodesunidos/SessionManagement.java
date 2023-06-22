package com.example.asodesunidos;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.asodesunidos.Modelos.cliente;
import com.example.asodesunidos.Modelos.usuario;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "Session";
    String SESSION_KEY_USER = "user";
    String SESSION_KEY_PASS = "password";

    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(usuario u){
        Integer username = u.getId();
        String pass = u.getClave();
        editor.putInt(SESSION_KEY_USER, username).commit();
        editor.putString(SESSION_KEY_PASS, pass).commit();
    }

    public void closeSession(){
        editor.putInt(SESSION_KEY_USER, -1).commit();
        editor.putString(SESSION_KEY_PASS, null).commit();
    }

    public int getUser(){
        return sharedPreferences.getInt(SESSION_KEY_USER, -1);
    }
    public String getPass(){
        return sharedPreferences.getString(SESSION_KEY_PASS, null);
    }
}
