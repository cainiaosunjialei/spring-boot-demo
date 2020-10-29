package com.example.demo.admin.service.impl;

import com.example.demo.admin.model.Resource;
import com.example.demo.admin.mapper.ResourceMapper;
import com.example.demo.admin.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台资源表 服务实现类
 * </p>
 *
 * @author sunjialei
 * @since 2020-10-24
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

}
