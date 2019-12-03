package cn.esign.demo.base.facade.dto;

import lombok.Data;

@Data
public abstract class BaseSignDTO {
    private String returnUrl;
    private Boolean notice;
    private String signPlatform;
    private String sealType;

    public BaseSignDTO() {
    }

}
