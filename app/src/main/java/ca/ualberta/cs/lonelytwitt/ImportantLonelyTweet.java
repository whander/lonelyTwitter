package ca.ualberta.cs.lonelytwitt;

import java.util.Date;

public class ImportantLonelyTweet extends LonelyTweet {

	private static final long serialVersionUID = 1L;
	protected Date tweetDate;
	protected String tweetBody;

	public ImportantLonelyTweet() {
	}

	public ImportantLonelyTweet(String text, Date date) {
		this.tweetDate = date;
		this.tweetBody = text;
	}

}