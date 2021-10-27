package com.barry.service.impl;

import com.barry.dao.MagasinRepository;
import com.barry.entities.Magasin;
import com.barry.exceptionshandler.CustomCodeMessageException;
import com.barry.service.MagasinService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class MagasinServiceImpl implements MagasinService {

    private final MagasinRepository magasinRepository;

    public MagasinServiceImpl(MagasinRepository magasinRepository) {
        this.magasinRepository = magasinRepository;
    }

    @Override
    public List<Magasin> findAllMagasin() {
        List<Magasin> magasinList = magasinRepository.findAll();
        if (CollectionUtils.isEmpty(magasinList)) {
            log.error("error.sample.listMagasin");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return magasinList;
    }

    @Override
    public Magasin getMagasinByCode(String code) {
        validateCodeMagasin(code);
        return magasinRepository.findById(code)
                .orElseThrow(() -> new CustomCodeMessageException("error.code.magasin.code",
                        "error.magasin.message",HttpStatus.BAD_REQUEST));
    }

    @Override
    public Magasin createMagasin(Magasin magasin) {
        Optional<Magasin> optionalMagasin = magasinRepository.findById(magasin.getCode());
        if (optionalMagasin.isPresent()) {
            throw new CustomCodeMessageException("error.magasinAlreadyDeclared.code",
                    "error.magasinAlreadyDeclared.message", HttpStatus.CONFLICT);
        }
        return magasinRepository.save(magasin);
    }

    @Override
    public void deleteMagasin(String code) {
        validateCodeMagasin(code);
        getMagasinByCode( code);
        magasinRepository.deleteById(code);
    }

    @Override
    public Magasin updateMagasin(String code, Magasin magasin) {
        validateCodeMagasin(code);
        getMagasinByCode( code);
        magasin.setCode(code);
        return magasinRepository.save(magasin);
    }

    private void validateCodeMagasin(String code){
        if(StringUtils.isEmpty(code))
            throw new CustomCodeMessageException("remark.blankCode.code",
                    "error.code.magasin.message",HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(code))
            throw new CustomCodeMessageException("remark.blankCode.code",
                    "remark.blankCode.message", HttpStatus.BAD_REQUEST);
    }

}
