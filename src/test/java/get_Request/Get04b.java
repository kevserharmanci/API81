package get_Request;

import base_Url.RestfullHerokuBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get04b extends RestfullHerokuBaseUrl {
  /*
    Given
        https://restful-booker.herokuapp.com/booking?firstname=Brandon&lastname=Wilson
    When
        User sends get request to the URL
    Then
        Status code is 200
And
   Among the data there should be someone whose firstname is "Brandon" and lastname is "Wilson"

 */

    @Test
    public void get01() {
      // Given
        //        https://restful-booker.herokuapp.com/booking?firstname=Almedi
        spec.pathParams("first","booking").queryParams("firstname","Almedin","lastname","Alikadic");
        //    When
        //        User sends get request to the URL
        Response response=given().spec(spec).when().get("/{first}");
        //    Then
        //        Status code is 200
        response.then().statusCode(200);
        //And
        //   Among the data there should be someone whose firstname is "Almedin" and lastname is "Alikadic"

        assertTrue(response.asString().contains("bookingid"));


    }
}
