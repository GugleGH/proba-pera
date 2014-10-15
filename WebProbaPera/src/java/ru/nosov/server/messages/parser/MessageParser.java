package ru.nosov.server.messages.parser;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import org.apache.log4j.Logger;
import ru.nosov.client.messages.IMessage;
import ru.nosov.server.exceptions.ExceptionsMessage;
import ru.nosov.server.exceptions.errors.ErrorsMessage;
import ru.nosov.server.messages.MessageError;

/**
 * Объект, возвращающий сообщение по его типу.
 */
public class MessageParser {

    // Variables declaration
    private static final Logger log = Logger.getLogger(MessageParser.class);
    // End of variables declaration

    /**
     * Преобразование из строки в IMessage с помощью JSON;
     * @param msg строка
     * @throws ExceptionsMessage исключение
     * @see IMessage
     * @return сообщение
     * @throws JsonParseException если строка не является JSON сообщением
     */
    public static IMessage parseJSON(String msg) throws ExceptionsMessage {
        try {
            Gson gson = new Gson();
            MessageParserType messageType = gson.fromJson(msg, MessageParserType.class);
            
            if (messageType == null || messageType.getTypeMessage() == null) {
                ExceptionsMessage ex = new ExceptionsMessage(ErrorsMessage.WARNING_UNPROCESSED_TYPE);
                log.error(ex.getMessage());
                throw ex;
            }

            switch (messageType.getTypeMessage()) {
                /** ---------- semnik msg ---------- */
//                case RqTelemetry:
//                case RxTelemetry:
//                    return gson.fromJson(msg, MessageTelemetry.class);
                    
                /** ---------- fields msg ---------- */
                    
                /** ---------- system msg ---------- */
                case Error:
                    return gson.fromJson(msg, MessageError.class);
                default:
                    ExceptionsMessage ex = new ExceptionsMessage(ErrorsMessage.WARNING_UNKNOWN_TYPE);
                    log.error(ex.getMessage());
                    throw ex;
            }

        } catch (JsonParseException jpe) {
            ExceptionsMessage ex = new ExceptionsMessage(ErrorsMessage.ERROR_JSON);
            log.error(msg + "\n" + jpe.getMessage());
            throw ex;
        }
    }
}
