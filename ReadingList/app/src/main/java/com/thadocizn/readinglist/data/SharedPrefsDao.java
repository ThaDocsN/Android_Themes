package com.thadocizn.readinglist.data;

import android.content.SharedPreferences;

import com.thadocizn.readinglist.activities.MainActivity;
import com.thadocizn.readinglist.classes.Book;

import java.util.ArrayList;

import static com.thadocizn.readinglist.classes.Constants.*;

public class SharedPrefsDao {

    private static String getIds(){
        String keyIds                 = null;
        if (MainActivity.preferences != null){
            keyIds                    = MainActivity.preferences.getString(KEY_IDS, null);
        }
        return keyIds;
    }
    private static String[] getAllBookIds(){
        // keys are stored as csv
        return getIds().split(SEPARATOR);
    }

    public static ArrayList<Book> getAllBooks(){
        String[] ids          = getAllBookIds();
        ArrayList<Book> books = new ArrayList<>(ids.length);
        for(String id : ids){
            if(!id.equals(""))
                books.add(getBook(id));
        }
        return books;
    }

    public static Book getBook(String id){
        Book currentBook              = null;
        if (MainActivity.preferences != null){
          final  String strBook = MainActivity.preferences.getString(KEY_ID_PREFIX + id, null);
          currentBook    = new Book(strBook);
        }
        return currentBook;
    }

    public static String getNextId() {

            int currentId                   = MainActivity.preferences.getInt(NEXT_KEY_ID, DEFAULT_VALUE);
            int nextId                      = currentId + ONE;
            SharedPreferences.Editor editor = MainActivity.preferences.edit();
            editor.putInt(NEXT_KEY_ID, nextId);
            editor.apply();
        return String.valueOf(nextId);
    }

    public static void updateBook(Book book){
        if (book.getId().isEmpty()){
            book.setId(getNextId());
        }
        String[] ids       = getAllBookIds();
        boolean active     = false;
        for(String id : ids){
                if(book.getId().equals(id)){
                    active = true;
                    break;
                }
            }
        if(!active){
            addId(book.getId());
        }
        addBook(book);
    }

    private static void addBook(Book book){
        SharedPreferences.Editor editor = MainActivity.preferences.edit();
        editor.putString(KEY_ID_PREFIX + book.getId(), book.toCsvString());
        editor.apply();
    }

    private static void addId(String id){
        String strGetId = getIds();
        strGetId        = strGetId + "," + id;

        SharedPreferences.Editor editor = MainActivity.preferences.edit();
        editor.putString(KEY_IDS, strGetId.replace(" ", ""));
        editor.apply();
    }
}
