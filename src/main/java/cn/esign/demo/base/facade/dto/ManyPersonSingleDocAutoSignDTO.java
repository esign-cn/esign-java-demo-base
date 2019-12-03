package cn.esign.demo.base.facade.dto;

import lombok.Data;

import java.util.List;

@Data
public class ManyPersonSingleDocAutoSignDTO extends BaseSignDTO {
    private String fileId;
    private List<SignTaskDTO> signTasks;

    public ManyPersonSingleDocAutoSignDTO() {
    }
}
