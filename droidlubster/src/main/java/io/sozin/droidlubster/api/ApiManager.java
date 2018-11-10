package io.sozin.droidlubster.api;

import org.json.JSONException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

/**
 * Created by tes1oner on 13/04/17.
 */
public abstract class ApiManager implements CustomApiManager{
    protected boolean enableSSL = false;
    public static String ERRORS[]={
            ""
    };
    public boolean isEnabledSSL() {
        return enableSSL;
    }
    public void enableSSL(){
        this.enableSSL = true;
    }
    public void disableSSL(){
        this.enableSSL = false;
    }
    public static DataOutputStream prepareConnection(HttpURLConnection connection, JsonRequest request) throws IOException {
        connection.setReadTimeout(10000);
        connection.setConnectTimeout(15000);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type","application/json");
        connection.setDoInput (true);
        connection.setDoOutput (true);
        DataOutputStream dataOutput = new DataOutputStream(connection.getOutputStream ());
        dataOutput.writeBytes(URLEncoder.encode(request.toString(),"UTF-8"));
        dataOutput.flush();
        return dataOutput;

    }
    @Deprecated
    public static String preparePostData(JsonRequest request) throws JSONException, UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> iterator = request.keys();
        while (iterator.hasNext()){
            String key = iterator.next();
            Object value = request.get(key);
            if(first)
                first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(),"UTF-8"));
        }
        return result.toString();
    }
    public HttpURLConnection httpConnection(String uri) throws IOException, MalformedURLException {
        URL url = new URL(getUrlService() + uri);
        return (HttpURLConnection) url.openConnection();
    }
    public HttpsURLConnection httpsConnection(String uri) throws IOException{
        URL url = new URL(getUrlService()+uri);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        SSLContext sc = null;
        try{
            sc = SSLContext.getInstance("TLS");
            sc.init(null, null, new java.security.SecureRandom());
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch (KeyManagementException e){
            e.printStackTrace();
        }
        conn.setSSLSocketFactory(sc.getSocketFactory());
        return conn;
    }

    public String prepareGetData(String uriBase, JsonRequest request) throws JSONException {
        String uri = uriBase+"?";
        Iterator<String> keys = request.keys();
        while (keys.hasNext()){
            String key = keys.next();
            uri+=key+"="+request.getString(key)+"&";
        }
        uri = uri.substring(0, uri.length()-1);
        return uri;
    }
}
