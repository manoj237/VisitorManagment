package com.ezio.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ezio.model.AssignData;

@Repository
public interface AssignRepo extends JpaRepository<AssignData, Long> {

	
	
	@Query(nativeQuery = true, value ="Select count(id) from assign_data where status=:status and empid=:employeeid")
	Integer getuserPendingTicketCount(String status, Long employeeid);

	List<AssignData> findAllByEmpid(Long id);
	@Query(nativeQuery = true, value ="Select count(id) from assign_data where date=:date" )
	Integer gettingDayCount(String date);

	@Query(nativeQuery = true, value ="Select count(id) from assign_data where YEARWEEK(`date`,1)=YEARWEEK(CURDATE(),1)" )
	Integer gettingWeeklyCount();

	@Query (nativeQuery = true, value ="Select count(id) from assign_data WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND MONTH(date) = MONTH(CURRENT_DATE())" )
	Integer gettingMonthlyDataCount();

	@Query (nativeQuery = true, value = "Select count(id) from assign_data WHERE status='Pending'")
	Integer gettingPendingDataCount();

	@Query (nativeQuery = true, value = "Select count(id) from assign_data WHERE status='Complete'")
	Integer gettingCompleteDataCount();

	


}
