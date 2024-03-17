package bankUtil;

public interface BankUtil {
    //також кожен імплементуючий клас має містити в собі зміну де буде зберігатися кількість змінних після коми
    // і List<Клас якогось банку> ця змінна буде ініціалізуватися з конструктора цього класу
    void setReduction(int numberAfterComa); //метод встановклює кількість знаків після коми
    void getUSD();
//    getUSD() – повертає відформатований рядок курсу USD певного банку. типу
//    course in Monobank USD/UAH
//    purchase: 38.30
//    selling: 38.80
    void getEUR();
//    getEUR() – повертає відформатований рядок курсу USD певного банку. типу
//    course in Monobank EUR/UAH
//    purchase: 42.30
//    selling: 42.80
}
