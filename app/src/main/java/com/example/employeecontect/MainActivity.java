package com.example.employeecontect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.baoyz.widget.PullRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button mBtnEdit,mBtnCreate,mBtnUpdate;
    private DatabaseHelper dbHelper;
    private ListView lv;
    private MyAdapter adapter;
    public List<PhoneInfo> lists=new ArrayList<PhoneInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnEdit=(Button)findViewById(R.id.mbtn_edit);
        mBtnCreate=(Button)findViewById(R.id.mbtn_create);
        mBtnUpdate=(Button)findViewById(R.id.mbtn_update);

        final PullRefreshLayout layout = (PullRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh();
                        layout.setRefreshing(false);
                    }
                },1000);
            }
        });

        dbHelper = new DatabaseHelper(this,"Book.db",null,1);
       //创建数据库
        mBtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
                Toast.makeText(MainActivity.this,"数据库创建成功",Toast.LENGTH_SHORT).show();
            }
        });
        //更新信息
        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
        //编辑跳转
        mBtnEdit.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到信息编辑
                Intent intent=new Intent(MainActivity.this,EDIT.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this,"进入修改信息",Toast.LENGTH_SHORT).show();
            }
        }));
    }
    public void refresh(){
        lists.clear();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from book",null);
        if(cursor.getCount()!=0){
            while(cursor.moveToNext()){
                PhoneInfo phoneInfo = new PhoneInfo(cursor.getString(1),cursor.getString(2));
                lists.add(phoneInfo);
                System.out.println(phoneInfo.getName()+phoneInfo.getNumber());
            }
        }
        lv=(ListView) findViewById(R.id.lv);
        adapter = new MyAdapter(lists,MainActivity.this);
        lv.setAdapter(adapter);
    }
}
