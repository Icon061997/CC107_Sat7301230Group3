package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, contact, dob;
    Button   insert, read, update, delete;
    TextView viewtext;

    Dbhelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name =  findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);
        viewtext =findViewById(R.id.viewtext);

        insert = findViewById(R.id.btninsert);
        read = findViewById(R.id.btnread);
        update = findViewById(R.id.btnupdate);
        delete = findViewById(R.id.btdelete);

        DB = new Dbhelper(this);


            insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String nameTXT  = name.getText().toString();
                 String contactTXT = contact.getText().toString();
                 String dobTXT = dob.getText().toString();

                 Boolean checkinsertdata = DB.insertuserdata(nameTXT, contactTXT, dobTXT);

                 if(checkinsertdata ==  true ) {

                     name.setText("");
                     contact.setText("");
                     dob.setText("");

                     viewtext.setText("Data Inserted");

                     Toast.makeText(MainActivity.this, "Succesfully Added", Toast.LENGTH_SHORT).show();
                 }
                 else
                     Toast.makeText(MainActivity.this,"This information is existing", Toast.LENGTH_SHORT).show();

            }
        });


            update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String nameTXT  = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTXT, contactTXT, dobTXT);

                if(checkupdatedata ==  true ) {

                    viewtext.setText("Data updated");
                    Toast.makeText(MainActivity.this,"Succesfully Updated", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this,"Failed update ", Toast.LENGTH_SHORT).show();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameTXT = name.getText().toString();

                Boolean checkdeletedata = DB.deletetuserdata(nameTXT);

                if(checkdeletedata ==  true ) {

                    name.setText("");
                    contact.setText("");
                    dob.setText("");
                    viewtext.setText("Data Deleted");

                    Toast.makeText(MainActivity.this, "Deleted Succesfully", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this,"Failed to delete", Toast.LENGTH_SHORT).show();

            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();

                if(res.getCount()== 0){

                    Toast.makeText(MainActivity.this,"Information Displayed", Toast.LENGTH_SHORT).show();
                    return;
                }

               StringBuffer buffer = new StringBuffer();

                buffer.append("Personal Information: "+"\n");


                while(res.moveToNext())
                {


                    buffer.append("Name:"+res.getString(0)+"\n");
                    buffer.append("Contact:"+res.getString(1)+"\n");
                    buffer.append("Date of birth:"+res.getString(2)+"\n\n");



                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });



    }
}