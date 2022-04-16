import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest10 extends TestBase{
    @Test
    public void get01(){
        Response response=given().
                            spec(spec02).
                            when().
                            get();
        response.prettyPrint();

        //status code is  200

        response.
                then().
                assertThat().
                statusCode(200);
        //10 dan büyük tüm id leri yazdiriniz
        JsonPath json=response.jsonPath();
        SoftAssert softAssert=new SoftAssert();
        List<Integer> idList=json.getList("data.findAll{(it.id)>10}.id");
        System.out.println(idList);
        //verify
        softAssert.assertEquals(idList.size(),14,"Eleman sayisi istenen gibi değil");
        //30'dan kücük tüm yaslari consola yazdır
        List<Integer> yasList=json.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println(yasList);
        //30'dan kücük en büyük yaşın 23 oldugunu verify ediniz
        Collections.sort(yasList);
        softAssert.assertTrue(yasList.get(yasList.size()-1).equals(23),"Yas istenen gibi değil");
        //maasi 350000'den cok olan iscilerin isimlerini yazdiriniz
        List<String>  isimList=json.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println(isimList);
        // Charde Marshall in maasinin 350000 den fazla oldugunu dogrulayınız(verify)
        softAssert.assertTrue(isimList.contains("Charde Marshall"));

        softAssert.assertAll();
    }
}
