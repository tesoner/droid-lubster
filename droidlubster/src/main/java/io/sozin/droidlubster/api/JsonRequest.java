package io.sozin.droidlubster.api;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by anake on 06/05/17.
 */
public class JsonRequest extends JSONObject{
    private final CustomApiManager apiManager;
    public JsonRequest(CustomApiManager apiManager) throws JSONException {
        this.apiManager = apiManager;
        this.put("app_token", apiManager.getAppToken());
    }
    public void setToken(String token) throws JSONException {
        this.put("session_token", token);
    }
}
