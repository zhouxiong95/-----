package zhouxiong.demo.demo.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import zhouxiong.demo.demo.domain.model.ParkingSpace;

/**
 * @Package zhouxiong.demo.demo.domain.mapper
 * @Description:
 * @author: zhouxiong
 * @create: 2021-04-23 20:33
 **/
@Repository
@Mapper
public interface ParkingSpaceMapper extends BaseMapper<ParkingSpace> {
}
