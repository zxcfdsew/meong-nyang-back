package com.meongnyang.shop.repository;

import com.meongnyang.shop.entity.Pet;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetMapper {
    int save(Pet pet);
    Pet findPetByUserid(Long id);
    int updatePetById(Pet pet);
}
