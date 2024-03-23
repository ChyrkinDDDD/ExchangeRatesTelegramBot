package bankUtil;

import bankModel.MonoBank;

import java.util.ArrayList;
import java.util.List;

public class MonoBankUtil implements BankUtil{
    private List<MonoBank> exchangeRates;
    private int numberAfterComa;
    public MonoBankUtil(int numberAfterComa){
        this.numberAfterComa = numberAfterComa;
        exchangeRates = new ArrayList<>();
    }

    public void setExchangeRates(List<MonoBank> exchangeRates){
        this.exchangeRates = exchangeRates;
    }
    @Override
    public void setReduction(int numberAfterComa) {
        this.numberAfterComa = numberAfterComa;
    }

    @Override
    public String getUSD() {
        MonoBank monoBank = new MonoBank();
        for (MonoBank element: exchangeRates) {
            if(element.getCurrencyCodeA() == 840
                    && element.getCurrencyCodeB() == 980){
                monoBank = element;
            }
        }

        return String.format("course in Monobank USD/UAH\npurchase: %."+numberAfterComa+"f" +
                        "\nselling: %."+numberAfterComa+"f",
                monoBank.getRateBuy(),monoBank.getRateSell());
    }

    @Override
    public String getEUR() {
        MonoBank monoBank = new MonoBank();
        for (MonoBank element: exchangeRates) {
            if(element.getCurrencyCodeA() == 978
                    && element.getCurrencyCodeB() == 980){
                monoBank = element;
            }
        }

        return String.format("course in Monobank EUR/UAH\npurchase: %."+numberAfterComa+"f" +
                        "\nselling: %."+numberAfterComa+"f",
                monoBank.getRateBuy(),monoBank.getRateSell());
    }
}