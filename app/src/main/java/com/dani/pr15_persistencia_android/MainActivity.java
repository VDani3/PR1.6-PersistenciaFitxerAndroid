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
        Button create = findViewById(R.id.button);
        File filesPath = getFilesDir();
        Toast notifier = new Toast(this.getApplicationContext());
        notifier.setDuration(Toast.LENGTH_LONG);
        notifier.setText("1");

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.newDocument();
                    //Cada contacto
                    Element elmRoot = doc.createElement("contacte");
                    doc.appendChild(elmRoot);
                    //Name
                    Element elmName = doc.createElement("name");
                    Text tName = doc.createTextNode(name.getText().toString());
                    elmName.appendChild(tName);
                    elmRoot.appendChild(elmName);
                    //Cognom
                    Element elmCog = doc.createElement("cognom");
                    Text tCog = doc.createTextNode(cognom.getText().toString());
                    elmCog.appendChild(tCog);
                    elmRoot.appendChild(elmCog);
                    //Email
                    Element elmMail = doc.createElement("email");
                    Text tMail = doc.createTextNode(email.getText().toString());
                    elmMail.appendChild(tMail);
                    elmRoot.appendChild(elmMail);

                    //Fer el document
                    TransformerFactory tF = TransformerFactory.newInstance();
                    Transformer trans = tF.newTransformer();
                    trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
                    trans.setOutputProperty(OutputKeys.INDENT, "yes");
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(filesPath);

                    trans.transform(source, result);
                    notifier.setText("Done");
                    notifier.show();

                } catch (Exception e) { notifier.setText(e.getMessage()); notifier.show();}
            }
        });



    }
}