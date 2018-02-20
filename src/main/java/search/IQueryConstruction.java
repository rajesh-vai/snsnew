package search;

import dataprocessor.ISNSCoreData;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by rvairamani on 20-02-2018.
 */
public class IQueryConstruction {
    @Inject
    ISNSCoreData objISNSCoreData;
    private String inputQuery = new String();

    public String getInputQuery() {
        return inputQuery;
    }

    /**
     * @param inputQuery
     */
    public void setInputQuery(String inputQuery) {
        this.inputQuery = inputQuery;
    }

    public HashSet<String> getCategoriesInQuery() {
        return categoriesInQuery;
    }

    public void setCategoriesInQuery(HashSet<String> categoriesInQuery) {
        this.categoriesInQuery = categoriesInQuery;
    }

    private HashSet<String> categoriesInQuery = new HashSet<>();

    public String formESQuery(String query){
        setInputQuery(query);
        try {
            HashMap<String, HashSet<String>> fields = findCategory(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private HashMap<String, HashSet<String>> findCategory(String q) throws Exception {
        HashMap<String, HashSet<String>> field_set = new HashMap<>();
        ArrayList<String> catToBeRemoved = new ArrayList<>();
        String[] w = q.split("\\W+");
        JSONArray categoryListsFromMap = objISNSCoreData.getFieldMapObject().getJSONObject("_defaultCategory_").getJSONArray("category");
        HashSet<String> tempSetCategory = new HashSet<>();
        for (int i = 0; i < w.length; i++) {
            for (int j = 0; j < categoryListsFromMap.length(); j++) {
                if (categoryListsFromMap.getString(j).trim().equalsIgnoreCase(w[i].trim())) {
                    tempSetCategory.add(categoryListsFromMap.getString(j));
                    catToBeRemoved.add(w[i]);
                }
            }
        }
        String inputQry = getInputQuery();
        for (String ca : catToBeRemoved) {
            inputQry = inputQry.replace(ca, "").trim();
        }
        setInputQuery(inputQry);
        if (tempSetCategory.size() > 0) {
            field_set.put("category", tempSetCategory);
            setCategoriesInQuery(tempSetCategory);
        }
        else {
            tempSetCategory.add("_default_");
            setCategoriesInQuery(new HashSet<String>());
        }
        return field_set;
    }

    private HashMap<String, HashSet<String>> probableFields() throws Exception {
        String q = getInputQuery();
        HashMap<String, HashSet<String>> field_set = new HashMap<>();
        StringUtils su = new StringUtils();
        String[] w = q.split("\\s+");
        ArrayList<String> words = new ArrayList<>(Arrays.asList(w));
        JSONParser parser = new JSONParser();
        JSONObject field_map = new JSONObject();
        for (String catgory : getCategoriesInQuery()) {
            HashSet<String> toBeRemoved = new HashSet<>();
            field_map = objISNSCoreData.getFieldMapObject().getJSONObject(catgory);

            org.json.simple.JSONObject set_o = (org.json.simple.JSONObject) parser.parse(field_map.toString());
            for (Object f : field_map.keySet()) {
                if (f.toString().equals("title")) {
                    continue;
                }
                String val = field_map.get((String) f).toString().toLowerCase();
                JSONArray validValuesFromMap = field_map.getJSONArray(f.toString());

                for (String tr : toBeRemoved) {
                    words.remove(tr);
                }
                for (String wr : words) {
                    for (int j = 0; j < validValuesFromMap.length(); j++) {
                        HashSet<String> sset = field_set.get(f);
                        if ((wr.trim().length() > 3 && su.getLevenshteinDistance(validValuesFromMap.getString(j).trim(), wr.trim()) < 2)) {
                            if (sset == null) {
                                sset = new HashSet<>();
                            }
                            sset.add(validValuesFromMap.getString(j).trim());
                            field_set.put((String) f, sset);
                            toBeRemoved.add(wr);
                        } else if (validValuesFromMap.getString(j).trim().equalsIgnoreCase(wr.trim())) {
                            if (sset == null) {
                                sset = new HashSet<>();
                            }
                            if (wr.contains("-")) {
                                String[] splitted = wr.split("-");
                                for (int k = 0; k < splitted.length; k++) {
                                    sset.add(splitted[k]);
                                }
                            } else {
                                sset.add(wr);
                            }
                            toBeRemoved.add(wr);
                            field_set.put((String) f, sset);
                            break;
                        }
                    }
                }
            }
            for (String tr : toBeRemoved) {
                words.remove(tr);
            }
        }

        for (String catgory : getCategoriesInQuery()) {
            HashSet<String> toBeRemoved = new HashSet<>();
            field_map = objISNSCoreData.getFieldMapObject().getJSONObject(catgory);

            org.json.simple.JSONObject set_o = (org.json.simple.JSONObject) parser.parse(field_map.toString());
            for (Object f : set_o.keySet()) {
                if (!f.toString().equals("title")) {
                    continue;
                }
                String val = field_map.get((String) f).toString().toLowerCase();
                JSONArray validValuesFromMap = field_map.getJSONArray(f.toString());

                for (String tr : toBeRemoved) {
                    words.remove(tr);
                }
                for (String wr : words) {
                    for (int j = 0; j < validValuesFromMap.length(); j++) {
                        HashSet<String> sset = field_set.get(f);
                        if ((wr.trim().length() > 3 && su.getLevenshteinDistance(validValuesFromMap.getString(j).trim(), wr.trim()) < 2)) {
                            if (sset == null) {
                                sset = new HashSet<>();
                            }
                            sset.add(validValuesFromMap.getString(j).trim());
                            field_set.put((String) f, sset);
                            toBeRemoved.add(wr);
                        } else if (validValuesFromMap.getString(j).trim().equalsIgnoreCase(wr.trim())) {

                            if (sset == null) {
                                sset = new HashSet<>();

                            }
                            if (wr.contains("-")) {
                                String[] splitted = wr.split("-");
                                for (int k = 0; k < splitted.length; k++) {
                                    sset.add(splitted[k]);
                                }
                            } else {
                                sset.add(wr);
                            }
                            toBeRemoved.add(wr);
                            field_set.put((String) f, sset);
                            break;
                        }
                    }
                }
            }
        }
        return field_set;
    }
}
