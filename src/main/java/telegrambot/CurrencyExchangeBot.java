package telegrambot;

import bankClient.MonoBankExchangeRateClient;

import bankClient.NBUExchangeRateClient;
import bankClient.PrivatBankExchangeRateClient;
import bankUtil.BankUtil;
import bankUtil.MonoBankUtil;
import bankUtil.NBUUtil;
import bankUtil.PrivatBankUtil;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import settings.UserSettings;
import java.util.List;
import java.util.*;
import static telegrambot.BotConstants.BOT_NAME;
import static telegrambot.BotConstants.BOT_TOKEN;


public class CurrencyExchangeBot extends TelegramLongPollingBot {
    String selectedClient = "";

    HashMap<Long, UserSettings> usersSettingsHashMap = new HashMap<>();

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = getChatId(update);

        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            switch (callbackData) {
                case "Settings":
                    sendSettingsMenu(chatId);
                    break;
                case "DecimalPlaces":
                    sendDecimalPlacesMenu(chatId, selectedClient);
                    break;
                case "Banks":
                    sendBanksMenu(chatId, selectedClient);
                    break;
                case "Currencies":
                    sendCurrenciesMenu(chatId, selectedClient);
                    break;
                case "AlertTime":
                    sendAlertTimeMenu(chatId, selectedClient);
                    break;
                case "GetInfo":
                    sendInfo(chatId);
                    break;
                case "Start":
                    sendStartMenu(chatId);
                    break;
                case "2":
                    selectedClient = "2";
                    usersSettingsHashMap.get(chatId).getBankUtil().setReduction(2);
                    usersSettingsHashMap.get(chatId).setNumberAfterComa(2);
                    sendSettingsMenu(chatId);
                    break;
                case "3":
                    selectedClient = "3";
                    usersSettingsHashMap.get(chatId).getBankUtil().setReduction(3);
                    usersSettingsHashMap.get(chatId).setNumberAfterComa(3);
                    sendSettingsMenu(chatId);
                    break;
                case "4":
                    selectedClient = "4";
                    usersSettingsHashMap.get(chatId).getBankUtil().setReduction(4);
                    usersSettingsHashMap.get(chatId).setNumberAfterComa(4);
                    sendSettingsMenu(chatId);
                    break;
                case "PrivatBank":
                    selectedClient = "PrivatBank";
                    handlePrivatBank(chatId);
                    sendSettingsMenu(chatId);
                    break;
                case "Monobank":
                    selectedClient = "Monobank";
                    handleMonoBank(chatId);
                    sendSettingsMenu(chatId);
                    break;
                case "NBU":
                    selectedClient = "NBU";
                    handleNBU(chatId);
                    sendSettingsMenu(chatId);
                    break;
                case "9":
                    selectedClient = "9";
                    sendSettingsMenu(chatId);
                    break;
                case "10":
                    selectedClient = "10";
                    sendSettingsMenu(chatId);
                    break;
                case "11":
                    selectedClient = "11";
                    sendSettingsMenu(chatId);
                    break;
                case "12":
                    selectedClient = "12";
                    sendSettingsMenu(chatId);
                    break;
                case "13":
                    selectedClient = "13";
                    sendSettingsMenu(chatId);
                    break;
                case "14":
                    selectedClient = "14";
                    sendSettingsMenu(chatId);
                    break;
                case "15":
                    selectedClient = "15";
                    sendSettingsMenu(chatId);
                    break;
                case "16":
                    selectedClient = "16";
                    sendSettingsMenu(chatId);
                    break;
                case "17":
                    selectedClient = "17";
                    sendSettingsMenu(chatId);
                    break;
                case "18":
                    selectedClient = "18";
                    sendSettingsMenu(chatId);
                    break;
                case "OFF":
                    selectedClient = "OFF";
                    sendSettingsMenu(chatId);
                case "USD":
                    selectedClient = "USD";
                    usersSettingsHashMap.get(chatId).setCurrency("USD");
                    sendSettingsMenu(chatId);
                    break;
                case "EUR":
                    selectedClient = "EUR";
                    usersSettingsHashMap.get(chatId).setCurrency("EUR");
                    sendSettingsMenu(chatId);
                    break;
                default:
            }
        } else {
            Map<String, String> buttons = new LinkedHashMap<>();
            buttons.put("Get Info", "GetInfo");
            buttons.put("Settings", "Settings");

            SendMessage message = new SendMessage();
            message.setText("Hello, glad to see you. This bot will help you track currency exchange rates.");
            attachButtons(message, buttons, 1);
            message.setChatId(chatId);

            usersSettingsHashMap.put(chatId,new UserSettings(getDefaultSettings(),2,"USD"));

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendInfo(Long chatId) {
        String outInfo;
        UserSettings userSettings = usersSettingsHashMap.get(chatId);
        if (userSettings.getCurrency().equals("USD"))
            outInfo = userSettings.getBankUtil().getUSD();
        else
            outInfo = userSettings.getBankUtil().getEUR();

        SendMessage info = new SendMessage();
        info.setChatId(chatId);
        info.setText(outInfo);

        Map<String, String> buttons = new LinkedHashMap<>();
        buttons.put("Get Info", "GetInfo");
        buttons.put("⚙\uFE0F Settings ⚙\uFE0F", "Settings");

        attachButtons(info, buttons, 1);

        try {
            execute(info);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private BankUtil getDefaultSettings() {
        int numberAfterComa = 2;
        PrivatBankUtil privatBankUtil = new PrivatBankUtil(numberAfterComa);
        privatBankUtil.setExchangeRates(new PrivatBankExchangeRateClient().getPrivatBankExchangeRates());
        return privatBankUtil;
    }

    private void sendStartMenu(Long chatId) {
        Map<String, String> buttons = new LinkedHashMap<>();
        buttons.put("Get Info", "GetInfo");
        buttons.put("⚙\uFE0F Settings ⚙\uFE0F", "Settings");

        SendMessage startMessage = new SendMessage();
        startMessage.setText("Your changes have been saved.");
        attachButtons(startMessage, buttons, 1);
        startMessage.setChatId(chatId);
        try {
            execute(startMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendSettingsMenu(Long chatId) {
        Map<String, String> buttons = new LinkedHashMap<>();
        buttons.put("\uD83D\uDD0D Number of decimal places \uD83D\uDD0E", "DecimalPlaces");
        buttons.put("\uD83D\uDCB0 Banks \uD83D\uDCB0", "Banks");
        buttons.put("\uD83C\uDFE6 Currencies \uD83C\uDFE6", "Currencies");
        buttons.put("\uD83D\uDD70 Alert time \uD83D\uDD70", "AlertTime");
        buttons.put("\uD83D\uDD19 Back \uD83D\uDD19", "Start");

        SendMessage settingsMessage = new SendMessage();
        settingsMessage.setText("Settings Menu");
        attachButtons(settingsMessage, buttons, 1);
        settingsMessage.setChatId(chatId);
        try {
            execute(settingsMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void sendDecimalPlacesMenu(Long chatId, String selectedClient) {
        Map<String, String> buttons = new LinkedHashMap<>();
        buttons.put("2" + (selectedClient.equals("2") ? " ✅" : ""), "2");
        buttons.put("3" + (selectedClient.equals("3") ? " ✅" : ""), "3");
        buttons.put("4" + (selectedClient.equals("4") ? " ✅" : ""), "4");
        buttons.put("\uD83D\uDD19 Back \uD83D\uDD19", "Settings");

        SendMessage decimalPlacesMessage = new SendMessage();
        decimalPlacesMessage.setText("DecimalPlaces");
        attachButtons(decimalPlacesMessage, buttons, 1);
        decimalPlacesMessage.setChatId(chatId);
        try {
            execute(decimalPlacesMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendBanksMenu(Long chatId, String selectedClient) {
        Map<String, String> buttons = new LinkedHashMap<>();
        buttons.put("Monobank" + (selectedClient.equals("Monobank") ? " ✅" : ""), "Monobank");
        buttons.put("PrivatBank" + (selectedClient.equals("PrivatBank") ? " ✅" : ""), "PrivatBank");
        buttons.put("NBU" + (selectedClient.equals("NBU") ? " ✅" : ""), "NBU");
        buttons.put("\uD83D\uDD19 Back \uD83D\uDD19", "Settings");

        SendMessage banksMessage = new SendMessage();
        banksMessage.setText("Choose bank:");
        attachButtons(banksMessage, buttons, 1);
        banksMessage.setChatId(chatId);
        try {
            execute(banksMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendCurrenciesMenu(Long chatId, String selectedClient) {
        Map<String, String> buttons = new LinkedHashMap<>();
        buttons.put("\uD83D\uDCB6 EUR \uD83D\uDCB6" + (selectedClient.equals("EUR") ? " ✅" : ""), "EUR");
        buttons.put("\uD83D\uDCB5 USD \uD83D\uDCB5" + (selectedClient.equals("USD") ? "✅" : ""), "USD");
        buttons.put("\uD83D\uDD19 Back \uD83D\uDD19", "Settings");

        SendMessage currenciesMessage = new SendMessage();
        currenciesMessage.setText("Choose currency:");
        attachButtons(currenciesMessage, buttons, 1);
        currenciesMessage.setChatId(chatId);
        try {
            execute(currenciesMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendAlertTimeMenu(Long chatId, String selectedClient) {

        Map<String, String> buttons = new LinkedHashMap<>();
        buttons.put("9" + (selectedClient.equals("9") ? " ✅" : ""), "9");
        buttons.put("10" + (selectedClient.equals("10") ? " ✅" : ""), "10");
        buttons.put("11" + (selectedClient.equals("11") ? " ✅" : ""), "11");
        buttons.put("12" + (selectedClient.equals("12") ? " ✅" : ""), "12");
        buttons.put("13" + (selectedClient.equals("13") ? " ✅" : ""), "13");
        buttons.put("14" + (selectedClient.equals("14") ? " ✅" : ""), "14");
        buttons.put("15" + (selectedClient.equals("15") ? " ✅" : ""), "15");
        buttons.put("16" + (selectedClient.equals("16") ? " ✅" : ""), "16");
        buttons.put("17" + (selectedClient.equals("17") ? " ✅" : ""), "17");
        buttons.put("18" + (selectedClient.equals("18") ? " ✅" : ""), "18");
        buttons.put("\uD83D\uDD15 OFF \uD83D\uDD15" + (selectedClient.equals("OFF") ? " ✅" : ""), "OFF");

        SendMessage alertTimeMessage = new SendMessage();
        alertTimeMessage.setText("AlertTime:");
        attachButtons(alertTimeMessage, buttons, 3);

        alertTimeMessage.setChatId(chatId);
        try {
            execute(alertTimeMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public Long getChatId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChatId();
        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage().getChatId();
        }
        return null;
    }

    public void attachButtons(SendMessage message, Map<String, String> buttons, int buttonsPerRow) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        int count = 0;

        for (String buttonName : buttons.keySet()) {
            String buttonValue = buttons.get(buttonName);
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(buttonName);
            button.setCallbackData(buttonValue);
            row.add(button);
            count++;

            if (count % buttonsPerRow == 0 || count == buttons.size()) {
                keyboard.add(row);
                row = new ArrayList<>();
            }
        }

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }

    private void handlePrivatBank(Long chatId) {
        UserSettings userSettings = usersSettingsHashMap.get(chatId);
        PrivatBankUtil privatBankUtil = new PrivatBankUtil(userSettings.getNumberAfterComa());
        privatBankUtil.setExchangeRates(new PrivatBankExchangeRateClient().getPrivatBankExchangeRates());
        usersSettingsHashMap.put(chatId,new UserSettings(privatBankUtil,userSettings.getNumberAfterComa(),userSettings.getCurrency()));
    }

    private void handleMonoBank(Long chatId) {
        UserSettings userSettings = usersSettingsHashMap.get(chatId);
        MonoBankUtil monoBankUtil = new MonoBankUtil(userSettings.getNumberAfterComa());
        monoBankUtil.setExchangeRates(new MonoBankExchangeRateClient().getMonoBankExchangeRates());
        usersSettingsHashMap.put(chatId,new UserSettings(monoBankUtil,userSettings.getNumberAfterComa(),userSettings.getCurrency()));
    }

    private void handleNBU(Long chatId) {
        UserSettings userSettings = usersSettingsHashMap.get(chatId);
        NBUUtil nbuUtil = new NBUUtil(userSettings.getNumberAfterComa());
        nbuUtil.setExchangeRates(new NBUExchangeRateClient().getNBUExchangeRates());
        usersSettingsHashMap.put(chatId,new UserSettings(nbuUtil,userSettings.getNumberAfterComa(),userSettings.getCurrency()));
    }
}
