import java.util.ArrayList;
import java.util.List;

public class CurrencyList {
    List<Currency> currencyList = new ArrayList<>();
    Currency USD = new Currency("Dolar amerykański", "USD", 3.75);
    Currency EUR = new Currency("Euro", "EUR", 4.21);
    Currency GBP = new Currency("Funt szterling", "GBP", 4.9227);
    Currency CHF = new Currency("Frank szwajcarski", "CHF", 4.5372);
    Currency AED = new Currency("Dirham ZEA", "AED", 1.0270);
    Currency AUD = new Currency("Dolar australijski", "AUD", 2.5510);
    Currency CAD = new Currency("Dolar kanadyjski", "CAD", 2.8640);
    Currency HUF = new Currency("Forint węgierski", "HUF", 1.0873);
    Currency JPY = new Currency("Jen japoński", "JPY", 2.6290);
    Currency CZK = new Currency("Korona czeska", "CZK", 16.9635);
    Currency DKK = new Currency("Korona duńska", "DKK", 0.5647);
    Currency NOK = new Currency("Korona norweska", "NOK", 0.3695);
    Currency SEK = new Currency("Korona szwedzka", "SEK", 0.3711);
    Currency RON = new Currency("Lej rumuński", "RON", 0.8453);
    Currency BGN = new Currency("Lew bułgarski", "BGN", 2.1523);
    Currency TRY = new Currency("Lira turecka", "TRY", 0.1247);
    Currency ZAR = new Currency("Rand południowoafrykański", "ZAR", 0.2050);
    Currency CNY = new Currency("Yuan renminbi", "CNY", 0.5392);

    public CurrencyList() {

        currencyList.add(USD);
        currencyList.add(EUR);
        currencyList.add(GBP);
        currencyList.add(CHF);
        currencyList.add(AED);
        currencyList.add(AUD);
        currencyList.add(CAD);
        currencyList.add(HUF);
        currencyList.add(JPY);
        currencyList.add(CZK);
        currencyList.add(DKK);
        currencyList.add(NOK);
        currencyList.add(SEK);
        currencyList.add(RON);
        currencyList.add(BGN);
        currencyList.add(TRY);
        currencyList.add(ZAR);
        currencyList.add(CNY);
    }



    public String displayShortName(){
        StringBuilder shortNames = new StringBuilder();
        for (Currency currency : currencyList) {
            shortNames.append(currency.getShortName()).append(",");
        }
        return shortNames.toString();
    }



}
