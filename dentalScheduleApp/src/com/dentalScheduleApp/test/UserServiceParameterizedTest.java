package com.dentalScheduleApp.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import com.dentalScheduleApp.entities.DentalOffice;
import com.dentalScheduleApp.entities.User;
import com.dentalScheduleApp.services.UserService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Parameterized.class)
public class UserServiceParameterizedTest {

	User expected;
	static UserService userServ = null;
	
	public UserServiceParameterizedTest(User expected) {
		super();
		this.expected = expected;
	}
	
	@SuppressWarnings("rawtypes")
	@Parameterized.Parameters
	public static Collection params() {
		userServ = new UserService();
		return Arrays.asList(new Object[][] {
			{new User((long) 1, "usernametest", "nametest", "passwordtest", "addresstest", "phonenumbertest", userServ.getUserPrimOfficeById((long) 1), null, null)}
		});
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userServ = new UserService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void AtestRegisterUser() {
		System.out.println("1. Testing Register user");
		DentalOffice newDentalOffice = new DentalOffice((long)1, "Tremont Dental Care", "222-333-444", "635 Tremont St.", null);
		boolean result;
		User newUser = new User((long)2,"des71", "desmond", "1234", "222mainave", "617-222-2222", newDentalOffice, null, null);
		result = userServ.registerUser(newUser.getUsername(), newUser.getAddress(), newUser.getName(), newUser.getPassword(), newUser.getPhoneNumber(), newUser.getPrimaryDentalOffice(), newUser.getFavHygienists());
		System.out.println("result is: " + result);
		assertTrue(result);
	}
	
	@Test
	public final void BtestValidateUser() {
		System.out.println("2. Testing validateUser method...");
		String username = "des71";
		String password = "1234";
		boolean actual = userServ.validateUser(username, password);
		System.out.println("our actual is: " + actual);
		assertTrue(actual);
		
	}
	
	@Test
	public final void CtestGetUserFavHygienists() {
		
	}

	@Test
	public final void testGetUserAppointments() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetUserByUsername() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetAllUsers() {
		fail("Not yet implemented"); // TODO
	}


	@Test
	public final void testScheduleUserAppointment() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testRegisterUserToHygienist() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetUserById() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetLastItemId() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetUserPrimOfficeById() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetUserIdWithUserNameAndPwd() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testUpdateUserPrimaryDental() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetListOfFavoriteHygienistById() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testAddToUsersFavHygList() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testDeleteHygFromListById() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testEditUserNameWithId() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testEditUserAddressWithId() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testEditFirstNameWithId() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testEditUserPhoneNumberWithId() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCheckCurrPassword() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testEditUserPwdWithId() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testDeleteUserAcctById() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testUpdateUser() {
		fail("Not yet implemented"); // TODO
	}

}
