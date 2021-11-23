package org.inria.restlet.mta.internals;

public class Tweet {
    private int _idTweet;
    private String tweet;

    public Tweet(int _idTweet, String comment){
        this._idTweet = _idTweet;
        this.tweet    = comment;
    }

    public int getIdTweet() {
        return _idTweet;
    }

    public void setIdTweet(int _idTweet) {
        this._idTweet = _idTweet;
    }
    public String getTweet(){
        return tweet;
    }
    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

}
