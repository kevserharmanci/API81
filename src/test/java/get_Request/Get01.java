package get_Request;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01 {
    /*
    1) Postman , manual API testleri icin kullandik
    2) Otomasyon Testleri icin Rest Assured Library kullanacagiz
    3) Otomasyon Testlerimizi yaparken asagidaki adimlari yapiyoruz
        a) Requirements i anlamak
        b) Test Case yaziyoruz
            i) Test Case yaziminda "Gherkin" dilini kullanacagiz.Bizler yazilim diline hakim olsak da
            karsimizdaki kisiler hakim olmayabilir ama gherkin ile yazilan testleri anlamakta zorluk cekmeyeceklerdir
            Gherkin dilinde kullanacah=gimiz Keywords ler

            - Given : On Kosullar
            - When  : Yapilacak aksiyonlar icin killanacaz (get(), put(), post(), patch(), ve delete() methodlari)
            - Then  : Istek yaptiktan(Request gonderdikten) sonra
            - And   : Coklu islemlerde kullanacagiz

        c) Test Kodlarimizi yazmaya baslayacagiz
            i)  Set the URL,
            ii) Set the Expected Data (Post,Put,Patch)
            iii) Type code to send request(talep gondermek icin kod yazimi)
            iv) Do Assertion (dogrulama yap)


     */
    /*
Given
        https://restful-booker.herokuapp.com/booking/101
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
        //i)  Set the URL
        String url="https://restful-booker.herokuapp.com/booking/101";
        //ii) Set the Expected Data (Post,Put,Patch)
        //Bizden post , put yada patch istenmedigi icin bu case de kullanmayacagiz

        //iii) Type code to send request(talep gondermek icin kod yazimi)
        Response response=given().when().get(url);
        response.prettyPrint();

        //iv) Do Assertion (dogrulama yap)
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

        //Status Code Konsola yazdiralim
        System.out.println("Status Code : "+response.getStatusCode());

        //Content Type Konsola yazdiralim
        System.out.println("Content Type : "+ response.getContentType());

        //Status Line Konsol Ciktisi
        System.out.println("Status Line : "+ response.statusLine());

        //Header Konsula Yazdiralim
        System.out.println("Header : "+response.headers());
        System.out.println();
        System.out.println("Header : "+response.header("Server"));

        //Time konsola yazdir
        System.out.println("Time : "+response.getTime());

    }
}
