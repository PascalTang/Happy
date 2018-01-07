package com.pascal.pray.android.network.volley;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class NetworkUtil {

    private static int TIMEOUT = 15000 ; //DefaultRetryPolicy.DEFAULT_TIMEOUT_MS;
    private static int RETRY = 0; //DefaultRetryPolicy.DEFAULT_MAX_RETRIES;

    //GET
    public static void httpGet(Context context, String url) {
        Log.d("ONEdna_net", "httpGet()1: "+url);
        StringRequest stringRequest = createStringRequest(Request.Method.GET, url, null, null, null);
        queue(context, stringRequest);
    }

    public static void httpGet(Context context, String url, final ResponseListener responseListener) {
        Log.d("ONEdna_net", "httpGet()2: "+url);
        StringRequest stringRequest = createStringRequest(Request.Method.GET, url, null, null, responseListener);
        queue(context, stringRequest);
    }

    public static void httpGet(Context context, String url, @Nullable final Map<String, String> headers, @Nullable final Map<String, String> params, @Nullable final ResponseListener responseListener) {
        Log.d("ONEdna_net", "httpGet()3: "+url);
        StringRequest stringRequest = createStringRequest(Request.Method.GET, formatUrl(url, params), headers, null, responseListener);
        queue(context, stringRequest);
    }

    //POST
    public static void httpPost(Context context, String url) {
        Log.d("ONEdna_net", "httpPost()1: "+url);
        StringRequest stringRequest = createStringRequest(Request.Method.POST, url, null, null, null);
        queue(context, stringRequest);
    }

    public static void httpPost(Context context, String url, final ResponseListener responseListener) {
        Log.d("ONEdna_net", "httpPost()2: "+url);
        StringRequest stringRequest = createStringRequest(Request.Method.POST, url, null, null, responseListener);
        queue(context, stringRequest);
    }


    public static void httpPost(Context context, String url, @Nullable final Map<String, String> headers, @Nullable final Map<String, String> params, @Nullable final ResponseListener responseListener) {
        Log.d("ONEdna_net", "httpPost()3: "+url);
        StringRequest stringRequest = createStringRequest(Request.Method.POST, url, headers, params, responseListener);
        queue(context, stringRequest);
    }


    //PUT
    public static void httpPut(Context context, String url) {
        Log.d("ONEdna_net", "httpPut()1: "+url);
        StringRequest stringRequest = createStringRequest(Request.Method.PUT, url, null, null, null);
        queue(context, stringRequest);
    }

    public static void httpPut(Context context, String url, final ResponseListener responseListener) {
        Log.d("ONEdna_net", "httpPut()2: "+url);
        StringRequest stringRequest = createStringRequest(Request.Method.PUT, url, null, null, responseListener);
        queue(context, stringRequest);
    }

    public static void httpPut(Context context, String url, @Nullable final Map<String, String> headers, @Nullable final Map<String, String> params, @Nullable final ResponseListener responseListener) {
        Log.d("ONEdna_net", "httpPut()3: "+url);
        StringRequest stringRequest = createStringRequest(Request.Method.PUT, url, headers, params, responseListener);
        queue(context, stringRequest);
    }


    private static RequestBody getRequestBody(@Nullable final Map<String, String> httpParams){
        RequestBody formBody = null;
        if (null != httpParams) {
            FormBody.Builder formBuilder = new FormBody.Builder();

            for (Map.Entry<String, String> entry : httpParams.entrySet()) {
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
                formBuilder.add(entry.getKey(), entry.getValue());
            }

            formBody = formBuilder.build();
        }
        return formBody;
    }

    //DELETE
    public static void httpDelete(final String url, @Nullable final Map<String, String> httpParams, @Nullable final ResponseListener responseListener) {
        Log.d("ONEdna_net", "httpDelete(): "+url);

        final RequestBody requestBody = getRequestBody(httpParams);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                OkHttpClient client = new OkHttpClient();
                okhttp3.Request.Builder builder = new okhttp3.Request.Builder()
                        .url(url);
                if (null != requestBody) {
                    builder.delete(requestBody);
                }
                okhttp3.Request request = builder.build();

                try {
                    okhttp3.Response response = client.newCall(request).execute();
                    String string = response.body().string();
                    if (null != responseListener) {
                        responseListener.onResponse(string);
                    }
                } catch (IOException e) {
                    if (null != responseListener) {
                        responseListener.onError(null);
                    }
                }
                return null;
            }
        }.execute();


    }

    private static RequestQueue mRequestQueue;

    private static void queue(Context context, StringRequest stringRequest) {
        Log.d("ONEdna_net", "queue(): "+stringRequest);
        if (mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(context, new HurlStack(null, VolleyClientSSLSocketFactory.getSocketFactory(context)));
        }
        setRetryPolicy(stringRequest);
        mRequestQueue.add(stringRequest);
    }



    private static StringRequest createStringRequest(int requestMethod, String url, final Map<String, String> headers, final Map<String, String> params, final ResponseListener responseListener) {
        return new StringRequest(requestMethod, url,
                initListener(responseListener), initErrorListener(responseListener)) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params != null ? params : super.getParams();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers != null ? headers : super.getHeaders();
            }
        };
    }

    private static Response.Listener<String> initListener(final ResponseListener responseListener) {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (null != responseListener)
                    responseListener.onResponse(response);
            }
        };
    }

    private static Response.ErrorListener initErrorListener(final ResponseListener responseListener) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (null != responseListener)
                    responseListener.onError(error);
            }
        };
    }

    private static String formatUrl(String url, Map params) {
        if (null == params) return url;

        StringBuilder stringBuilder = new StringBuilder(url);
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        int i = 1;
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            if (i == 1) {
                stringBuilder.append("?" + entry.getKey() + "=" + entry.getValue());
            } else {
                stringBuilder.append("&" + entry.getKey() + "=" + entry.getValue());
            }
            iterator.remove(); // avoids a ConcurrentModificationException
            i++;
        }
        url = stringBuilder.toString();
        return url;
    }

    public static void setHttpTimeOut(int MS) {
        TIMEOUT = MS;
    }

    public static void setHttpRetry(int retry) {
        RETRY = retry;
    }

    public interface ResponseListener {
        public void onResponse(String responseString);

        public void onError(VolleyError error);
    }

    /**
     * download image from network , and set into imageView
     */

    //notwork
    public static void setImage(Context context, Uri uri, ImageView imageView) {
        if (null != uri && null != imageView) {
            Glide.with(context).load(new File(uri.getPath())).into(imageView);
        }
    }

    private static Activity getActivityFromView(View view) {
        try {
            Activity activity = (Activity) view.getContext();
            return activity;
        } catch (ClassCastException cce) {
            return null;
        }
    }

    private static boolean isViewStillValid(View view) {
        Activity activity = getActivityFromView(view);
        if (null==activity) return false;
        if (activity.isDestroyed() || activity.isFinishing())   return false;
        return true;
    }

    public static void setImage(Context context, String imageUrl, ImageView imageView, int defaultIconResId) {
        if (null != imageUrl && null != imageView) {
            Glide.with(context).load(imageUrl).placeholder(defaultIconResId).crossFade().into(imageView);
        }
    }

    public static void setImage(Context context, String imageUrl, ImageView imageView) {
        if (null != imageUrl && null != imageView) {
            Glide.with(context).load(imageUrl).crossFade().into(imageView);
        }
    }

    // resizes the image to these dimensions (in pixel)
    public static void setImage(Context context, String imageUrl, ImageView imageView, @Nullable Drawable progressDrawble,
                                int width, int height, @Nullable final LoadImageListener loadImageListener) {
        if (null != imageUrl && null != imageView) {
            RequestListener<String, GlideDrawable> requestListener = new RequestListener<String, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    if (null != loadImageListener) {
                        loadImageListener.finish();
                    }
                    return false;
                }
            };

            if (0 == width || 0 == height) {
                Glide.with(context).load(imageUrl).placeholder(progressDrawble).crossFade().listener(requestListener).into(imageView);
            } else
                Glide.with(context).load(imageUrl).placeholder(progressDrawble).override(width, height).crossFade().listener(requestListener).into(imageView);
        }
    }

    public static void setImage(Context context, String imageUrl, ImageView imageView, Drawable progressDrawble) {
        if (null != imageUrl && null != imageView) {
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(progressDrawble)
                    .crossFade()
                    .into(imageView);
        }
    }

    public static synchronized Bitmap getImage(final Context context, final String imageUrl, final BitmapListener bitmapListener) {
        if (null != imageUrl) {
            Bitmap bitmap = null;
            new AsyncTask<Void, Void, Bitmap>() {
                @Override
                protected Bitmap doInBackground(Void... params) {
                    if (Looper.myLooper() == null) {
                        Looper.prepare();
                    }

                    try {
                        return Glide.with(context)
                                .load(imageUrl)
                                .asBitmap().
                                        into(-1, -1).
                                        get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    if (null != bitmap) {
                        // The full bitmap should be available here
                        bitmapListener.get(bitmap);
                    } else bitmapListener.get(null);
                }
            }.execute();
        }
        return null;
    }


    public static void setImage(Context context, String imageUrl, ImageView imageView, Drawable progressDrawble, Drawable errorDrawable) {
        if (null != imageUrl && null != imageView) {
            Glide.with(context)
                    .load(imageUrl)
                    .listener(createRequestListener(imageView, errorDrawable))
//                    .centerCrop() //置中剪裁
                    .placeholder(progressDrawble)
                    .crossFade() //淡化
                    .into(imageView);
        }
    }

    private static RequestListener<String, GlideDrawable> createRequestListener(final ImageView imageView, final Drawable drawable) {
        return new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                imageView.setImageDrawable(drawable);
                return true;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        };
    }


    private static InputStream getInputStream(String urlPath) throws IOException {
        HttpURLConnection urlConnection = null;
        URL url = new URL(urlPath);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoOutput(true);
        //connect
        urlConnection.connect();
        //Stream used for reading the data from the internet
        return urlConnection.getInputStream();
    }

    private static FileOutputStream getOutputStream(File localPath, String fileName) throws FileNotFoundException {
        File file = new File(localPath, fileName);

        FileOutputStream fileOutput = null;
        fileOutput = new FileOutputStream(file);
        return fileOutput;
    }

    /**
     * download file from server
     *
     * @param urlPath   url
     * @param localPath devices path
     * @param fileName  file name
     */
    public static void downloadFile(final String urlPath, final File localPath, final String fileName, final DownloadListener listener) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                int downloadedSize = 0;
                try {
                    FileOutputStream fileOutput = getOutputStream(localPath, fileName);

                    InputStream inputStream = getInputStream(urlPath);

                    byte[] buffer = new byte[1024];
                    int bufferLength = 0;

                    while ((bufferLength = inputStream.read(buffer)) > 0) {
                        fileOutput.write(buffer, 0, bufferLength);
                        downloadedSize += bufferLength;
                    }
                    //close the output stream when complete //
                    fileOutput.close();

                    listener.finish("success");
                } catch (IOException ignored) {
                    ignored.printStackTrace();
                    listener.finish("fail");
                }
                return null;

            }
        }.execute();

    }

    public interface DownloadListener {
        public void finish(String responseString);
    }

    public interface LoadImageListener {
        public void finish();
    }

    public interface BitmapListener {
        public void get(Bitmap bitmap);
    }

    public static void parseVolleyError(VolleyError error) {
        // As of f605da3 the following should work
        NetworkResponse response = error.networkResponse;
        if (error instanceof ServerError && response != null) {
            try {
                String res = new String(response.data,
                        HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                // Now you can use any deserializer to make sense of data
                Log.i("pascal", "res is: " + res);
                JSONObject obj = new JSONObject(res);
                Log.i("pascal", "obj is: " + obj.toString());
            } catch (UnsupportedEncodingException e1) {
                // Couldn't properly decode data to string
                Log.i("pascal", "e1 is: " + e1.toString());
                e1.printStackTrace();
            } catch (JSONException e2) {
                Log.i("pascal", "e2 is: " + e2.toString());
                // returned data is not JSONObject?
                e2.printStackTrace();
            }
        }
    }

    public static void httpUpload(Context context, Request<String> multipartRequest) {
//        getMultipartRequestBodyLog(multipartRequest);
//        getBodyContentType(multipartRequest);
        queue(context, multipartRequest);
    }

    private static void getMultipartRequestBodyLog(Request<String> multipartRequest) {
        try {
            String body = new String(multipartRequest.getBody(), StandardCharsets.UTF_8);
            Log.i("aaa", "MultipartRequestBody : " + body);
        } catch (AuthFailureError authFailureError) {
            authFailureError.printStackTrace();
        }
    }

    private static void getBodyContentType(Request<String> multipartRequest) {
        Log.i("aaa", "MultipartRequestBodyContentType : " + multipartRequest.getBodyContentType());
    }

    private static void queue(Context context, Request<String> multipartRequest) {
        if (mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(context);
        }
        setRetryPolicy(multipartRequest);
        mRequestQueue.add(multipartRequest);
    }

    private static void setRetryPolicy(Request<String> retryPolicy) {
        retryPolicy.setRetryPolicy(new DefaultRetryPolicy(TIMEOUT,
                RETRY,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    private static void setRetryPolicy(StringRequest retryPolicy) {
        retryPolicy.setRetryPolicy(new DefaultRetryPolicy(TIMEOUT,
                RETRY,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }
}

