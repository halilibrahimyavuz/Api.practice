import Utilities.JsonUtil;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class objectMapperTestWithMap extends  TestBase {

    @Test
    public void javaToJson(){

        HashMap<Integer,String>map= new HashMap<>();
        map.put(101,"Ali");
        map.put(102,"Can");
        map.put(103,"Remziye");
        System.out.println(map);//{101=Ali, 102=Can, 103=Remziye}

        String  jsonFromMap= JsonUtil.convertJavaToJson(map);
        System.out.println(jsonFromMap);//{"101":"Ali","102":"Can","103":"Remziye"}



    }

    @Test
    public void JsontoJava(){
        Response response=given().
                spec(spec01).
                when().get("/booking/3");

        response.prettyPrint();
        //Api dene gelen Json formatındaki datayı map'e cevirdik
        Map<String,Object> jsonToMapApi=JsonUtil.convertJsonToJava(response.asString(), Map.class);
        System.out.println(jsonToMapApi);


        /*
         1)APi den gelen Json formatındaki datayı Map'e çevirdim
         2)testcase de bana verilen datayı Map e çevireceğim
         3)1. adimda olusturugum Map'deki dataları karsılastırarak verify yapacağım
         */
        Map<String,Object> jsonToMapTestCase=new HashMap<>();
        jsonToMapTestCase.put("firstname","Susan");
        jsonToMapTestCase.put("lastname","Jones");



        response.
                then().
                assertThat().
                statusCode(200);

        assertEquals(jsonToMapTestCase.get("firstname"),jsonToMapApi.get("firstname"));
        assertEquals(jsonToMapTestCase.get("lastname"),jsonToMapApi.get("lastname"));






    }
}
