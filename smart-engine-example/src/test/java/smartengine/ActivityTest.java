package smartengine;

import com.alibaba.smart.framework.engine.SmartEngine;
import com.alibaba.smart.framework.engine.model.instance.ProcessInstance;
import com.alibaba.smart.framework.engine.persister.custom.session.PersisterSession;
import com.alibaba.smart.framework.engine.service.command.ProcessCommandService;
import org.gskeno.smartengine.AppConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 流程编排测试, 创建订单--->支付订单
 */

public class ActivityTest {

    @Test
    public void testPurchase(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SmartEngine smartEngine = context.getBean(SmartEngine.class);
        // 1、流程上下文需要的参数
        Map<String, Object> request = new HashMap<>();
        request.put("order", UUID.randomUUID().toString().replace("-",""));
        try {
            PersisterSession.create();
            ProcessCommandService processCommandService = smartEngine.getProcessCommandService();
            // 其中processDefinitionId和processDefinitionVersion对应bpmn中的 process标签的id和versionTag
            // 调用start则流程流转开始
            String processDefinitionId = "basicTest";
            String processDefinitionVersion = "1.0.0";
            ProcessInstance processInstance = processCommandService.start(processDefinitionId, processDefinitionVersion, request);

            // 将流程实例介质更新到库中，方便后面取出
//            BusinessProces businessProcess = new BusinessProcess();
//            // 唯一id
//            businessProcess.setUniqueId(order.getOrderNo());
//
//            businessProcess.setProcessInstance(InstanceSerializerFacade.serialize(processInstance));
//            myProcessInstacneService.updateProcessInstance(businessProcess);
//            return processInstance;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("初始化流程失败");
        } finally {
            PersisterSession.destroySession();
        }
    }
}
