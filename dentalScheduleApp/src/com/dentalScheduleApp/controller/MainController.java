package com.dentalScheduleApp.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dentalScheduleApp.entities.Appointment;
import com.dentalScheduleApp.entities.DentalOffice;
import com.dentalScheduleApp.entities.Hygienist;
import com.dentalScheduleApp.entities.User;
import com.dentalScheduleApp.services.AppointmentService;
import com.dentalScheduleApp.services.DentalOfficeService;
import com.dentalScheduleApp.services.HygienistService;
import com.dentalScheduleApp.services.UserService;

@Controller
//@SessionAttributes("user")
public class MainController {

//	@ModelAttribute("user")
//	public User setUpUser() {
//		return new User();
//	}

	@RequestMapping("/")
	public ModelAndView welcome() {
		System.out.println("--- Inside of get / - aka our index! ---- ");
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@Valid @ModelAttribute("loginUser") User user, BindingResult result) {
		System.out.println("Inside of login - GET version");
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}

	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public ModelAndView getHomePage(HttpServletResponse response, HttpServletRequest request) {
		System.out.println("inside getHomePage method!");
		HttpSession session = request.getSession(false);
		String userName = (String) session.getAttribute("username");
		String passWord = (String) session.getAttribute("password");
		
		
		System.out.println("our usernaem in getHomePage method is: " + userName);
		System.out.println("our password in getHomePage method is: " + passWord);

		UserService userServ = new UserService();
		Long userId = userServ.getUserIdWithUserNameAndPwd(userName, passWord);

		AppointmentService apptServ = new AppointmentService();
		List<Long> listOfApptIds = apptServ.getListOfAppointmentIdsWithUsername(userName);

		ModelAndView mav = new ModelAndView("loginForm");
		mav.addObject("userId", userId);
		mav.addObject("apptList", apptServ.getListOfApptsByUsername(userName));
		session.setAttribute("userApptCount", listOfApptIds.size());
		return mav;
	}

	@RequestMapping(value = "/loginForm", method = RequestMethod.POST)
	public ModelAndView loginForm(@Valid @ModelAttribute("loginUser") User user, BindingResult result,
			@RequestParam(value = "username") String username, @RequestParam(value = "password") String password,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside loginForm method!");

		ModelAndView mav = new ModelAndView();
		System.out.println("username is: " + username);
		System.out.println("password is: " + password);

		UserService us = new UserService();

		HttpSession session = request.getSession();

		System.out.println("Before user validation...");
//		System.out.println("our user object: " + user);
//		System.out.println("our user name: " + user.getUsername());
//		System.out.println("our username lenght: " + user.getUsername().length());
//		System.out.println("Our user password is: " + user.getPassword());
//		System.out.println("our password lenght: " + user.getPassword().length());

		if (user != null && user.getUsername() != null && user.getPassword() != null && user.getUsername().length() > 0
				&& user.getPassword().length() > 0) {
			System.out.println("Made it past the first if!");
			if (us.validateUser(username, password)) {
				System.out.println("inside if AFTER validation..");
				session.setAttribute("username", username);
				session.setAttribute("password", password);
				
				UserService userServ = new UserService();
				Long userId = userServ.getUserIdWithUserNameAndPwd(username, password);
				session.setAttribute("userId", Long.toString(userId));
				
				AppointmentService apptServ = new AppointmentService();
				List<Long> listOfApptIds = apptServ.getListOfAppointmentIdsWithUsername(username);
				System.out.println("Our listOfApptIds is: " + listOfApptIds);
				session.setAttribute("userApptCount", listOfApptIds.size());
				
				//Next we need to loop thru the appointments Id list and make each editable (UPDATE / DELETE)
				mav.addObject("apptList", apptServ.getListOfApptsByUsername(username));
				
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
				response.setDateHeader("Expires", 0);
				response.setHeader("Pragma", "no-cache");
				// u.setName(username);
				// mav.addObject("username", username);
				// mav.addObject("password", password);
				// mav.addObject("user", u);
				// mav.setViewName("home");
				mav.setViewName("loginForm");

			} else {
				mav.setViewName("login");
				mav.addObject("loginStatus", "Username or password is wrong");
			}
		} else {
			mav.setViewName("login");
			mav.addObject("loginStatus", "Username or password is wrong");
		}

		return mav;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView getRegistrationForm() {
		System.out.println("inside of getRegistrationForm");
		HygienistService hygServ = new HygienistService();
		List<Hygienist> hygList = hygServ.getAllHygienists();

		DentalOfficeService dentalOfficeServ = new DentalOfficeService();
		List<DentalOffice> dentalOfficeList = dentalOfficeServ.getAllDentalOffices();

		ModelAndView mav = new ModelAndView("register");
		mav.addObject("registerUser", new User());
		mav.addObject("hygList", hygList);
		mav.addObject("dentalOfficeList", dentalOfficeList);
		return mav;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView doSubmitRegistrationForm(@Valid @ModelAttribute("registerUser") User user, BindingResult errors,
			HttpServletRequest request, RedirectAttributes attributes) {
		System.out.println("--Inside of doSubmitRegistrationForm--");
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView();
		HygienistService hygServ = new HygienistService();
		List<Hygienist> hygList = hygServ.getAllHygienists();

		DentalOfficeService dentalOfficeServ = new DentalOfficeService();
		List<DentalOffice> dentalOfficeList = dentalOfficeServ.getAllDentalOffices();

//		mav.addObject("hygList", hygList);
//		mav.addObject("dentalOfficeList", dentalOfficeList);
		
		String dentalOfficeValue = request.getParameter("dentalOfficeId");
		String dentalHygienistValue = request.getParameter("dentalHygienistId");
		String[] dentalHygienistChecks = request.getParameterValues("dentalHygienistId");

		System.out.println("DentalOfficeValue is: " + dentalOfficeValue);
		System.out.println("DentalOFficeValue from userObject: " + user.getPrimaryDentalOffice());

		System.out.println("HygienistValue is: " + dentalHygienistValue);

		if(!user.getPassword().contentEquals(user.getConfirmPassword())) {
			System.out.println("Passwords are not the same!");
			errors.rejectValue("confirmPassword", "error.confirmPassword", "Confirm Password must be the same as Password.");
		} else {
			System.out.println("Passwords are the same. We are good to go!");
		}
		
		if (dentalHygienistValue != null) {
			for (String hyg : dentalHygienistChecks) {
				System.out.println("Hyg: " + hyg);
			}
		}

		DentalOfficeService dentalOfficeService = new DentalOfficeService();
		DentalOffice userPickedOffice = dentalOfficeService.getOfficeById(Long.parseLong(dentalOfficeValue));

		List<Hygienist> userHyg = new ArrayList<>();
		HygienistService hygienistService = new HygienistService();

		if (dentalHygienistChecks != null) {
			for (String hyg : dentalHygienistChecks) {
				userHyg.add(hygienistService.getHygById(Long.parseLong(hyg)));
			}
		}
		user.setFavHygienists(userHyg);
		user.setPrimaryDentalOffice(userPickedOffice);

		for (Hygienist uFavHyg : user.getFavHygienists()) {
			System.out.println("IN OUR USER NOW --- Hyg: " + uFavHyg.getName());
		}

		if (errors.hasErrors()) {
			System.out.println("Inside of errors IF statement!");
			
			ModelAndView mav2 = new ModelAndView("register");
			mav2.addObject("hygList", hygList);
			mav2.addObject("dentalOfficeList", dentalOfficeList);
			return mav2;
			
//			return new ModelAndView("register");
			
//			ModelAndView mav = new ModelAndView();
//			mav.setViewName("redirect:/register");
//			return mav;
		}

		System.out.println("---");
		System.out.println("Creating a new user...");
//		ModelAndView mav = new ModelAndView();
		UserService userServ = new UserService();

		System.out.println("getUsername: " + user.getUsername());
		System.out.println("getAddress:" + user.getAddress());
		System.out.println("getName: " + user.getName());
		System.out.println("getPassword: " + user.getPassword());
		System.out.println("getPhoneNumber: " + user.getPhoneNumber());
		System.out.println("getFavHygienists: " + user.getFavHygienists());
		boolean result = userServ.registerUser(user.getUsername(), user.getAddress(), user.getName(),
				user.getPassword(), user.getPhoneNumber(), user.getPrimaryDentalOffice(), user.getFavHygienists());
		String message;

		if (result) {
			message = "User successfully created!";
			session.setAttribute("username", user.getUsername());
			session.setAttribute("password", user.getPassword());
		} else {
			message = "Did not create user. Username may already exists. Try again.";
		}

		System.out.println("**Our result from our registerUser service is: " + result);
		mav.addObject("isUserCreated", message);
		mav.addObject("hygList", hygList);
		mav.addObject("dentalOfficeList", dentalOfficeList);
		mav.setViewName("register");
		return mav;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logoutUser(HttpServletResponse response, HttpServletRequest request) {
		System.out.println("Inside logoutUser");

		HttpSession session = request.getSession(false);

		String test = (String) session.getAttribute("username");
		System.out.println("test is: " + test);
		if (session.getAttribute("username") != null) {
			System.out.println("Inside of if statement");
			Enumeration<String> en = session.getAttributeNames();
			if (en == null) {
				System.out.println("ENUMERATION is null");
			} else {
				System.out.println("Enumeration is NOT null. What's in it? ");
				while (en.hasMoreElements()) {
					String attrName = en.nextElement();
					System.out.println("our attrName is: " + attrName);
					session.removeAttribute(attrName);
					session.setAttribute(attrName, null);
//					request.getSession(false);
				}
			}
//			session.removeAttribute(attrName);
			session.invalidate();

		}
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setDateHeader("Expires", 0);
		response.setHeader("Pragma", "no-cache");
//		System.out.println("test at the bottom of logOutUser is: " + test);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/schedule-appointment/user/{userId}", method = RequestMethod.POST)
	public ModelAndView postScheduleAppointment(@Valid @ModelAttribute("appointmentForm") Appointment appt,
			BindingResult errors, HttpServletRequest request) {
		System.out.println("--Inside of postScheduleAppointment--");
		HttpSession session = request.getSession();
		
		//1. Make sure we are getting data from date + time picker
		System.out.println("**Request get param of date picker: " + request.getParameter("datePicker"));
		System.out.println("**Request get param of time picker: " + request.getParameter("timePicker"));

		// To put into db we need this format: YYYY-MM-DD
		AppointmentService apptServ = new AppointmentService();
		List<Long> testList = apptServ.getListOfAppointmentIdsWithUsername((String) session.getAttribute("username"));
		System.out.println("Our list of appointments the user has: " + testList);

		//1. we need to have username to insert appt to APPOINTMENT table
		System.out.println("session get attribute of username: " + session.getAttribute("username"));
		
		//2. we need to convert date (currently a string) to a DATE object to pass to AppointmentService
		
		String dateString = request.getParameter("datePicker");
		System.out.println("Our dateString from requet.GetParams is: " + dateString);
		Date dateObj = null;
		try {
			dateObj = new SimpleDateFormat("MM/dd/yyyy").parse(dateString);
			System.out.println("Our dateObj is: " + dateObj);
		} catch (ParseException e) {
			System.out.println("Inside catch block when we try to format date!");
			e.printStackTrace();
		} 
		
		//3. Converting time (currently as a string) to a Date object to pass to AppointmentService
//		String dateTimeString = "06:30:00";
		String dateTimeString = request.getParameter("timePicker");
		Date dateTimeObj = null;
		try {
			dateTimeObj = new SimpleDateFormat("hh:mm a").parse(dateTimeString);
			System.out.println("Our dateTimeObj is: " + dateTimeObj);
		} catch(ParseException e) {
			System.out.println("Inside catch block when we try to format DATETIME!");
			e.printStackTrace();
		}
		
		
		boolean savedToDatebase = apptServ.addAppointment(dateObj, "hard-coded-hygiene-name", (String)session.getAttribute("username"), dateTimeObj);
		if(!savedToDatebase) {
			System.out.println("Did not save appointment to database");
		} else {
			System.out.println("Successfully saved to database.");
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/loginForm");
		return mav;
	}

	@RequestMapping(value="/schedule-appointment/user/{userId}",method=RequestMethod.GET)
	public ModelAndView getScheduleAppointment(@Valid @ModelAttribute("appointmentForm") Appointment appt,
			BindingResult errors, @PathVariable String userId) {
		System.out.println("--Inside of getScheduleAppointment/user/userid--");
		return new ModelAndView("add-appointment");
	}
	
	@RequestMapping(value="/edit-profile/user/{username}/{userId}", method=RequestMethod.GET)
	public ModelAndView getEditProfile(@PathVariable String userId, @PathVariable String username) {
		System.out.println("--Inside of getEditProfile/user/userid--");
		
		Long userIdLong = Long.parseLong(userId);
		
		UserService uServ = new UserService();
		User foundUser = uServ.getUserById(userIdLong);	
		
		DentalOffice currentDentalOffice = uServ.getUserPrimOfficeById(userIdLong);
		Long currentDentalOfficeId = currentDentalOffice.getId();
		
		List<Hygienist> listOfHygienistsForCurrentUser = uServ.getListOfFavoriteHygienistById(userIdLong);
		
		DentalOfficeService dentalOfficeServ = new DentalOfficeService();
		List<DentalOffice> allDentalOffices = dentalOfficeServ.getAllDentalOffices();
		DentalOffice currentDentalOffice2 = dentalOfficeServ.getOfficeById(currentDentalOfficeId);
		
		HygienistService hygServ = new HygienistService();
		List<Hygienist> hygList = hygServ.getAllHygienists();
		
		System.out.println("foundUser name is: " + foundUser.getName());
		ModelAndView mav = new ModelAndView("edit-profile");
		mav.addObject("user", foundUser);
		mav.addObject("dentalOffices", allDentalOffices);
		mav.addObject("currentDentalOffice", currentDentalOffice2);
		mav.addObject("currentListOfHygienists", listOfHygienistsForCurrentUser);
		mav.addObject("allHygienists", hygList);
		return mav;
	}
	
	@RequestMapping(value="/edit-profile/user/{username}/{userId}", method=RequestMethod.POST)
	public ModelAndView postEditProfile(@PathVariable String userId, @PathVariable String username, @ModelAttribute User user, HttpServletRequest request) {
		System.out.println("--Inside of postEditProfile/user/userid--");

		System.out.println("Username from path variable: " + username);
		boolean result = true;
		Long userIdLong = Long.parseLong(userId);
		
		UserService uServ = new UserService();
		if( user != null) {
			System.out.println("user model attribute is NOT null");
			result = uServ.updateUser(user);
		}
		
		
		String message = result ? "User Updated: " + user.getId() : "User not updated.";
		System.out.println("Message is: " + message);
		
		User foundUser = uServ.getUserById(userIdLong);	
		
		if(result) {
			System.out.println("--- RESULT IS TRUE ----");
			AppointmentService apptServ = new AppointmentService();
			List<Long> currUserApptIds = apptServ.getListOfAppointmentIdsWithUsername(username);
			System.out.println("Our currUserApptIds is: " + currUserApptIds);
			for(int x = 0; x < currUserApptIds.size(); x++) {
				apptServ.updateApptUsernameById(currUserApptIds.get(x), foundUser.getUsername());
			}
			HttpSession session = request.getSession(false);
			String usernameInSession = (String)session.getAttribute("username");
			System.out.println("username in session is currently: " + usernameInSession);
			
//			System.out.println("foundUser.getUserName is: " + foundUser.getUsername());
			session.setAttribute("username", foundUser.getUsername());
			String usernameInSessionUpdated = (String)session.getAttribute("username");
			System.out.println("username in session updated is currently: " + usernameInSessionUpdated);
		}
		
		
		
		DentalOffice currentDentalOffice = uServ.getUserPrimOfficeById(userIdLong);
		Long currentDentalOfficeId = currentDentalOffice.getId();
		
		DentalOfficeService dentalOfficeServ = new DentalOfficeService();
		List<DentalOffice> allDentalOffices = dentalOfficeServ.getAllDentalOffices();
		DentalOffice currentDentalOffice2 = dentalOfficeServ.getOfficeById(currentDentalOfficeId);
		
		List<Hygienist> listOfHygienistsForCurrentUser = uServ.getListOfFavoriteHygienistById(userIdLong);

		HygienistService hygServ = new HygienistService();
		List<Hygienist> hygList = hygServ.getAllHygienists();
		
		ModelAndView mav = new ModelAndView();
//		mav.addObject("messageResult", message);
		mav.addObject("dentalOffices", allDentalOffices);
		mav.addObject("currentDentalOffice", currentDentalOffice2);
		mav.addObject("currentListOfHygienists", listOfHygienistsForCurrentUser);
		mav.addObject("allHygienists", hygList);
//		mav.setViewName("redirect:/loginForm");
//		mav.setViewName("redirect:/edit-profile/user/{username}/{userId}");
		mav.setViewName("redirect:/loginForm");
		return mav;
	}
	

	@RequestMapping(value="/cancel-appt/{apptId}", method=RequestMethod.GET)
	public ModelAndView cancelUserAppt(@PathVariable String apptId) {
		System.out.println("Canceling user's appt...");
		System.out.println("Our appointment id is: " + apptId);
		
		//1. Convert appointment id string to long
		Long apptIdLong = Long.parseLong(apptId);
		
		//2. Set up AppointmentService to inititate deletion
		AppointmentService apptServ = new AppointmentService();
		boolean isApptDeleted = apptServ.deleteAppointmentById(apptIdLong);
		
		//3. Check to see what we get from the appointmentService
		//	This can be deleted / commented after everything is working.
		if(!isApptDeleted) {
			System.out.println("Appointment has NOT been deleted");
		} else {
			System.out.println("Appointment has been deleted!");
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/loginForm");
		return mav;
	}
	
	@RequestMapping(value="/update-appt/{apptId}", method=RequestMethod.GET)
	public ModelAndView updateUserAppt(@PathVariable String apptId) {
		System.out.println("Inside of updateUserAppt");
		
		//1. Convert appointment id string to long
		Long apptIdLong = Long.parseLong(apptId);
				
		//1. Get appointment by id
		AppointmentService apptServ = new AppointmentService();
		Appointment appt = apptServ.getAppointmentById(apptIdLong);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("currentAppt",appt);
		mav.setViewName("edit-appointment");
		return mav;
	}
	
	@RequestMapping(value="/update-appt/{apptId}", method=RequestMethod.POST)
	public ModelAndView postUpdateUserAppt(@PathVariable String apptId, HttpServletRequest request) {
		System.out.println("Inside of postUpdateUserAppt");
		System.out.println("**Request get param of date picker: " + request.getParameter("datePicker"));
		
		//1. Convert appointment id string to long
		Long apptIdLong = Long.parseLong(apptId);
		
		//2. Convert request datePicker from String to Date Object
		String newDateString = request.getParameter("datePicker");
		System.out.println("Our dateString from requet.GetParams is: " + newDateString);
		Date updatedDateObj = null;
		try {
			updatedDateObj = new SimpleDateFormat("MM/dd/yyyy").parse(newDateString);
			System.out.println("Our updatedDateObj is: " + updatedDateObj);
		} catch (ParseException e) {
			System.out.println("Inside catch block when we try to format date!");
			e.printStackTrace();
		} 
		
		//3. pass new date object to our Appointment service to update DB
		AppointmentService apptServ = new AppointmentService();
		boolean savedToDatebase = apptServ.updateAppointmentDateById(apptIdLong, updatedDateObj);
		if(!savedToDatebase) {
			System.out.println("Did not save appointment to database");
		} else {
			System.out.println("Successfully saved to database.");
		}
		

		
		ModelAndView mav = new ModelAndView();
//		mav.addObject("currentAppt",appt);
		mav.setViewName("redirect:/loginForm");
		return mav;
	}
	
	@RequestMapping(value="/edit-profile/user/primary-dental-office/{userId}", method=RequestMethod.GET)
	public ModelAndView getEditProfilePrimaryDentalOffice(@PathVariable String userId) {
		System.out.println("--Inside of getEditProfilePrimaryDentalOffice--");
		
//		Long userIdLong = Long.parseLong(userId);
//		
//		UserService uServ = new UserService();
//		User foundUser = uServ.getUserById(userIdLong);	
//		
//		DentalOffice currentDentalOffice = uServ.getUserPrimOfficeById(userIdLong);
//		Long currentDentalOfficeId = currentDentalOffice.getId();
//		
//		DentalOfficeService dentalOfficeServ = new DentalOfficeService();
//		List<DentalOffice> allDentalOffices = dentalOfficeServ.getAllDentalOffices();
//		DentalOffice currentDentalOffice2 = dentalOfficeServ.getOfficeById(currentDentalOfficeId);
//		
//		
//		System.out.println("foundUser name is: " + foundUser.getName());
		ModelAndView mav = new ModelAndView("edit-profile");
//		mav.addObject("user", foundUser);
//		mav.addObject("dentalOffices", allDentalOffices);
//		mav.addObject("currentDentalOffice", currentDentalOffice2);
		return mav;
	}
	
	
	@RequestMapping(value="/edit-profile/user/primary-dental-office/{userId}", method=RequestMethod.POST)
	public ModelAndView postEditProfilePrimaryDentalOffice(@PathVariable String userId, HttpServletRequest request) {
		System.out.println("--Inside of postEditProfile/user/primary-dental-office/userid--");
		
		String dentalOfficeValue = request.getParameter("dentalOfficeId");
		System.out.println("Our dentalOfficeValue is: " + dentalOfficeValue);
		boolean result = false;
		
		UserService uServ = new UserService();
		if( Integer.parseInt(dentalOfficeValue) != 0) {
			System.out.println("dentalOfficeValue is NOT equal to 0");
			result = uServ.updateUserPrimaryDental(Long.parseLong(userId), Long.parseLong(dentalOfficeValue));
		}

		String message = result ? "User Primary Office updated: " : "User Primary office not updated.";
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("messageResult2", message);
		mav.setViewName("redirect:/edit-profile/user/{userId}");
//		mav.setViewName("edit-profile");
		return mav;
	}
	
	@RequestMapping(value="/edit-profile/user/favorite-hygienist/{userId}", method=RequestMethod.POST)
	public ModelAndView postEditProfileFavHygienist(@PathVariable String userId, HttpServletRequest request) {
		System.out.println("--Inside of postEditProfileFavHygienist--");
		
		String[] dentalHygienistChecks = request.getParameterValues("dentalHygienistId");
		
		List<Hygienist> userHyg = new ArrayList<>();
		HygienistService hygienistService = new HygienistService();
		
		System.out.println("our userHyg at the start: " + userHyg);
		System.out.println("Our dentalHygienistChecks: " + dentalHygienistChecks);
		
		
		if (dentalHygienistChecks != null) {
			for (String hyg : dentalHygienistChecks) {
				userHyg.add(hygienistService.getHygById(Long.parseLong(hyg)));
			}
		}
		System.out.println("our userHyg at the end: " + userHyg);
	
		UserService uServ = new UserService();
		
		if(userHyg != null) {
			System.out.println("UserHyg is not null!");
			for(Hygienist hyg : userHyg) {
				uServ.addToUsersFavHygList(Long.parseLong(userId), hyg.getId());
			}
		}		
		ModelAndView mav = new ModelAndView("redirect:/edit-profile/user/{userId}");
		return mav;
	}
	
	@RequestMapping(value="/edit-profile/user/remove-hygienist/{userId}", method=RequestMethod.POST)
	public ModelAndView postRemoveHygienist(@PathVariable String userId, HttpServletRequest request) {
		System.out.println("--Inside of postRemoveHygienist--");
		
		String dentalHygienistCheckId = request.getParameter("hygienistId");
		System.out.println("dentalHygienistCheck is: " + dentalHygienistCheckId);
		
		UserService uServ = new UserService();
		
		boolean result = uServ.deleteHygFromListById(Long.parseLong(userId), Long.parseLong(dentalHygienistCheckId));
		if(!result) {
			System.out.println("We have not deleted hygienst from list..");
		} else {
			System.out.println("We have deleted hyginiest successfully from the list!");
		}
		
		ModelAndView mav = new ModelAndView("redirect:/edit-profile/user/{userId}");
		return mav;
	}

	@RequestMapping(value="/edit-profile/user/delete-profile/{userId}", method=RequestMethod.GET)
	public ModelAndView getDeleteUserAccount(@PathVariable String userId, HttpServletRequest request, SessionStatus status) {
		System.out.println("---Inside of getDeleteUserAccount!---");
		
		//1. Get username with id
		UserService uServ = new UserService();
		User currentUser = uServ.getUserById(Long.parseLong(userId));
		
		//2. Get all ids for user's appointments using username
		AppointmentService apptServ = new AppointmentService();
		List<Long> apptIdList = apptServ.getListOfAppointmentIdsWithUsername(currentUser.getUsername());
		
		//3. Delete all of user's appointments using appt id list
		boolean resultAppts = apptServ.deleteAllUserAppointments(apptIdList);
		
		if(!resultAppts) {
			System.out.println("Result is false. We have not deleted appointments!");
		} else {
			System.out.println("Result is true. We have successfully deleted all appointments!");
		}
		
		//3. Delete user with id
		boolean resultUser = uServ.deleteUserAcctById(Long.parseLong(userId));
		
		if(!resultUser) {
			System.out.println("Result is false. We have not deleted user!");
		} else {
			System.out.println("Result is true. We have successfully deleted user!");
		}
		
		//4. Next we need to set the correct 
		//register redirect / forward / or new jsp page with no session / request attributes
		
		ModelAndView mav = new ModelAndView("redirect:/");
		status.setComplete();
		HttpSession session = request.getSession(false);
		
		session.setAttribute("username", null);
		session.setAttribute("password", null);
		
		
		System.out.println("our usernaem in getHomePage method is: " + session.getAttribute("username"));
		System.out.println("our password in getHomePage method is: " + session.getAttribute("password"));
		
		return mav;
	}
	
}
