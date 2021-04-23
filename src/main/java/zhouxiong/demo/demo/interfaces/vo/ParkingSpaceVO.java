package zhouxiong.demo.demo.interfaces.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Package zhouxiong.demo.demo.interfaces.vo
 * @Description:
 * @author: zhouxiong
 * @create: 2021-04-23 21:36
 **/
@ApiModel("停车场相关信息返回VO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingSpaceVO {

    @ApiModelProperty("剩余车位")
    private Integer remainingParkingSpaces;

    @ApiModelProperty("车辆停车费用")
    private BigDecimal parkingFee;

    @ApiModelProperty("车牌号")
    private String numberPlate;
}
