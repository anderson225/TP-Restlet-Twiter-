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

public class TweetsRessource extends ServerResource {
    /** Backend. */
    private Backend backend_;



    /**
     * Constructor.
     * Call for every single user request.
     */
    public TweetsRessource()
    {
        super();
        backend_ = (Backend) getApplication().getContext().getAttributes()
                .get("backend");
    }


    @Get("json")
    public Representation getAllTweets() throws JSONException
    {

        Collection<Tweet> tweets = backend_.getDatabase().getAllTweets();
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
