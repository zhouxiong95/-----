package zhouxiong.demo.demo.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Package zhouxiong.demo.demo.domain.model
 * @Description:   停车位数据库对象
 * @author: zhouxiong
 * @create: 2021-04-23 20:26
 **/
@Data
@TableName(value ="parking_space" )
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingSpace {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 剩余车位  数据库初始数据为当前停车场的停车位数量
     */
    @TableField(value = "remaining_parking_spaces" )
    private Integer remainingParkingSpaces;

    /**
     * 改变时间
     */
/*    @TableField(value = "change_time" )
    private LocalDateTime changeTime;*/

}
