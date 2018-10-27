package com.example.xyzreader.remote;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RemoteEndpointUtil {
    private static final String TAG = "RemoteEndpointUtil";

    private RemoteEndpointUtil() {
    }

    public static JSONArray fetchJsonArray() {
        String itemsJson = null;
        try {
            itemsJson = fetchPlainText(Config.BASE_URL);
        } catch (IOException e) {
            Log.e(TAG, "Error fetching items JSON", e);
            return null;
        }

        // Parse JSON
        try {
            Log.d("json", "json parser started");
            JSONArray jsonArray = new JSONArray(itemsJson);
            if (!(jsonArray instanceof JSONArray)) {
                throw new JSONException("Expected JSONArray");
            }
            Log.d("json", "json parser ended");
            return jsonArray;
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing items JSON", e);
        }
        return null;
    }





    static String fetchPlainText(URL url) throws IOException {
Log.d("http", "request started");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        Log.d("http", "request ended");
        return response.body().string();
    }
}
