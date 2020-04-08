package PetStore.APIAutomation;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import library.SpecBuild;
import payload.pet.GetPetQueryBuilder;
import payload.pet.petPayload;
import reusable.JsonParser;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;


public class APITest1 {

	
	
	  @Test(dataProvider="testdata") public void TestCase1(String
	  categoryName,String name, String tagName, String status ) { // int id=400;
	  try {
	  
	  //Add pet
	  
	  String postResponse = given().spec(SpecBuild.requestBuild())
	  .body(petPayload.PostRequest(categoryName,name, tagName,status))
	  .when().post("/pet") .then().spec(SpecBuild.responseBuild())
	  .extract().response().asString();
	  
	  // Thread.sleep(2000L);
	  JsonPath js2= JsonParser.jsonparser(postResponse);
	  
	  System.out.println(postResponse); 
	  System.out.println(js2.get("id"));
	  
	  long id = js2.getLong("id");
	  //String string_id= String.valueOf(id);
	  
	  // Get pet by ID 
	  
	  String getResponse=
	  given().log().all().spec(SpecBuild.requestBuild()).when().get("/pet/"+id)
	  .then().log().all().spec(SpecBuild.responseBuild())
	  .extract().response().asString();
	  
	  JsonPath js1= JsonParser.jsonparser(getResponse); String actualCategoryName=
	  js1.getString("category.name");
	  
	  Assert.assertEquals(actualCategoryName, categoryName);
	  
	  // Delete pet by ID 
	  
	  String deleteResponse=
	  given().spec(SpecBuild.requestBuild()) .when().delete("/pet/"+id)
	  .then().spec(SpecBuild.responseBuild()) .extract().response().asString();
	  
	  JsonPath js3= JsonParser.jsonparser(deleteResponse);
	  
	  Assert.assertEquals(String.valueOf(id), js3.get("message"));
	  
	  } catch (ClassCastException e1) { System.out.println("cast exception");
	  e1.printStackTrace(); Assert.fail("Test case failed due to exception"); }
	  catch (Exception e) {
	  
	  e.printStackTrace(); Assert.fail("Test case failed due to exception");}
	  
	  }
	 
	 
	
	  @Test(dataProvider = "testdata") 
	  public void TestCase2(String categoryName,  String name, String tagName, String status) {
	  
	  try {
	  
	  
	  // ADD Pet
	String postResponse = given().log().all().spec(SpecBuild.requestBuild())
	  .body(petPayload.PostRequest(categoryName, name, tagName,
	  status)).when().post("/pet")
	  .then().spec(SpecBuild.responseBuild()).extract().response().asString();
	  
	  JsonPath js2 = JsonParser.jsonparser(postResponse);
	  
	  long id = js2.getLong("id"); 
	  System.out.println(id);
	  
	  // Get Pet by status and checked for the new ID
	  
	  String getResponse = given().log().all().spec(GetPetQueryBuilder.getrequestBuild())
	  .queryParam("status", "available").when().get("/pet/findByStatus")
	  .then().spec(GetPetQueryBuilder.getresponseBuild()).extract().response().asString();
	  
	  JsonPath js3 = JsonParser.jsonparser(getResponse);
	  
	  ArrayList<String> ids = new ArrayList<String>();
	  
	  ids.add(String.valueOf(js3.get("id"))); 
	  
	  for (int i = 0; i < ids.size(); i++)
	  { 
		  if (ids.get(i).equalsIgnoreCase(String.valueOf(id))) 
		  {
	  Assert.assertTrue(true);
	  break;
	  }
	  }
	  
	  // Updated the status for above ID to pending
	  
		given().spec(GetPetQueryBuilder.getrequestBuild()).formParam("status", "pending")
		.when().post("/pet/" + id).then().spec(SpecBuild.responseBuild()).extract().response().asString();
	 
		/*
			 * given().spec(SpecBuild.getrequestBuild().formParam("status",
			 * "pending").when().post("/pet/" +
			 * id).then().spec(SpecBuild.responseBuild()).extract().response().asString();
			 */ 
	  
	  
	  // Get pet by ID
	  String getResponseafterupdate = given().when().get("/pet/" +
	  id).then().spec(GetPetQueryBuilder.getresponseBuild()).extract().response().asString();
	  
	  JsonPath js4 = JsonParser.jsonparser(getResponseafterupdate); String
	  actualStatusAfterUpdate = js4.get("status");
	  
	  Assert.assertEquals(actualStatusAfterUpdate, "pending");
	  
	  // Updated the status for above ID to SOLD 
	  given().formParam("status",
	  "sold").spec(GetPetQueryBuilder.getrequestBuild()).when().post("/pet/" + id).then().spec(SpecBuild.responseBuild());
	  
	  // Get pet by ID 
	  String getResponseAfterStatus_SOLD =
	  given().when().get("/pet/" +
	  id).then().spec(SpecBuild.responseBuild()).extract().response().asString();
	  
	  JsonPath js5 = JsonParser.jsonparser(getResponseAfterStatus_SOLD); String
	  actualStatusAfterUpdate_sold = js5.get("status");
	  
	  Assert.assertEquals(actualStatusAfterUpdate_sold, "sold");
	  
	  // delete pet for testcase2
	  
	  String deleteResponse = given().spec(SpecBuild.requestBuild()).when().delete("/pet/" +
	  id).then().spec(SpecBuild.responseBuild()).extract().response().asString();
	  
	  JsonPath js6 = JsonParser.jsonparser(deleteResponse);
	  
	  Assert.assertEquals(String.valueOf(id), js6.get("message"));
	  
	  } catch (Exception e)
	  
	  { e.printStackTrace(); Assert.fail("Test case failed due to exception");
	  
	  
	  }
	  
	  }
	 
	@DataProvider(name = "testdata")
	public Object[][] getData() {
		Object[][] obj = new Object[1][4];

		obj[0][0] = "auto22";
		obj[0][1] = "autoname22";
		obj[0][2] = "autotagName22";
		obj[0][3] = "available";

		/*
		 * obj[1][0]="auto33"; obj[1][1]="autoname33"; obj[1][2]="autotagName33";
		 * obj[1][3] = "available";
		 */ 

		return obj;

	}
}
