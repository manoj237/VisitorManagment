package com.ezio.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ezio.model.Admin;
import com.ezio.model.AssignData;
import com.ezio.model.ServiceData;



public interface AdminRepo extends JpaRepository<Admin,Long>{

	Admin findByEmailAndPasswordAndStatus(String email, String password, String status);

	List<Admin> findByRoleNot(String role);

	Boolean existsByEmail(String email);
	
	@Query(nativeQuery = true, value = "SELECT wallet FROM admin where id=:branchId")
	String getwalletdatasync(Long branchId);

	@Modifying
	@Transactional
	@Query("update Admin set wallet=:amount where id=:branchid")
	Integer updateWallet(Integer amount,Long branchid);

	List<Admin> findByRole(String role);


}
