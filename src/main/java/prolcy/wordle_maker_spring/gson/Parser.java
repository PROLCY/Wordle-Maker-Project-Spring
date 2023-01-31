package prolcy.wordle_maker_spring.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
public class Parser {
    private final Gson gson = new Gson();
    public List<List<Map<String, String>>> parseWordList(String json) {
        if(json == null)
            return null;
        Type listType = new TypeToken<List<List<Map<String, String>>>>() {}.getType();
        return gson.fromJson(json, listType);
    }
    public Map<String, String> parseKeyState(String json) {
        if(json == null)
            return null;
        Type mapType = new TypeToken<Map<String, String>>() {}.getType();
        return gson.fromJson(json, mapType);
    }
    public List<Map<String, String>> parseWord(String json) {
        if(json == null)
            return null;
        Type mapType = new TypeToken<List<Map<String, String>>>() {}.getType();
        return gson.fromJson(json, mapType);
    }
}
