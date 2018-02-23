package dataprocessor;

import org.json.JSONException;
import org.json.JSONObject;
import utils.DbUtils;
import utils.FileUtils;

import javax.inject.Inject;

/**
 * Created by Rajesh on 19-02-2018.
 */
public class ISNSCoreData {
    @Inject
    DbUtils dbUtils;

    @Inject
    FileUtils fileUtils;
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    int companyId=0;

    public JSONObject getFieldMapObject() {
        return fieldMapObject;
    }

    public void setFieldMapObject(JSONObject fieldMapObject) {
        this.fieldMapObject = fieldMapObject;
    }

    JSONObject fieldMapObject  = new JSONObject();

    public void loadFieldMap(String folderName) throws Exception {
        JSONObject jsonObj  = new JSONObject( fileUtils.readFileToString(folderName) );
        setFieldMapObject(jsonObj);
        jsonObj = null;
    }
}
