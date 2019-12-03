
package cn.esign.demo.base.facade;

import cn.esign.demo.base.facade.dto.HandSignResult;
import cn.esign.demo.base.facade.dto.ManyPersonManyDocHandSignDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManyPersonManyDocHandSignFacade extends BaseFlowFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManyPersonManyDocHandSignFacade.class);

    public ManyPersonManyDocHandSignFacade() {
    }

    public HandSignResult createSignTask(ManyPersonManyDocHandSignDTO handSignDTO) {
        long startTime = System.currentTimeMillis();
        String flowId = this.createFlow(handSignDTO);
        LOGGER.info("flowId:{}", flowId);
        this.addDocFlow(flowId, handSignDTO.getFileIds());
        this.addTask(flowId, handSignDTO.getSigners(), handSignDTO.getFileIds(), handSignDTO);
        this.startFlow(flowId);
        HandSignResult result = this.getSignUrl(flowId, handSignDTO.getSigners());
        long endTime = System.currentTimeMillis();
        LOGGER.info("cost time {}ms", endTime - startTime);
        return result;
    }
}
