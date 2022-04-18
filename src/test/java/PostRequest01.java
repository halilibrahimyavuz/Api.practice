import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends TestBase {
/*
accept type json olsun
when POST request yolladığımda
1)https://restful-booker.herokuapp.com/booking
2)Request Body
{
    "firstname": "Eric",
    "lastname": "Brown",
    "totalprice": 857,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2019-06-22",
        "checkout": "2020-05-07"
    }
    "additionalneeds":"Wifi"
}
Status Code 200 olmalı
Response Body,Request Body ile aynı olamalı(verify)
 */
    @Test
        public void post01() {
        //1.yol kötü yol





        String jsonRequestBody = "{\n" +
                "\"firstname\": \"Nafiz\",\n" +
                "\"lastname\": \"hans\",\n" +
                "\"totalprice\": 111,\n" +
                "\"depositpaid\": false,\n" +
                "\"bookingdates\": {\n" +
                "\"checkin\": \"2020-05-02\",\n" +
                "\"checkout\": \"2020-05-05\"\n" +
                "}\n" +
                "\"additionalneeds\":\"Wifi\"\n" +
                "}";
        Response response=given().
                            contentType(ContentType.JSON).
                            spec(spec01).
                            auth().basic("admin","password123").
                            body(jsonRequestBody).
                            when().post("/booking");

        response.prettyPrint();


        //status code 200 olsun
        response.then().
                assertThat().
                statusCode(200);
        //JsonPath kullanarak assertion yapalım
        JsonPath json=response.jsonPath();
        SoftAssert softAssert=new SoftAssert();

        softAssert.assertEquals(json.getString("booking.firstname"),"Nafiz");
        softAssert.assertEquals(json.getString("booking.lastname"),"hans");
        softAssert.assertEquals(json.getInt("booking.totalprice"),111);


        softAssert.assertAll();


    }









}
