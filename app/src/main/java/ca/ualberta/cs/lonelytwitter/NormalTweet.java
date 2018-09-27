package ca.ualberta.cs.lonelytwitter;

public class NormalTweet extends Tweet {

    public NormalTweet(String message){
        super(message);
    }


    @Override
    public boolean isImportant(){
        return false;

    }

}//end class
