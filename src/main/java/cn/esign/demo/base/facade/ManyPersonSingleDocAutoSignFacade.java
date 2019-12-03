package cn.esign.demo.base.facade;

import cn.esign.demo.base.facade.dto.ManyPersonSingleDocAutoSignDTO;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManyPersonSingleDocAutoSignFacade extends BaseFlowFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManyPersonSingleDocAutoSignFacade.class);

    public ManyPersonSingleDocAutoSignFacade() {
    }

    public void createSignTask(ManyPersonSingleDocAutoSignDTO autoSignDTO) {
        long startTime = System.currentTimeMillis();
        String flowId = this.createFlow(autoSignDTO);
        LOGGER.info("flowId:{}", flowId);
        this.addDocFlow(flowId, Arrays.asList(autoSignDTO.getFileId()));
        this.addAutoTask(flowId, autoSignDTO.getSignTasks(), Arrays.asList(autoSignDTO.getFileId()), autoSignDTO);
        this.startFlow(flowId);
        long endTime = System.currentTimeMillis();
        LOGGER.info("cost time {}ms", endTime - startTime);
    }
}