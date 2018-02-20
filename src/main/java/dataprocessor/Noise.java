package dataprocessor;

import java.sql.ResultSet;
import java.util.Vector;

/**
 * Created by Rajesh on 19-02-2018.
 */
public class Noise extends ISNSCoreData {

    public Vector<String> getNoise() {
        return noise;
    }

    public void setNoise(Vector<String> noise) {
        this.noise = noise;
    }

    Vector<String> noise = new Vector<>();
    public void readNoiseList(String folder) throws Exception {
        Vector<String> list = new Vector<>();
        String stopwordQuery = "Select noise from noise where companyid=" + companyId;
        ResultSet rs = dbUtils.selectOutput(stopwordQuery);
        while (rs.next()) {
            String stopword[] = rs.getString("noise").split(",");
            for (int i = 0; i < stopword.length; i++) {
                if (stopword[i].length() > 0) {
                    list.add(stopword[i]);
                }

            }

        }
        setNoise(list);
    }
}
