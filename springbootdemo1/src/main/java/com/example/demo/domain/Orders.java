package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    private Integer oid;
    private String ostate;
    private Integer did;
    private Integer cid;
    private Integer sid;
    private Boolean isdeli;
}
