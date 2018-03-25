package com.example.android.KHAIRENNIZAMEFID_1202150285_MODUL5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddTodo extends AppCompatActivity {

    //deklarasi
    private EditText etName, etDesc, etPriority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        etName = (EditText)findViewById(R.id.etName);
        etDesc = (EditText)findViewById(R.id.etDesc);
        etPriority = (EditText)findViewById(R.id.etPriority);
    }

    //ketika klik tambahkan to do akan pindah ke actvity selanjutnya
    public void addTodo(View view) {
        Intent intent=new Intent();
        intent.putExtra("name",etName.getText().toString());
        intent.putExtra("desc",etDesc.getText().toString());
        intent.putExtra("priority",etPriority.getText().toString());
        setResult(1,intent);
        finish();//finishing activity
    }
}
