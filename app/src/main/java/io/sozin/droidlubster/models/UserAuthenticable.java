package io.sozin.droidlubster.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tes1oner on 13/04/17.
 */
public abstract class UserAuthenticable extends Model implements Modelable, Authenticable{

    @Override
    public String toString(){
        try {
            return this.toJson().toString();
        } catch (JSONException e){
            e.printStackTrace();
            return "Error en la conversión JSon."+e.getMessage();
        }
    }
    public static UserAuthenticable fromJson(JSONObject json) throws JSONException{
        return null;
    }

}
