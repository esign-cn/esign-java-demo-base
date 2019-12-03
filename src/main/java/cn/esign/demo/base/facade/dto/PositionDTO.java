
package cn.esign.demo.base.facade.dto;

import lombok.Data;

@Data
public class PositionDTO {
    private String posPage;
    private Float posX;
    private Float posY;
    private Float width;
    private Boolean addSignTime;

    public PositionDTO() {
    }

}
