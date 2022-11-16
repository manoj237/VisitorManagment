package com.ezio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezio.common.Mail;
import com.ezio.model.Admin;
import com.ezio.model.AssignData;
import com.ezio.model.Attendance;
import com.ezio.repository.AdminRepo;
import com.ezio.repository.AssignRepo;
import com.ezio.repository.AttendanceRepo;


@Service
public class WebService {

	@Autowired
	AdminRepo adminrepo;
	
	@Autowired
	AssignRepo assignrepo;
	
	@Autowired
	AttendanceRepo attendrepo;	


	public Admin validateAdmin(String email, String password) {

		String status = "Active";
		return adminrepo.findByEmailAndPasswordAndStatus(email, password, status);
	}

	

	public List<Admin> getUserList() {
		
		return adminrepo.findAll();
	}

	public void deleteUsers(Long id) {
		 
		adminrepo.deleteById(id);
	}

	public Admin editUser(Long id) {
		
		return adminrepo.findById(id).get();
	}

	public void updateUser1(Admin admin) {
		
		adminrepo.save(admin);
	}

	public List<Admin> getUserList1() {
		
		return adminrepo.findAll();
	}

	public List<AssignData> getAssignDataList() {
		
		return assignrepo.findAll();
	}

	public List<Admin> getEmployeeList(String role) {
		
		return adminrepo.findByRole(role);
	}

	public void saveData(AssignData assigndata) {
		
		assignrepo.save(assigndata);
		}

	public Admin getEmployeeName(Long empid) {
		
		return adminrepo.findById(empid).get();
	}

	public Admin popUp1(Long id) {
		
		return adminrepo.findById(id).get();
	}

	public AssignData getAssignedDataList(Long id) {
		
		return assignrepo.findById(id).get();
	}

	public void updateAssignedData(AssignData assigndata) {
		
		assignrepo.save(assigndata);
	}

	


	public Integer getPendingTicketCount(String status, Long employeeid) {
		
		return assignrepo.getuserPendingTicketCount(status,employeeid);
	}

	public List<AssignData> getAssignDataById(Long id) {
		
		return assignrepo.findAllByEmpid(id);
	}

	public Integer getDateTicketCount( String date) {
		
		return assignrepo.gettingDayCount(date);
	}

	public Integer getWeekTicketCount() {
		
		return assignrepo.gettingWeeklyCount();
	}

	public Integer geMonthlyDataCount() {
		
		return assignrepo.gettingMonthlyDataCount();
	}

	public Integer gePendingDataCount() {
		
		return assignrepo.gettingPendingDataCount();
	}

	public Integer geCompleteDataCount() {
		
		return assignrepo.gettingCompleteDataCount();
	}
//  **********************  Edit and Delete Methods For Assign data  ******************************//	
	public AssignData editAssignData(Long id) {
		
		return assignrepo.findById(id).get();
	}

	public void updateAssignData1(AssignData assigndata) {
		
		assignrepo.save(assigndata);
	}

	public List<AssignData> getAssignDataList1() {
		
		return assignrepo.findAll();
	}

	public void deleteAssignData(Long id) {
		
		assignrepo.deleteById(id);
	}
//  **********************  Edit and Delete Methods For Assign data  ******************************//	

	public Admin gettingDataById(Long id) {
		
		return adminrepo.findById(id).get();
	}

	public void saveRegistration(Admin admin) {
		
		adminrepo.save(admin);
	}

	public List<Admin> getUserList2() {
		
		return adminrepo.findAll();
	}

	public static  void saveEmail(Mail sendmail) {
		
		
	}



	public void saveService(Admin admin) {
	
		adminrepo.save(admin);
	}


// -------------------------------------------------------------------------- //
	public Attendance saveAttendance(Attendance attendance) {
		attendrepo.save(attendance);
		return attendance;
		
		
	}



	public List<Attendance> getAttendanceList() {
		
		return attendrepo.findAll();
	}

                 //=====//

	public Attendance loginCheck(String date, Long userId) {
		
		return attendrepo.findByDateAndUserId(date,userId);
	}



	public void saveAllattendance(Attendance attend) {
		
		attendrepo.save(attend);
	}



	
	  public Long updateAllAttendance(String checkOut, String date, Long userId) {
	  
	  return attendrepo.save(checkOut,date,userId);
	  
	  }
	 



	
	/*public void saveUser(Admin admin) {

		adminrepo.save(admin);
	}*/

	

}