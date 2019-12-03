package cn.esign.demo.base.facade.dto;

import lombok.Data;

@Data
public class SignerDTO {
    private String signerAccountId;
    private String authorizedAccountId;

    public SignerDTO(String signerAccountId, String authorizedAccountId) {
        this.signerAccountId = signerAccountId;
        this.authorizedAccountId = authorizedAccountId;
    }

}
