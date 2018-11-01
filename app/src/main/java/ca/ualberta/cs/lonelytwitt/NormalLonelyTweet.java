package ca.ualberta.cs.lonelytwitt;

import java.util.Date;

public class NormalLonelyTweet extends LonelyTweet {

	private static final long serialVersionUID = 1L;
	protected Date tweetDate;
	protected String tweetBody;

	public NormalLonelyTweet() {
	}

	public NormalLonelyTweet(String text, Date date) {
		this.tweetDate = date;
		this.tweetBody = text;
	}

}