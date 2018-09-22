package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

public class Sad extends Mood {
    private Date date;

    public Sad(Date date){
        this.date = date;

    }

    public Date getDate(){
        return this.date;

    }

    public String getType(){
        return "Sad";

    }
}
