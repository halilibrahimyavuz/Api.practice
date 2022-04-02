import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest06 extends TestBase {
    //TestBase class olusturup her testte kullanilan datalari TestBase e koyup tekrar tekrar
    //aynı şeyleri yazmaktan kurtulacağız

    /*
     positive scenario:
    When asagidaki endpoint e bir get request yolla
             https://restful-booker.herokuapp.com/booking/1
     then  http status code 200
     and response content type "application/json"

    "firstname": "Mary",
    "lastname": "Brown",
    "totalprice": 314,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2020-06-27",
        "checkout": "2021-09-07"
    }
    "additionalneeds": "Breakfast"
 */
@Test
    public void  test01(){

    Response response=given().
                            spec(spec01).
                            when().
                            get("/booking/5");
    response.prettyPrint();

    response.
            then().
            assertThat().
            statusCode(200).
            contentType(ContentType.JSON).
            body("firstname", equalTo("Mary"),
                    "lastname",equalTo("Jones"),
                    "totalprice",equalTo(367),
                    "depositpaid",equalTo(false),
                    "bookingdates.checkin",equalTo("2019-07-27"),
                    "bookingdates.checkout",equalTo("2020-11-22"));



}

}
