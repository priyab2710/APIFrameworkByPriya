package payload.pet;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import library.SpecBuild;
import reusable.JsonParser;

public class GetPetQueryBuilder {
  
	static String GetPetResource="/pet/";
	static String Base_URI="https://petstore.swagger.io/v2/";
	
	public static JsonPath GetResponse(long id)
	{
		  String getResponse=
					 given().spec(getrequestBuild()).when().get(GetPetResource+ id)
					 .then().spec(getresponseBuild())
					 .extract().response().asString();
		  JsonPath getPetResponse = JsonParser.jsonparser(getResponse);
          return getPetResponse;
	}
	
	public static RequestSpecification getrequestBuild()
	{
		
		RequestSpecification req= new RequestSpecBuilder().setBaseUri(Base_URI).build();
		return req;
	}
	
	public static ResponseSpecification getresponseBuild()
	{
		
		ResponseSpecification res= new ResponseSpecBuilder().expectStatusCode(200).build();
		return res;
	}

}
