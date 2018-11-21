package com.thadocizn.readinglist.ViewModel;

import com.thadocizn.readinglist.classes.Book;
import com.thadocizn.readinglist.data.SharedPrefsDao;

import java.util.ArrayList;

public class BookModel {

    public static ArrayList<Book> getAllBooks() {
        ArrayList<Book> booksArray = SharedPrefsDao.getAllBooks();
        return booksArray;
    }

    public static Book getBook(String id) {
        return SharedPrefsDao.getBookCsv(id);
    }

    public String nextId() {
        return SharedPrefsDao.getNextId();
    }

    public static void updateBook(Book book) {
        if (book.getId() != null) {
            SharedPrefsDao.updateBook(book);
        }
    }
}
