import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.yourkoder.com.dataformat.Trade;
import java.io.FileOutputStream;
import java.io.IOException;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.binance.BinanceExchange;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.dto.marketdata.Trades;
import org.yourkoder.com.dataformat.Trade_List;


public class protobufSerialize {
    public static void main(String... args) throws IOException {
        //fetching exchange data
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BinanceExchange.class);
        FileOutputStream outputStream = new FileOutputStream(System.getProperty("user.dir")+"\\data");
        MarketDataService marketDataService = exchange.getMarketDataService();
        Trades tradesData = marketDataService.getTrades(CurrencyPair.BTC_USDT, 1);

        //start time
        Long start=System.currentTimeMillis();

        //build objects
        Trade.Builder trade = Trade.newBuilder();
        Trade_List.Builder trade_list=Trade_List.newBuilder();

        //creating objects
         tradesData.getTrades().stream().forEach(event ->  {
            trade.setId(Integer.parseInt(event.getId()));
            trade.setSymbol(event.getInstrument().toString());
            trade.setTime(event.getTimestamp().toString());
            trade.setTimeStamp(event.getTimestamp().getTime());
            trade.setPrice(event.getPrice().doubleValue());
            trade_list.addTradelist(trade.build());

        });
         //write to output
        trade_list.build().writeTo(outputStream);
        //end time
        Long end=System.currentTimeMillis();

        Long elapsed=end-start;
        outputStream.close();
        System.out.println("Time elapsed in milliseconds "+ elapsed);
    }
}
