package library;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuild {

	
	public static RequestSpecification requestBuild()
	{
		
		RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2/").setContentType(ContentType.JSON).build();
		return req;
	}
	
	public static ResponseSpecification responseBuild()
	{
		
		ResponseSpecification res= new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
		return res;
	}
	
	
}
