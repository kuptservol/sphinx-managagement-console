package ru.skuptsov.sphinx.console.coordinator.agent.command;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.access.MBeanProxyFactoryBean;
import org.springframework.stereotype.Component;
import ru.skuptsov.sphinx.console.coordinator.model.Status;
import ru.skuptsov.sphinx.console.coordinator.model.Task;
import ru.skuptsov.sphinx.console.sphinx.service.api.SphinxService;

import java.io.IOException;

@Component
public class AddProcessToStartupCommandService extends CommandService {

	@Autowired
	public AddProcessToStartupCommandService(MBeanProxyFactoryBean coordinatorCallbackServiceClient) {
        super(coordinatorCallbackServiceClient);

        logger = LoggerFactory.getLogger(AddProcessToStartupCommandService.class);
    }

    @Override
    protected void executeCommands(SphinxService sphinxService, String processName, Object... params) throws IOException {
    	sphinxService.executeAddProcessToStartupCommand(processName);
    }

    @Override
    protected void callback(Status status, Task task) {
        getCoordinatorCallbackServiceClient().addProcessToStartupCommandFinished(status, task);
    }
}
