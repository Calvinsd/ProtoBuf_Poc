import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import java.io.IOException;
import java.io.PrintWriter;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.binance.BinanceExchange;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.dto.marketdata.Trades;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.LinkedHashMap;
import java.util.Map;

public class jsonSerialize {
    public static void main(String... args) throws IOException {
        //fetch exchange data
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BinanceExchange.class);
        MarketDataService marketDataService = exchange.getMarketDataService();
        Trades trades = marketDataService.getTrades(CurrencyPair.BTC_USDT, 1);
        PrintWriter pw=new PrintWriter(System.getProperty("user.dir")+"\\data.json");

        //start time
        Long start=System.currentTimeMillis();
        JSONObject jo=new JSONObject();
        JSONArray ja = new JSONArray();


        trades.getTrades().stream().forEach(trade ->  {
            Map m=new LinkedHashMap(5);
            m.put("Id",trade.getId().toString());
            m.put("Symbol",trade.getInstrument().toString());
            m.put("Time",trade.getTimestamp().toString());
            m.put("timestamp",trade.getTimestamp().getTime());
            m.put("price",String.valueOf(trade.getPrice()));
            ja.add(m);
        });

        jo.put("Trades_list",ja);
        pw.write(jo.toJSONString());
        pw.flush();
        //end time
        Long end=System.currentTimeMillis();
        pw.close();

        Long elapsed=end-start;
        System.out.println("Time elapsed in milliseconds "+ elapsed);
    }
}