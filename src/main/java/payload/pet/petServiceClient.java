package payload.pet;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import reusable.JsonParser;


public class petServiceClient {

	static String resource="/pet";
	static String Base_URI="https://petstore.swagger.io/v2/";
	
	public JsonPath addPet(String categoryName,  String name, String tagName, String status)
	{
		
		String request="{\r\n" + 
				"  \"id\": 0,\r\n" + 
				"  \"category\": {\r\n" + 
				"    \"id\": 0,\r\n" + 
				"    \"name\": \""+categoryName+"\"\r\n" + 
				"  },\r\n" + 
				"  \"name\": \""+name+"\",\r\n" + 
				"  \"photoUrls\": [\r\n" + 
				"    \"string\"\r\n" + 
				"  ],\r\n" + 
				"  \"tags\": [\r\n" + 
				"    {\r\n" + 
				"      \"id\": 0,\r\n" + 
				"      \"name\": \""+tagName+"\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"status\": \""+status+"\"\r\n" + 
				"}";

		String postResponse = given().spec(postrequestBuild())
				  .body(request)
				  .when().post(resource)
				  .then().spec(postresponseBuild()).extract().response().asString();
		
		JsonPath createPetResponse = JsonParser.jsonparser(postResponse);
		return createPetResponse;
	}
	
	public static RequestSpecification postrequestBuild()
	{
		
		RequestSpecification req= new RequestSpecBuilder().setBaseUri(Base_URI).setContentType(ContentType.JSON).build();
		return req;
	}
	
	public static ResponseSpecification postresponseBuild()
	{
		
		ResponseSpecification res= new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
		return res;
	}
	
	// Delete pet request
	public JsonPath deletePet(long id)
	{
		 String deleteResponse=
				  given().spec(deleterequestBuild())
				  .when().delete(resource+id)
				  .then().spec(deleteresponseBuild()).extract().response().asString();
		 JsonPath deletePetResponse = JsonParser.jsonparser(deleteResponse);
         
		 return deletePetResponse;
	}
	
	public RequestSpecification deleterequestBuild()
	{
		
		RequestSpecification req= new RequestSpecBuilder().setBaseUri(Base_URI).build();
		return req;
	}
	
	public ResponseSpecification deleteresponseBuild()
	{
		
		ResponseSpecification res= new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
		return res;
	}
	
	
	// Get pet request
	
	public JsonPath getPet(long id)
	{
		  String getResponse=
					 given().spec(getrequestBuild()).when().get(resource+ id)
					 .then().spec(getresponseBuild())
					 .extract().response().asString();
		  JsonPath getPetResponse = JsonParser.jsonparser(getResponse);
          return getPetResponse;
	}
	
	public RequestSpecification getrequestBuild()
	{
		
		RequestSpecification req= new RequestSpecBuilder().setBaseUri(Base_URI).build();
		return req;
	}
	
	public ResponseSpecification getresponseBuild()
	{
		
		ResponseSpecification res= new ResponseSpecBuilder().expectStatusCode(200).build();
		return res;
	}
			
}
