package com.ezio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezio.model.Admin;
import com.ezio.model.Visitor;
import com.ezio.repository.VisitorRepository;

@Service
public class VisitorService {
	
@Autowired
VisitorRepository visitorrepo;
	public List<Visitor> getUserList() {
		// TODO Auto-generated method stub
		return visitorrepo.findByOrderByIdDesc();
	}
	public void saveService(Visitor visitor) {
		// TODO Auto-generated method stub
		visitorrepo.save(visitor);
	}
	public void deleteVisitor(Long id) {
		// TODO Auto-generated method stub
		visitorrepo.deleteById(id);
	}
	public Visitor editVisitor(Long id) {
		// TODO Auto-generated method stub
		return visitorrepo.findById(id).get();
	}
	public void updateVisitor(Visitor visitor) {
		// TODO Auto-generated method stub
		visitorrepo.save(visitor);
	}
	public List<Visitor> getEmployeeList() {
		// TODO Auto-generated method stub
		return visitorrepo.findAll();
	}
	public Visitor savePhoto(Visitor visitor) {
		// TODO Auto-generated method stub
		return visitorrepo.save(visitor);
	}
	public void saveImage(Visitor visitor) {
		// TODO Auto-generated method stub
		visitorrepo.save(visitor);
	}
	
	
	
	

}
