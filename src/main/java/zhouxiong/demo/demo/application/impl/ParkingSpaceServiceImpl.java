package zhouxiong.demo.demo.application.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zhouxiong.demo.demo.application.ParkingSpaceService;
import zhouxiong.demo.demo.domain.mapper.ParkingSpaceMapper;
import zhouxiong.demo.demo.domain.model.ParkingSpace;

import java.util.Collection;

/**
 * @Package zhouxiong.demo.demo.application.impl
 * @Description:
 * @author: zhouxiong
 * @create: 2021-04-23 20:41
 **/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ParkingSpaceServiceImpl extends BaseServiceImpl<ParkingSpaceMapper,ParkingSpace> implements ParkingSpaceService {
}
