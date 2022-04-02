import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends TestBase {
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
  /*  JSONObject jsonBookingDatesBody=new JSONObject();
    jsonBookingDatesBody.put("checkin","2019-06-22");
    jsonBookingDatesBody.put("checkout","2020-05-07");

    JSONObject jsonRequestBody=new JSONObject();
    jsonRequestBody.put("firstname","Nafiz");
    jsonRequestBody.put("lastname","Han");
    jsonRequestBody.put("totalprice",111);
    jsonRequestBody.put("depositpaid",true);
    jsonRequestBody.put("bookingdates",jsonBookingDatesBody);
    jsonRequestBody.put("additionalneeds","Wifi");

    Response response=given().
            contentType(ContentType.JSON).
            spec(spec01).
            auth().basic("admin","password123").
            body(jsonRequestBody.toString()).when().post("/booking");
*/

    Response response= createRequestBodyByJSONObject();

    response.prettyPrint();
    //status code 200 olsun
    response.then().
            assertThat().
            statusCode(200);
    //JsonPath kullanarak assertion yapalım
    JsonPath json=response.jsonPath();
    SoftAssert softAssert=new SoftAssert();

    softAssert.assertEquals(json.getString("booking.firstname"),
            jsonRequestBody.getString("firstname"));
    softAssert.assertEquals(json.getString("booking.lastname"),
            jsonRequestBody.getString("lastname"));
    softAssert.assertEquals(json.getInt("booking.totalprice"),
            jsonRequestBody.getInt("totalprice"));
    softAssert.assertEquals(json.getString("booking.bookingdates.checkin"),
            jsonBookingDatesBody.getString("checkin"));


    softAssert.assertAll();










}
}
