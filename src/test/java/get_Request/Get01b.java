package get_Request;

import base_Url.RegresInBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01b extends RegresInBaseUrl {
    /*
   Given
       https://reqres.in/api/users/3
   When
       User sends a GET Request to the url
   Then
       HTTP Status Code should be 200
   And
       Content Type should be JSON
   And
       Status Line should be HTTP/1.1 200 OK
*/

    @Test
    public void get01() {
        //Given
        //       https://reqres.in/api/users/3
        spec.pathParams( "second", "users", "third", 3);


        //   When
        //       User sends a GET Request to the url
        Response response = given().spec(spec).when().get("/{second}/{third}");
        //   Then
        //       HTTP Status Code should be 200   //   And
        //       Content Type should be JSON   //   And
        //       Status Line should be HTTP/1.1 200 OK
        response.then().
                        statusCode(200).
                        contentType(ContentType.JSON).
                        statusLine("HTTP/1.1 200 OK");


    }
}
