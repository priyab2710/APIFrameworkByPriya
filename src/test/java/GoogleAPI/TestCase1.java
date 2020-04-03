package GoogleAPI;

import io.restassured.RestAssured;
import pojoPet.AddAPI;
import pojoPet.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

import org.testng.annotations.Test;

public class TestCase1 {

	
	@Test
	public void AddPlace()
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		AddAPI ap=new AddAPI();
		Location l=new Location();
		ap.setAccuracy(20);
		ap.setAddress("123 dix street wlg");
		ap.setName("priya test");
		ap.setPhone_number("+47 280000989");
		ap.setWebsite("www.test.com");
		ap.setLanguage("EN");
		ArrayList<String> al=new ArrayList<String>();
		al.add("test1");
		al.add("test2");
		ap.setTypes(al);
		l.setLat(-20.90);
		l.setLng(30.234);
		ap.setLocation(l);
		
	   String response=	given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
	    .body(ap)
		.when().log().all().post("/maps/api/place/add/json")
		.then().log().all().extract().response().asString();
	   
	   System.out.println(response);
	}
}
