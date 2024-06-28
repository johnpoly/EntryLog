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

public class MainActivity extends AppCompatActivity {

    EditText ed1,ed2;
    AppCompatButton b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        SharedPreferences preference=getSharedPreferences("logapp",MODE_PRIVATE);
        String Username=preference.getString("user",null);
        if(Username!=null){
            Intent i=new Intent(getApplicationContext(), LogEntryPage.class);
            startActivity(i);
        }

        ed1=(EditText) findViewById(R.id.uname);
        ed2=(EditText) findViewById(R.id.pass);
        b1=(AppCompatButton) findViewById(R.id.logbtn);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUsername=ed1.getText().toString();
                String getPassword=ed2.getText().toString();
                if(getUsername.equals("admin") && getPassword.equals("12345")){
                    SharedPreferences preference=getSharedPreferences("logapp",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preference.edit();
                    editor.putString("user","admin");
                    editor.apply();
                    Intent i=new Intent(getApplicationContext(), LogEntryPage.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}