import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import pojo.ObjectFinal;

import static io.restassured.RestAssured.*;

public class GetPetRequest {

	@Test
	public void GetPet()
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		ObjectFinal obj=given().pathParam("petId", 400).expect().defaultParser(Parser.JSON)
		.when().get("/pet/{petId}").as(ObjectFinal.class);
		
		String response=given().pathParam("petId", 400).urlEncodingEnabled(false)
				.when().get("/pet/{petId}").then().extract().response().asString();
		
		System.out.println(response);
		
		
		System.out.println(obj.getId());
		System.out.println(obj.getName());
		System.out.println(obj.getStatus());
		//System.out.println(obj.getCategory());
		System.out.println(obj.getPhotoUrls().toString());
		
		System.out.println(obj.getTags().get(0).getId());
		System.out.println(obj.getTags().get(0).getName());
		//System.out.println(obj.getTags().gettagId());
		
           
		
	}
	
}
