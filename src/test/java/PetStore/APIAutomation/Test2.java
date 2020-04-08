package PetStore.APIAutomation;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import library.SpecBuild;
import payload.pet.GetPetQueryBuilder;

public class Test2 {

	
	@Test
	public void getreq()
	{
		
		long id = 15435006005156L; 
		 String getResponse = given().spec(GetPetQueryBuilder.getrequestBuild()).queryParam("status",
				  "available").when().get("/pet/findByStatus").then()
				  .spec(SpecBuild.responseBuild()).extract().response().asString();
				
		 
		 System.out.println(getResponse);
	}
}
