import org.yourkoder.com.dataformat.Trade;
import org.yourkoder.com.dataformat.Trade_List;
import java.io.FileInputStream;
import java.io.IOException;

public class protobufDeserializer {
    public static void main(String... args) throws IOException{
        //start time
        Long start=System.currentTimeMillis();
        FileInputStream fileInputStream=new FileInputStream(System.getProperty("user.dir")+"\\data");

        //parse form file and create object
        Trade_List trade_list=Trade_List.parseFrom(fileInputStream);
        //end time
        Long end=System.currentTimeMillis();


        Long elapsed=end-start;

        fileInputStream.close();
        System.out.println("Time elapsed in milliseconds "+ elapsed);

    }

}
