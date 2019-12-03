package cn.esign.demo.base.facade.dto;

import lombok.Data;

import java.util.List;

@Data
public class SignTaskDTO extends SignerDTO {
    private String sealId;
    private Integer signType;
    private List<PositionDTO> posList;

    public SignTaskDTO(String signerAccountId, String authorizedAccountId, String sealId, List<PositionDTO> posList) {
        super(signerAccountId, authorizedAccountId);
        this.sealId = sealId;
        this.posList = posList;
    }

}
