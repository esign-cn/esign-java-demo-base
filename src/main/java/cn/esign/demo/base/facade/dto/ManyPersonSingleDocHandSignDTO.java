package cn.esign.demo.base.facade.dto;

import lombok.Data;

import java.util.List;

@Data
public class ManyPersonSingleDocHandSignDTO extends BaseSignDTO {
    private String fileId;
    private List<SignerDTO> signers;

    public ManyPersonSingleDocHandSignDTO() {
    }
}
