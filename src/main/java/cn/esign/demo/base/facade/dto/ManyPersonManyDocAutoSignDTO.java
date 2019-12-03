package cn.esign.demo.base.facade.dto;

import lombok.Data;

import java.util.List;

@Data
public class ManyPersonManyDocAutoSignDTO extends BaseSignDTO {
    private List<String> fileIds;
    private List<SignTaskDTO> signTasks;

    public ManyPersonManyDocAutoSignDTO() {
    }
}
