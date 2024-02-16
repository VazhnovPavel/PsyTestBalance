package com.balance.bot.service;

import com.balance.config.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    BotConfig config;

    private Long ownerId;
    static final String ERROR_OCCURED = "Error occurred: ";

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(getBotToken());
        this.ownerId = config.getOwnerId();
        if (update.hasMessage()){
            long chatID = update.getMessage().getChatId();
            sendMessage(chatID,"Hello World");
        }
    }

    public void sendMessage(long chatID, String texToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatID);
        message.setText(texToSend);
        executedMessage(message);
    }

    public void executedMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(ERROR_OCCURED + e.getMessage());
        }
    }
}
