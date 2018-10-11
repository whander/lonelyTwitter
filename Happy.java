package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

public class Happy extends Mood {
    private Date date;

    public Happy(Date date){
        this.date = date;

    }

    public Date getDate(){
        return this.date;

    }

    public String getType(){
        return "Happy";

    }

}
