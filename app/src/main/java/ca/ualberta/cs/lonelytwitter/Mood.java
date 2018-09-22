package ca.ualberta.cs.lonelytwitter;


public abstract class Mood {
    private String type;


    public abstract String getType();

    public String getMood(){
        return this.type;

    }
}
