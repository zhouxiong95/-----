package zhouxiong.demo.demo.application.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import zhouxiong.demo.demo.application.BaseService;

/**
 * @Package zhouxiong.demo.demo.application.impl
 * @Description:
 * @author: zhouxiong
 * @create: 2021-04-23 20:37
 **/
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {
}
