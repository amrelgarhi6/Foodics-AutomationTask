package utiltites.readers.json_reader;

import utiltites.exceptions.Exceptions;
import io.restassured.path.json.JsonPath;
import utiltites.logger.Log4JLogger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

public class JSONDataManager {
    private static FileReader reader;
    private static JsonPath jsonPath;
    private static JSONObject object = null;

    /**
     * Read JSON File with File Reader
     *
     * @return FileReader type, JSON File stored in @param reader value
     */
    public static FileReader readFileJSON(String filePath) {
        if (filePath != null)
            try {
                reader = new FileReader(filePath);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        else Log4JLogger.logINFO(JSONDataManager.class,"Please send the JSON file path");
        return reader;
    }

    /**
     * Read JSON File with File Reader
     *
     * @return JSONObject data type, the desired JSON Object sent by @param filePath
     */
    public static JSONObject parseJSON(String filePath) {
        try {
            JSONParser parser = new JSONParser();
            object = (JSONObject) parser.parse(readFileJSON(filePath));
        } catch (Exception e) {
            Exceptions.handle(JSONDataManager.class, e);
        }
        return object;
    }

    /**
     * Extract the desired JSON Data based on the key path
     *
     * @param object
     * @param keyPath
     * @param type
     * @return Object data type, the desired JSON Object sent by @param filePath
     */
    public static Object getJSONData(JSONObject object, String keyPath, Types type) {
        return getObject(keyPath, type, object);
    }

    /**
     * Extract the desired JSON Data based on the key path
     *
     * @param filePath
     * @param keyPath
     * @param type
     * @return Object data type, the desired JSON Object sent by @param filePath
     */
    public static Object getJSONData(String filePath, String keyPath, Types type) {
        Object keyValue;
        Log4JLogger.logINFO(JSONDataManager.class,"Test data file path: " + filePath);
        Log4JLogger.logINFO(JSONDataManager.class,"Key path: " + keyPath);
        object = parseJSON(filePath);
        keyValue = getObject(keyPath, type, object);
        Log4JLogger.logINFO(JSONDataManager.class,"Key value: " + keyValue);
        return keyValue;
    }

    /**
     * Extract the desired JSON Data to String Value
     *
     * @param object
     * @param keyPath
     * @return String data type
     */
    private static String getDataAsString(JSONObject object, String keyPath) {
        jsonPath = new JsonPath(String.valueOf(object));
        return jsonPath.getJsonObject(keyPath).toString();
    }

    /**
     * Extract the desired JSON Data to Map Value
     *
     * @param object
     * @param keyPath
     * @return Map  data type
     */
    private static Map<?, ?> getDataAsMap(JSONObject object, String keyPath) {
        jsonPath = new JsonPath(String.valueOf(object));
        return jsonPath.getMap(keyPath);
    }

    /**
     * Extract the desired JSON Data to List Value
     *
     * @param object
     * @param keyPath
     * @return List data type
     */
    private static List<?> getDataAsList(JSONObject object, String keyPath) {
        jsonPath = new JsonPath(String.valueOf(object));
        return jsonPath.getList(keyPath);
    }

    /**
     * Retrieve the desired JSON Object from the desired keyPath
     *
     * @param keyPath
     * @param type
     * @param object
     * @return Object data type
     */
    private static Object getObject(String keyPath, Types type, JSONObject object) {
        Object data;
        switch (type) {
            case STRING -> data = getDataAsString(object, keyPath);
            case MAP -> data = getDataAsMap(object, keyPath);
            case LIST -> data = getDataAsList(object, keyPath);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
        if (data != null)
            return data;
        else {
            Log4JLogger.logINFO(JSONDataManager.class,"No data found");
        }
        return null;
    }

    public enum
    Types {
        STRING, MAP, LIST
    }
}
