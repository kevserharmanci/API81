package get_Request;

import base_Url.RegresInBaseUrl;
import base_Url.RestfullHerokuBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.AssertJUnit.*;

public class Get06b extends RegresInBaseUrl {

    /*
       /*
   Given
          https://reqres.in/api/unknown/
   When
        I send GET Request to the URL
   Then

        1)Status code is 200
        2)Print all pantone_values
        3)Print all ids greater than 3 on the console
          Assert that there are 3 ids greater than 3
        4)Print all names whose ids are less than 3 on the console
          Assert that the number of names whose ids are less than 3 is 2
*/


    @Test
    public void get01() {
        //     I send GET Request to the URL
        spec.pathParams("first","unknown");
        Response response=given().spec(spec).when().get("/{first}");
        //****** asagidaki method la collection icinde body ile assertion yapabiliyoruz ama datayi disari alamiyoruz
        response.then().body("data",hasSize(6));
        //   Then
        //
        //        1)Status code is 200
        assertEquals(200,response.statusCode());
        response.then().statusCode(200);

        JsonPath jsonPath=response.jsonPath();
        //        2)Print all pantone_values
        System.out.println("pantone values - - "+jsonPath.getList("data.pantone_value"));
        //        3)Print all ids greater than 3 on the console
        List<Integer> ids=jsonPath.getList("data.findAll{it.id>3}.id");
        System.out.println(ids);
        //          Assert that there are 3 ids greater than 3
assertEquals(3,ids.size());
        //        4)Print all names whose ids are less than 3 on the console
        List<String> names=jsonPath.getList("data.findAll{it.id<3}.name");
        System.out.println(names);
        //          Assert that the number of names whose ids are less than 3 is 2
        assertEquals(2,names.size());
    }
}
