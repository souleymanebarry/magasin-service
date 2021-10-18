package com.barry.service.impl;

import com.barry.dao.MagasinRepository;
import com.barry.entities.Magasin;
import com.barry.exceptions.ConflictException;
import com.barry.service.MagasinService;
import com.barry.utils.ParamUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MagasinServiceImpl implements MagasinService {

    private final MagasinRepository magasinRepository;

    public MagasinServiceImpl(MagasinRepository magasinRepository) {
        this.magasinRepository = magasinRepository;
    }

    @Override
    public List<Magasin> findAllMagasin() {
        return magasinRepository.findAll();
    }

    @Override
    public Magasin getMagasinByCode(String code) {
        ParamUtils.validateCodeMagasin(code);
        return magasinRepository.findById(code)
                .orElseThrow(()-> new IllegalArgumentException("Magasin not found"));
    }

    @Override
    public Magasin createMagasin(Magasin magasin) {
        Optional<Magasin> optionalMagasin=magasinRepository.findById(magasin.getCode());
        if (optionalMagasin.isPresent()){
            throw new ConflictException("Magasin already exist");
        }
        return magasinRepository.save(magasin);
    }

    @Override
    public void deleteMagasin(String code) {
        ParamUtils.validateCodeMagasin(code);
        magasinRepository.findById(code)
                .orElseThrow(()-> new IllegalArgumentException("Magasin not found"));
        magasinRepository.deleteById(code);
    }

    @Override
    public Magasin updateMagasin(String code, Magasin magasin) {
        ParamUtils.validateCodeMagasin(code);
        magasinRepository.findById(code)
                .orElseThrow(()-> new RuntimeException("Magasin not found"));
        magasin.setCode(code);
        return magasinRepository.save(magasin);
    }
}
