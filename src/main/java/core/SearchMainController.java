package core;


import dataprocessor.ISNSCoreData;
import search.IAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;


@RestController
@CrossOrigin
@RequestMapping(value = "/")
public class SearchMainController {

    //	private static final String URL = "http://%s:%s/jabong/_search?size=250";
    private static final String URL = "http://%s:%s/%s/_search?";


    @Inject
    ApplicationContext context;

    @Inject
    IAction objIAction;
    @Inject
    PostProcessor objPostProcessor;
    @Inject
    ISNSCoreData objISNSCoreData;

    @CrossOrigin
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(@RequestBody UrlParams urlParams) {
        String query = urlParams.getQuery();
        String login = urlParams.getLogin();
        String api_key = urlParams.getApi_key();
        String rows = urlParams.getRows();
        String start = urlParams.getStart();
        String sort_by = urlParams.getSort_by();
        String sort_order = urlParams.getSort_order();
        String error_message = "{\"error\":\"authorization failed\"}";
        boolean flag = false;
        if ((login.equals("sns") && api_key.equals("eyJhbGciOiJIUzI1NiJ9"))
                || (login.equals("salt") && api_key.equals("salt")))
            flag = true;

        if (!flag)
            return error_message;
        String searchOutput = "", url = "";
        try {
            Integer.parseInt(rows);
            flag = true;
        } catch (NumberFormatException e) {
            flag = false;
        } catch (NullPointerException e) {
            flag = false;
        }
        if (!flag)
            rows = "5000";
        try {
            Integer.parseInt(start);
            flag = true;
        } catch (NumberFormatException e) {
            flag = false;
        } catch (NullPointerException e) {
            flag = false;
        }
        if (!flag)
            start = "0";
        try {
            searchOutput = objIAction.performSearch(query, rows, start, sort_by, sort_order);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return searchOutput;
    }

    @RequestMapping(value = "esquery", method = RequestMethod.GET)
    public String getQuery(@RequestParam(value = "query") String query) throws Exception {

        String searchOutput = objIAction.performSearch(query, "10", "0", "price", "asc");
        boolean doPostProcess = objISNSCoreData.getCompanyConfig().getJSONObject("configurations").getJSONObject("search_configurations").getBoolean("postprocess");
        if(doPostProcess){
            searchOutput = objPostProcessor.postProcess(searchOutput , 0 , 12);
        }
        return searchOutput;
    }
}