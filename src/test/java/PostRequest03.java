import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class PostRequest03 extends TestBase {
       /*
accept type json olsun
when POST request yolladığımda
1)https://restful-booker.herokuapp.com/booking
2)Request Body
{
    "firstname": "Nafiz",
    "lastname": "Han",
    "totalprice": 1234,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2019-06-22",
        "checkout": "2020-05-07"
    }
    "additionalneeds":"Wifi"
}
Then
Status Code 200 olmalı
Response Body,Request Body ile aynı olamalı(verify)
 */
    @Test
    public void post01(){

     /*   Map<String,String> bookingDatesMap=new HashMap<>();
        bookingDatesMap.put("checkin","2019-06-22");
        bookingDatesMap.put("checkout","2020-05-07");

        Map<String,Object> requestBodyMap=new HashMap<>();
        requestBodyMap.put("firstname","Nafiz");
        requestBodyMap.put("lastname","Han");
        requestBodyMap.put("totalprice",1234);
        requestBodyMap.put("depositpaid",true);
        requestBodyMap.put("bookingdates",bookingDatesMap);
        requestBodyMap.put("additionalneeds","Wifi");

        Response response=given().
                            contentType(ContentType.JSON).
                            spec(spec01).
                            auth().basic("admin","password123").
                            body(requestBodyMap).when().post("/booking");
*/

       Response response=createRequestBodyByMap();

        response.prettyPrint();



        response.then().assertThat().statusCode(200);

        JsonPath json=response.jsonPath();
        SoftAssert softAssert=new SoftAssert();

        softAssert.assertEquals(json.getString("booking.firstname"),requestBodyMap.get("firstname"));
        softAssert.assertEquals(json.getString("booking.lastname"),requestBodyMap.get("lastname"));
        softAssert.assertEquals(json.getInt("booking.totalprice"),requestBodyMap.get("totalprice"));
        softAssert.assertEquals(json.getString("booking.bookingdates.checkin"),bookingDatesMap.get("checkin"));
        softAssert.assertEquals(json.getString("booking.bookingdates.checkout"),bookingDatesMap.get("checkout"));


        softAssert.assertAll();



}


}
