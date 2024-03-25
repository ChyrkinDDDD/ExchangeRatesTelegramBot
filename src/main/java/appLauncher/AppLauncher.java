package appLauncher;

import alertTime.AlertTime;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import telegrambot.CurrencyExchangeBot;

public class AppLauncher {
    public static void main(String[] args) throws TelegramApiException {
        CurrencyExchangeBot bot = new CurrencyExchangeBot();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);

        Thread alertTimeThread = new Thread(new AlertTime(bot));
        alertTimeThread.start();
    }
}
