package utiltites.reports;

import utiltites.cli.CLI;

public class AllureReport {
    public void deleteOutDatedAllureReport() {
        CLI.executeCommandLine("cd allure-results");
        CLI.executeCommandLine("DEL /S /Q *.json");
    }

    public void generateAllureReport() {
        CLI.executeCommandLine("allure serve allure-results");
    }
}