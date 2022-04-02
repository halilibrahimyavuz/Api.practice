import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class GetRequest09 extends  TestBase{

    @Test
    public void get01(){

        Response response=given().
                         spec(spec02).
                         when().
                         get();

        response.prettyPrint();

        JsonPath json=response.jsonPath();
        //tum employee isimlerini consola yazıdırınız
        System.out.println(json.getList("data.employee_name"));
        //ikinci iscinin isminin Garret Winters oldugunu dogrulayınız
        Assert.assertEquals("isim istenildiği gibi değil","Garrett Winters",json.getString("data[1].employee_name"));
        //verify dogrulama dediği için soft assert yaparız
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(json.getString("data[1].employee_name"),"Garrett Winters","isim istenildiği gibi değil");
        //ikinci iscinin isminin Herrod Chandler oldugunu dogrulayınız
        softAssert.assertTrue(json.getList("data.employee_name").contains("Herrod Chandler"),"isim istenildiği gibi değil");
        //24 tane isci oldugunu verify yap
        softAssert.assertEquals(json.getList("data.id").size(),24,"24 isci yok");
        //7.iscinin maasinin 137500 oldugunu verify ediniz
        softAssert.assertEquals(json.getInt("data[6].employee_salary"),137500,"maas 137500 degil");

        softAssert.assertAll();

}

}
