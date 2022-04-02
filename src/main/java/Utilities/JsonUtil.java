package Utilities;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    private static ObjectMapper mapper;

    static{//statik bloklar variable lere değer atamak için kullanılır
        //static blok herşeyden önce çalışır.Yani class oluştuğu anda mapper oluşur.
        mapper=new ObjectMapper();
    }

    //Java objectini JSON formatina çeviren metot(Serialization)

    public  static String convertJavaToJson(Object object){
       String jsonResult="";
        try {
            jsonResult =mapper.writeValueAsString(object);
        } catch (IOException e) {
            System.out.println("Java Obje'sini Json ' a cevirken exception olustu"+e.getMessage());
        }
        return jsonResult;

    }

    //Java formatındaki datayı Java Object'ine çeviren metot(De-serialization)

    public static <T> T convertJsonToJava(String json,Class<T> cls){
        T javaResult=null;

        try {
           javaResult= mapper.readValue(json,cls);
        } catch (IOException e) {
            System.out.println("Json'i Javaya ceviriken exception olustu"+e.getMessage());
        }

            return javaResult;
    }
}
