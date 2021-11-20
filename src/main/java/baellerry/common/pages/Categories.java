package baellerry.common.pages;

public enum Categories {

    MEN_WALLETS("Кошельки и портмоне мужские"),
    WOMEN_PURSES("Кошельки и портмоне женские"),
    MEN_BAGS("Сумки мужские"),
    WOMEN_BAGS("Сумки женские"),
    MEN_BRACELETS("Браслеты мужские"),
    BELT("Ремни"),
    ORGANIZERS_CARDHOLDERS("Органайзеры, кардхолдеры, визитницы"),
    ECOBAGS("Экосумки");

    private final String category;

    Categories(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
