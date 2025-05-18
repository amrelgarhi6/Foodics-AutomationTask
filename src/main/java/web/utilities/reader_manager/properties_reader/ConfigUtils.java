package web.utilities.reader_manager.properties_reader;

public class ConfigUtils {
    private static final String CONFIG_PROPERTIES_FILE_PATH = "/src/main/resources/WebConfig.properties";
    private static String portal_URl;
    private static String browser_TYPE;
    private static String isHeadless;
    private static String operatingSystem;

    private static void setConfigProperties() {
        portal_URl = PropertiesDataManager.getProperty("portal_URL", CONFIG_PROPERTIES_FILE_PATH);
    }
    private static void setBrowserProperties() {
        browser_TYPE = PropertiesDataManager.getProperty("browser", CONFIG_PROPERTIES_FILE_PATH);
    }
    private static void setBrowserHeadlessProperties() {
        isHeadless = PropertiesDataManager.getProperty("headless", CONFIG_PROPERTIES_FILE_PATH);
    }
    public static void setOperatingSystem() {
        operatingSystem = PropertiesDataManager.getProperty("operating.sys", CONFIG_PROPERTIES_FILE_PATH);
    }
    public static boolean isAllureReportGenerationEnabled() {
        return Boolean.parseBoolean(PropertiesDataManager.getProperty("generate.allure.report",CONFIG_PROPERTIES_FILE_PATH));
    }


    public static String get_PortalURL() {
        setConfigProperties();
        return portal_URl;
    }

    public static String get_OperatingSystem() {
        setOperatingSystem();
        return operatingSystem;
    }

    public static String get_BrowserType() {
        setBrowserProperties();
        return browser_TYPE;
    }

    public static String get_BrowserHeadlessType() {
        setBrowserHeadlessProperties();
        return isHeadless;
    }
}