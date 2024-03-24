package bankUtil;

import bankModel.NBU;
import bankModel.PrivatBank;

import java.util.List;

public class NBUUtil implements BankUtil{
    private List<NBU> exchangeRates;
    private int numberAfterComa;
    public NBUUtil(int numberAfterComa){
        this.numberAfterComa = numberAfterComa;
    }

    public void setExchangeRates(List<NBU> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    @Override
    public void setReduction(int numberAfterComa) {
        this.numberAfterComa = numberAfterComa;
    }

    @Override
    public String getUSD() {
        if (exchangeRates == null || exchangeRates.isEmpty()) {
            return "Exchange rates not available. Please fetch data first.";
        }

        NBU nbu = findCurrencyExchange("USD");
        if (nbu == null) {
            return "USD exchange rate not available.";
        }

        return getFormattedExchangeRate(nbu);

    }

    @Override
    public String getEUR() {
        if (exchangeRates == null || exchangeRates.isEmpty()) {
            return "Exchange rates not available. Please fetch data first.";
        }

        NBU nbu = findCurrencyExchange("EUR");
        if (nbu == null) {
            return "EUR exchange rate not available.";
        }

        return getFormattedExchangeRate(nbu);

    }
    public String getFormattedExchangeRate(NBU bank) {
        if (bank == null) {
            return "Currency exchange information not available.";
        } else {
            return String.format("Course in NBU %s/UAH\npurchase: %." + numberAfterComa + "f\nselling: %." + numberAfterComa + "f",
                    bank.getCc(), bank.getRate(), bank.getRate());
        }
    }

    private NBU findCurrencyExchange(String currency) {
        for (NBU bank : exchangeRates) {
            if (bank.getCc().equals(currency)) {
                return bank;
            }
        }
        return null;
    }
}
