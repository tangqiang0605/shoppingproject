package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shops {
    private Integer sid;
    private String sname;
    private Integer totalsales;
    private Integer onlinegoodsnum;
}
