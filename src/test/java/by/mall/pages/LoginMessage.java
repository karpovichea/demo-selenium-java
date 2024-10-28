package by.mall.pages;

public class LoginMessage {
    public static final String EMPTY_PHONE_NUMBER_FIELD = "Значение поля \"Номер телефона\" должно начинаться с +375 затем код (25|29|33|44) и далее 7 цифр (первая из которых не 0)";
    public static final String EMPTY_PASSWORD_FIELD = "Поле \"Пароль\" обязательно для заполнения.";
    public static final String EMPTY_PHONE_NUMBER_AND_PASSWORD_FIELDS = "Значение поля \"Номер телефона\" должно начинаться с +375 затем код (25|29|33|44) и далее 7 цифр (первая из которых не 0)\n" +
            "Поле \"Пароль\" обязательно для заполнения.";
    public static final String WRONG_CREDENTIALS = "Неверный логин или пароль";
}
