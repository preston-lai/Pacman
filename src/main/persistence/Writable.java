package persistence;

import org.json.JSONObject;

// Referenced JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object

    JSONObject toJson();
}
