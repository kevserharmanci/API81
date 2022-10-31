package get_Request;

import base_Url.RegresInBaseUrl;
import base_Url.RestfullHerokuBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.equalTo;


public class Get05b extends RegresInBaseUrl {
   /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}
      */

    @Test
    public void get01() {
       // Given
        //          https://reqres.in/api/unknown/3
        spec.pathParams("first","unknown","second",3);
        //        When
        //            User send a GET request to the URL
Response response=given().spec(spec).when().get("/{first}/{second}");
        //        Then
        //            HTTP Status Code should be 200
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.id",equalTo(3)
                ,"data.name",equalTo("true red")
                ,"data.year",equalTo(2002)
                ,"data.color",equalTo("#BF1932")
                ,"data.pantone_value",equalTo("19-1664")
                ,"support.url",equalTo("https://reqres.in/#support-heading")
                ,"support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!")
                );


        SoftAssert softAssert=new SoftAssert();
        JsonPath jsonPath=response.jsonPath();
        System.out.println(jsonPath.getInt("data.id"));

        softAssert.assertEquals(jsonPath.getInt("data.id"),3,"ID patladi gardas***");
        softAssert.assertEquals(jsonPath.getString("data.name"),"true red","Name patladi gardas***");
        softAssert.assertEquals(jsonPath.getInt("data.year"),2002,"Year patladi gardas***");
        softAssert.assertEquals(jsonPath.getString("data.color"),"#BF1932","Color patladi gardas***");
        softAssert.assertEquals(jsonPath.getString("data.pantone_value"),"19-1664","Value patladi gardas***");
        softAssert.assertEquals(jsonPath.getString("support.url"),"https://reqres.in/#support-heading","URL patladi gardas *** ");
        softAssert.assertEquals(jsonPath.getString("support.text"),"To keep ReqRes free, contributions towards server costs are appreciated!","TEXT patladi gardas***");



        softAssert.assertAll();

        //        {
        //        "data": {
        //            "id": 3,
        //            "name": "true red",
        //            "year": 2002,
        //            "color": "#BF1932",
        //            "pantone_value": "19-1664"
        //        },
        //        "support": {
        //            "url": "https://reqres.in/#support-heading",
        //            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        //        }
    }
}
