package edu.tacoma.uw.plsanch.gitgud.guide;

import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Zachary on 3/6/2017.
 */

public class Guide implements Serializable {

    String mGuideTitle;
    String mGuideAuthor;
    String mGuideHero;
    String mGuideText;
    int mGuideId;


    public static final String TITLE = "title", AUTHOR = "username"
            , HERO = "heroname", TEXT = "content";

    public Guide(String theGuideTitle, String theGuideAuthor, String theGuideHero, String theGuideText) {
        mGuideTitle = theGuideTitle;
        mGuideAuthor = theGuideAuthor;
        mGuideHero = theGuideHero;
        mGuideText = theGuideText;
    }

    public String getmGuideTitle() {
        return mGuideTitle;
    }

    public void setmGuideTitle(String mGuideTitle) {
        this.mGuideTitle = mGuideTitle;
    }

    public String getmGuideAuthor() {
        return mGuideAuthor;
    }

    public void setmGuideAuthor(String mGuideAuthor) {
        this.mGuideAuthor = mGuideAuthor;
    }

    public String getmGuideHero() {
        return mGuideHero;
    }

    public void setmGuideHero(String mGuideHero) {
        this.mGuideHero = mGuideHero;
    }

    public String getmGuideText() {
        return mGuideText;
    }

    public void setmGuideText(String mGuideText) {
        this.mGuideText = mGuideText;
    }

    /**
     * Parses the json string, returns an error message if unsuccessful.
     * Returns course list if success.
     * @param courseJSON
     * @return reason or null if successful.
     */
    public static String parseGuideJSON(String courseJSON, List<Guide> courseList) {
        String reason = null;
        if (courseJSON != null) {
            try {
                JSONArray arr = new JSONArray(courseJSON);

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    Guide course = new Guide(obj.getString(Guide.TITLE), obj.getString(Guide.AUTHOR)
                            , obj.getString(Guide.HERO), obj.getString(Guide.TEXT));
                    courseList.add(course);
                }
            } catch (JSONException e) {
                reason =  "Unable to parse data, Reason: " + e.getMessage();
            }

        }
        return reason;
    }
}
