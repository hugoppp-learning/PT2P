package praktikum.aufgabe2F;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    public static double getNumber(String str) {
        Pattern pattern = Pattern.compile("(\\d[.]\\d+)\\s?m.*");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return Math.round(Float.parseFloat(matcher.group(1))*100.0)/100.0;
        }
        return 0.0;
    }

    public static String getNumberNotNull(JSONObject json, String key) {
        Object obj = json.get(key);
        return obj.equals(JSONObject.NULL) ? "0" : ((String) obj).isEmpty() ?
          "0" : (String) obj;
    }

    public static String getNotNull(JSONObject json, String key) {
        if (json.has(key)) {
            Object obj = json.get(key);
            return obj.equals(JSONObject.NULL) ? null :
              ((String) obj).isEmpty() ? null : (String) obj;
        }
        return null;
    }

    public static JSONObject jObjErzeugen() {
        try (FileReader inFile = new FileReader(new File("C:/Users" +
          "/Ziska/SynologyDrive/Drive/HAW/Module/2.Semester" +
          "/PM2/pm2_vorgabe/src/main/resources/json/figuren.json"));
             StringWriter sw = new StringWriter()) {
            inFile.transferTo(sw);
            String contents = sw.toString();
            JSONObject o = new JSONObject(contents);
            JSONArray docs = o.getJSONArray("docs");
            JSONObject figur = (JSONObject) docs.get(892);
            return figur;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
