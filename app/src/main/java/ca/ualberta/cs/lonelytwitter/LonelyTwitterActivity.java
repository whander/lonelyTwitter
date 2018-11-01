package ca.ualberta.cs.lonelytwitter;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import ca.ualberta.cs.lonelytwitt.ImportantLonelyTweet;
import ca.ualberta.cs.lonelytwitt.LonelyTweet;
import ca.ualberta.cs.lonelytwitt.NormalLonelyTweet;

public class LonelyTwitterActivity extends Activity {

	private EditText bodyText;
	private ListView oldTweetsList;

	private List<LonelyTweet> tweets;
	private ArrayAdapter<LonelyTweet> adapter;
	private TweetsFileManager tweetsProvider;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);
	}

	@Override
	protected void onStart() {
		super.onStart();

		tweetsProvider = new TweetsFileManager(this);
		tweets = tweetsProvider.loadTweets();
		adapter = new ArrayAdapter<LonelyTweet>(this, R.layout.list_item,
				tweets);
		oldTweetsList.setAdapter(adapter);
	}

	public void save(View v) {
		String text = bodyText.getText().toString();

		LonelyTweet tweet;

		//tweet = new NormalLonelyTweet(text, new Date());
		tweet = getNormalOrImportantLonelyTweet(text);

		//TODO: use different sub-classes (Normal or Important) based on usage of "*" in the text.


		if (tweet.isValid()) {
			tweets.add(tweet);
			adapter.notifyDataSetChanged();

			bodyText.setText("");
			tweetsProvider.saveTweets(tweets);
		} else {
			Toast.makeText(this, "Invalid tweet", Toast.LENGTH_SHORT).show();
		}
	}

	private LonelyTweet getNormalOrImportantLonelyTweet(String text) {
		LonelyTweet tweet;
		if (text.contains("*")){
			tweet = new ImportantLonelyTweet(text, new Date());
		}else{
			tweet = new NormalLonelyTweet(text, new Date());
		}
		return tweet;
	}

	public void clear(View v) {
		tweets.clear();
		adapter.notifyDataSetChanged();
		tweetsProvider.saveTweets(tweets);
	}

	public List<LonelyTweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<LonelyTweet> tweets) {
		this.tweets = tweets;
	}
}
