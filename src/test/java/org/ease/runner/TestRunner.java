package org.ease.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@CucumberOptions(
        features = "src/test/resources/features/",
        glue = {"org.ease.stepDefinitions","org.ease.utils"},
        plugin ={"org.ease.stepDefinitions.StepUnderExecutionDetails",
                "html:target/cucumber-reports.html",
                "json:target/cucumber-reports/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true,
        tags = "@defaultTag"
)
public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeClass
    public static void setUp() throws IOException {
        String tagName = System.getProperty("cucumber.filter.tags", "default-tag").replaceAll("@", "");
        File extentPropertiesFile = new File("src/test/resources/extent.properties");
        try (FileWriter writer = new FileWriter(extentPropertiesFile)) {
            writer.write("extent.reporter.spark.start=true\n");
            writer.write("extent.reporter.html.out=target/ExtentReport-" + tagName + ".html\n");
            writer.write("extent.reporter.spark.config=src/test/resources/extent-config.xml\n");
            writer.write("extent.logger.start=true\n");
        }
    }
}
