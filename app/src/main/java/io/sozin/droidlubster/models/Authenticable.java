package io.sozin.droidlubster.models;

import org.json.JSONException;
import org.json.JSONObject;

public interface Authenticable {
    public JSONObject toJson() throws JSONException;
    public String toString();

}