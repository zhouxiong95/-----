package zhouxiong.demo.demo.interfaces.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Package zhouxiong.demo.demo.interfaces.domain
 * @Description:
 * @author: zhouxiong
 * @create: 2021-04-23 21:07
 **/
@ApiModel("停车场进入离开DO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParklotDO {

    @ApiModelProperty("车牌号")
    private String numberPlate;

    @ApiModelProperty("车辆进入时间")
    private LocalDateTime entryTime;

    @ApiModelProperty("车辆离开时间")
    private LocalDateTime departureTime;

    @ApiModelProperty("车辆类型，1位小车，2为货车")
    private Integer carType;

    @ApiModelProperty("车辆停车费用")
    private BigDecimal parkingFee;

}
