package io.sozin.droidlubster.api;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by anake on 06/05/17.
 */
public class JsonResponse extends JSONObject{
    public JsonResponse(StringBuffer sb) throws JSONException {
        super(sb.toString());
    }
    public boolean hasError() throws JSONException {
        if(this.has("success") && this.getBoolean("success"))
            return false;
        return true;
    }
    public boolean hasError(String errorCode) throws JSONException {

        if(!errorCode.contains("EA"))
            errorCode="EA"+errorCode;
        if(this.has("error") && this.getString("error").equals(errorCode))
            return true;
        return false;
    }
    public String getError() throws JSONException {
        if(this.has("error_bag")){
            JSONObject errorBag = this.getJSONObject("error_bag");
            if(errorBag.has("error_message"))
                return errorBag.getString("error_message");
            else if(errorBag.has("error"))
                return errorBag.getString("error");
            return null;
        }
        return null;
    }
    public boolean hasErrorCode()throws JSONException{
        boolean result = false;
        if(this.has("error_bag")) {
            JSONObject errorBag = this.getJSONObject("error_bag");
            if(errorBag.has("error"))
                result = true;
            errorBag = null;
        }
        return result;
    }
    public String getErrorCode() throws JSONException {
        if(this.has("error_bag")){
            JSONObject errorBag = this.getJSONObject("error_bag");
            if(errorBag.has("error"))
                return errorBag.getString("error");
            else if(errorBag.has("error_message"))
                return errorBag.getString("error_message");
            return null;
        }
        return null;
    }
    public boolean success() throws JSONException {
        return (this.has("success") && this.getBoolean("success") == true) ? true : false;
    }

}
