import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 {

   //get ile endpointe gidip status code kontrolü yapılmak isteniyor
    @Test
    public void getMethod01(){

        given().
        when().
        get("https://restful-booker.herokuapp.com/booking").
        then().
        assertThat().statusCode(200);



    }
    //get ile alınan datayi console da gormek istiyorum
    @Test
    public void getMethod02(){

      Response response=  given().
                          when().
                          get("https://restful-booker.herokuapp.com/booking/5");

        //response body i console yazdırmak için kullanılır
       response.prettyPrint();
        //statuscode u console da yazadırmak için
        System.out.println("Status Code : "+response.getStatusCode());
        //statusline ı console da gormek için
        System.out.println("Status line : "+response.getStatusLine());
        System.out.println("Status line : "+response.statusLine());
        //response body'deki datanin content type için
        System.out.println("Content Type : "+response.contentType());
        //Header'daki tüm bilgileri almak
        System.out.println(response.getHeaders());
        //Header'daki spesifik bilgileri almak
        System.out.println(response.getHeader("Date"));
        //Assertions yapalim
        //1
        response.then().assertThat().
                statusLine("HTTP/1.1 200 OK").
                contentType("application/json; charset=utf-8").
                statusCode(200);

    }

}
