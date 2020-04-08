package payload.pet;

import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class DeletePetQueryBuilder {

	static String DeletePetResource="/pet/";
	static String Base_URI="https://petstore.swagger.io/v2/";
	
	public static String DeletePetResponse(long id)
	{
		 String deleteResponse=
				  given().spec(deleterequestBuild())
				  .when().delete(DeletePetResource+id)
				  .then().spec(deleteresponseBuild()).extract().response().asString();
		 return deleteResponse;
	}
	
	public static RequestSpecification deleterequestBuild()
	{
		
		RequestSpecification req= new RequestSpecBuilder().setBaseUri(Base_URI).build();
		return req;
	}
	
	public static ResponseSpecification deleteresponseBuild()
	{
		
		ResponseSpecification res= new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
		return res;
	}
}
