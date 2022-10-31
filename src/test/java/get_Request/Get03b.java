package get_Request;

import base_Url.JsonplaceholderBaseUrl;
import base_Url.RegresInBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get03b extends RegresInBaseUrl {
 /*
   Given
       https://reqres.in/api/users/2
   When
       User send GET Request to the URL
   Then
       HTTP Status Code should be 200
   And
       Response format should be "application/json"
   And
       "email" is "janet.weaver@reqres.in",
   And
       "first_name" is "Janet"
   And
       "last_name" is "Weaver"
   And
       "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
*/

    @Test
    public void get01() {
       // Given
        //       https://reqres.in/api/users/2
        spec.pathParams("first","users","second","2");
        //   When
        //       User send GET Request to the URL
        Response response=given().spec(spec).when().get("/{first}/{second}");
        //   Then
        //       HTTP Status Code should be 200//   And
        //       Response format should be "application/json"
        response.then()
                .statusCode(200)
                .contentType("application/json")
                .body("data.first_name",equalTo("Janet")
                ,"data.last_name",equalTo("Weaver")
                ,"support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!")
                );

        //   And
        //       "email" is "janet.weaver@reqres.in",

        //   And
        //       "first_name" is "Janet"
        //   And
        //       "last_name" is "Weaver"
        //   And
        //       "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}
