package org.inria.restlet.mta.resources;
import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.internals.Tweet;
import org.inria.restlet.mta.internals.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.util.ArrayList;
import java.util.Collection;

public class UserTweetsRessource extends ServerResource {
    /** Backend. */
    private Backend backend_;
    private User user_;


    /**
     * Constructor.
     * Call for every single user request.
     */
    public UserTweetsRessource()
    {
        super();
        backend_ = (Backend) getApplication().getContext().getAttributes()
                .get("backend");
    }

    @Post("json")
    public Representation createTweet(JsonRepresentation representation) throws Exception {
        JSONObject object = representation.getJsonObject();
        String tweet = object.getString("tweet");
        String userIdString = (String) getRequest().getAttributes().get("userId");
        int userId = Integer.valueOf(userIdString);

        // Save the tweet
        Tweet tweet1 = backend_.getDatabase().createUserTweet(tweet,userId);

        // generate result
        JSONObject resultObject = new JSONObject();
        resultObject.put("idTweet", tweet1.getIdTweet());
        resultObject.put("tweet",tweet1.getTweet());
        JsonRepresentation result = new JsonRepresentation(resultObject);
        return result;
    }
    @Get("json")
    public Representation getTweet() throws JSONException
    {
        String userIdString = (String) getRequest().getAttributes().get("userId");
        int userId = Integer.valueOf(userIdString);
        Collection<Tweet> tweets = backend_.getDatabase().getTweets(userId);
        Collection<JSONObject> jsonTweets = new ArrayList<JSONObject>();

        for (Tweet tweet : tweets)
        {
            JSONObject current = new JSONObject();
            current.put("idTweet", tweet.getIdTweet());
            current.put("tweet",tweet.getTweet());
            jsonTweets.add(current);
        }
        JSONArray jsonArray = new JSONArray(jsonTweets);
        return new JsonRepresentation(jsonArray);
    }

}
