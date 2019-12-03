package cn.esign.demo.base.facade;

import cn.esign.demo.base.facade.dto.HandSignResult;
import cn.esign.demo.base.facade.dto.ManyPersonSingleDocHandSignDTO;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManyPersonSingleDocHandSignFacade extends BaseFlowFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManyPersonSingleDocHandSignFacade.class);

    public ManyPersonSingleDocHandSignFacade() {
    }

    public HandSignResult createSignTask(ManyPersonSingleDocHandSignDTO handSignDTO) {
        long startTime = System.currentTimeMillis();
        String flowId = this.createFlow(handSignDTO);
        LOGGER.info("flowId:{}", flowId);
        this.addDocFlow(flowId, Arrays.asList(handSignDTO.getFileId()));
        this.addTask(flowId, handSignDTO.getSigners(), Arrays.asList(handSignDTO.getFileId()), handSignDTO);
        this.startFlow(flowId);
        HandSignResult result = this.getSignUrl(flowId, handSignDTO.getSigners());
        long endTime = System.currentTimeMillis();
        LOGGER.info("cost time {}ms", endTime - startTime);
        return result;
    }
}