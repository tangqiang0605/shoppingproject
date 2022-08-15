package com.example.demo.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowingCart {
    private Integer cid;
    private Integer gid;
    private String gname;
    private String srcurl;
    private Integer oamount;
}
