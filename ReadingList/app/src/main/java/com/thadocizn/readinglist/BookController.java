package com.thadocizn.readinglist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thadocizn.readinglist.activities.EditBookActivity;
import com.thadocizn.readinglist.classes.Book;

import java.util.ArrayList;

import static com.thadocizn.readinglist.ViewModel.BookModel.*;
import static com.thadocizn.readinglist.classes.Constants.*;

public class BookController {
    private static LinearLayout parentLayout;
    private static Context context;

    public static View getBooksView(Context context, Activity activity){
        ArrayList<Book> books;
        books        = getAllBooks();
        parentLayout = new LinearLayout(context);
        parentLayout.setOrientation(LinearLayout.VERTICAL);
        for(Book book : books){
            getTextView(book, activity, context, parentLayout );
        }
        return parentLayout;
    }
    private static TextView getTextView(final Book book, final Activity activity, final Context
                                 context, LinearLayout linearLayout) {

        int padding           = context.getResources().getDimensionPixelSize(R.dimen.text_padding);
        TextView textView     = new TextView(context);
        textView.setText(book.getTitle());
        textView.setTextSize(context.getResources().getDimensionPixelSize(R.dimen.text_size));
        textView.setPadding(padding, padding, padding, padding);
        parentLayout.addView(textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra(CSV_STRING, getBook(book.getId()));
                activity.startActivityForResult(intent, EDIT_BOOK_REQUEST_CODE);
            }
        });
        return textView;
    }

    public static String getNextId(){
        return nextId();
    }

    public static void handleEditActivityResult(Intent intent){
        String csvString  = intent.getStringExtra(EDIT_BOOK_KEY);
        String[] csv      = csvString.split(SEPARATOR);
        String title      = csv[INDEX];
        String reason     = csv[INDEX + ONE];
        String id         = csv[INDEX + TWO];
        Boolean read      = Boolean.parseBoolean(csv[INDEX + THREE]);
        Book returnedBook = new Book(title,reason,id,read);
        updateBook(returnedBook);
    }

}
