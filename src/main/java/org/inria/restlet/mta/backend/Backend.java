package org.inria.restlet.mta.backend;

import org.inria.restlet.mta.database.InMemoryDatabase;

/**
 *
 * Backend for all resources.
 *
 * @author ctedeschi
 * @author msimonin
 *
 */
public class Backend
{
    /** Database.*/
    private InMemoryDatabase database_;

    public Backend()
    {
        database_ = new InMemoryDatabase();
    }

    public InMemoryDatabase getDatabase()
    {
        return database_;
    }
}
