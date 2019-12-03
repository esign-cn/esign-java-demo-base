package cn.esign.demo.base.facade.dto;

import lombok.Data;

import java.util.List;

@Data
public class HandSignResult {
    private List<String> signUrls;

    public HandSignResult(List<String> signUrls) {
        this.signUrls = signUrls;
    }
}