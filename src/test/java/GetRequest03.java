import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest03 {


    /*
    positive scenario:
    When asagidaki endpoint e bir get request yolla
             https://restful-booker.herokuapp.com/booking/1
     and accept type "application/json"
     then
     http status code 200
     and response  format "application/json"
    and  "firstname": "Mary",
    and "lastname": "Brown"
    and "checkin": "2021-10-11",
    and   "checkout": "2021-11-07"
    }
    */
    @Test
    public  void get01(){

        Response response=
                            given().
                                    accept("application/json").
                            when().
                                    get("https://restful-booker.herokuapp.com/booking/1");

        response.prettyPrint();
        //StatusCode için 1.yol
       response.
                then().
                assertThat().statusCode(200).
               contentType("application/json").
               body("firstname", Matchers.equalTo("Susan")).
               body("lastname", Matchers.equalTo("Jones")).
               body("bookingdates.checkin",Matchers.equalTo("2020-05-17")).
               body("bookingdates.checkout",Matchers.equalTo("2021-09-16")).
               body("additionalneeds",Matchers.equalTo("Breakfast"));

      //tekrarsiz body kullanma
        response.
                then().
                assertThat().statusCode(200).
                contentType("application/json").
                body("firstname", Matchers.equalTo("Susan"),
                        "lastname", Matchers.equalTo("Jones"),
                        "bookingdates.checkin",Matchers.equalTo("2020-05-17"),
                        "bookingdates.checkout",Matchers.equalTo("2021-09-16"),
                        "additionalneeds",Matchers.equalTo("Breakfast"));


      //StatusCode için 2.yol
        assertEquals(200, response.getStatusCode());


    }

}
