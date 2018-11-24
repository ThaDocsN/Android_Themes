package com.thadocizn.readinglist.classes;

import java.io.Serializable;

import static com.thadocizn.readinglist.classes.Constants.*;

public class Book implements Serializable {
    private String title;
    private String reasonToRead;
    private String id;
    private boolean hasBeenRead;


    public String toCsvString() {
        return String.format("%s,%s,%s,%b".replaceAll(" ", SEPERATOR), this.title, this.reasonToRead, this.id, this.hasBeenRead);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReasonToRead() {
        return reasonToRead;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isHasBeenRead() {
        return hasBeenRead;
    }

    public Book(String csv) {
        String[] csvs     = csv.split(SEPERATOR);
        this.title        = csvs[INDEX];
        this.reasonToRead = csvs[INDEX + ONE];
        this.id           = csvs[INDEX + TWO];
        this.hasBeenRead  = Boolean.parseBoolean(csvs[INDEX + THREE]);
        toCsvString();
    }

    public Book(String title, String reasonToRead, String id, boolean hasBeenRead) {
        this.title        = title;
        this.reasonToRead = reasonToRead;
        this.id           = id;
        this.hasBeenRead  = hasBeenRead;
    }
}
