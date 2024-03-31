package org.gskeno.smartengine;

import com.alibaba.smart.framework.engine.context.ExecutionContext;
import com.alibaba.smart.framework.engine.delegation.JavaDelegation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class PayOrderActivity implements JavaDelegation {
    @Override
    public void execute(ExecutionContext executionContext) {
        Map<String, Object> request = executionContext.getRequest();
        log.info("PayOrderActivity Request Map {}", request);
    }
}
