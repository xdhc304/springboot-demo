package com.xdhc.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdhc.demo.dao.PeopleMapper;
import com.xdhc.demo.entity.People;
import com.xdhc.demo.service.PeopleService;
import org.springframework.stereotype.Service;

@Service
public class PeopleServiceImpl extends ServiceImpl<PeopleMapper, People> implements PeopleService {
}