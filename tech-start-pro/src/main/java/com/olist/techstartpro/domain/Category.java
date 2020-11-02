package com.olist.techstartpro.domain;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@JsonPOJOBuilder
@EqualsAndHashCode(exclude = {"id"})
@Data
@Table
@Builder
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    private String name;
}
