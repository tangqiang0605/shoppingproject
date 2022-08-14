package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreKeeper {
    private Integer sid;
    private String sname;
    private String spassword;
    private Boolean isban;
}
