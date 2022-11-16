package com.ezio.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import com.ezio.model.Admin;
import com.ezio.model.Visitor;
import com.ezio.service.VisitorService;

@Controller
public class VisitorController {

	@Autowired
	VisitorService visitorService;

	@RequestMapping("/vstr")
	public String visitorTable() {

		return "visitor/visitor";
	}

//table data get//
	@RequestMapping(value = "/fetch-visitor-list", method = RequestMethod.GET)
	@ResponseBody // http request call
	public List<Visitor> gettingData() {
		// System.out.println("getting data ");
		return this.visitorService.getUserList();

	}

// save method
	@RequestMapping(value = "/saveVisitor", method = RequestMethod.POST)
	@ResponseBody
	public Visitor saveVisitor(Visitor visitor, HttpSession session) throws IOException {
		//System.out.println("visitor" + visitor);
		visitorService.saveService(visitor);
		return visitor;
	}

//delete method//
	@RequestMapping(value = "/deletevisitor", method = RequestMethod.GET)
	@ResponseBody
	public void deleteVisitor(Long id, Model model) {
		System.out.println("Are You Sure!!");
		visitorService.deleteVisitor(id);
	}
// delete method close//

	//edit method start//
	@RequestMapping (value = "/editvisitor", method = RequestMethod.GET)
	@ResponseBody
	public Visitor editVisitor(Long id, Model model) {
		System.out.println("editing");
		return this.visitorService.editVisitor(id);
	}
	
	//update method start//
	@RequestMapping(value = "/updatevisitor", method = RequestMethod.POST)
	@ResponseBody
	public void updateVisitor(Visitor visitor,HttpSession session) {
		//System.out.println("updating   " + admin);
		visitorService.updateVisitor(visitor);
	}
	
// edit and update method end //
	
	//  employee list in visitor //
	
	//((((((((((((((((((((((((((((((((((((((camera method))))))))))))))))))))))))))))))))))))))//
	/*
	 * @RequestMapping(value = "/updating-image", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public Visitor updatingImage(Visitor visitor, BindingResult
	 * bindingResult, @RequestParam("image") MultipartFile image, HttpSession
	 * session, String uploadProductDirectory) throws IOException {
	 * System.out.println("Save image " + image); Long id = (Long)
	 * session.getAttribute("id"); if (!image.isEmpty() && image != null) {
	 * 
	 * String dateName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
	 * String originalFileName = dateName + "-" +
	 * image.getOriginalFilename().replace(" ", "-").toLowerCase();
	 * 
	 * Path fileNameAndPath = Paths.get(uploadProductDirectory, originalFileName);
	 * 
	 * try { Files.write(fileNameAndPath, image.getBytes()); } catch (IOException e)
	 * { e.printStackTrace(); } visitor.setImage(originalFileName); } else {
	 * 
	 * // Product product3 = productService.getProductDetailsById(product.getId());
	 * // product.setImage3(product3.getImage3()); visitor.setImage(""); } //
	 * productService.savingProduct(product); return visitor;
	 * 
	 * }
	 */
}
