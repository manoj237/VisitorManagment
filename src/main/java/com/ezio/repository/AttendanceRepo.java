package com.ezio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ezio.model.Attendance;
@Repository
public interface AttendanceRepo extends JpaRepository<Attendance,Long> {

	
	 @Query(nativeQuery = true, value="Select findAttendance(id) from attendance where empid(id)=:empid(id)" )
	  Long findAttendance();
	 

	
	@Query(nativeQuery = true, value ="Select * from attendance where date=:date and user_id=:userId" )
	Attendance findByDateAndUserId(String date, Long userId);

	
	 @Query(nativeQuery = true, value="Select id from attendance where check_out=:checkOut and date=:date and user_id=:userId" )
	 Long save(String checkOut, String date, Long userId);
	 

	//@Query(nativeQuery = true, value ="Select id from attendance where check_out=:checkOut and date=:date and user_id=:userId" )
	//Long saveById(String checkOut, String date, Long id);//

	
}
