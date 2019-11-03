package com.medialink.mvvm1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.medialink.mvvm1.constant.Const;

public class AddMovieActivity extends AppCompatActivity {
    private EditText edTitle, edDesc;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        initViews();
        initListener();
    }

    private void initListener() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edTitle.getText().toString();
                String desc = edDesc.getText().toString();
                if (title.trim().isEmpty() || desc.trim().isEmpty()) {
                    Toast.makeText(AddMovieActivity.this, "Please enter Title and Description", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent result = new Intent();
                result.putExtra(Const.TITLE, title);
                result.putExtra(Const.DESC, desc);
                setResult(RESULT_OK, result);
                finish();
            }
        });
    }

    private void initViews() {
        edTitle = findViewById(R.id.ed_title);
        edDesc = findViewById(R.id.ed_desc);
        btnAdd = findViewById(R.id.btn_add);
    }
}
