package com.barry.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Magasin {

    @Id
    private String code;

    private String ville;

    private String adresse;

    private Integer cp;

    private String flagTrt;

    private String handle;

}
