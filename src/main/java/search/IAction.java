package search;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.json.JSONObject;
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
    ReadProperties properties;

    public String resourceLocation;

    public String warName;
    String elasticDbUrl;
    String elasticDbPort;
    public String searchUrl;
    public String dataBase;
    public String indexname;
    RestTemplate restTemplate = new RestTemplate();
    private static final String URL = "http://%s:%s/%s/_search?";

    public String performSearch(String query, String rows, String start, String sort_by, String sort_order) {
        setProperties();
        query = objISynthesis.preprocessQuery(query);
        String esquery = objIQueryConstruction.constructESQuery(query,rows,start,sort_by,sort_order);
        JSONObject results = null;
        String url = String.format(URL, elasticDbUrl,elasticDbPort,indexname);
        boolean hasError = false;
        try {
            results = new JSONObject(restTemplate.postForObject(url, esquery, String.class).toString());
        } catch (Exception e) {
            System.out.println(e);
            hasError = true;

        }
        return results.toString();
    }

    public void setProperties() {
        ReadProperties properties = new ReadProperties();
        try {
            resourceLocation = properties.getPropValues("RESOURCES_LOCATION");
            warName = properties.getPropValues("WARNAME");
            elasticDbUrl = properties.getPropValues("ELASTICHOST");
            elasticDbPort = properties.getPropValues("ELASTICPORT");
            searchUrl = properties.getPropValues("SEARCH_URL");
            dataBase = properties.getPropValues("DATABASE");
            indexname = properties.getPropValues("INDEXNAME");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
