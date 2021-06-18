import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class jsonDeserialize {
public static void main(String...args) throws IOException, ParseException {
        //start time
        Long start=System.currentTimeMillis();
        //parse from file
        Object obj=new JSONParser().parse(new FileReader(System.getProperty("user.dir")+"\\data.json"));

        // typecasting obj to JSONObject
        JSONObject jo=(JSONObject)obj;
        jo.toJSONString();
        //end time
        Long end=System.currentTimeMillis();
        Long elapsed=end-start;

        System.out.println("Time elapsed in milliseconds "+ elapsed);
        }
}
