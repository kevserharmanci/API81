package get_Request;

import base_Url.AutomationExerciseAPIBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.codec.StringEncoder;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetOdev01 extends AutomationExerciseAPIBaseUrl {

    @Test
    public void test01() {
        /*
Given
    https://automationexercise.com/api/productsList
When
    User sends a GET Request to the url
Then
    HTTP Status Code should be 200
And
    Content Type should be "text/html; charset=utf-8"
And
    Status Line should be HTTP/1.1 200 OK
And
     There must be 12 Women, 9 Men, 13 Kids usertype in products
  */

        // User sends a GET Request to the url
        spec.pathParam("first","productsList");
        Response response=given().spec(spec).when().get("/{first}");
        //Then
        //    HTTP Status Code should be 200
        //And
        //    Content Type should be "text/html; charset=utf-8"
        //And
        //    Status Line should be HTTP/1.1 200 OK
        response.then()
                .statusCode(200)
                .contentType(ContentType.HTML)
                .statusLine("HTTP/1.1 200 OK");
        //And
        //     There must be 12 Women, 9 Men, 13 Kids usertype in products

        //response.prettyPrint();
        JsonPath jsonPath=response.jsonPath();
        jsonPath.prettyPrint();

        List<String> men=jsonPath.getList("products.category.usertype.findAll{it.usertype=='Men'}.usertype");
        assertEquals(9,men.size());

        List<String> women=jsonPath.getList("products.category.usertype.findAll{it.usertype=='Women'}.usertype");
        assertEquals(12,women.size());

        List<String> kids=jsonPath.getList("products.category.usertype.findAll{it.usertype=='Kids'}.usertype");
        assertEquals(13,kids.size());

        //Extralar
        //name i blue top olanlarin fiyati ?
        List<String> blueToPrice=jsonPath.getList("products.findAll{it.name=='Blue Top'}.price");
        System.out.println("blueToPrice = " + blueToPrice);

        // id si 40 tan buyuk lerin Name lerini getir
        List<String> idsbuyuk=jsonPath.getList("products.findAll{it.id>40}.name");
        System.out.println("id si 40 tan buyuk name = " + idsbuyuk);
        //id si 40 tan buyuk olanlarin tum bilgilerini getir
        List<String> element=jsonPath.getList("products.findAll{it.id>40}");
        System.out.println("id si 40 tan buyuk element = " + element);



    }
}
