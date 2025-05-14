package com.archetype.architectural.saga.service;

import java.util.List;

public interface Workflow {

    List<WorkflowStep> getSteps();

}
