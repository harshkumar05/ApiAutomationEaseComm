package org.ease.utils;

import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ease.module.Warehouse;
import org.ease.stepDefinitions.StepUnderExecutionDetails;

public class Hooks {
    private Scenario currentScenario;
    private static final Logger logger = LogManager.getLogger(Warehouse.class);

    @Before
    public void beforeScenario(Scenario scenario) {
        currentScenario = scenario; // Store the scenario reference
        logger.info("Starting Execution For Scenario: " + scenario.getName());
    }

    @AfterStep
    public void afterStep() {
        logger.info("Execution Completed For Step: "+StepUnderExecutionDetails.stepName);
    }

    @After
    public void tearDown(Scenario scenario) {
        String scenarioExecutionStatus="FAILED";
        if (currentScenario != null) {
            logger.info("Scenario Execution Completed: " + currentScenario.getName());
        }
        if(scenario.getStatus().toString().equalsIgnoreCase("Passed")){
            scenarioExecutionStatus="PASSED";
        }
        logger.info("This Scenario is::"+scenarioExecutionStatus);
    }
}
