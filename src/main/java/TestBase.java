import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestBase {

    protected RequestSpecification spec01;
    protected RequestSpecification spec02;
    protected RequestSpecification spec03;
    protected Map<String,String> bookingDatesMap=new HashMap<>();
    protected Map<String,Object> requestBodyMap=new HashMap<>();
    protected JSONObject jsonBookingDatesBody=new JSONObject();
    protected JSONObject jsonRequestBody=new JSONObject();


    @Before
    public void setUp01(){

        spec01=new RequestSpecBuilder().
                        setBaseUri("https://restful-booker.herokuapp.com").
                        build();

    }
    @Before
    public void setUp02(){

        spec02=new RequestSpecBuilder().
                setBaseUri("http://dummy.restapiexample.com/api/v1/employees").
                build();

    }

    @Before
    public  void  setUp03(){
        spec03=new RequestSpecBuilder().
                setBaseUri("https://jsonplaceholder.typicode.com/todos").
                build();

    }
    protected Response createRequestBodyByJSONObject() {


        jsonBookingDatesBody.put("checkin","2019-06-22");
        jsonBookingDatesBody.put("checkout","2020-05-07");


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

    return response;
    }

    protected  Response createRequestBodyByMap(){


        bookingDatesMap.put("checkin","2019-06-22");
        bookingDatesMap.put("checkout","2020-05-07");


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



        return response;
    }
}
