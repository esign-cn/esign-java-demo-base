package cn.esign.demo.base.facade.dto;


import lombok.Data;

@Data
public class SinglePersonSingleDocHandSignDTO extends BaseSignDTO {
    private String fileId;
    private SignerDTO signer;

    public SinglePersonSingleDocHandSignDTO() {
    }

}