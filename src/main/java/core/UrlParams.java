package core;

/**
 * Created by Rajesh on 21-01-2018.
 */
public class UrlParams {

    public UrlParams() {
    }

    public UrlParams(String query, String login, String api_key) {
        this.query = query;
        this.login = login;
        this.api_key = api_key;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getSort_by() {
        return sort_by;
    }

    public void setSort_by(String sort_by) {
        this.sort_by = sort_by;
    }

    public String getSort_order() {
        return sort_order;
    }

    public void setSort_order(String sort_order) {
        this.sort_order = sort_order;
    }

    private String query;
    private String login;
    private String api_key;
    private String rows;
    private String start;
    private String sort_by;
    private String sort_order;
}
