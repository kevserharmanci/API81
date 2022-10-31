package get_Request;

import base_Url.RestfullHerokuBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.*;

public class Get06 extends RestfullHerokuBaseUrl {

    /*
       Given
           https://restful-booker.herokuapp.com/booking/2325
       When
           User send a GET request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response content type is "application/json"
       And
           Response body should be like;
        {
    "firstname": "Bradley",
    "lastname": "Pearson",
    "totalprice": 132,
    "depositpaid": false,     ----> Outer Json
    "bookingdates": {
        "checkin": "2022-10-27",   ----> Inner Json
        "checkout": "2022-11-07"
    },
    "additionalneeds": "None"
}
    */

    @Test
    public void get01() {
        spec.pathParams("first","booking","second",2325);

        Response response=given().spec(spec).when().get("/{first}/{second}");
       // response.prettyPrint();
//1.Yol
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("Bradley"),
                        "lastname",equalTo("Pearson"),
                        "totalprice",equalTo(132),
                        "depositpaid",equalTo(false),
                        "bookingdates.checkin",equalTo("2022-10-27"),
                        "bookingdates.checkout",equalTo( "2022-11-07"),
                        "additionalneeds",equalTo( "None"));

//2.yol Json Path Class inin kullanimi
        JsonPath json=response.jsonPath();
        assertEquals("Bradley",json.getString("firstname"));
        assertEquals("Pearson",json.getString("lastname"));
        assertEquals(132,json.getInt("totalprice"));
        assertFalse(json.getBoolean("depositpaid"));
        assertEquals("2022-10-27",json.getString("bookingdates.checkin"));
        assertEquals("2022-11-07",json.getString("bookingdates.checkout"));
        assertEquals("None",json.getString("additionalneeds"));

        //3.yol SoftAssertion
        //softAssert class 3 adimda kullanilir

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(json.getString("firstname"),"Bradley","First de hata var haci");
        softAssert.assertEquals(json.getString("lastname"),"Pearson","Last da hata var haci");
        softAssert.assertEquals(json.getInt("totalprice"),132,"Total da hata var haci");
        softAssert.assertEquals(json.getBoolean("depositpaid"),false, "deposit hatali haci");
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2022-10-27","checkin da hata var haci");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2022-11-07","checkout da hata var haci");
        softAssert.assertEquals(json.getString("additionalneeds"),"None","additional da hata var haci");
        softAssert.assertAll();

        /* iii) Dogrulama islemleri sonunda softAssert.assertAll() diyerek yaptigimiz tum dogrulama islemlerinin kontrol edilmesini
         sagliyoruz.
         Eger islemin sonunda softAssert.assertAll() kullanmaz isek taleplerimiz hatalı bile olsa testimiz pass olacaktir.

         */
    }
}
