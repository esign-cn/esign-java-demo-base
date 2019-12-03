package cn.esign.demo.base.facade;

import cn.esign.demo.base.facade.dto.ManyPersonManyDocAutoSignDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManyPersonManyDocAutoSignFacade extends BaseFlowFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManyPersonManyDocAutoSignFacade.class);

    public ManyPersonManyDocAutoSignFacade() {
    }

    public void createSignTask(ManyPersonManyDocAutoSignDTO autoSignDTO) {
        long startTime = System.currentTimeMillis();
        String flowId = this.createFlow(autoSignDTO);
        LOGGER.info("flowId:{}", flowId);
        this.addDocFlow(flowId, autoSignDTO.getFileIds());
        this.addAutoTask(flowId, autoSignDTO.getSignTasks(), autoSignDTO.getFileIds(), autoSignDTO);
        this.startFlow(flowId);
        long endTime = System.currentTimeMillis();
        LOGGER.info("cost time {}ms", endTime - startTime);
    }
}
