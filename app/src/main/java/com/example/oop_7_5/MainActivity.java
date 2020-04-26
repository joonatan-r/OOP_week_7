package com.example.oop_7_5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Context context = null;
    EditText fnInput;
    EditText textField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textField = findViewById(R.id.textField);
        fnInput = findViewById(R.id.fnInput);
        context = MainActivity.this;
    }

    public void saveText(View v) {
        try {
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(fnInput.getText().toString(), Context.MODE_PRIVATE));
            ows.write(textField.getText().toString());
            ows.close();
        } catch (IOException e) {
            Log.e("IOException", "Couldn't save text");
            new AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage("Couldn't save text")
                .setPositiveButton(android.R.string.yes, null)
                .show();
        }
    }

    public void loadText(View v) {
        try {
            InputStream in = context.openFileInput(fnInput.getText().toString());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String s;
            StringBuilder sb = new StringBuilder();

            while ((s = br.readLine()) != null) {
                sb.append(s);
                sb.append("\n");
            }

            sb.setLength(sb.length() - 1); // remove the last (unneeded) newline
            textField.setText(sb.toString());
            br.close();
            in.close();
        } catch (IOException e) {
            Log.e("IOException", "Couldn't load text");
            new AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage("Couldn't load text")
                .setPositiveButton(android.R.string.yes, null)
                .show();
        }
    }
}
