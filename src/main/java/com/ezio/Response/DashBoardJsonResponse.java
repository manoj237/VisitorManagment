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
public class DashBoardJsonResponse {
   private Integer dailyMeeting;
   private Integer weeklyMeeting;
   private Integer monthlyMeeting;
   private Integer completeMeeting;
   private Integer pendingMeeting;
   
}
