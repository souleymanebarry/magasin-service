package com.barry.controllers;


import com.barry.entities.Magasin;
import com.barry.service.MagasinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@Slf4j
@RestController
@Api("Magasins ressource : allow access to the magasins referencial data")
@RequestMapping("/magasins")
public class MagasinController {

    private final MagasinService magasinService;

    @GetMapping(path = "/")
    @ApiOperation(value = "Load all magasins")
    public List<Magasin> listMagasin() {
        log.info("find all magasin ");
        return magasinService.findAllMagasin();
    }

    @GetMapping(path = "/{code}")
    @ApiOperation("Load magasin By Id")
    public Magasin getMagasinByCode(String code) {
        log.info("find magasin by code{}",code);
        return magasinService.getMagasinByCode(code);
    }

    @PostMapping(path = "/")
    @ApiOperation(value="Create a magasin")
    public Magasin createMagasin(Magasin magasin) {
        return magasinService.createMagasin(magasin);
    }

    @DeleteMapping(path = "/{code}")
    @ApiOperation(value="delete a magasin by it's code")
    public void deleteMagasin(String code) {
        log.info("delete magasin by code{}",code);
        magasinService.deleteMagasin(code);
    }


    @PutMapping(path = "/{code}")
    @ApiOperation(value="Update a magasin")
    public Magasin updateMagasin(String code, Magasin magasin) {
        log.info("update magasin by code{}",code);
        return magasinService.updateMagasin(code,magasin);
    }
}
