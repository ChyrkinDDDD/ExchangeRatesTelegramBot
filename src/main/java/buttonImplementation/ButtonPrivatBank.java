package buttonImplementation;

import bankClient.PrivatBankExchangeRateClient;
import bankModel.PrivatBank;
import bankUtil.PrivatBankUtil;

import java.util.List;

public class ButtonPrivatBank {
    private final PrivatBankUtil privatBankUtil;

    public ButtonPrivatBank() {
        this.privatBankUtil = new PrivatBankUtil(2);
    }

    public void setDecimalPlaces(int numberAfterComa) {
        privatBankUtil.setReduction(numberAfterComa);
    }

    public String handleUSDButton() {
        List<PrivatBank> exchangeRates = fetchExchangeRates();
        return handleExchangeRateRequest(exchangeRates, "USD");
    }

    public String handleEURButton() {
        List<PrivatBank> exchangeRates = fetchExchangeRates();
        return handleExchangeRateRequest(exchangeRates, "EUR");
    }

    private String handleExchangeRateRequest(List<PrivatBank> exchangeRates, String currency) {
        if (exchangeRates.isEmpty()) {
            return "Exchange rates are not available. Please fetch data first.";
        } else {
            return privatBankUtil.getFormattedExchangeRate(findCurrencyExchange(exchangeRates, currency));
        }
    }

    public void setExchangeRates(List<PrivatBank> exchangeRates) {
        privatBankUtil.setExchangeRates(exchangeRates);
    }

    private List<PrivatBank> fetchExchangeRates() {
        PrivatBankExchangeRateClient client = new PrivatBankExchangeRateClient();
        return client.getPrivatBankExchangeRates();
    }

    private PrivatBank findCurrencyExchange(List<PrivatBank> exchangeRates, String currency) {
        for (PrivatBank bank : exchangeRates) {
            if (bank.getCcy().equals(currency)) {
                return bank;
            }
        }
        return null;
    }
}