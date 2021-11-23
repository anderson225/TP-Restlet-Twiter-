package org.inria.restlet.mta.resources;

import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.internals.User;
import org.restlet.resource.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

/**
 *
 * Resource exposing a user.
 *
 * @author msimonin
 * @author ctedeschi
 *
 */
public class UserResource extends ServerResource
{

    /** Backend.*/
    private Backend backend_;

    /** Utilisateur géré par cette resource.*/
    private User user_;

    /**
     * Constructor.
     * Call for every single user request.
     */
    public UserResource()
    {
        backend_ = (Backend) getApplication().getContext().getAttributes()
                .get("backend");
    }


    @Get("json")
    public Representation getUser() throws Exception
    {
        //
        String userIdString = (String) getRequest().getAttributes().get("userId");
        int userId = Integer.valueOf(userIdString);
        user_ = backend_.getDatabase().getUser(userId);

        JSONObject userObject = new JSONObject();
        userObject.put("name", user_.getName());
        userObject.put("age", user_.getAge());
        userObject.put("id", user_.getId());

        return new JsonRepresentation(userObject);
    }
    @Delete("json")
    public Representation deleteUser() throws Exception
    {
        String userIdString = (String) getRequest().getAttributes().get("userId");
        int userId = Integer.valueOf(userIdString);
        backend_.getDatabase().deleteUser(userId);
        JSONObject userObject = new JSONObject();
        userObject.put("ok", true);
        return new JsonRepresentation(userObject);
    }


}
