package com.dani.pr15_persistencia_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.time.Duration;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name = findViewById(R.id.name);
        EditText cognom = findViewById(R.id.cognom);
        EditText email = findViewById(R.id.email);
        EditText telf = findViewById(R.id.tel);
        Button create = findViewById(R.id.button);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fo =  openFileOutput("data.txt", MODE_APPEND);
                    String text = name.getText()+";"+cognom.getText()+";"+telf.getText()+";"+email.getText()+";\n";
                    fo.write(text.getBytes());
                    fo.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



    }
}