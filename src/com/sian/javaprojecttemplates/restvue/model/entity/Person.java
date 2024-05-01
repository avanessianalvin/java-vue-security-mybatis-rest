package com.sian.javaprojecttemplates.restvue.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Person {
    Long id;
    String name;
    String family;
    String username;
}
