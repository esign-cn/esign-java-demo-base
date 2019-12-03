package cn.esign.demo.base.facade.dto;

import lombok.Data;

import java.util.List;

@Data
public class ManyPersonManyDocHandSignDTO extends BaseSignDTO {
    private List<String> fileIds;
    private List<SignerDTO> signers;

    public ManyPersonManyDocHandSignDTO() {
    }
}
