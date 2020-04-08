package reusable;

import io.restassured.path.json.JsonPath;

public class JsonParser {

	
	public static JsonPath jsonparser(String payload) {
	JsonPath js = new JsonPath(payload);
	return js;
	}
}
