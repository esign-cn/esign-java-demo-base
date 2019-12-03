package cn.esign.demo.base.facade;

import cn.esign.demo.base.facade.dto.BaseSignDTO;
import cn.esign.demo.base.facade.dto.HandSignResult;
import cn.esign.demo.base.facade.dto.PositionDTO;
import cn.esign.demo.base.facade.dto.SignTaskDTO;
import cn.esign.demo.base.facade.dto.SignerDTO;
import cn.esign.demo.base.http.OkHttp3Client;
import cn.esign.demo.base.model.BaseResult;
import cn.esign.demo.base.model.ResultSupport;
import cn.esign.demo.base.provider.FlowDocProvider;
import cn.esign.demo.base.provider.FlowProvider;
import cn.esign.demo.base.provider.FlowSignFieldProvider;
import cn.esign.demo.base.provider.FlowSignerProvider;
import cn.esign.demo.base.provider.request.DocumentBatchAddRequest;
import cn.esign.demo.base.provider.request.FlowCreateRequest;
import cn.esign.demo.base.provider.request.SignfieldHandSignBatchAddRequest;
import cn.esign.demo.base.provider.request.SignfieldPosBean;
import cn.esign.demo.base.provider.request.DocumentBatchAddRequest.DocumentBean;
import cn.esign.demo.base.provider.request.FlowCreateRequest.FlowConfigBean;
import cn.esign.demo.base.provider.request.SignfieldHandSignBatchAddRequest.SignfieldHandSignBean;
import cn.esign.demo.base.provider.response.FlowCreateResponse;
import cn.esign.demo.base.provider.response.FlowGetExecuteUrlResponse;
import cn.esign.demo.base.provider.response.SignfieldAddResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BaseFlowFacade {
    public BaseFlowFacade() {
    }

    protected String createFlow(BaseSignDTO baseSignDTO) {
        FlowProvider flowProvider = OkHttp3Client.getApi(FlowProvider.class);
        FlowCreateRequest createRequest = new FlowCreateRequest();
        createRequest.setAutoArchive(true);
        createRequest.setBusinessScene("合同签署");
        FlowConfigBean configInfo = new FlowConfigBean();
        createRequest.setConfigInfo(configInfo);
        if (baseSignDTO.getNotice() != null && baseSignDTO.getNotice()) {
            configInfo.setNoticeType("1,2");
        } else {
            configInfo.setNoticeType("");
        }

        configInfo.setSignPlatform(baseSignDTO.getSignPlatform());
        configInfo.setRedirectUrl(baseSignDTO.getReturnUrl());
        BaseResult<FlowCreateResponse> result = flowProvider.createFlow(createRequest);
        if (ResultSupport.isFail(result)) {
            throw new RuntimeException("创建流程失败:" + result.getMessage());
        } else {
            return (result.getData()).getFlowId();
        }
    }

    protected void addDocFlow(String flowId, List<String> fileIds) {
        FlowDocProvider docProvider = OkHttp3Client.getApi(FlowDocProvider.class);
        DocumentBatchAddRequest request = new DocumentBatchAddRequest();
        List<DocumentBean> docs = new ArrayList();
        request.setDocs(docs);
        Iterator iterator = fileIds.iterator();

        while (iterator.hasNext()) {
            String fileId = (String) iterator.next();
            DocumentBean documentBean = new DocumentBean();
            documentBean.setFileId(fileId);
            docs.add(documentBean);
        }

        BaseResult result = docProvider.addDocuments(flowId, request);
        if (ResultSupport.isFail(result)) {
            throw new RuntimeException("添加流程文档失败:" + result.getMessage());
        }
    }

    protected void startFlow(String flowId) {
        FlowProvider flowProvider = OkHttp3Client.getApi(FlowProvider.class);
        BaseResult result = flowProvider.startFlow(flowId);
        if (ResultSupport.isFail(result)) {
            throw new RuntimeException("启动流程失败:" + result.getMessage());
        }
    }

    protected void addTask(String flowId, List<SignerDTO> signers, List<String> fileIds, BaseSignDTO baseSignDTO) {
        FlowSignFieldProvider fieldProvider = OkHttp3Client.getApi(FlowSignFieldProvider.class);
        SignfieldHandSignBatchAddRequest signBatchAddRequest = new SignfieldHandSignBatchAddRequest();
        List<SignfieldHandSignBean> signfields = new ArrayList();
        signBatchAddRequest.setSignfields(signfields);
        Iterator signerIterator = signers.iterator();

        while (signerIterator.hasNext()) {
            SignerDTO signer = (SignerDTO) signerIterator.next();
            String signerAccountId = signer.getSignerAccountId();
            String authorizedAccountId = signer.getAuthorizedAccountId();
            Integer actorIndentityType = signerAccountId.equals(authorizedAccountId) ? 0 : 1;
            Iterator fileIdIterator = fileIds.iterator();

            while (fileIdIterator.hasNext()) {
                String fileId = (String) fileIdIterator.next();
                SignfieldHandSignBean signBean = new SignfieldHandSignBean();
                signBean.setActorIndentityType(actorIndentityType);
                signBean.setFileId(fileId);
                signBean.setSignType(Integer.valueOf(0));
                signBean.setSealType(baseSignDTO.getSealType());
                signBean.setSignerAccountId(signerAccountId);
                signBean.setAuthorizedAccountId(authorizedAccountId);
                signfields.add(signBean);
            }
        }

        BaseResult<SignfieldAddResponse> result = fieldProvider.handDosign(flowId, signBatchAddRequest);
        if (ResultSupport.isFail(result)) {
            throw new RuntimeException("创建签署任务失败:" + result.getMessage());
        }
    }

    protected void addAutoTask(String flowId, List<SignTaskDTO> signers, List<String> fileIds, BaseSignDTO baseSignDTO) {
        FlowSignFieldProvider fieldProvider = OkHttp3Client.getApi(FlowSignFieldProvider.class);
        SignfieldHandSignBatchAddRequest signBatchAddRequest = new SignfieldHandSignBatchAddRequest();
        List<SignfieldHandSignBean> signfields = new ArrayList();
        signBatchAddRequest.setSignfields(signfields);
        Iterator signerIterator = signers.iterator();

        while (signerIterator.hasNext()) {
            SignTaskDTO signer = (SignTaskDTO) signerIterator.next();
            String signerAccountId = signer.getSignerAccountId();
            String authorizedAccountId = signer.getAuthorizedAccountId();
            Integer actorIndentityType = signerAccountId.equals(authorizedAccountId) ? 0 : 1;
            Iterator fileIdIterator = fileIds.iterator();

            while (fileIdIterator.hasNext()) {
                String fileId = (String) fileIdIterator.next();
                Iterator positionIterator = signer.getPosList().iterator();

                while (positionIterator.hasNext()) {
                    PositionDTO posDTO = (PositionDTO) positionIterator.next();
                    SignfieldPosBean posBean = new SignfieldPosBean();
                    posBean.setAddSignTime(posDTO.getAddSignTime());
                    posBean.setWidth(posDTO.getWidth());
                    posBean.setPosPage(posDTO.getPosPage());
                    posBean.setPosX(posDTO.getPosX());
                    posBean.setPosY(posDTO.getPosY());
                    SignfieldHandSignBean signBean = new SignfieldHandSignBean();
                    signBean.setActorIndentityType(actorIndentityType);
                    signBean.setFileId(fileId);
                    signBean.setSignType(signer.getSignType());
                    signBean.setSealType(baseSignDTO.getSealType());
                    signBean.setSignerAccountId(signerAccountId);
                    signBean.setAuthorizedAccountId(authorizedAccountId);
                    signBean.setSealId(signer.getSealId());
                    signBean.setPosBean(posBean);
                    signfields.add(signBean);
                }
            }
        }

        BaseResult<SignfieldAddResponse> result = fieldProvider.handDosign(flowId, signBatchAddRequest);
        if (ResultSupport.isFail(result)) {
            throw new RuntimeException("创建签署任务失败:" + result.getMessage());
        }
    }

    protected HandSignResult getSignUrl(String flowId, List<SignerDTO> signers) {
        FlowSignerProvider signerProvider = OkHttp3Client.getApi(FlowSignerProvider.class);
        List<String> signUrls = new ArrayList();
        for (SignerDTO signerDTO : signers) {
            BaseResult<FlowGetExecuteUrlResponse> result = signerProvider.getFlowExecuteUrl(flowId, signerDTO.getSignerAccountId(), null, null);
            if (ResultSupport.isFail(result)) {
                throw new RuntimeException("获取签署地址失败:" + result.getMessage());
            }

            signUrls.add((result.getData()).getUrl());
        }

        return new HandSignResult(signUrls);
    }
}
