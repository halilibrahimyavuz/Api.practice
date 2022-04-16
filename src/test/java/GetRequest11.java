import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequest11 extends  TestBase{
    @Test
    public void get01(){

        Response response=given().
                spec(spec03).
                when().
                get("/2");

       // response.prettyPrint();

        //De-Serializations
        //(Json formatındaki datayı Java Objectlerine dönüştürür(gson kullanarak))
        HashMap<String,String> map=response.as(HashMap.class);
        System.out.println(map);//{id=2, completed=false, title=quis ut nam facilis et officia qui, userId=1}

        System.out.println(map.keySet());//[id,completed,title,userId]
        System.out.println(map.values());//[2,false,quis ut nam facilis et officia qui,1]

        SoftAssert softAssert=new SoftAssert();
        //completed key'in degeri false oldugunu verify ediniz
        softAssert.assertEquals(map.get("completed"),false,"completed istenilen gibi değil");

        //userId degerini verify ediniz

        softAssert.assertEquals(map.get("userId"),1);

        //Serializations:(Java Objectini JSOn formatına dönüştürme)

        Gson gson=new Gson();
        String jsonFromMap=gson.toJson(map);
        System.out.println(jsonFromMap);//json formata çevrildi






        softAssert.assertAll();


    }

}
