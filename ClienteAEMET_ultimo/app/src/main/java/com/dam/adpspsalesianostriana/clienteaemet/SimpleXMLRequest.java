package com.dam.adpspsalesianostriana.clienteaemet;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.nio.charset.Charset;
import java.util.Map;


public class SimpleXMLRequest<T> extends Request<T> {

    private final Serializer serializer = new Persister();
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;


    public SimpleXMLRequest(String url, Class<T> clazz, Map<String, String> headers,
                            Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);

        setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }




    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {

            String xml = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
                    Charset.forName("utf-8");
            return Response.success(
                    serializer.read(clazz, xml, false),
                    HttpHeaderParser.parseCacheHeaders(response));

        }catch (Exception e) {
            Response<T> res = Response.error(new ParseError(e));
            return res;
        }

    }

}
