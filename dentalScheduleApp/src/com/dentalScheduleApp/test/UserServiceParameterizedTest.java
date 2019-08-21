package com.dentalScheduleApp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
import com.dentalScheduleApp.entities.Hygienist;
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
		return Arrays.asList(new Object[][] { { new User((long) 1, "usernametest", "nametest", "passwordtest",
				"addresstest", "phonenumbertest", userServ.getUserPrimOfficeById((long) 1), null, null) } });
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
		DentalOffice newDentalOffice = new DentalOffice((long) 1, "Tremont Dental Care", "222-333-444",
				"635 Tremont St.", null);
		boolean result;
		User newUser = new User((long) 2, "des71", "desmond", "1234", "222mainave", "617-222-2222", newDentalOffice,
				null, null);
		result = userServ.registerUser(newUser.getUsername(), newUser.getAddress(), newUser.getName(),
				newUser.getPassword(), newUser.getPhoneNumber(), newUser.getPrimaryDentalOffice(),
				newUser.getFavHygienists());
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
	public final void CtestGetUserByUsername() {
		System.out.println("3. Testing getUserByUsername...");
		User actual = userServ.getUserByUsername("des71");
		assertEquals("des71", actual.getUsername());
	}

	@Test
	public final void DtestGetAllUsers() {
		System.out.println("4. Testing getAllusers...");
		int expectedUserCount = 2;
		int actualUserCount = userServ.getAllUsers().size();

		System.out.println("expecteduserCount: " + expectedUserCount);
		System.out.println("actualtUserCount: " + actualUserCount);

		assertEquals(expectedUserCount, actualUserCount);
	}

	@Test
	public final void EtestGetUserById() {
		System.out.println("5. Testing getUserById...");
		User currUser = userServ.getUserById((long) 1);
		System.out.println("currUser.getUsername is: " + currUser.getUsername());
		assertEquals("destest", currUser.getUsername());
	}

	@Test
	public final void FtestGetUserPrimOfficeById() {
		System.out.println("6. Testing getUserPrimOfficeById...");
		DentalOffice currUserDentalOffice = userServ.getUserPrimOfficeById((long) 1);
		System.out.println("currUserDentalOffice.getId(): " + currUserDentalOffice.getId());
		assertEquals("1", currUserDentalOffice.getId().toString());
	}

	@Test
	public final void GtestGetUserIdWithUserNameAndPwd() {
		System.out.println("7. Testing getUserIdWithUserNameAndPwd...");
		String username = "des71";
		String pwd = "1234";

		Long actualUserId = userServ.getUserIdWithUserNameAndPwd(username, pwd);
		Long expectedUserId = userServ.getUserById(actualUserId).getId();

		System.out.println("acutalUserId: " + actualUserId);
		System.out.println("expectedUserId: " + expectedUserId);
		assertEquals(expectedUserId, actualUserId);
	}

	@Test
	public final void HtestUpdateUserPrimaryDental() {
		System.out.println("8. Testing UpdateUserPrimaryDental...");

		String username = "des71";
		String pwd = "1234";

		// This will change it from initial primary dental office id 1 to 3.
		boolean actual = userServ.updateUserPrimaryDental(userServ.getUserIdWithUserNameAndPwd(username, pwd),
				(long) 3);
		System.out.println("Our actual is: " + actual);
		assertTrue(actual);
	}

	@Test
	public final void ItestGetListOfFavoriteHygienistById() {
		System.out.println("9. Testing GetListOfFavoriteHygienistById...");
		String username = "des71";
		String pwd = "1234";

		// This should be null bc we have not added any hygienists to the initialized
		// user!
		List<Hygienist> listOfUsersFavHygienist = userServ
				.getListOfFavoriteHygienistById(userServ.getUserIdWithUserNameAndPwd(username, pwd));

		System.out.println("listOfUsersFavHygienist is: " + listOfUsersFavHygienist.size());
		assertEquals(0, listOfUsersFavHygienist.size());
	}

	@Test
	public final void JtestAddToUsersFavHygList() {
		System.out.println("10. Testing AddToUsersFavHygList...");

		String username = "des71";
		String pwd = "1234";

		long currUserId = userServ.getUserIdWithUserNameAndPwd(username, pwd);
		boolean actual = userServ.addToUsersFavHygList(currUserId, (long) 1);
		System.out.println("actual is: " + actual);
		assertTrue(actual);
	}

	@Test
	public final void KtestDeleteHygFromListById() {
		System.out.println("11. Testing DeleteHygFromListById...");

		String username = "des71";
		String pwd = "1234";

		long currUserId = userServ.getUserIdWithUserNameAndPwd(username, pwd);

		boolean actual = userServ.deleteHygFromListById(currUserId, (long) 1);
		System.out.println("actual is: " + actual);
		assertTrue(actual);

	}

	@Test
	public final void LtestEditUserNameWithId() {
		System.out.println("12. Testing EditUserNameWithId...");

		String username = "des71";
		String pwd = "1234";

		long currUserId = userServ.getUserIdWithUserNameAndPwd(username, pwd);

		userServ.editUserNameWithId(currUserId, "updatedName");
		String updatedUsername = userServ.getUserByUsername("updatedName").getUsername();

		System.out.println("updatedUsername is: " + updatedUsername);
		assertEquals("updatedName", updatedUsername);
	}

	@Test
	public final void MtestEditUserAddressWithId() {
		System.out.println("13. Testing EditUserAddressWithId...");

		String username = "updatedName";
		String pwd = "1234";

		long currUserId = userServ.getUserIdWithUserNameAndPwd(username, pwd);

		userServ.editUserAddressWithId(currUserId, "111testave");

		String updatedAddress = userServ.getUserByUsername(username).getAddress();
		System.out.println("Updated address : " + updatedAddress);

		assertEquals("111testave", updatedAddress);
	}

	@Test
	public final void NtestEditFirstNameWithId() {
		System.out.println("14. Testing EditFirstNameWithId...");

		String username = "updatedName";
		String pwd = "1234";

		long currUserId = userServ.getUserIdWithUserNameAndPwd(username, pwd);

		userServ.editFirstNameWithId(currUserId, "testfirstname");

		String updatedFirstName = userServ.getUserByUsername(username).getName();
		System.out.println("updated first name: " + updatedFirstName);
		assertEquals("testfirstname", updatedFirstName);
	}

	@Test
	public final void OtestEditUserPhoneNumberWithId() {
		System.out.println("15. Testing EditUserPhoneNumberWithId...");

		String username = "updatedName";
		String pwd = "1234";

		long currUserId = userServ.getUserIdWithUserNameAndPwd(username, pwd);
		
		userServ.editUserPhoneNumberWithId(currUserId, "000-111-2222");
		String updatedPhoneNum = userServ.getUserByUsername(username).getPhoneNumber();
		System.out.println("updated phone number: " + updatedPhoneNum);
		assertEquals("000-111-2222", updatedPhoneNum);
	}
	
	@Test
	public final void PtestEditUserPwdWithId() {
		System.out.println("16. Testing EditUserPhoneNumberWithId...");

		String username = "updatedName";
		String pwd = "1234";

		long currUserId = userServ.getUserIdWithUserNameAndPwd(username, pwd);
		userServ.editUserPwdWithId(currUserId, "testpwdupdated");
		String updatedPwd = userServ.getUserByUsername(username).getPassword();
		System.out.println("Updated pwd is: " + updatedPwd);
		assertEquals("testpwdupdated", updatedPwd);
	}

	@Test
	public final void QtestCheckCurrPassword() {
		System.out.println("17. Testing EditUserPhoneNumberWithId...");

		String username = "updatedName";
		String pwd = "testpwdupdated";

		long currUserId = userServ.getUserIdWithUserNameAndPwd(username, pwd);
		boolean actual = userServ.checkCurrPassword(currUserId, "testpwdupdated");
		System.out.println("Our actual is: " + actual);
		assertTrue(actual);
	}
	
	@Test
	public final void RtestDeleteUserAcctById() {
		System.out.println("18. Testing DeleteUserAcctById...");

		String username = "updatedName";
		String pwd = "testpwdupdated";

		long currUserId = userServ.getUserIdWithUserNameAndPwd(username, pwd);
		userServ.deleteUserAcctById(currUserId);
		
		User actual = userServ.getUserByUsername(username);
		System.out.println("Our actual is: " + actual);
		assertNull(null, actual);
		
	}	

}
