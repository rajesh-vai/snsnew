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
    ReadProperties properties = new ReadProperties();
    public String resourceLocation = properties.getPropValues("RESOURCES_LOCATION");

    public String warName = properties.getPropValues("WARNAME");
    String elasticDbUrl = properties.getPropValues("ELASTIC_URL");
    public String searchUrl = properties.getPropValues("SEARCH_URL");
    public String dataBase = properties.getPropValues("DATABASE");
    public String indexname = properties.getPropValues("INDEXNAME");

    public IAction() throws IOException {
    }

    public String performSearch(String query){
        query = objISynthesis.preprocessQuery(query);
        query = objIQueryConstruction.findQueryParams(query);
        return "";
    }
}
