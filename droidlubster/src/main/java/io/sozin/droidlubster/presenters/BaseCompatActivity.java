package io.sozin.droidlubster.presenters;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import ve.com.anake.libs.lubster.R;
import io.sozin.droidlubster.session.Session;
import io.sozin.droidlubster.views.ErrorDialog;


/**
 * Created by anake on 16/04/17.
 */
public abstract class BaseCompatActivity extends AppCompatActivity
        implements BaseActivity {
    protected Session session;
    protected void forcePortrait(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    protected void forceLandscape(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
    public void goTo(Class<?> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    public void goTo(Class<?> activity, Bundle bundle) {
        Intent intent = new Intent(this, activity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public boolean checkSession() {
        boolean sesex = (session == null) ? false : true;
        return sesex;
    }
    public void debug(String message){
        Log.d("debugging", message);
    }
    public void debug(Exception e){
        Log.d("debugging", "Causa: "+e.getCause()+" Tipo: "+e.getClass()+" Mensaje: "+e.getMessage());
    }
    public Session getSession(){
        return session;
    }
    public String getSessionToken(){
        return session.getToken();
    }
    protected ErrorDialog errorDialog(String title, String message, @Nullable String btnText){
        FragmentManager fragmentManager = getSupportFragmentManager();
        ErrorDialog dialog = new ErrorDialog();
        dialog.setArgs(title, message, btnText);
        dialog.show(fragmentManager, "error_alert");
        return dialog;
    }
    protected ErrorDialog errorDialog(String message, @Nullable String btnText){
        String title = getString(R.string.w_error);
        FragmentManager fragmentManager = getSupportFragmentManager();
        ErrorDialog dialog = new ErrorDialog();
        dialog.setArgs(title, message, btnText);
        dialog.show(fragmentManager, "error_alert");
        return dialog;
    }
    protected ErrorDialog errorDialog(String message){
        String title = getString(R.string.w_error);
        FragmentManager fragmentManager = getSupportFragmentManager();
        ErrorDialog dialog = new ErrorDialog();
        dialog.setArgs(title, message, getString(R.string.action_accept));
        dialog.show(fragmentManager, "error_alert");
        return dialog;
    }
    protected ErrorDialog alert(String title, String message, @Nullable String btnText){
        FragmentManager fragmentManager = getSupportFragmentManager();
        ErrorDialog dialog = new ErrorDialog();
        dialog.setArgs(title, message, btnText);
        dialog.show(fragmentManager, "error_alert");
        return dialog;
    }
    protected ErrorDialog alert(String title, String message){
        FragmentManager fragmentManager = getSupportFragmentManager();
        ErrorDialog dialog = new ErrorDialog();
        dialog.setArgs(title, message, getString(R.string.action_accept));
        dialog.show(fragmentManager, "error_alert");
        return dialog;
    }
    protected ErrorDialog alert(String message){
        FragmentManager fragmentManager = getSupportFragmentManager();
        ErrorDialog dialog = new ErrorDialog();
        dialog.setArgs(getString(R.string.prompt_attention), message, getString(R.string.action_accept));
        dialog.show(fragmentManager, "error_alert");
        return dialog;
    }

}
