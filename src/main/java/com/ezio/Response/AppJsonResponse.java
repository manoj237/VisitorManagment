package com.ezio.Response;
import com.ezio.model.Admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppJsonResponse {
	
	private String visname;
	private String visreason;
	private String date;
	private String time;
	private String email;
	
	
	public void setMessage(String string) {
		// TODO Auto-generated method stub
		
	}
	public void setStatus(String string) {
		// TODO Auto-generated method stub
		
	}
	
	

}
