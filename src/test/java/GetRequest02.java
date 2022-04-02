import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import  static  io.restassured.RestAssured.*;

public class GetRequest02 {
    /*Positive Scenario:
    when() bir GET Request aşağıdaki verilen endpoint e yollanır
            https://restful-booker.herokuapp.com/booking
     and()  accept type i "application/json"dir
     then() status code 200 dur
     and() content type "application/json" dir
     */
    @Test
    public void get01(){
    given().
            accept("application/json").
    when().
            get("https://restful-booker.herokuapp.com/booking").

    then().
            assertThat().
            statusCode(200).
            contentType("application/json");

    }

     /*Negative Scenario:
    when() bir GET Request aşağıdaki verilen endpoint e yollanır
            https://restful-booker.herokuapp.com/booking/47
     and()  accept type i "application/json"dir
     then() status code 404 dur
     */
    @Test
    public void get02(){
      Response response=  given().
                accept("application/json").
        when().
                get("https://restful-booker.herokuapp.com/booking/1001");
      response.prettyPrint();
      response.
                then().
                assertThat().
                statusCode(404);



    }
  /*Negative Scenario:
    when() bir GET Request aşağıdaki verilen endpoint e yollanır
            https://restful-booker.herokuapp.com/booking/1001
     then() status code 404 dur
     and() Response body de "Not Found" var
     and() Response body de "Suleyman" yok
     */
    @Test
    public  void  get03(){
     Response response =   given().
                           when().
                                get("https://restful-booker.herokuapp.com/booking/1");

        response.prettyPrint();
        Assert.assertEquals(404,response.getStatusCode());
        Assert.assertTrue(response.asString().contains("Not Found"));
        Assert.assertFalse(response.asString().contains("Suleyman"));

    }
}
