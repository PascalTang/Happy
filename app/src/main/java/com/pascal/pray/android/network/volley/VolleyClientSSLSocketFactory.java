package com.pascal.pray.android.network.volley;

import android.content.Context;
import android.net.SSLCertificateSocketFactory;
import android.net.SSLSessionCache;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by pascal on 2017/6/22.
 */

public class VolleyClientSSLSocketFactory extends SSLCertificateSocketFactory {
    private static SSLContext sslContext;

    /**
     * @param handshakeTimeoutMillis
     * @deprecated Use {@link #getDefault(int)} instead.
     */
    public VolleyClientSSLSocketFactory(int handshakeTimeoutMillis) {
        super(handshakeTimeoutMillis);
    }

    public static SSLSocketFactory getSocketFactory(Context context){
        try
        {
            X509TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {}

                public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {}

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[] { tm }, null);

            SSLSocketFactory ssf = VolleyClientSSLSocketFactory.getDefault(10000, new SSLSessionCache(context));

            return ssf;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
        return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
    }

    @Override
    public Socket createSocket() throws IOException {
        return sslContext.getSocketFactory().createSocket();
    }
}
