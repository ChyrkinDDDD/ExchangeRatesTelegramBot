package alertTime;

import settings.UserSettings;
import telegrambot.CurrencyExchangeBot;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class AlertTime  implements Runnable{
    CurrencyExchangeBot bot;
    public AlertTime(CurrencyExchangeBot bot){
        this.bot = bot;
    }
    @Override
    public void run() {
        while (true) {
            LocalTime currentTime = LocalTime.now();
            int minute = currentTime.getMinute();

            if (minute > 0) {
                try {
                    Thread.sleep((long) (60 - minute) * 60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("CANT SEEP!");
                }
            }else {
                sendMassage(CurrencyExchangeBot.getBot().getUsersSettingsHashMap(),currentTime);
                try {
                    Thread.sleep(3600000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("CANT SEEP FOR HOUR");
                }
            }
        }
    }

    public void sendMassage(Map<Long,UserSettings> usersSettingsHashMap,LocalTime currentTime){
        for (Long chatId : usersSettingsHashMap.keySet()){
            UserSettings userSettings = usersSettingsHashMap.get(chatId);
            if(userSettings.getTime() > 0 && userSettings.getTime() == currentTime.getHour()) {
                bot.sendInfo(chatId);
            }
        }
    }
}
