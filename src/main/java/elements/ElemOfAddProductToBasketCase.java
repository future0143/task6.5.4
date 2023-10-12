package elements;

public class ElemOfAddProductToBasketCase {

    public static String buttonFiltersCssSelector = ".j-menu-burger-btn";
    public static String filterAppliances = "Бытовая техника";
    public static String homeAppliances= "//span[contains(text(), 'Техника для дома')]";
    public static String vacuumAndSteamCleaners = "Пылесосы и пароочистители";
    public static String homeLinkPath = "//a[@href='/']/span[@itemprop='name']";
    public static String appliancesLinkPath = "//a[@href='/catalog/bytovaya-tehnika']/span[@itemprop='name']";
    public static String homeAppliancesLinkPath = "//a[@href='/catalog/elektronika/tehnika-dlya-doma']/span[@itemprop='name']";
    public static String vacuumCleanersLinkPath = "//span[@itemprop='name'][text()='Пылесосы и пароочистители']";
    public static String buttonAddToBasketSelector = "a.product-card__add-basket";
    public static String countOfProductsInBasket = "span.navbar-pc__notify";
    public static String basketDivSelector = "div.navbar-pc__item.j-item-basket";
    public static String basketLinkSelector = "a.navbar-pc__link.j-wba-header-item";
    public static String nameOfProductInBasket = ".good-info__good-name";
    public static String brandOfProductInBasket = ".good-info__good-brand";
    public static String priceOfProductInBasket = ".list-item__price-new";
    public static String brandOfProductFromList = ".product-card__brand";
    public static String nameOfProductFromList = ".product-card__name";
    public static String priceOfProductFromList = ".price__lower-price";
    public static String listOfGoodsInBasketSelector = "accordion__list";
    public static String listOfProductsPrices = "div.accordion__list-item div.list-item__price-new";
    public static String buttonToOrderName = "ConfirmOrderByRegisteredUser";
}