import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by USER on 19-02-2016.
 */
public class ParseJSON {
    public static String[] codes;
    public static String[] names;
    public static String[] descriptions;
    public static String[] credits;
    public static String[] ids;
    public static String[] ltps;


    public static final String JSON_ARRAY = "course";
    public static final String KEY_CODE = "code";
    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_CREDIT = "credits";
    public static final String KEY_ID = "id";
    public static final String KEY_LTP = "l_t_p";

    private JSONArray course=null;

    private String json;
    public ParseJSON(String json)
    {
        this.json=json;
    }
    protected void parseJSON()
    {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            course = jsonObject.getJSONArray(JSON_ARRAY);

            codes = new String[course.length()];
            names  = new String[course.length()];
            descriptions = new String[course.length()];
            credits = new String[course.length()];
            ids = new String[course.length()];
            ltps = new String[course.length()];
            for(int i=0;i<course.length();i++){
                JSONObject jo = course.getJSONObject(i);
                codes[i] = jo.getString(KEY_CODE);
                names[i] = jo.getString(KEY_NAME);
                descriptions[i] = jo.getString(KEY_DESCRIPTION);
                credits[i] = jo.getString(KEY_CREDIT);
                ids[i] = jo.getString(KEY_ID);
                ltps[i] = jo.getString(KEY_LTP);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
