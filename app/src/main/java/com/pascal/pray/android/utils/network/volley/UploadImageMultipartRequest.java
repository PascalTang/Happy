package com.pascal.pray.android.utils.network.volley;

import android.support.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by pascal on 12/30/16.
 */

public class UploadImageMultipartRequest extends Request<String> {
    private MultipartEntity entity = new MultipartEntity();

    private final NetworkUtil.ResponseListener mListener;
    private final File mFilePart;
    private final String mServiceAccount;
    private final String mTokenID;
    private String mFoodTitle;
    private String mTargetCalories;

    public UploadImageMultipartRequest(String url , final NetworkUtil.ResponseListener listener, String serviceAccount, String tokenID, String targetCalories, String foodTitle , @Nullable File file) {
        super(Method.POST, url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        });

        mListener = listener;
        mFilePart = file;
        mServiceAccount = serviceAccount;
        mTokenID = tokenID;
        mFoodTitle = foodTitle;
        mTargetCalories = targetCalories;
        buildMultipartEntity();
    }

    public UploadImageMultipartRequest(String url, final NetworkUtil.ResponseListener listener, String serviceAccount, String tokenID, @Nullable File file) {
        super(Method.POST, url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        });

        mListener = listener;
        mFilePart = file;
        mServiceAccount = serviceAccount;
        mTokenID = tokenID;

        buildMultipartEntity();
    }


    private void buildMultipartEntity() {
        try {
            //前面是name , 後面是content
            entity.addPart("serviceAccount", new StringBody(mServiceAccount));
            entity.addPart("tokenId", new StringBody(mTokenID));

            if (null==mFoodTitle)    mFoodTitle = "";
            entity.addPart("foodTitle", new StringBody(mFoodTitle, (Charset.forName("UTF-8"))));

            if (null==mTargetCalories)    mTargetCalories = "";
            entity.addPart("targetCalories", new StringBody(mTargetCalories));

            if (null != mFilePart) {
                entity.addPart(mTokenID, new FileBody(mFilePart, getExtension()));
            }
        } catch (UnsupportedEncodingException e) {
            VolleyLog.e("UnsupportedEncodingException");
        }
    }



    private String getExtension() {
        // http://fecbob.pixnet.net/blog/post/34740477-%5Bandroid%5D-%22文件類型——mime類型%22匹配表
        String path = mFilePart.getAbsolutePath();
        String filenameArray[] = path.split("\\.");
        String extension = filenameArray[filenameArray.length-1];

        String result = "";
        switch (extension) {
            case "jpeg":
            case "jpg":
                result = "image/jpeg";
                break;
            case "png":
                result = "image/png";
                break;
            case "mp4":
                result = "video/mp4";
                break;
            case "mp3":
                result = "audio/mp3";
                break;
            default:
                result = "application/octet-stream";
                break;
        }
        return result;
    }

    @Override
    public String getBodyContentType() {
        return entity.getContentType().getValue();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            entity.writeTo(bos);
        } catch (IOException e) {
            VolleyLog.e("IOException writing to ByteArrayOutputStream");
        }
        return bos.toByteArray();
    }


    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String responseData = new String(response.data, StandardCharsets.UTF_8);
//        Log.i("aaa", response.statusCode + " " + responseData);
        return Response.success(responseData, getCacheEntry());
    }

    @Override
    protected void deliverResponse(String response) {
//        Log.i("aaa", "deliverResponse " + response);
        mListener.onResponse(response);
    }

}