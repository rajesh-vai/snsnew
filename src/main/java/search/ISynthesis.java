package search;

import dataprocessor.Noise;
import dataprocessor.Synonyms;
import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

/**
 * Created by Rajesh on 17-02-2018.
 */
public class ISynthesis {

    StringUtils su = new StringUtils();
    @Inject
    Synonyms synonyms;

    @Inject
    Noise noise;
     /**
     * @param query
     * @return
     */
    public String preprocessQuery(String query) {

        query = replaceSynonyms(query);
        query = removeNoise(query);
        return query;
    }

    /**
     * @param query
     * @return
     */
    public String replaceSynonyms(String query) {
        Hashtable<String, String> synonymsFromTable = synonyms.getSynonyms();
        String _w[] = query.split(" ");

        Set<String> k = synonymsFromTable.keySet();
        for (String s : k) {
            if (s.contains("-") || s.contains(" ") || s.contains("/") || s.contains("'")) {
                if (query.contains(s)) {
                    query = query.replace(s, synonymsFromTable.get(s));
                }
            }
        }

        String[] qryArray = query.split(" ");
        String currWord = "";
        for (int i = 0; i < qryArray.length; i++) {
            currWord = qryArray[i];
            if (currWord.length() < 1)
                continue;
            for (String s : k) {
                if (currWord.length() > 4 && su.getLevenshteinDistance(currWord, s) < 2) {
                    query = query.replace(currWord, synonymsFromTable.get(s));
                    break;
                } else if (currWord.equalsIgnoreCase(s)) {
                    query = query.replace(currWord, synonymsFromTable.get(s));
                    break;
                }
            }
        }
        query = query.trim().replaceAll(" +", " ");
        return query;
    }

    private String removeNoise(String query) {
        String w[] = query.split("\\W+");
        Vector<String> noise = new Vector<>();
        for (int i = 0; i < w.length; i++) {
            if (noise.contains(w[i])) {
                query = query.replace(w[i] + " ", " ");
            }
        }
        return query;
    }

}
