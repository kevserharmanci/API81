package get_Request;

import base_Url.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.awt.geom.RectangularShape;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonplaceholderBaseUrl {
    /*
    Given
       https://jsonplaceholder.typicode.com/todos/2
   When
       I send GET Request to the URL
   Then
       Status code is 200
       And "completed" is false
       And "userId" is 1
       And "title" is "quis ut nam facilis et officia qui"
       And header "Via" is "1.1 vegur"
       And header "Server" is "cloudflare"
       {
           "userId": 1,
           "id": 2,
           "title": "quis ut nam facilis et officia qui",
           "completed": false
       }
*/

    @Test
    public void get01() {
        spec.pathParams("first", "todos", "second", 2);


        //Set The Expected Data --- Payload
        Map<String, Object> expextedData = new HashMap<>();
        expextedData.put("userId", 1);
        expextedData.put("id", 2);
        //expextedData.put("title", "quis ut nam facilis et officia qui");
        //.put("completed", false);
        System.out.println("expextedData = " + expextedData);

        Response response = given().spec(spec).when().get("/{first}/{second}");

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expextedData.get("userId"), actualData.get("userId"));
        assertEquals(expextedData.get("id"), actualData.get("id"));
        assertEquals(expextedData.get("title"), actualData.get("title"));
        assertEquals(expextedData.get("completed"), actualData.get("completed"));
        assertEquals(expextedData.get("expextedData"), actualData.get("expextedData"));


       /* SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < actualData.size(); i++) {
            softAssert.assertEquals(expextedData.get(i), actualData.get(i));
        }
        softAssert.assertAll();*/

        // Status code is 200
        response.then().statusCode(200);


        assertEquals("1.1 vegur",response.header("Via"));
        //       And header "Server" is "cloudflare"
        assertEquals("cloudflare",response.header("Server"));


    }

}
