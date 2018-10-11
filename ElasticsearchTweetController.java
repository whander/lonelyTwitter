package ca.ualberta.cs.lonelytwitter;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;

/**
 * Created by romansky on 10/20/16.
 */
public class ElasticsearchTweetController {

    private static JestDroidClient client=null;

    public static void addTweets(Tweet tweet){
        setClient();
        Index index = new Index.Builder(tweet).index("shaiful-wednesday").type("tweet").build();
        try {
            DocumentResult result = client.execute(index);
            if (result.isSucceeded()){
                tweet.setTweetId(result.getId());

            }
        }catch(IOException e){}

    }

    public static void setClient(){
        if (client==null){
            DroidClientConfig config=new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080/").build();
            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient)factory.getObject();
        }

    }


   public static class AddtweetsTask extends AsyncTask<Tweet, Void, Void>{
            protected Void doInBackground(Tweet... params){
            setClient();
            Tweet tweet = params[0];
            Index index = new Index.Builder(tweet).index("shaiful-wednesday").addType("tweet").build();

        try {
            DocumentResult result = client.execute(index);
            if (result.isSucceeded()){
                tweet.setTweetId(result.getId());

            }
        }catch(IOException e){}
                return null;

        }
     }

     public static class GetTweetsTask extends AsyncTask<String, Void, ArrayList<Tweet>>{
        @Override
         protected ArrayList<Tweet> doInBackground(String...params){
            setClient();
            ArrayList<Tweet> tweets = new ArrayList<Tweet>();
            Search search = new Search.Builder(params[0]).addIndex("shaiful-new-test").addType("tweet").build();
            try{
                JestResult result = client.execute(search);

                if (result.isSucceeded()){
                    List<NormalTweet> tweetList;
                    tweetList = result.getSourceAsObjectList(NormalTweet.class);
                    tweets.addAll(tweetList);
                }

            }catch(IOException e){}

            return tweets;

        }


     }


}