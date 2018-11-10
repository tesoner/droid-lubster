package io.sozin.droidlubster.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import ve.com.anake.libs.lubster.R;
import io.sozin.droidlubster.models.UserAuthenticable;
import io.sozin.droidlubster.presenters.BaseCompatActivity;
import io.sozin.droidlubster.utils.AppUtils;
import io.sozin.droidlubster.utils.Hash;
import io.sozin.droidlubster.utils.PreferenceManager;


/**
 * Created by tes1oner on 15/04/17.
 */
public abstract class SessionManager implements Session{
    protected BaseCompatActivity context;
    protected UserAuthenticable authenticable;
    protected String token;
    protected boolean guest;
    protected boolean crypt;
    protected JSONObject data;
    private boolean expiration = false;
    private String expirationDate;
    private int version;
    protected SessionManager(BaseCompatActivity context) throws NullPointerException{
        this.context = context;
        this.data = new JSONObject();
        guest = true;

    }
    @Override
    public boolean load() throws JSONException {
        SharedPreferences prefs = context.getSharedPreferences(getDbName(), Context.MODE_PRIVATE);
        String sessionString = prefs.getString("sdata", null);
        if(sessionString == null) {
            guest = true;
            return false;
        }
        guest = false;
        JSONObject json;
        sessionString = Hash.fromB64(sessionString);
        json = new JSONObject(sessionString);
        setToken(json.getString("token"));
        this.version = (json.has("version")) ? json.getInt("version") : 0;
        setExpirationDate(json.getString("expires_at"));
        this.data = json.getJSONObject("data");
        if(this.version != AppUtils.getVersionCode(context) || this.version == 0){
            destroyOld();
            Toast.makeText(context, R.string.message_invalid_session_version, Toast.LENGTH_LONG).show();
            this.destroy();
            this.context.logout();
            return false;
        }
        checkExpirationDate();
        onLoad();
        return true;
    }
    private void checkExpirationDate(){
    }

    public boolean save() throws JSONException {
        boolean success;
        onSave();
        SharedPreferences prefs = context.getSharedPreferences(this.getDbName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("sdata", this.toB64());
        success = editor.commit();
        guest = false;
        return success;
    }
    public void destroy(){
        SharedPreferences prefs = context.getSharedPreferences(getDbName(), Context.MODE_PRIVATE);
        if(!prefs.getString("sdata", "void").equals("void")){
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove("sdata");
            editor.commit();
        }
        if(!prefs.getString("session", "void").equals("void")){
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove("session");
            editor.commit();
        }
    }
    private void destroyOld(){
        PreferenceManager.clearSharedPreferences(context, Hash.toB64(getDbName())+".xml", getDbName()+".xml");
    }

    @Override
    public String toString(){
        JSONObject json = new JSONObject();
        try {

            json.put("version", AppUtils.getVersionCode(context) );
            json.put("token", this.token);
            json.put("expires_at", expirationDate);
            json.put("data", this.data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public boolean isGuest(){
        return this.guest;
    }
    public UserAuthenticable getAuthenticable(){
        return this.authenticable;
    }
    public void enableExpiration() {
        this.expiration = true;
    }
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
    public String getExpirationDate(){
        return this.expirationDate;
    }
    public String toB64(){
        return Hash.toB64(this.toString());
    }

}
