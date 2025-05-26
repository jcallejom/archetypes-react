package com.archetype.architectural.saga.core;

import java.util.List;

public interface Workflow {

    List<WorkflowStep> getSteps();

}
