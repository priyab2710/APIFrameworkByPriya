package PetStore.APIAutomation;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import payload.pet.petServiceClient;

public class PetAPITest {
	private long id;
	petServiceClient petServiceClient =new petServiceClient();
	
	@Test(dataProvider="testdata")
	public void createPet_getPetByID_deletePet(String categoryName,  String name, String tagName, String status) {
		  
		  try {		  		  
		 
		JsonPath createPetResponse = petServiceClient.addPet(categoryName, name, tagName, status);
		  //Get id of the created Pet
		  id = createPetResponse.getLong("id"); 
		  //Validate Acceptance Criteria after Add Pet
		  Assert.assertNotNull(id);
		  Assert.assertEquals(createPetResponse.get("category.name"), categoryName);
		  Assert.assertEquals(createPetResponse.get("name"), name);
		  Assert.assertEquals(createPetResponse.get("tags[0].name"), tagName);
		  Assert.assertEquals(createPetResponse.get("status"), status);
		
		  // Use above Pet id to Get Pet
		  JsonPath getPetResponse=petServiceClient.getPet(id);
		  
		  // Validate Acceptance Criteria after GetPet
		 Assert.assertEquals(getPetResponse.get("category.name"), categoryName);
		 Assert.assertEquals(getPetResponse.get("name"), name);
		 Assert.assertEquals(getPetResponse.get("tags[0].name"), tagName);
		 Assert.assertEquals(getPetResponse.get("status"), status);
		 	 
		 // Delete pet by ID 
		 JsonPath deletePetResponse= petServiceClient.deletePet(id);		  
		 // Validate Acceptance Criteria
		  Assert.assertEquals(String.valueOf(id), deletePetResponse.get("message"));
		 
      }
		  catch (Exception e)
		  {e.printStackTrace();}
	}
	
		
	@DataProvider(name = "testdata")
	public Object[][] getData()
	{
		Object[][] obj = new Object[3][4];

		obj[0][0] = "auto22";
		obj[0][1] = "autoname22";
		obj[0][2] = "autotagName22";
		obj[0][3] = "available";
		
		  obj[1][0]="auto33"; 
		  obj[1][1]="autoname33"; 
		  obj[1][2]="autotagName33";
		  obj[1][3] ="pending";
		  
		  obj[2][0]="auto55"; 
		  obj[2][1]="autoname55"; 
		  obj[2][2]="autotagName55";
		  obj[2][3] ="sold";
		  
		 
		return obj;

	}
	
	
}
