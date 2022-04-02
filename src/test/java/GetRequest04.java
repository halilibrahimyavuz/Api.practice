import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest04 {

    /*
    positive Sceneraio
    When Get request iste
        http://dummy.restapiexample.com/api/v1/employees
     and accept type "application/json"
     then
     http status code 200
     and response  format "application/json"
     And 24 employess olsun
     And"Ashton Cox" employee biri olsun
     And 21,61, ve 23 employee yaslarından biri olsun

     */


    @Test
    public  void  get01(){
     Response response= given().
                                accept(ContentType.JSON).
                        when().
                                get(" http://dummy.restapiexample.com/api/v1/employees");

     response.prettyPrint();

     response.
             then().
             assertThat().
             statusCode(200).
             contentType(ContentType.JSON).
             body("data.id", Matchers.hasSize(24)).
             body("data.employee_name",Matchers.hasItem("Ashton Cox")).
             body("data.employee_age",Matchers.hasItems(21,61,23));

    }

}
