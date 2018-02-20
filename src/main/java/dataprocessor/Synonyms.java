package dataprocessor;

import java.sql.ResultSet;
import java.util.Hashtable;

/**
 * Created by Rajesh on 19-02-2018.
 */
public class Synonyms extends ISNSCoreData {

    private Hashtable<String, String> synonyms = new Hashtable<>();

    public Hashtable<String, String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(Hashtable<String, String> synonyms) {
        this.synonyms = synonyms;
    }


    public void loadSynonyms(String folderName) throws Exception {

        String synonymQuery = "Select keyword,synonyms from synonyms where companyid=" + getCompanyId();
        ResultSet rs = dbUtils.selectOutput(synonymQuery);
        while (rs.next()) {
            String syn[] = rs.getString("synonyms").split(",");
            for (int i = 0; i < syn.length; i++) {
                if (syn[i].length() > 0) {
                    synonyms.put(syn[i].trim(), rs.getString("keyword"));
                }
            }
        }

        String spellingQuery = "Select keyword,spellings from spellcheck where companyid=" + getCompanyId();
        rs = dbUtils.selectOutput(spellingQuery);
        while (rs.next()) {
            String spel[] = rs.getString("spellings").split(",");
            for (int i = 0; i < spel.length; i++) {
                if (spel[i].length() > 0) {
                    synonyms.put(spel[i].trim(), rs.getString("keyword"));
                }
            }

        }
    }
}
