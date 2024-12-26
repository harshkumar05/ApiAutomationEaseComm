package org.ease.stepDefinitions;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestStepStarted;


public class StepUnderExecutionDetails implements ConcurrentEventListener {
    public static String stepName;

    public EventHandler <TestStepStarted> stepHandler = new EventHandler<TestStepStarted>() {
        @Override
        public void receive(TestStepStarted testStepStartedEvent) {
            handleStartedTestStep(testStepStartedEvent);
        }
    };

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestStepStarted.class,stepHandler);
    }

    private void handleStartedTestStep(TestStepStarted testStepStartedEvent){
        if(testStepStartedEvent.getTestStep() instanceof PickleStepTestStep){
            PickleStepTestStep testStepToBeExecuted = (PickleStepTestStep) testStepStartedEvent.getTestStep();
            stepName=testStepToBeExecuted.getStep().getText();
        }
    }
}
