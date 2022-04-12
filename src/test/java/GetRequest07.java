import io.restassured.response.Response;
import org.junit.Assert.*;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class GetRequest07 extends  TestBase{

    //URL spec01 testbase ni kullan
    //datalar arasında firstname' i "Sara" olanı bul


    @Test
    public void get01(){

        Response response=given().spec(spec01).when().get("/booking?firstname=Sara");


        response.prettyPrint();
        assertTrue(response.getBody().asString().contains("bookingid"));

    }

    @Test
    public void get02(){

        spec01.queryParams("firstname","Susan",
                "depositpaid",true);
        Response response=given().spec(spec01).get("/booking");
        response.prettyPrint();
        assertTrue(response.getBody().asString().contains("bookingid"));


    }


}
