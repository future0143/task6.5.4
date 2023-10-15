package elements;

public class ElemOfWorkWithFiltersCase {

    public static String filterElectronicsSelector = "Электроника";
    public static String filterLaptopsAndPC = "//span[contains(text(), 'Ноутбуки и компьютеры')]";
    public static String filterLaptops = "Ноутбуки";
    public static String buttonAllFiltersSelector = ".dropdown-filter__btn--all";
    public static String minPriceFilterName = "startN";
    public static String maxPriceFilterName = "endN";
    public static String deliveryTimeSelector = "//*[contains(text(), 'до 3 дней')]";
    public static String brandFilterSelector = "//span[contains(text(), 'Apple')]";
    public static String screenDiagonalSelector = "//span[contains(text(), '13.3\"')]";
    public static String buttonToShowResults = ".filters-desktop__btn-main";
    public static String listOfSelectedFilters = ".your-choice__list";
    public static String listProductsAfterFiltersAsElement = "product-card-list";
    public static String buttonToResetFilters = "//button[text()='Сбросить все']";
    public static String tagNameOfFiltersList = "li";
    public static String listProductsAfterFiltersSelector = "product-card";
    public static String countOfProductsElementName = "goods-count";
}