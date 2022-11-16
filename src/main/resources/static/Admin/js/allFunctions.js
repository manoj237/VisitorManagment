//(  For Employee Page Count   )
function getTicketCount() {
	$.ajax({
		type: 'ajax',
		method: 'GET',
		url: "/get-Pending-Ticket-Count",
		data: {},

		success: function(data) {

			var d = document.getElementById('pendingTicketCount').innerHTML = data;

		},
		error: function() {

		}
	});
}
//   ( For Day Data Count  )
function getAllDashboardCount() {
	$.ajax({
		type: 'ajax',
		method: 'GET',
		url: "/get-all-dashboard-count",
		data: {},

		success: function(data) {

			document.getElementById('dayTicketCount').innerHTML = data.dailyMeeting;
			document.getElementById('weeklyTicketCount').innerHTML = data.weeklyMeeting;
			document.getElementById('monthlydataCount').innerHTML = data.monthlyMeeting;
			document.getElementById('pendingdataCount').innerHTML = data.pendingMeeting;
			document.getElementById('completedataCount').innerHTML = data.completeMeeting;

		},
		error: function() {

		}
	});
}

//   ( For Weekly Data Count  )
/*function getWeeklyTicketCount() {
	$.ajax({
		type: 'ajax',
		method: 'GET',
		url: "/get-Weekly-Data-Count",
		data: {},

		success: function(data) {

			var f = document.getElementById('weeklyTicketCount').innerHTML = data;

		},
		error: function() {

		}
	});
}

//   ( For Monthly Data Count  )
function getMonthlydataCount() {
	$.ajax({
		type: 'ajax',
		method: 'GET',
		url: "/get-Monthly-Data-Count",
		data: {},

		success: function(data) {

			var g = document.getElementById('monthlydataCount').innerHTML = data;

		},
		error: function() {

		}
	});
}

//   ( For Pending Data Count  )
function getPendingDataCount() {
	$.ajax({
		type: 'ajax',
		method: 'GET',
		url: "/get-Pending-Data-Count",
		data: {},

		success: function(data) {

			var h = document.getElementById('pendingdataCount').innerHTML = data;

		},
		error: function() {

		}
	});
}

//   (   Get Complete Data Count )
function getCompleteDataCount() {
	$.ajax({
		type: 'ajax',
		method: 'GET',
		url: "/get-Complete-Data-Count",
		data: {},

		success: function(data) {

			var i = document.getElementById('completedataCount').innerHTML = data;

		},
		error: function() {

		}
	});
}*/

