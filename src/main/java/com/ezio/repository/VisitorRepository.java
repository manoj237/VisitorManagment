package com.ezio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezio.model.Visitor;
@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {

	Optional<Visitor> findAllById(String id);

	List<Visitor> findByOrderByIdDesc();

}
