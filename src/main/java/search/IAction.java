package search;

import javax.inject.Inject;

/**
 * Created by Rajesh on 20-02-2018.
 */
public class IAction {
    @Inject
    ISynthesis objISynthesis;
    IQueryConstruction objIQueryConstruction;
    public String performSearch(String query){
        query = objISynthesis.preprocessQuery(query);
        query = objIQueryConstruction.formESQuery(query);
        return "";
    }
}
