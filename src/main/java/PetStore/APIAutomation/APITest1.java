package PetStore.APIAutomation;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import payload.JsonParser;
import payload.Payload;

import static io.restassured.RestAssured.*;

public class APITest1 {
public JsonPath js;
	
	@Test
	public void TestCase1()
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2/";
		
		js= JsonParser.jsonparser(Payload.PostRequest());
		
		System.out.println();
		String postResponse=  given().header("Content-Type","application/json")
		.body(Payload.PostRequest())
		.when().post("/pet")
		.then().assertThat().statusCode(200).extract().response().asString();
				
		System.out.println("post respnse = " +postResponse);
		
		js= JsonParser.jsonparser(postResponse);
		
		int id= js.getInt("id");
		
		String getResponse=  given()
				.when().log().all().get("/pet/"+id)
				.then().assertThat().statusCode(200).extract().response().asString();
				
	//	js= JsonParser.jsonparser(getResponse);
		
		System.out.println("get response = "+getResponse);
		
		
		
	}
}
