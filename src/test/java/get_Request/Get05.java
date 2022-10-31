package get_Request;

import base_Url.RestfullHerokuBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.*;


public class Get05 extends RestfullHerokuBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        User sends get request to the URL
    Then
        Status code is 200
And
   Among the data there should be someone whose firstname is "Ali" and lastname is "Cengiz"
 */

    @Test
    public void get01() {
        //Set Url
        //https://restful-booker.herokuapp.com/booking?firstname=Ali&lastname=Cengiz
        spec.pathParam("first","booking").queryParams("firstname","Ali","lastname","Cengiz");
//Expexted Data
        // Send The Request and Get The response
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();
        //Do asertion
        assertEquals(200,response.getStatusCode());
        assertTrue(response.asString().contains("bookingid"));
        

    }
}
