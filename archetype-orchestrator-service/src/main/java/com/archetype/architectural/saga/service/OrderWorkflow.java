package com.archetype.architectural.saga.service;

import java.util.List;

import com.archetype.architectural.saga.core.Workflow;
import com.archetype.architectural.saga.core.WorkflowStep;

public class OrderWorkflow implements Workflow {

    private final List<WorkflowStep> steps;

    public OrderWorkflow(List<WorkflowStep> steps) {
        this.steps = steps;
    }

    @Override
    public List<WorkflowStep> getSteps() {
        return this.steps;
    }

}
