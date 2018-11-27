package com.thadocizn.readinglist.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import com.thadocizn.readinglist.R;
import com.thadocizn.readinglist.classes.ThemeUtils;

import java.util.Objects;

import static com.thadocizn.readinglist.BookController.*;
import static com.thadocizn.readinglist.classes.Constants.*;

public class MainActivity extends AppCompatActivity {
    public static SharedPreferences preferences;

    Context    context;
    Activity   activity;
    ScrollView scrollView;
    int themeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.onActivityCreateSetTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = this.getSharedPreferences(COM_THADOCIZN_READING_LIST, Context.MODE_PRIVATE);
        context     = this;
        activity    = this;
        scrollView  = findViewById(R.id.scrollView1);

        findViewById(R.id.btnAddBook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra(EDIT_BOOK_KEY, getNextId());
                startActivityForResult(intent, EDIT_BOOK_REQUEST_CODE);
            }
        });
    }

    public void checkTheme(){
        if (themeId != ThemeUtils.getSelectedTheme(activity)){
            ThemeUtils.refreshActivity(activity);
        }
    }

    @Override
    protected void onStart() {
        checkTheme();
        super.onStart();
    }

    @Override
    public void setTheme(int themeId) {
        super.setTheme(themeId);
    }

    @Override
    protected void onResume() {
        checkTheme();
        super.onResume();
        scrollView.removeAllViews();
        scrollView.addView(getBooksView(context, activity));


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == EDIT_BOOK_REQUEST_CODE){
                handleEditActivityResult(Objects.requireNonNull(data));
            }
        }
    }
}
