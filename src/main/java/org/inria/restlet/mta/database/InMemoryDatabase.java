package org.inria.restlet.mta.database;

import java.util.*;

import org.inria.restlet.mta.internals.Tweet;
import org.inria.restlet.mta.internals.User;

/**
 *
 * In-memory database
 *
 * @author ctedeschi
 * @author msimonin
 *
 */
public class InMemoryDatabase
{

    /** User count (next id to give).*/
    private int userCount_;
    /** tweet count (next id to give).*/
    private int tweetCount_;

    /** User Hashmap. */
    Map<Integer, User> users_;



    public InMemoryDatabase()
    {
        users_ = new HashMap<Integer, User>();
    }

    /**
     *
     * Synchronized user creation.
     * @param name
     * @param age
     *
     * @return the user created
     */
    public synchronized User createUser(String name, int age)
    {
        User user = new User(name, age);
        user.setId(userCount_);
        users_.put(userCount_, user);
        userCount_ ++;
        return user;
    }

    public Collection<User> getUsers()
    {
        return users_.values();
    }

    public User getUser(int id)
    {
        return users_.get(id);
    }

    public synchronized Tweet createUserTweet(String tweet1, int userId){
        User user = getUser(userId);
        Tweet tweet = new Tweet(tweetCount_,tweet1);
        user.addTweet(tweet);
        tweetCount_++;
        return tweet;
    }
    public synchronized void deleteUser(int userId){
        users_.remove(userId);
    }
    public Collection<Tweet>getTweets(int userId){
        User user = getUser(userId);
        return user.getListTweet();
    }
    public Collection<Tweet>getAllTweets(){
        ArrayList<Tweet>list = new ArrayList<>();
        for( Map.Entry<Integer, User> user: users_.entrySet() ){
            list.addAll(user.getValue().getListTweet());
        }
        return list;
    }

}
