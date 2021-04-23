package zhouxiong.demo.demo.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import zhouxiong.demo.demo.application.ParklotService;
import zhouxiong.demo.demo.infrastructure.utils.JsonResponse;
import zhouxiong.demo.demo.interfaces.domain.ParklotDO;
import zhouxiong.demo.demo.interfaces.vo.ParkingSpaceVO;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Package zhouxiong.demo.demo.interfaces
 * @Description:
 * @author: zhouxiong
 * @create: 2021-04-23 20:48
 **/
@RestController
@Api(tags = "停车场调用接口")
@RequestMapping("/parklot")
public class ParklotController {
    @Resource
    private ParklotService parklotService;

    @ApiOperation("停车场车辆进入调用接口")
    @PostMapping("/vehicleEntering ")
    public JsonResponse<ParkingSpaceVO> vehicleEntering(@RequestBody ParklotDO parklotDO){
        return JsonResponse.success(parklotService.vehicleEntering(parklotDO));
    }

    @ApiOperation("停车场车辆离开调用接口")
    @PostMapping("/vehicleLeaving  ")
    public JsonResponse<ParkingSpaceVO> vehicleLeaving(@RequestBody ParklotDO parklotDO){
        return JsonResponse.success(parklotService.vehicleLeaving(parklotDO));
    }

    @ApiOperation("当日缴费总金额")
    @GetMapping("/todayTotalAmount  ")
    public JsonResponse<BigDecimal> todayTotalAmount (){
        return JsonResponse.success(parklotService.todayTotalAmount());
    }
}
