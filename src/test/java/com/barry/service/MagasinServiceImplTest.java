package com.barry.service;


import com.barry.dao.MagasinRepository;
import com.barry.entities.Magasin;
import com.barry.exceptions.BadRequestException;
import com.barry.exceptions.ConflictException;
import com.barry.exceptions.ResourceNotFoundException;
import com.barry.service.impl.MagasinServiceImpl;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MagasinServiceImplTest {

    @InjectMocks
    MagasinServiceImpl magasinService;

    @Mock
    MagasinRepository magasinRepository;


    @Test
    void test_getMagasinByCode_whenExistingMagasinCode_returnsAMagasin(){

        //given
        Optional<Magasin> magasin =Optional.of(
              new Magasin("101","Lille","13 Rue de la vollaile",12, "flag","handl1"));

        // when
        when(magasinRepository.findById("101")).thenReturn(magasin);
        final Magasin magasinByCode = magasinService.getMagasinByCode("101");

        assertAll(
                ()-> assertNotNull(magasinByCode),
                ()-> assertEquals("101",magasinByCode.getCode()),
                ()-> assertEquals("Lille",magasinByCode.getVille())
        );

        verify(magasinRepository).findById("101");
        verifyNoMoreInteractions(magasinRepository);
    }

    @Test
    void test_getMagasinByCode_whenUnknownMagasinCode_returnThrowResourceNotfound(){
        //given
        when(magasinRepository.findById("ZZ")).thenThrow(new ResourceNotFoundException("not_found"));

        //when
        assertThrows(ResourceNotFoundException.class,
                ()-> magasinService.getMagasinByCode("ZZ"), "???not_found???");

        //then
        // exception thrown
        verify(magasinRepository).findById("ZZ");
        verifyNoMoreInteractions(magasinRepository);
    }

    @Test
    void test_getMagasinByCode_whenMagasinCodeIsEmpty_returnThrowBadRequestException() {
        assertThrows(BadRequestException.class,
                () -> magasinService.getMagasinByCode(""), "???it needs to be non empty???");
        verifyNoMoreInteractions(magasinRepository);
    }

    @Test
    void test_getMagasinByCode_whenMagasinCodeIsBlank_returnThrowBadRequestException() {
        assertThrows(BadRequestException.class,
                () -> magasinService.getMagasinByCode(" "), "???it needs to be non Blank???");
        verifyNoMoreInteractions(magasinRepository);
    }

    @ParameterizedTest
    @ValueSource(strings = {""," ", "\t"})
    @NullSource
    void test_getMagasinByCode_should_not_accept_empty_or_blank_code(String code) {
        assertThrows(BadRequestException.class,
                () -> magasinService.getMagasinByCode(code));
        verifyNoMoreInteractions(magasinRepository);
    }

    @Test
    void test_findAllMagasin_returnsAllMagasins(){
        //given
        List<Magasin> answer= Arrays.asList(
                new Magasin("101", "Lille", "test",59,"O","test"),
                new Magasin("200", "paris", "72 rue de paris",2,"1","1")
        );
        //when
        when(magasinRepository.findAll()).thenReturn(answer);
        final List<Magasin> magasins = magasinService.findAllMagasin();

        //then
        assertThat(magasins).isNotNull();
        assertThat(magasins).extracting("code").contains("101","200").doesNotContain("XX");

        verify(magasinRepository).findAll();
        verifyNoMoreInteractions(magasinRepository);
    }

    @Test
    void test_createMagasin_should_addNew_magasin(){
        //given
        Magasin magasin=
                new Magasin("101", "Lille", "test",59,"O","test");
        //when
        when(magasinRepository.save(magasin)).thenReturn(magasin);
        Magasin savedmagasin = magasinService.createMagasin(magasin);

        //then
        assertAll(
                ()-> assertEquals(magasin.getCode(),savedmagasin.getCode()),
                ()-> assertEquals(magasin.getVille(),savedmagasin.getVille())
        );

        verify(magasinRepository).save(magasin);
    }

    @Test
    void test_createMagasin_throwsConflictException_whenMagasinAlreadyExists(){
        //given
        final Optional<Magasin> magasin =Optional.of( new Magasin());
        magasin.get().setCode("222");

        //when
        when(magasinRepository.findById("222")).thenReturn(magasin);
        assertThrows(ConflictException.class,()->
            magasinService.createMagasin(magasin.get())
        );

        //then

        verify(magasinRepository, times(1)).findById(anyString());
        verifyNoMoreInteractions(magasinRepository);
    }

    @Test
    void  test_deleteMagasinByCode_shouldRemoveMagasin(){
        //given
        String code = "TEST";

        Magasin magasin = new Magasin();

        magasin.setCode(code);

        //when
        when(magasinRepository.findById(code)).thenReturn(Optional.of(magasin));
        magasinService.deleteMagasin(code);

        //then
        verify(magasinRepository, times(1)).deleteById(code);
        verifyNoMoreInteractions(magasinRepository);
    }

    @ParameterizedTest
    @ValueSource(strings = {""," ", "\t"})
    @NullSource
    void  test_deleteMagasinByCode_should_not_accept_empty_or_blank_or_null_code(String code){
        assertThrows(BadRequestException.class, ()->
                magasinService.deleteMagasin(code)
                );
        verifyNoInteractions(magasinRepository);
    }

    @Test
    void test_updateMagasin_shouldUpdateMagasin(){
        //given
        final Optional<Magasin> magasin =Optional.of( new Magasin());
        magasin.get().setCode("xxx");

        //when
        when(magasinRepository.findById("xxx")).thenReturn(magasin);
        Magasin updateMagasin = magasinService.updateMagasin("xxx", magasin.get());

        verify(magasinRepository, times(1)).findById("xxx");
    }

    @ParameterizedTest
    @ValueSource(strings = {""," ", "\t"})
    @NullSource
    void test_updateMagasin_should_not_accept_empty_or_blank_or_null_code(String code){
        assertThrows(BadRequestException.class, ()->
                magasinService.updateMagasin(code,new Magasin())
        );
    }

}
