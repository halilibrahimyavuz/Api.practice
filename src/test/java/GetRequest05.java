import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest05 {
    /*
    positive scenario:
    When asagidaki endpoint e bir get request yolla
             https://restful-booker.herokuapp.com/booking/5
     then
     http status code 200
     and response  format "application/json"
    and  "firstname": "Jim",
    and "totalprice": "404"
    and "checkin": "2018-11-03",

    }
    */

    @Test
    public  void get01(){

        Response response=given().
                          when().
                          get("https://restful-booker.herokuapp.com/booking/5");


        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", Matchers.equalTo("Jim"),
                        "totalprice",Matchers.equalTo(404),
                        "bookingdates.checkin",Matchers.equalTo("2018-11-03"));




    }

}
