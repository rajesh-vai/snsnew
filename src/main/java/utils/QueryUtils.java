package utils;

import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;

/**
 * Created by rvairamani on 20-02-2018.
 */
public class QueryUtils {
    /*private String priceQuery(String query){
        String result = "";
        Matcher m = null;
        int count =0;

        VerbalExpression vb = _amount_range.build();
        if(vb.test(query)){
            String ssss = vb.getText(query);
            inputQry = inputQry.replaceAll(ssss, "");
            List<String> list = vb.getTextGroups(query,1);
            list.addAll(vb.getTextGroups(query,2));
            list.addAll(vb.getTextGroups(query,4));
            list.remove(1);
            TreeSet<Double> ts = new TreeSet<Double>();
            for(int i=0;i<list.size();i++)
                ts.add(Double.parseDouble(amountK(list.get(i))));

            return String.format("{\"range\": {\"sale_price\": {\"gte\": "+ts.first()+", \"lte\": "+ts.last()+"}}}");
        }

        vb = _amount_range_without_and.build();
        if(vb.test(query)){
            String ssss = vb.getText(query);
            inputQry = inputQry.replaceAll(ssss, "");
            List<String> list = vb.getTextGroups(query,1);
            list.addAll(vb.getTextGroups(query,4));
            TreeSet<Double> ts = new TreeSet<Double>();
            for(int i=0;i<list.size();i++)
                ts.add(Double.parseDouble(amountK(list.get(i))));

            return String.format("{\"range\": {\"sale_price\": {\"gte\": "+ts.first()+", \"lte\": "+ts.last()+"}}}");
        }

        vb = _amount_above.build();
        if(vb.test(query)){
            String ssss = vb.getText(query);
            inputQry = inputQry.replaceAll(ssss, "");
            List<String> list = vb.getTextGroups(query,1);

            return String.format("{\"range\": {\"sale_price\": {\"gte\": "+amountK(list.get(0))+"}}}");
        }

        vb = _amount_below.build();
        if(vb.test(query)){
            String ssss = vb.getText(query);
            inputQry = inputQry.replaceAll(ssss, "");
            List<String> list = vb.getTextGroups(query,1);
            return String.format("{\"range\": {\"sale_price\": {\"lte\": "+amountK(list.get(0))+"}}}");
        }

        vb = _amount_around.build();
        if(vb.test(query)){
            String ssss = vb.getText(query);
            inputQry = inputQry.replaceAll(ssss, "");
            List<String> list = vb.getTextGroups(query,1);
            double val = Double.parseDouble(amountK(list.get(0)));
            return String.format("{\"range\": {\"sale_price\": {\"gte\": "+(val-(val*0.05))+", \"lte\": "+(val+(val*0.05))+"}}}");
        }

        return result;
    }*/
}
