package com.example.entrylog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LogEntryPage extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    AppCompatButton b1,b2;

    String apiurl="http://10.0.4.16:3000/api/students";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_entry_page);

        ed1=(EditText) findViewById(R.id.name);
        ed2=(EditText) findViewById(R.id.admnum);
        ed3=(EditText) findViewById(R.id.sysnum);
        ed4=(EditText) findViewById(R.id.depname);
        b1=(AppCompatButton) findViewById(R.id.addbtn);
        b2=(AppCompatButton) findViewById(R.id.logoutbtn);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Value Reading
                String getName=ed1.getText().toString();
                String getAdmissionNumber=ed2.getText().toString();
                String getSystemNumber=ed3.getText().toString();
                String getDepartment=ed4.getText().toString();

                //creating JSON Object
                JSONObject student=new JSONObject();
                try {
                    student.put("name",getName);
                    student.put("admission_number",getAdmissionNumber);
                    student.put("system_number",getSystemNumber);
                    student.put("department",getDepartment);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                //JSON Object request creation
                JsonObjectRequest jasonObjectRequest=new JsonObjectRequest(
                        Request.Method.POST,
                        apiurl,
                        student,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                );

                //Request Queue
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jasonObjectRequest);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preference=getSharedPreferences("logapp",MODE_PRIVATE);
                SharedPreferences.Editor editor=preference.edit();
                editor.clear();
                editor.apply();
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }
}