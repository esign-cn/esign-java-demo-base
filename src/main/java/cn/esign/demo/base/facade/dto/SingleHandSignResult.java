package cn.esign.demo.base.facade.dto;

import lombok.Data;

@Data
public class SingleHandSignResult {
    private String signUrl;

    public SingleHandSignResult(String signUrl) {
        this.signUrl = signUrl;
    }
}