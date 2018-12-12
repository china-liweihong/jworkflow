package net.jworkflow.kernel.models;

import com.google.inject.Injector;
import net.jworkflow.kernel.interfaces.StepBody;
import net.jworkflow.kernel.interfaces.StepFieldConsumer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WorkflowStep {
    private Class<StepBody> bodyType;
    private int id;
    private String name;
    private List<StepOutcome> outcomes;
    private List<Integer> children;
    private List<StepFieldConsumer> inputs;
    private List<StepFieldConsumer> outputs;
    private ErrorBehavior retryBehavior;
    private Duration retryInterval;
    private Integer compensationStepId;

    public WorkflowStep(Class bodyType) {
        this.outcomes = new ArrayList<>();
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
        this.children = new ArrayList<>();
        this.bodyType = bodyType;
    }
    
    public StepBody constructBody(Injector injector) throws InstantiationException, IllegalAccessException {
        return (StepBody)injector.getInstance(bodyType);
        //return (StepBody)bodyType.newInstance();
    }

    /**
     * @return the bodyType
     */
    public Class getBodyType() {
        return bodyType;
    }

    /**
     * @param bodyType the bodyType to set
     */
    public void setBodyType(Class bodyType) {
        this.bodyType = bodyType;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the outcomes
     */
    public List<StepOutcome> getOutcomes() {
        return outcomes;
    }

    /**
     * @param outcomes the outcomes to set
     */
    public void setOutcomes(List<StepOutcome> outcomes) {
        this.outcomes = outcomes;
    }
    
    public void addOutcome(int nextStep, Object value) {
        StepOutcome outcome = new StepOutcome();
        outcome.setNextStep(nextStep);
        outcome.setValue(value);        
        outcomes.add(outcome);
    }
    
    public void addOutcome(StepOutcome outcome) {
        outcomes.add(outcome);
    }

    /**
     * @return the inputs
     */
    public List<StepFieldConsumer> getInputs() {
        return inputs;
    }

    /**
     * @param inputs the inputs to set
     */
    public void setInputs(List<StepFieldConsumer> inputs) {
        this.inputs = inputs;
    }
    
    public void addInput(StepFieldConsumer value) {
        inputs.add(value);
    }

    /**
     * @return the outputs
     */
    public List<StepFieldConsumer> getOutputs() {
        return outputs;
    }

    /**
     * @param outputs the outputs to set
     */
    public void setOutputs(List<StepFieldConsumer> outputs) {
        this.outputs = outputs;
    }
    
    public void addOutput(StepFieldConsumer value) {
        outputs.add(value);
    }
        
    public ExecutionPipelineResult initForExecution(WorkflowExecutorResult executorResult, WorkflowDefinition defintion, WorkflowInstance workflow, ExecutionPointer executionPointer) {
        return ExecutionPipelineResult.NEXT;
    }

    public ExecutionPipelineResult beforeExecute(WorkflowExecutorResult executorResult, StepExecutionContext context, ExecutionPointer executionPointer, StepBody body) {
        return ExecutionPipelineResult.NEXT;
    }
    
    public void afterExecute(WorkflowExecutorResult executorResult, StepExecutionContext context, ExecutionResult result, ExecutionPointer executionPointer) {            
    }
    
    public void primeForRetry(ExecutionPointer pointer) {
        
    }

    /**
     * @return the retryBehavior
     */
    public ErrorBehavior getRetryBehavior() {
        return retryBehavior;
    }

    /**
     * @param retryBehavior the retryBehavior to set
     */
    public void setRetryBehavior(ErrorBehavior retryBehavior) {
        this.retryBehavior = retryBehavior;
    }

    /**
     * @return the retryInterval
     */
    public Duration getRetryInterval() {
        return retryInterval;
    }

    /**
     * @param retryInterval the retryInterval to set
     */
    public void setRetryInterval(Duration retryInterval) {
        this.retryInterval = retryInterval;
    }

    /**
     * @return the children
     */
    public Collection<Integer> getChildren() {
        return children;
    }
    
    public void addChild(Integer child) {
        children.add(child);
    }

    /**
     * @param children the children to set
     */
    public void setChildren(List<Integer> children) {
        this.children = children;
    }

    public Integer getCompensationStepId() {
        return compensationStepId;
    }

    public void setCompensationStepId(Integer compensationStepId) {
        this.compensationStepId = compensationStepId;
    }
    
    public boolean getResumeChildrenAfterCompensation() {
        return true;
    }
    
    public boolean getRevertChildrenAfterCompensation() {
        return false;
    }    
}


