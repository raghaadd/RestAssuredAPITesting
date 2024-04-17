import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import dev.failsafe.internal.util.Assert;
import io.restassured.RestAssured;
import io.restassured.internal.path.json.mapping.JsonObjectDeserializer;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;

import java.util.Scanner;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookAPITest {
	 private String BASE_URL = "";
	 Scanner scanner;
	@BeforeEach
	public void beforeTest() {
		BASE_URL= "http://jsonplaceholder.typicode.com";
		scanner = new Scanner(System.in);
		
	}
	
	 @Test
	 @Order(0)
	    public void testGetAllBooks() {
	        given().
	        when().
	            get(BASE_URL+"/posts").
	        then().
	            assertThat().
	            statusCode(200).
	            body("size()", greaterThan(0)).log().body();
	  }
	 
	 @Test
	 @Order(1)
	    public void testGetBookByID() {
	        System.out.print("Please enter the book ID: ");
	        int bookID=scanner.nextInt();
	        
	        given().
	        when().
	            get(BASE_URL+"/posts/"+bookID).
	        then().
	            assertThat().
	            statusCode(200).
	            body("id", equalTo(bookID)).log().body();
	  }
	
	 @Test
	 @Order(2)
	    public void testPostAddNewBook() {
		    System.out.println("*************************Add New Book*************************");
	        System.out.println("Enter the user ID:");
	        int userId = scanner.nextInt();
	        System.out.println("Enter the book ID:");
	        int bookId = scanner.nextInt();
	        scanner.nextLine(); 
	        System.out.println("Enter the book title:");
	        String title = scanner.nextLine();
	        System.out.println("Enter the book body:");
	        String body = scanner.nextLine();
	        
	        RequestSpecification request=RestAssured.given();
	        request.header("Content-Type","application/json");
	        JSONObject json=new JSONObject();
	        json.put("userId", userId);
	        json.put("id", bookId);
	        json.put("title", title);
	        json.put("body", body);
	        
	        request.body(json.toJSONString());
	        Response response=request.post(BASE_URL+"/posts");
	        int code=response.getStatusCode();
	        org.junit.Assert.assertEquals(code, 201);
	  }
	 
	 @Test
	 @Order(3)
	    public void testPutUpdateBook() {
	        System.out.println("*************************Update Book Details*************************");
	        System.out.println("Enter the user ID:");
	        int userId = scanner.nextInt();
	        System.out.println("Enter the book ID:");
	        int bookId = scanner.nextInt();
	        scanner.nextLine(); 
	        System.out.println("Enter the book title:");
	        String title = scanner.nextLine();
	        System.out.println("Enter the book body:");
	        String body = scanner.nextLine();

	        given().
	            contentType("application/json").
	            body("{\"userId\":\"" + userId + "\", \"title\":\"" + title + "\", \"body\":\"" + body + "\"}"). 
	        when().
	            put(BASE_URL + "/posts/" + bookId).
	        then().
	            assertThat().
	            statusCode(200);
	    }
	 
	 @Test
	 @Order(4)
	    public void testDeleteBook() {
		 System.out.println("*************************Delete Book Details*************************");
		 System.out.println("Enter the book ID:");
	      int bookId = scanner.nextInt();
		  RequestSpecification request=RestAssured.given();
	      
	        Response response=request.delete(BASE_URL+"/posts/"+bookId);
	        int code=response.getStatusCode();
	        org.junit.Assert.assertEquals(code, 200);
	    }
	 
	 @AfterEach
		public void AfterTest() {
		 
			
		}

}
