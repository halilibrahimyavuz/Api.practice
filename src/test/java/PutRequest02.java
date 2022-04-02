import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.net.Socket;

import static io.restassured.RestAssured.given;

public class PutRequest02 extends TestBase{
    /*1)spec03 kullanarak herhangi bir datayı update ediniz
      2)update edildiğini status code ve response vody ile verify ediniz.

     */

    @Test
    public void put01(){

        Response response=given().
                spec(spec03).
                when().get("/190");

        response.prettyPrint();

        JSONObject jsonObject=new JSONObject();

        jsonObject.put("title","Ziyan");
         jsonObject.put("userId",5757);
        jsonObject.put("completed",false);
        jsonObject.put("id",666);

        Response responseAfterPut=given().
                contentType(ContentType.JSON).
                spec(spec03).
                body(jsonObject.toString()).
                when().put("/190");

        responseAfterPut.prettyPrint();

        responseAfterPut.then().
                assertThat().
                statusCode(200);

        JsonPath json=responseAfterPut.jsonPath();
        SoftAssert softAssert=new SoftAssert();

        softAssert.assertEquals(json.getBoolean("completed"),jsonObject.get("completed"));
        softAssert.assertEquals(json.getString("title"),jsonObject.get("title"));
        softAssert.assertEquals(json.getInt("userId"),jsonObject.get("userId"));


        softAssert.assertAll();

    }
}
