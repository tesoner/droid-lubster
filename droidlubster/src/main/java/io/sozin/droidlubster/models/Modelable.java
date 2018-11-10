package io.sozin.droidlubster.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by anake on 08/05/17.
 */
public interface Modelable{
    public JSONObject toJson() throws JSONException;

}
