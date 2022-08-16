package com.example.demo.service;

import com.example.demo.domain.Src;
import com.example.demo.mapper.SrcMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SrcService {

    @Autowired
    private SrcMapper srcMapper;

    public int add(Src src){
        srcMapper.insert(src);
        return src.getSrcid();
    }

//    public List<Src> findAll(){
//        return srcMapper.findAll();
//    }


}
