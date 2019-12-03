package cn.esign.demo.base.facade;

import cn.esign.demo.base.facade.dto.HandSignResult;
import cn.esign.demo.base.facade.dto.SingleHandSignResult;
import cn.esign.demo.base.facade.dto.SinglePersonSingleDocHandSignDTO;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SinglePersonSignleDocHandSignFacade extends BaseFlowFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(SinglePersonSignleDocHandSignFacade.class);

    public SinglePersonSignleDocHandSignFacade() {
    }

    public SingleHandSignResult createSignTask(SinglePersonSingleDocHandSignDTO handSignDTO) {
        long startTime = System.currentTimeMillis();
        String flowId = this.createFlow(handSignDTO);
        LOGGER.info("flowId:{}", flowId);
        this.addDocFlow(flowId, Arrays.asList(handSignDTO.getFileId()));
        this.addTask(flowId, Arrays.asList(handSignDTO.getSigner()), Arrays.asList(handSignDTO.getFileId()), handSignDTO);
        this.startFlow(flowId);
        HandSignResult result = this.getSignUrl(flowId, Arrays.asList(handSignDTO.getSigner()));
        long endTime = System.currentTimeMillis();
        LOGGER.info("cost time {}ms", endTime - startTime);
        return new SingleHandSignResult(result.getSignUrls().get(0));
    }
}