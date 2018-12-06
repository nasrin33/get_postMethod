package com.example.user.getmethod2;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static com.example.user.getmethod2.R.id.textView;

public class MainActivity extends AppCompatActivity {
    TextView mTextView;

    Button mButton;
    Button button;
    String server_url= "http://192.168.99.1:8000/diabatic/default/post_img";
    String server_url2= "http://192.168.99.1:8000/diabatic/default/post_img";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView=(TextView)findViewById(R.id.textView);
        mButton= (Button)findViewById(R.id.button);
        button=(Button)findViewById(R.id.button2);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
                StringRequest stringRequest= new StringRequest(Request.Method.GET, server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                mTextView.setText(response);
                                requestQueue.stop();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        mTextView.setText("Network Error");
                        error.printStackTrace();
                        requestQueue.stop();
                    }
                });

                requestQueue.add(stringRequest);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final RequestQueue requestQueue2= Volley.newRequestQueue(MainActivity.this);

                StringRequest stringsent= new StringRequest(Request.Method.POST, server_url2,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Log.d("Response", response);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                       // Log.d("Response", response);

                    }
                }){

                    @Override
                    protected Map<String, String> getParams(){

                        Map <String, String> params= new HashMap<String, String>();
                        String msg= "Nasrin";
                        params.put("name", msg);
                        mTextView.setText(msg);
                        return params;
                    }
                };


                requestQueue2.add(stringsent);
            }
        });



    }
}
