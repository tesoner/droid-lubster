package io.sozin.droidlubster.session;

import org.json.JSONException;

import io.sozin.droidlubster.api.JsonResponse;

/**
 * Created by anake on 06/07/17.
 */
public interface Session {
    public boolean save() throws JSONException;
    public void saveWithResponse(JsonResponse response)throws JSONException ;
    public boolean load() throws JSONException;
    public String getDbName();
    public String getToken();
    public void destroy();
    //public void reloadWithResponse(JsonResponse response) throws JSONException;
    public void onSave() throws JSONException;
    public void onLoad() throws JSONException;
    public boolean isGuest();
    public String getCypherKey();

}
