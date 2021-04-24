package zhouxiong.demo.demo.application.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zhouxiong.demo.demo.application.ParklotService;
import zhouxiong.demo.demo.domain.mapper.ParkingSpaceMapper;
import zhouxiong.demo.demo.domain.mapper.ParklotMapper;
import zhouxiong.demo.demo.domain.model.ParkingSpace;
import zhouxiong.demo.demo.domain.model.Parklot;
import zhouxiong.demo.demo.infrastructure.exception.LadOpenException;
import zhouxiong.demo.demo.infrastructure.status.ErrorInfoEnum;
import zhouxiong.demo.demo.interfaces.domain.ParklotDO;
import zhouxiong.demo.demo.interfaces.vo.ParkingSpaceVO;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

/**
 * @Package zhouxiong.demo.demo.application.impl
 * @Description:
 * @author: zhouxiong
 * @create: 2021-04-23 20:45
 **/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ParklotServiceImpl extends BaseServiceImpl<ParklotMapper,Parklot>implements ParklotService {

    @Resource
    private ParklotMapper parklotMapper;

    @Resource
    private ParkingSpaceMapper parkingSpaceMapper;

    /*
     * 停车场车辆进入调用接口
     * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ParkingSpaceVO vehicleEntering(ParklotDO parklotDO) {

        //首先查询停车位是否大于1
        // 前提：数据库初始数据为当前停车场全部的停车位数量
        ParkingSpace parkingSpace = parkingSpaceMapper.selectOne(Wrappers.<ParkingSpace>lambdaQuery()
        );
        if(parkingSpace.getRemainingParkingSpaces().intValue()<1){
            //剩余停车位为0时。抛出剩余为0的信息
            throw new LadOpenException(ErrorInfoEnum.NO_PARKING_SPACE);
        }

        //首先将该汽车，车类型，车牌号，入停车场时间保存到数据库中
        Parklot build = Parklot.builder()
                .entryTime(LocalDateTime.now())
                .carType(parklotDO.getCarType())
                .numberPlate(parklotDO.getNumberPlate())
                .effectiveState(Integer.valueOf(0))
                .build();
        Boolean success = parklotMapper.insert(build) > 0;

        if (!success){
            //如果不成功，则抛出错误
            throw new LadOpenException(ErrorInfoEnum.ERROR);
        }

        //保存成功后，停车位数据库数据减一
        //在数据库中修改停车位剩余车位
        parkingSpace.setRemainingParkingSpaces(parkingSpace.getRemainingParkingSpaces()-1);
        parkingSpaceMapper.updateById(parkingSpace);

        //将车牌号，停车场剩余车位返回
        ParkingSpaceVO parkingSpaceVO = ParkingSpaceVO.builder()
                .numberPlate(parklotDO.getNumberPlate())
                .remainingParkingSpaces(parkingSpace.getRemainingParkingSpaces()-1)
                .build();
        return parkingSpaceVO;
    }

    /*
     * 停车场车辆离开调用接口
     * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ParkingSpaceVO vehicleLeaving(ParklotDO parklotDO) {

        //查询此次行程生效的该车牌号车辆
        Parklot parklot = parklotMapper.selectOne(Wrappers.<Parklot>lambdaQuery()
                .eq(Parklot::getNumberPlate, parklotDO.getNumberPlate())
                .eq(Parklot::getEffectiveState, Integer.valueOf(0))
        );
        //出停车场时间
        LocalDateTime departureTime = LocalDateTime.now();
        //计算时间差 ，
        Duration duration = Duration.between(parklot.getEntryTime(), departureTime);
        long hours = duration.toHours();
        //计算停车费用
        //小车时
        BigDecimal cost = BigDecimal.valueOf(0L);
        if (parklot.getCarType().equals(Integer.valueOf(1))){
            BigDecimal time = BigDecimal.valueOf(hours + 1L);
            BigDecimal price = BigDecimal.valueOf(5L);
            cost = time.multiply(price);
            int i = cost.compareTo(BigDecimal.valueOf(60L));
            if (i == 1){
                cost = BigDecimal.valueOf(60L);
            }
        }else {
            //货车时
            BigDecimal time = BigDecimal.valueOf(hours + 1L);
            BigDecimal price = BigDecimal.valueOf(10L);
            cost = time.multiply(price);
            int i = cost.compareTo(BigDecimal.valueOf(120L));
            if (i == 1){
                cost = BigDecimal.valueOf(120L);
            }
        }
        //统计该车辆剩余信息
        parklot.setDepartureTime(departureTime);
        parklot.setParkingFee(cost);
        parklot.setEffectiveState(Integer.valueOf(1));
        parklotMapper.updateById(parklot);

        //修改成功后，停车位数据加一       前提：数据库初始数据为当前停车场的停车位数量，为方便默认只有此一条数据
        ParkingSpace parkingSpace = parkingSpaceMapper.selectOne(Wrappers.<ParkingSpace>lambdaQuery()
        );
        //将车牌号，停车场剩余车位返回
        ParkingSpaceVO parkingSpaceVO = ParkingSpaceVO.builder()
                .numberPlate(parklotDO.getNumberPlate())
                .remainingParkingSpaces(parkingSpace.getRemainingParkingSpaces()+1)
                .parkingFee(cost)
                .build();

        //在数据库中修改停车位剩余车位
        parkingSpace.setRemainingParkingSpaces(parkingSpace.getRemainingParkingSpaces()+1);
        parkingSpaceMapper.updateById(parkingSpace);
        return parkingSpaceVO;
    }

    /*
     * 当日缴费总金额
     * */
    @Override
    public BigDecimal todayTotalAmount() {
        LocalDateTime today_start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime today_end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        List<Parklot> parklots = parklotMapper.selectList(Wrappers.<Parklot>lambdaQuery()
                .between(Parklot::getDepartureTime, today_start, today_end)
        );
        BigDecimal todayTotalAmount = BigDecimal.valueOf(0L);
        for (int i = 0; i <parklots.size() ; i++) {
            todayTotalAmount = todayTotalAmount.add(parklots.get(i).getParkingFee());
        }
        return todayTotalAmount;
    }

}
