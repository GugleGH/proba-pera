package ru.nosov.client.messages.types;

/**
 * Типы меню.
 * @author Носов А.В.
 */
public enum TypeMenu {
    
    // Variables declaration
    // Левое меню 
    /** Моя страница. */
    Aboute(11, "Моя страница"),
    /** Сообщения. */
    Messages(12, "Сообщения"),
    /** Группы. */
    Goups(13, "Группы"),
    /** Новости. */
    News(14, "Новости"),
    /** Настройки. */
    Settings(15, "Настройки"),
    
    // Верхнее меню
    /** Люди. */
    Person(101, "Люди"),
    /** Сообщества. */
    Community(102, "Сообщества"),
    /** Выход. */
    Exit(103, "Выход");
    
    /** Описание ошибки. */
    private final String description;
    /** Код ошибки. */
    private final int code;
    // End of variables declaration

    /**
     * Инициализация ошибки.
     * @param description описание ошибки
     */
    TypeMenu(int code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Возвращает описание ошибки.
     * @return описание ошибки
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Возвращает код ошибки.
     * @return код ошибки
     */
    public int getCode() {
        return code;
    }
}
