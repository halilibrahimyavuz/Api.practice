import Utilities.JsonUtil;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class objectMapperTestWithPojo extends TestBase {

    @Test
    public void javaToJson(){

        BookingDates bookingDates=new BookingDates("2020-11-03","2020-11-08");
        String jsonFromPojo=JsonUtil.convertJavaToJson(bookingDates);
        System.out.println(jsonFromPojo);
    }
    @Test

    public void jsonToJava(){

        Response response=given().
                spec(spec01).
                when().get("/booking/3");

        response.prettyPrint();
        //Api den gelen Json datayı pojo class a çevirdik
        Booking jsonToPojoApi=JsonUtil.convertJsonToJava(response.asString(),Booking.class);
        System.out.println(jsonToPojoApi);
        BookingDates bookingDates=new BookingDates("2019-08-22","2020-12-14");
        Booking booking=new Booking("Eric","Jackson",276,true,bookingDates,"Breakfast");


        response.
                then().
                assertThat().
                statusCode(200);

        Assert.assertEquals(booking.getBookingdates().getCheckin(),jsonToPojoApi.getBookingdates().getCheckin());
        Assert.assertEquals(booking.getBookingdates().getCheckout(),jsonToPojoApi.getBookingdates().getCheckout());




    }



}
