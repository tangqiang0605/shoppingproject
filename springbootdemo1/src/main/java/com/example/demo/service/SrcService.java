package com.example.demo.service;

import com.example.demo.domain.Src;
import com.example.demo.mapper.SrcMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SrcService {

    @Autowired
    private SrcMapper srcMapper;

    public int add(Src src){
        srcMapper.insert(src);
        return src.getSrcid();
    }
//    todo:给接口（直接通过srcid查询文件并下载）用，发挥src表的srcname的作用
    public Src findBySrcid(String srcid) {
        return srcMapper.findBySrcid(srcid);
    }
}
