package get_Request;

import base_Url.RegresInBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;


public class Get02b extends RegresInBaseUrl {
/*
   Given
       https://reqres.in/api/users/23

   When
       User send a GET Request to the url
   Then
       HTTP Status code should be 404
   And
       Status Line should be HTTP/1.1 404 Not Found
   And
       Server is "cloudflare"
   And
       Response body should be empty

*/

    @Test
    public void get01() {


        //Given
        //       https://reqres.in/api/users/23
        spec.pathParams("second","users","third",23);
        //   When
        //       User send a GET Request to the url
        Response response=given().spec(spec).when().get("/{second}/{third}");
        response.prettyPrint();
        //   Then
        //       HTTP Status code should be 404 //   And
        //       Status Line should be HTTP/1.1 404 Not Found
        response.then().statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found");


        assertEquals(404,response.statusCode());
        assertEquals("HTTP/1.1 404 Not Found",response.getStatusLine());

        //   And
        //       Server is "cloudflare"
        assertEquals("cloudflare",response.header("Server"));

        //   And
        //       Response body should be empty
        assertEquals("",response.asString().replaceAll("\\W",""));
        assertEquals(0,response.asString().replaceAll("[^A-Za-z0-9]","").length());
        assertEquals(2,response.asString().replaceAll("\\s","").length());

    }
}
