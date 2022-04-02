import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest12 extends TestBase{

@Test
    public void get01(){
    Response response=given().
            spec(spec03).when().get();

    //response.prettyPrint();

    //De-Serializaton:(Json formatındaki datayı Java Objectlerine dönüştürür(gson kullanarak))

    List<Map<String,Object>> listOfMapByGson=response.as(ArrayList.class);
   // System.out.println("Gson ile Java Object'e donusuturuldu"+ listOfMapByGson);
    System.out.println(listOfMapByGson.get(0));

    SoftAssert softAssert=new SoftAssert();
    //200 tane id oldugunu verify ediniz
    softAssert.assertTrue(listOfMapByGson.size()==200,"Id istenilen gibi değil");
    //121. elemanın completed degerinin null oldugunu verify ediniz
    softAssert.assertEquals(listOfMapByGson.get(120).get("complete"),null,"istenilen gibi değil");
    //sondan bir önceki elemanın titlle'inin "numquam repellendus a magnam" oldugunu verify ediniz
    softAssert.assertEquals(listOfMapByGson.get(listOfMapByGson.size()-2).get("title"),"numquam repellendus a magnam");


    //Serialization:(Java Objectini JSOn formatına dönüştürme)

    Gson gson=new Gson();

    String jsonFromList=gson.toJson(listOfMapByGson);
    System.out.println(jsonFromList);








    softAssert.assertAll();
}








}
