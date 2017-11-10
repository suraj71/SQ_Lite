package com.example.admin.sq_lite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name,address;
    Button save,delete;
    SQLiteDatabase db;
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.etxtName);
        address = (EditText)findViewById(R.id.etxtAddress);
        save = (Button)findViewById(R.id.btnSave);
        delete = (Button)findViewById(R.id.btnDelete);
        name.setOnClickListener(this);
        address.setOnClickListener(this);
        save.setOnClickListener(this);
        delete.setOnClickListener(this);

        db = openOrCreateDatabase("school", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(name VARCHAR, address VARCHAR);");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnSave:
                db.execSQL("INSERT INTO student VALUES('"+name.getText()+"' , '"+address.getText()+"' );");
                Toast.makeText(getApplicationContext(),"record added",Toast.LENGTH_LONG).show();
                name.setText("");
                address.setText("");
                break;

            case R.id.btnDelete:
                Cursor c = db.rawQuery("SELECT * FROM student WHERE name='"+name.getText()+"'", null);
                if(c.moveToFirst()){
                    db.execSQL("DELETE FROM student WHERE name='"+name.getText()+"'");
                    Toast.makeText(getApplicationContext(),"record deleted",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
