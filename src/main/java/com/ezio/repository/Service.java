package com.ezio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezio.model.ServiceData;

@Repository
public interface Service extends JpaRepository<ServiceData,Long> {

}
