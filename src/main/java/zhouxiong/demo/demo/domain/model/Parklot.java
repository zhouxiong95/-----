package zhouxiong.demo.demo.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Package zhouxiong.demo.demo.domain.model
 * @Description:  停车场数据库对象   没有在数据库建表，只能模拟
 * @author: zhouxiong
 * @create: 2021-04-23 20:14
 **/
@Data
@TableName(value ="parklot" )
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Parklot {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 车牌号
     */
    @TableField(value = "number_plate" )
    private String numberPlate;

    /**
     * 车辆进入时间
     */
    @TableField(value = "entry_time" )
    private LocalDateTime entryTime;

    /**
     * 车辆离开时间
     */
    @TableField(value = "departure_time" )
    private LocalDateTime departureTime;

    /**
     * 车辆类型，1位小车，2为货车
     */
    @TableField(value = "car_type" )
    private Integer carType;

    /**
     * 车辆停车费用
     */
    @TableField(value = "parking_fee" )
    private BigDecimal parkingFee;

    /**
     * 生效状态,此次行程，生效，1往此行程，不生效
     */
    @TableField(value = "effective_state" )
    private Integer effectiveState;

}
