import groovy.json.JsonToken;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest08 extends TestBase{


    /* positive scenario:
    When asagidaki endpoint e bir get request yolla
    https://restful-booker.herokuapp.com/booking/5
    then  http status code 200
    and response content type "application/json"

    "firstname": "Sally",
    "lastname": "Wilson",
    "totalprice": 999,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-08-16",
        "checkout": "2020-09-26"
    }

            */



@Test
    public void get01() {
    spec01.pathParam("bookingid",5);
    Response response = given().
            spec(spec01).
            when().
            get("booking/{bookingid}");

    response.prettyPrint();

    JsonPath json = response.jsonPath();

    System.out.println(json.getString("firstname"));
    Assert.assertEquals("Firstname istenilen gibi değil", "Sally", json.getString("firstname"));
    System.out.println(json.getString("lastname"));
    Assert.assertEquals("Lastname istenilen gibi değil", "Jackson", json.getString("lastname"));
    System.out.println(json.getInt("totalprice"));
    Assert.assertEquals("totalprice istenilen gibi değil", 835, json.getInt("totalprice"));
    System.out.println(json.getBoolean("depositpaid"));
    Assert.assertEquals("depositpaid istenilen gibi değil", true, json.getBoolean("depositpaid"));
    System.out.println(json.getString("bookingdates"));//ikisi aynanda gelir [checkin:2017-12-10, checkout:2019-07-02]

    System.out.println(json.getString("bookingdates.checkin"));
    Assert.assertEquals("Bookindates istenilen gibi değil", "2016-06-02", json.getString("bookingdates.checkin"));
    System.out.println(json.getString("bookingdates.checkout"));
    Assert.assertEquals("Bookindates istenilen gibi değil", "2018-05-26", json.getString("bookingdates.checkout"));
}
}
