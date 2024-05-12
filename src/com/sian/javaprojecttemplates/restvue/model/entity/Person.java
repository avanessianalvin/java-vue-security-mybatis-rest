package com.sian.javaprojecttemplates.restvue.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

@Data
@Accessors(chain = true)
public class Person {
    Long id;
    @Length(max = 50)@NotNull@NotBlank
    String name;
    @Length(max = 50)@NotNull@NotBlank
    String family;
    String username;
}
