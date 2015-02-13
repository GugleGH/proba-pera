package ru.nosov.client.messages.types;

/**
 * Типы сообщений внутреннего протокола.
 * @author Носов А.В.
 */
public enum TypeMessage {
    
    /** ---------- msg ---------- */
    /** Запрос информации о пользователе. */
    RqUser,
    
    /** Общие ошибки. */
    Error,
    
    /** Вход в систему (авторизация). */
    Login,
    
    /** Информация о пользователе. */
    LoginInfo,
    
    /** Новая сессия. */
    NewSession,
    
    /** Регистрация нового пользователя. */
    Registration,
    
    /** Проверка логина. */
    isLoginName
}
