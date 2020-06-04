package com.example.employeecontect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EDIT extends AppCompatActivity {

    private Button eBtnChange,eBtnDelete,eBtnAdd;
    private EditText eEtPhone,eEtName;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        eEtName=(EditText)findViewById(R.id.et_name);
        eEtPhone=(EditText)findViewById(R.id.et_phone);
        eBtnAdd=(Button)findViewById(R.id.btn_add);
        eBtnChange=(Button)findViewById(R.id.btn_change);
        eBtnDelete=(Button)findViewById(R.id.btn_delete);
        db=openOrCreateDatabase("Book.db",MODE_PRIVATE,null);
        eBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=eEtName.getText().toString();
                String phone=eEtPhone.getText().toString();
                ContentValues values = new ContentValues();
                values.put("name",name);
                values.put("phone",phone);
                db.insert("book",null,values);
                values.clear();
                //db.execSQL("insert into book(name,phone) values('"+name+"','"+phone+"')");
            }
        });
        eBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=eEtName.getText().toString();
                ContentValues values=new ContentValues();
                values.put("name",name);
                db.delete("book","name=?",new String[]{name});
            }
        });
        eBtnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=eEtName.getText().toString();
                String phone=eEtPhone.getText().toString();
                ContentValues values = new ContentValues();
                values.put("name",name);
                values.put("phone",phone);
                db.update("book",values,"name=?",new String[]{name});
            }
        });
    }
}
