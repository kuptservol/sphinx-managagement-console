package ru.skuptsov.sphinx.console.coordinator.task.command;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.skuptsov.sphinx.console.coordinator.model.*;
import ru.skuptsov.sphinx.console.coordinator.task.IndexNameTask;

/**
 * Created with IntelliJ IDEA.
 * User: Crow
 * Date: 13.08.14
 * Time: 21:56
 * To change this template use File | Settings | File Templates.
 */
@Component("STOP_PROCESS")
@Scope("prototype")
public class StopProcessCommand<T extends IndexNameTask> extends AgentCommand<T> implements AsyncCommand {

    public static final String AGENT_REMOTE_METHOD_NAME = "stopProcess";

    @Override
    public AgentRemoteMethod getAgentRemoteMethod(IndexNameTask task) throws NoSuchMethodException {
        Object[] arguments = {task, task.getProcessName()};
        return new AgentRemoteMethod(AGENT_REMOTE_CLASS.getMethod(AGENT_REMOTE_METHOD_NAME, Task.class, String.class), arguments);
    }

    @Override
    public String getAgentAddress(IndexNameTask task){
        return task.getSearchAgentAddress();
    }
}
