package com.barry.service;

import com.barry.entities.Magasin;

import java.util.List;

public interface MagasinService {


    List<Magasin> findAllMagasin();

    /**
     *
     * @param code
     * @return magasin by code
     */
    Magasin getMagasinByCode(String code);

    /**
     *  create un new magasin
     * @param magasin
     * @return magasin
     */
    Magasin saveMagasin(Magasin magasin);

    /**
     *  update magasin by code
     * @param code
     * @return magasin
     */
    Magasin updateMagasin(String code,Magasin magasin);


    /**
     * delete magasin by code
     * @param code
     */
    void deleteMagasin(String code);
}
