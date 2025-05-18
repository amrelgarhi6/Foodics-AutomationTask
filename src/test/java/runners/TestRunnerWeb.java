package runners;

import io.cucumber.testng.CucumberOptions;
import web.utilities.hooks.Hooks;
import io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm;


@CucumberOptions
        (
                publish = true,
                features = {"src/test/java/features"},
                glue = {"steps"},
                tags = ("@Regression_FE"),
                plugin = {
                        "pretty",
                        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
                },
                monochrome = true
        )

public class TestRunnerWeb extends Hooks {
}