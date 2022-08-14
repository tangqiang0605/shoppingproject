package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
    private Integer gid;
    private Integer gsave;
    private Integer gsales;
    private Integer sid;
    private String state;
    private Integer srcid;
    private String gname;
    private Integer gonlinenum;
    private Date time;
}
