package get_Request;

import base_Url.JsonplaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Get07 extends JsonplaceholderBaseUrl {
     /*
      Given
       https://jsonplaceholder.typicode.com/todos
When
    I send GET Request to the URL == > URL'e Get Request gonderin
Then
    1)Status code is 200 == > Status kodu 200 olmali
    2)Print all ids greater than 190 on the console ==> id si 190 dan buyuk olanlari konsola yazdirin
      Assert that there are 10 ids greater than 190 == > 10 tane id nin 190 dan buyuk oldugunu dogrulayin
    3)Print all userIds whose ids are less than 5 on the console ==> id si 5 den kucuk olan tum userid lerini konsolunu yazdirin
      Assert that the number of userIds whose ids are less than 5 is 4 ==> id si 5 den kucuk olan 4 tane userId oldugunu dogrulayin
    4)Print all titles whose ids are less than 5 ==> ıd si 5 den kucuk olan tum basliklari yazdirin
      Assert that "delectus aut autem" is one of the titles whose id is less than 5 ==> id si 5 den kucuk olan datalarin birinin
      basliginin "delectus aut autem" icerdigini dogrulayin
   */

    @Test
    public void get01() {
        spec.pathParam("first", "todos");

        Response response = given().spec(spec).when().get("/{first}");

        //1)Status code is 200 == > Status kodu 200 olmali
        response.then().assertThat().statusCode(200);
        //assertEquals(200,response.getStatusCode());

        //    2)Print all ids greater than 190 on the console ==> id si 190 dan buyuk olanlari konsola yazdirin
        JsonPath json = response.jsonPath();
        List<Integer> ids = json.getList("findAll{it.id>190}.id");//--> Groovy Language / Java temelli programlama dili
        System.out.println("ids ->" + ids);

        //      Assert that there are 10 ids greater than 190 == > 10 tane id nin 190 dan buyuk oldugunu dogrulayin
        assertEquals("ID 190 dan buyuk olan eslesmedi", 10, ids.size());

        //    3)Print all userIds whose ids are less than 5 on the console ==> id si 5 den kucuk olan tum userid lerini konsolunu yazdirin
        List<Integer> userIds = json.getList("findAll{it.id<5}.userId");
        System.out.println(userIds);
        //      Assert that the number of userIds whose ids are less than 5 is 4 ==> id si 5 den kucuk olan 4 tane userId oldugunu dogrulayin
        assertEquals("ID si 5 tenk kucuk 4 tane degil haci", 4, userIds.size());

        //    4)Print all titles whose ids are less than 5 ==> ıd si 5 den kucuk olan tum basliklari yazdirin
        List<String> titles = json.getList("findAll{it.id<5}.title");
        System.out.println(titles);
        //      5)Assert that "delectus aut autem" is one of the titles whose id is less than 5 ==> id si 5 den kucuk olan datalarin birinin
        //      basliginin "delectus aut autem" icerdigini dogrulayin;

        assertTrue("Title lardan herhangi bir tanesinde delectus aut autem icermemektedir",
                titles.contains("delectus aut autem"));

        assertTrue("Title lardan herhangi bir tanesinde delectus aut autem icermemektedir",
                titles.stream().anyMatch(t -> t.equals("delectus aut autem")));
    }
}
