package core;


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

    @RequestMapping(value = "esquery", method = RequestMethod.GET)
    public String getQuery(@RequestParam(value="query") String query) throws Exception{

        String helloTest = objIAction.performSearch(query);
        return "hiii";
    }
}