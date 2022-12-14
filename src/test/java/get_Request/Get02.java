package get_Request;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;


public class Get02 {
    /* Given
        https://restful-booker.herokuapp.com/booking/1
    When
        User send a GET Request to the url
    Then
        HTTP Status code should be 404
    And
        Status Line should be HTTP/1.1 404 Not Found
    And
        Response body contains "Not Found"
    And
        Response body does not contain "TechProEd"
    And
        Server is "Cowboy"
 */

    @Test
    public void get01() {
        // i)  Set the URL,
            String url="https://restful-booker.herokuapp.com/booking/1";
            Response response=given().when().get(url);
            response.prettyPrint();
        // ii) Set the Expected Data (Post,Put,Patch)
        // iii) Type code to send request(talep gondermek icin kod yazimi)
        // iv) Do Assertion (dogrulama yap)
        response.then().assertThat().
                 statusCode(404).
                 statusLine("HTTP/1.1 404 Not Found");
        assertTrue(response.asString().contains("Not Found"));
        //Body not foun iceriyor mu testi yapiliyor
        assertFalse(response.asString().contains("TechProEd"));
        //Body nin techproed icermedigi test ediliyor
        assertEquals("Cowboy",response.header("Server"));
        //Server in covboy olup olmadigini test ediyor



    }
}
