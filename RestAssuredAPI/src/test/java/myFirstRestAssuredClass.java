import static io.restassured.RestAssured.*;
import io.restassured.path.xml.XmlPath;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class myFirstRestAssuredClass {
	public static void getResponseBody() {
		given().queryParam("CUSTOMER_ID","68195")
        .queryParam("PASSWORD","1234!")
        .queryParam("Account_No","1")
        .when().get("http://demo.guru99.com/V4/sinkministatement.php").then().log()
        .body();	
	}
	
	public static void getResponseStatus(){
		   int statusCode= given().queryParam("CUSTOMER_ID","68195")
		           .queryParam("PASSWORD","1234!")
		           .queryParam("Account_No","1") .when().get("http://demo.guru99.com/V4/sinkministatement.php")
		           .getStatusCode();
		   System.out.println("The response status is "+statusCode);
		   given().when().get("http://demo.guru99.com/V4/sinkministatement.php").then().assertThat()
		   .statusCode(200);
	}
	
	public static void getResponseHeaders(){
		   System.out.println("The headers in the response "+
		                   get("http://demo.guru99.com/V4/sinkministatement.php").then().extract()
		           .headers());
		}
	
	public static void getResponseTime(){
		  System.out.println("The time taken to fetch the response "
				  +get("http://demo.guru99.com/V4/sinkministatement.php")
		         .timeIn(TimeUnit.MILLISECONDS) + " milliseconds");
		}
	
	public static void getResponseContentType(){
		   System.out.println("The content type of response "+
		           get("http://demo.guru99.com/V4/sinkministatement.php").then().extract()
		              .contentType());
		}
	
	 public static void getSpecificPartOfResponseBody() {
	        XmlPath xmlPath = when().get("http://demo.guru99.com/V4/sinkministatement.php").then().extract().xmlPath();
	        List<String> amounts = xmlPath.getList("result.statements.AMOUNT", String.class);
	        int sumOfAll = 0;
	        for (String a : amounts) {
	            System.out.println("The amount value fetched is " + a);
	            sumOfAll = sumOfAll + Integer.valueOf(a);
	        }
	        System.out.println("The total amount is " + sumOfAll);
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getResponseBody();
		//getResponseStatus();
		//getResponseHeaders();
		//getResponseTime();
		//getResponseContentType();
		getSpecificPartOfResponseBody();
	}

}
