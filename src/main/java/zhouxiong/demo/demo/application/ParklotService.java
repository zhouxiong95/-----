package zhouxiong.demo.demo.application;

import zhouxiong.demo.demo.domain.model.Parklot;
import zhouxiong.demo.demo.interfaces.domain.ParklotDO;
import zhouxiong.demo.demo.interfaces.vo.ParkingSpaceVO;

import java.math.BigDecimal;

/**
 * @Package zhouxiong.demo.demo.application
 * @Description:
 * @author: zhouxiong
 * @create: 2021-04-23 20:44
 **/
public interface ParklotService extends BaseService<Parklot>{

    /*
    * 停车场车辆进入调用接口
    * */
    ParkingSpaceVO vehicleEntering(ParklotDO parklotDO);

    /*
     * 停车场车辆离开调用接口
     * */
    ParkingSpaceVO vehicleLeaving(ParklotDO parklotDO);

    /*
     * 当日缴费总金额
     * */
    BigDecimal todayTotalAmount();

}
