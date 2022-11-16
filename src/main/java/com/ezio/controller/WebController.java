package com.ezio.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezio.Response.AppJsonResponse;
import com.ezio.Response.DashBoardJsonResponse;
import com.ezio.Response.Images;
import com.ezio.common.Mail;
import com.ezio.model.Admin;
import com.ezio.model.AssignData;
import com.ezio.model.Attendance;
import com.ezio.model.Visitor;
import com.ezio.service.VisitorService;
import com.ezio.service.WebService;

@Controller
public class WebController {

	@Autowired
	WebService webService;
	@Autowired
	VisitorService visitorService;

	String uploadProductDirectory = System.getProperty("user.dir") + "/uploads/";

	@RequestMapping("/") // log in page mapping//
	public String signIn() {

		File f = new File(uploadProductDirectory);

		if (!f.exists()) {
			f.mkdirs();
		}
		System.out.println("first call");
		return "admin/login";

	}

	@RequestMapping("/index") // index html page mapping//
	public String adminIndex() {

		// System.out.println("2nd call");
		return "admin/index";

	}

	@RequestMapping("/user")
	public String userTable() {

		// System.out.println("4th call");
		return "admin/user";

	}

	// $$ Validation For Log In $$//
	@RequestMapping("/adminvalid")
	public String validSignIn(RedirectAttributes redireAttributes, HttpServletRequest request, HttpSession session,
			@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
		System.out.println("valid admn");
		Admin user = webService.validateAdmin(email, password);
		if (user != null) {
			if (user.getStatus().equals("Active") && user.getRole().equals("Admin")) {
				session.setAttribute("username", user.getName());
				session.setAttribute("id", user.getId());
				session.setAttribute("sign-in-userEmail", user.getEmail());
				session.setAttribute("admin-id", user.getId());
				session.setAttribute("role", user.getRole());
				model.addAttribute("signin", user.getId());
				System.out.println("ytiutyi");
				return "admin/index";
			} else if (user.getStatus().equals("Active") && user.getRole().equals("Receptionist")) {
				session.setAttribute("username", user.getName());
				session.setAttribute("id", user.getId());
				session.setAttribute("sign-in-userEmail", user.getEmail());
				session.setAttribute("admin-id", user.getId());
				session.setAttribute("role", user.getRole());
				model.addAttribute("signin", user.getId());
				System.out.println("recp");
				return "admin/index";
				// return "admin/recphome";
			} else if (user.getStatus().equals("Active") && user.getRole().equals("Employee")) {
				session.setAttribute("username", user.getName());
				session.setAttribute("id", user.getId());
				session.setAttribute("sign-in-userEmail", user.getEmail());
				session.setAttribute("admin-id", user.getId());
				session.setAttribute("role", user.getRole());
				model.addAttribute("signin", user.getId());
				System.out.println("recp");
				return "admin/index";
			}
		} else {
			return "redirect:/";
		}
		return "redirect:/";
	}

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
//save user method//
	@RequestMapping(value = "/save-user", method = RequestMethod.POST)
	@ResponseBody
	public Admin saveProduct(Admin admin, HttpSession session) throws IOException {
		System.out.println("cl check");

		// product.setUpdatedName(session.getAttribute("sign-in-user").toString());
		webService.saveService(admin);
		return admin;

	}

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
	// api of registarion //
	@RequestMapping(value = "/save-registration", method = RequestMethod.POST)
	@ResponseBody
	public String saveUsers(Admin admin, HttpSession session) throws IOException {
		System.out.println("cl check");

		// product.setUpdatedName(session.getAttribute("sign-in-user").toString());
		webService.saveRegistration(admin);
		return "admin/login";
	}

	// ><><<>><<>><><><><><><><><><><<>><><//
	@RequestMapping(value = "/fetch-all-usr-reg", method = RequestMethod.GET)
	@ResponseBody // http request call
	public List<Admin> gettingData2() {
		// System.out.println("getting data ");
		return this.webService.getUserList2();
	}

// *************************************************************************************  //
	@RequestMapping(value = "/fetch-all-users-list", method = RequestMethod.GET)
	@ResponseBody
	public List<Admin> gettingData() {
		// System.out.println("getting data ");
		List<Admin> prod = webService.getUserList();
		return prod;
	}

// *************************************************************************************  //
	@RequestMapping("/register")
	public String doRegistration() {

		File f = new File(uploadProductDirectory);

		if (!f.exists()) {
			f.mkdirs();
		}
		// System.out.println("2nd call");
		return "admin/register";
	}

	// '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''//
	@RequestMapping("/recplog")
	public String receptionHomePage() {

		File f = new File(uploadProductDirectory);

		if (!f.exists()) {
			f.mkdirs();
		}
		// System.out.println("3rd call");
		return "admin/recplog";
	}

	// =======================================================//
	@RequestMapping("/attend")
	public String attendanceHTML() {

		File g = new File(uploadProductDirectory);

		if (!g.exists()) {
			g.mkdirs();
		}
		// System.out.println("3rd call");
		return "admin/attendance";
	}

	// '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''//
//  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$  Delete  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$//	
	@RequestMapping(value = "/deleteusers", method = RequestMethod.GET)
	@ResponseBody
	public void deleteUsers(Long id, Model model) {
		System.out.println("Are You Sure!!");
		webService.deleteUsers(id);
	}
//  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$//

	// ((((((((((((((((((((( Edit and Update Method For User )))))))))))))))))))))
	// //

	@RequestMapping(value = "/edituser", method = RequestMethod.GET)
	@ResponseBody
	public Admin editUser(Long id, Model model) {
		// System.out.println("do you want to edit");
		return this.webService.editUser(id);

	}

	////////////////////////////
	@RequestMapping(value = "/updateuser", method = RequestMethod.GET)
	@ResponseBody
	public void updateUser(Admin admin) {
		// System.out.println("updating " + admin);
		webService.updateUser1(admin);
	}

	@RequestMapping(value = "/fetch-all-usr-list", method = RequestMethod.GET) // 2//
	@ResponseBody // http request call
	public List<Admin> gettingData1() {
		// System.out.println("getting data ");
		return this.webService.getUserList1();
	}

	// ><<><><><><>><<><><><><><><><><>><<> edit method for assign data start
	// <><>><><><><><><<>><><><><><><><<>><<><><>//
	// ((((((((((((((((((((( Edit and Update Method For Assign Data
	// ))))))))))))))))))))) //

	@RequestMapping(value = "/editassigndata", method = RequestMethod.GET)
	@ResponseBody
	public AssignData editAssignData(Long id, Model m) {
		// System.out.println("do you want to edit");
		return this.webService.editAssignData(id);

	}

	////////////// Update//////////////
	@RequestMapping(value = "/updateassigndata", method = RequestMethod.GET)
	@ResponseBody
	public void updateAssignData(AssignData assigndata) {
		// System.out.println("updating " + admin);
		webService.updateAssignData1(assigndata);
	}

	@RequestMapping(value = "/fetch-all-assigndata-list", method = RequestMethod.GET)
	@ResponseBody // http request call
	public List<AssignData> gettingAssignData1() {
		// System.out.println("getting data ");
		return this.webService.getAssignDataList1();
	}

	// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Delete Assign Data
	// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$//
	@RequestMapping(value = "/deleteasdta", method = RequestMethod.GET)
	@ResponseBody
	public void deleteAssignData(Long id, Model model) {
		System.out.println("Are You Sure!!");
		webService.deleteAssignData(id);
	}
	// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$//

	// ^^^^^^^^^^^^^^ Log Out Method ^^^^^^^^^^^^^^^//
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("sign-in-user", null);

		String name = (String) session.getAttribute("username");

		// System.out.println(" logout "+name);
		session.invalidate();

		return "admin/login";

	}
	// ^^^^^^^^^^^^^^ Log Out Method ^^^^^^^^^^^^^^^//

	// ************* employee list in visitor ************//

	// ((((((((((assign data call))))))))))//

	@RequestMapping("/assigndata")
	public String assignData() {

		return "visitor/assigndata";
	}

	// -----------------------------//

	@RequestMapping(value = "/fetch-assigndata-list", method = RequestMethod.GET)
	@ResponseBody
	public List<AssignData> getAllEmployeeList(HttpSession session) {
		Long id = (Long) session.getAttribute("id");
		return webService.getAssignDataById(id);
	}

//============================================================================================//
	// Assign Data //
	@RequestMapping(value = "/fetch-assigndata-All-list", method = RequestMethod.GET)
	@ResponseBody
	public List<AssignData> getAllAssignedDataList(HttpSession session) {
		return webService.getAssignDataList();
	}
//   ^^^^^^^^^^^^^^^^^  Employee list Api ^^^^^^^^^^^^^^^^^^^//

	@RequestMapping(value = "/fetch-all-employee-list-in-visitor", method = RequestMethod.GET)
	@ResponseBody
	public List<Admin> getAllEmployeeList1() {
		return this.webService.getEmployeeList("Employee");
	}
// >>>>>>>>>>>>>>>>> Visitor List In Employee Api >>>>>>>>>>>>>>>>>//

	@RequestMapping(value = "/fetch-all-vistr-list-in-emp", method = RequestMethod.GET)
	@ResponseBody
	public List<Visitor> getAllVistorList() {
		// String role = "Visitor";
		return visitorService.getEmployeeList();
	}

	// &&&&&&&&&&Assign Data in visitor&&&&&&&&//
	@RequestMapping(value = "/save-data", method = RequestMethod.POST)
	@ResponseBody
	public AssignData saveData(AssignData assigndata, HttpSession session) throws IOException {
		Long recid = (Long) session.getAttribute("id");

		java.util.Date date1 = new java.util.Date(System.currentTimeMillis());
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		String date = formatter1.format(date1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
		String datetime = formatter.format(date1);
		// assigndata.setDate(date);

		// assigndata.setTime(time);
		assigndata.setRecpid(recid);
		Admin name = webService.getEmployeeName(assigndata.getEmpid());
		assigndata.setEmpname(name.getName());
		// product.setUpdatedName(session.getAttribute("sign-in-user").toString());
		webService.saveData(assigndata);
		return assigndata;
	}

	// $$$$$$$$$$$$$$$$$ EMPLOYEE HTML PAGE CALL $$$$$$$$$$$$$$$$$$$$$$//
	@RequestMapping("/emppage")
	public String emptable() {

		return "visitor/employee";
	}

	// @@@@@@@@@@@@@@@@@@@@@@@@ In emp.html edit modal@@@@@@@@@@@@@@@@@@@@@@@@@@@//
	@RequestMapping(value = "/popup1", method = RequestMethod.GET)
	@ResponseBody
	public Admin popUp1(Long id, Model model) {
		// System.out.println("do you want to edit");
		return this.webService.popUp1(id);

	}
	// @@@@@@@@@@@@@@@@@@@@@@@@ In emp.html assign data
	// list@@@@@@@@@@@@@@@@@@@@@@@@@@@//

	@RequestMapping(value = "/get-assigned-data-list", method = RequestMethod.GET)
	@ResponseBody
	public AssignData getAssignedDataList(Long id) {

		return webService.getAssignedDataList(id);
	}

	@RequestMapping(value = "/update2-assigned-data", method = RequestMethod.GET)
	@ResponseBody
	public void updateUser(AssignData assigndata) {
		// System.out.println("updating " + admin);
		webService.updateAssignedData(assigndata);
	}

	@RequestMapping(value = "/get-Pending-Ticket-Count", method = RequestMethod.GET)
	@ResponseBody
	public Integer getPendingTicketCount(HttpSession session) throws IOException {
		Long employeeid = (Long) session.getAttribute("id");
		return webService.getPendingTicketCount("Pending", employeeid);
	}

	// (((((((((((((((((((((((((((/get-day-Ticket-Count/)))))))))))))))))))))))))))//
	@RequestMapping(value = "/get-all-dashboard-count", method = RequestMethod.GET)
	@ResponseBody
	public DashBoardJsonResponse getdayTicketCount(HttpSession session) throws IOException {
		java.util.Date date1 = new java.util.Date(System.currentTimeMillis());
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		String date = formatter1.format(date1);
		DashBoardJsonResponse response = new DashBoardJsonResponse();
		response.setDailyMeeting(webService.getDateTicketCount(date));
		response.setWeeklyMeeting(webService.getWeekTicketCount());
		response.setMonthlyMeeting(webService.geMonthlyDataCount());
		response.setPendingMeeting(webService.gePendingDataCount());
		response.setCompleteMeeting(webService.geCompleteDataCount());
		return response;
	}

	/*
	 * // <<<<<<<<<<<<<<<<<<<<<<<< Get-Weekly Data Get <<<<<<<<<<<<<<<<<<<<<<<<<<>//
	 * 
	 * @RequestMapping(value = "/get-Weekly-Data-Count", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public Integer getweeklyDataCount(HttpSession session) throws
	 * IOException { return webService.getWeekTicketCount(); }
	 * 
	 * // <<<<<<<<<<<<<<<<<<<<<<<< Get-Monthly Data Get
	 * <<<<<<<<<<<<<<<<<<<<<<<<<<>//
	 * 
	 * @RequestMapping(value = "/get-Monthly-Data-Count", method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseBody public Integer getMonthlyDataCount(HttpSession session) throws
	 * IOException { return webService.geMonthlyDataCount(); }
	 * 
	 * // <<<<<<<<<<<<<<<<<<<<<<<< Get-pending Data Get
	 * <<<<<<<<<<<<<<<<<<<<<<<<<<>//
	 * 
	 * @RequestMapping(value = "/get-Pending-Data-Count", method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseBody public Integer getPendingDataCount(HttpSession session) throws
	 * IOException { return webService.gePendingDataCount(); }
	 * 
	 * // <<<<<<<<<<<<<<<<<<<<<<<< Get-complete Data Get
	 * <<<<<<<<<<<<<<<<<<<<<<<<<<>//
	 * 
	 * @RequestMapping(value = "/get-Complete-Data-Count", method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseBody public Integer getCompleteDataCount(HttpSession session) throws
	 * IOException { return webService.geCompleteDataCount(); }
	 */
// ========================================================================//
	// Email Calling Method Start Here//

	@RequestMapping(value = "/sendEMail", method = RequestMethod.POST)
	@ResponseBody
	public AppJsonResponse sendEmail(Mail sendmail, BindingResult bindingResult, HttpSession session)
			throws IOException {

		java.util.Date date1 = new java.util.Date(System.currentTimeMillis());
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
		String date = formatter1.format(date1);

		System.out.println("          1         " + sendmail);
		String mail = "pritithakur01@gmail.com";
		System.out.println("mail testing  2  " + mail);
		Mail mail1 = new Mail();
		System.out.println("mail  3  " + mail1);
		int result1 = mail1.sendMail(sendmail.getSubject(), mail, sendmail.getMessage(), sendmail.getName(),
				sendmail.getMail());

		sendmail.setDate(date);
		System.out.println("Check Result   4 " + result1);

		WebService.saveEmail(sendmail);

		AppJsonResponse resp = new AppJsonResponse();
		resp.setMessage("mail send");
		resp.setStatus("True");
		return resp;
	}

	// <<<<<<<<<<<<<<<<<<<<<<<< Profile Html Page <<<<<<<<<<<<<<<<<<<<<<<<<<>//
	@RequestMapping("/profile")
	public String profilePage(HttpSession session, Model model) {
		model.addAttribute("profile", webService.gettingDataById((Long) session.getAttribute("id")));
		return "admin/common/profile";
	}

	// <<<<<<<<<<<<<<<<<<<<<<<< Camera <<<<<<<<<<<<<<<<<<<<<<<<<<>//

	@RequestMapping("/camera")
	public String takePhoto() {

		return "admin/camera";
	}
	// <<<<<<<<<<<<<<<<<<<<<<<< Upload Camera Image <<<<<<<<<<<<<<<<<<<<<<<<<>//

	@RequestMapping(value = "/updating-image", method = RequestMethod.POST)
	@ResponseBody
	public Visitor updatingImage(Visitor visitor, @RequestParam("images") MultipartFile image, HttpSession session)
			throws IOException {
		System.out.println("Save image " + image + "user");
		Long id = (Long) session.getAttribute("id");
		if (!image.isEmpty() && image != null) {

			String dateName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
			String originalFileName = dateName + "-" + image.getOriginalFilename().replace(" ", "-").toLowerCase();

			Path fileNameAndPath = Paths.get(uploadProductDirectory, originalFileName);

			try {
				Files.write(fileNameAndPath, image.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			 visitor.setImage(originalFileName);
		} else {

			// Product product3 = productService.getProductDetailsById(product.getId());
			// product.setImage3(product3.getImage3()); admin.setImage(""); } //
			// productService.savingProduct(product); return admin;

		}
		// return null;
		return visitorService.savePhoto(visitor);

	}

//===================== methods for  attendance==========================//

	@RequestMapping(value = "/saveAttendance", method = RequestMethod.POST)
	@ResponseBody
	public Attendance saveAttendance(Attendance attend, HttpSession session) throws IOException { // System.out.println("attendance"
																									// +attendance);
		webService.saveAttendance(attend);
		return attend;
	}

	// ---------------------------------------------------------------------------

	/*
	 * @RequestMapping(value = "/fetch-attendance-list", method = RequestMethod.GET)
	 * 
	 * @ResponseBody // http request call public
	 * List<Attendance>gettingAttendance1() { //
	 * System.out.println("getting data "); return
	 * this.webService.getAttendanceList();
	 * 
	 * }
	 */

	// ;;;;;attendance data get method;;;;;;//
	@RequestMapping(value = "/fetch-attendance-list", method = RequestMethod.GET)
	@ResponseBody // http request call
	public List<Attendance> gettingAttendance() { // System.out.println("getting data "); return

		return this.webService.getAttendanceList();
	}

//====================================================================================================================
	//Attendance check in check out methods starts from here//
	@RequestMapping(value = "/checkIn", method = RequestMethod.GET)
	@ResponseBody
	public String checkInUser(String status, HttpSession session) {

		System.out.println("@@@@@@@@@@@@@@@@@@@@  " + session.getAttribute("username"));
		Attendance attend = new Attendance();
		if (session.getAttribute("username") != null) {
			System.out.println("))))))))))))  ");
			String employeeName = (String) session.getAttribute("username");
			String email = (String) session.getAttribute("sign-in-userEmail");
			Long id = (Long) session.getAttribute("id");

			java.util.Date date2 = new java.util.Date(System.currentTimeMillis());
			SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatter3 = new SimpleDateFormat("hh:mm:ss a");

			attend.setUserId(id);
			attend.setEmployeeName(employeeName);
			attend.setEmail(email);

			String date = formatter2.format(date2);
			String checkIn = formatter3.format(date2);

			System.out.println("check ");
			// System.out.println(" cheking email "+email);

			attend.setEmployeeName(employeeName);
			attend.setEmail(email);
			attend.setDate(date);
			// attend.setCheckIn(checkIn);

			System.out.println("check" + id);

			Attendance datecheck = webService.loginCheck(date, id);
			System.out.println("!!!!!!!!!!!   " + datecheck + "       staus   " + status);
			// System.out.println(id != datecheck);
			if (status.equals("checkin")) {
				System.out.println("111111111");
				if (datecheck == null) {
					System.out.println("check innn  " + datecheck);
					attend.setCheckIn(checkIn);
					String checkOut = "00:00:00";
					attend.setCheckOut(checkOut);
					System.out.println("if if");
					webService.saveAllattendance(attend);
					return "check in successfully";
				}
			}
			if (status.equals("checkout")) {
				System.out.println("check out 22222 " + datecheck);
				if (datecheck != null) {
					if (datecheck.getCheckOut().equals("00:00:00")) {
						System.out.println("check out");
						attend.setCheckIn(datecheck.getCheckIn());
						attend.setId(datecheck.getId());
						attend.setCheckOut(checkIn);
						webService.saveAllattendance(attend);
					} else {
						String resp="You Already Check Out";
						return resp;
					}
				}
			} else {
				System.out.println("abcdddd");
				return "You Already Checked in";
			}

		}
		return "checked out";
	}
	// -------------//

	@RequestMapping(value = "/checkOut", method = RequestMethod.GET)

	@ResponseBody // http request call
	public void checkOutUser(Attendance attend, HttpSession session) {

		java.util.Date date2 = new java.util.Date(System.currentTimeMillis());
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatter3 = new SimpleDateFormat("hh:mm:ss a");
		System.out.println("in check out");
		String date = formatter2.format(date2);
		String checkOut = formatter2.format(date2);
		Long id = (Long) session.getAttribute("id");

		webService.updateAllAttendance(checkOut, date, id);

	}
// check in check out methods end here //
	
	//((((((((((Image Method))))))))))))//
	@RequestMapping(value = "/getImage", method = RequestMethod.GET)
	@ResponseBody
	public AppJsonResponse addDestination(Visitor visitor, BindingResult bindingResult, HttpSession session,
			@RequestParam("image") MultipartFile image) throws IOException {

		// System.out.println(" comming "+desti.getDestination()+ " "+images+"
		// "+desti.getPrice());
		// Destination desti=new Destination();

		if (!image.isEmpty() && image.equals(image)) {
			System.out.println("    coming 1111   " + image);
			String dateName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
			String originalFileName = dateName + "-" + image.getOriginalFilename().replace(" ", "-").toLowerCase();

			Path fileNameAndPath = Paths.get(uploadProductDirectory, originalFileName);

			try {
				Files.write(fileNameAndPath, image.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}

			visitor.setImage(originalFileName);
		} else {

			visitor.setImage("");
		}
		// System.out.println(" desti.getImages "+desti.getImages());
		// desti.setPrice(price);
		// desti.setDestination(destination);
		visitorService.saveImage(visitor);

		AppJsonResponse resp = new AppJsonResponse();
		return resp;
	}
}