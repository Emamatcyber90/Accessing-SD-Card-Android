package com.example.AccessSDcard;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MyActivity extends Activity {
    EditText filename,data;
    Button save,read;

    String readData,buffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        filename = (EditText) findViewById(R.id.fileName);
        data = (EditText) findViewById(R.id.data);
        save = (Button) findViewById(R.id.save);
        read = (Button) findViewById(R.id.read);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    File file = new File("/sdcard/Files"+filename.getText().toString());
                    file.createNewFile();
                    FileOutputStream fout = new FileOutputStream(file);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fout);
                    outputStreamWriter.append(data.getText().toString());
                    outputStreamWriter.close();
                    fout.close();
                    Toast.makeText(getApplicationContext(),"File saved",Toast.LENGTH_SHORT).show();
                }  catch (FileNotFoundException e){
                    Toast.makeText(getApplicationContext(),"File not creatred",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    File file = new File("/sdcard/Files" + filename.getText().toString());
                    FileInputStream fin = new FileInputStream(file);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
                    while ((readData = reader.readLine()) != null) {
                        buffer += readData + "\n";
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), buffer, Toast.LENGTH_SHORT).show();
//                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//                builder.create();
//                builder.setCancelable(true);
//                builder.setTitle(filename.getText().toString());
//                builder.setMessage(buffer);
//                builder.show();
            }
        });
    }
}
