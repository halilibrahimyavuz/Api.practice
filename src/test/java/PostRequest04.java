import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.testng.asserts.SoftAssert;

import java.util.Optional;

import static io.restassured.RestAssured.*;

public class PostRequest04 extends TestBase{
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
   //----------------POJO------------------------

@Test
    public void post01(){

    BookingDates bookingDates=new BookingDates("2019-06-22","2020-05-07");
    Booking booking=new Booking("Nafiz","Han",1234,
             true,bookingDates,"Wifi");

    Response response=given().
                        contentType(ContentType.JSON).
                        spec(spec01).auth().basic("admin","password123").
                        body(booking).
                        when().post("/booking");

    response.prettyPrint();

    response.then().assertThat().statusCode(200);

    JsonPath json=response.jsonPath();
    SoftAssert softAssert=new SoftAssert();

    softAssert.assertEquals(json.getString("booking.firstname"),booking.getFirstname());
    softAssert.assertEquals(json.getString("booking.lastname"),booking.getLastname());
    softAssert.assertEquals(json.getInt("booking.totalprice"),1234);
    softAssert.assertEquals(json.getString("booking.bookingdates.checkin"),"2019-06-22");


    softAssert.assertAll();


}


}
