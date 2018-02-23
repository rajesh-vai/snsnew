package search;

import org.springframework.web.client.RestTemplate;
import utils.ReadProperties;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by Rajesh on 20-02-2018.
 */
public class IAction {
    @Inject
    ISynthesis objISynthesis;
    @Inject
    IQueryConstruction objIQueryConstruction;
    @Inject

    public String resourceLocation;

    public String warName;
    String elasticDbUrl;
    public String searchUrl;
    public String dataBase;
    public String indexname;


    public String performSearch(String query) {
        setProperties();
        query = objISynthesis.preprocessQuery(query);
        query = objIQueryConstruction.findQueryParams(query);
        return "";
    }

    public void setProperties() {
        ReadProperties properties = new ReadProperties();
        try {
            resourceLocation = properties.getPropValues("RESOURCES_LOCATION");
            warName = properties.getPropValues("WARNAME");
            elasticDbUrl = properties.getPropValues("ELASTIC_URL");
            searchUrl = properties.getPropValues("SEARCH_URL");
            dataBase = properties.getPropValues("DATABASE");
            indexname = properties.getPropValues("INDEXNAME");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
