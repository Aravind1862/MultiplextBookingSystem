package com.capg;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capg.dto.UsersDto;
import com.capg.entity.Users;
import com.capg.exception.IdNotFoundException;
import com.capg.exception.InvalidEmailException;
import com.capg.exception.InvalidPasswordException;
import com.capg.repository.BookingRepository;
import com.capg.repository.UsersRepository;
import com.capg.service.UsersServiceImpl;

public class UsersServiceImplTest {
	@Mock
	private UsersRepository userRepository;
	
	@Mock
	private BookingRepository bookingRepository;
	@InjectMocks
	private UsersServiceImpl userService;

	private Object result;
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	void testGetAllUsers() {
		Users user1 = new Users();
		Users user2 = new Users();
		List<Users> userList = Arrays.asList(user1, user2);
		when(userRepository.findAll()).thenReturn(userList);
		List<Users> result = userService.getAllUsers();
		assertEquals(2, result.size());
		verify(userRepository, times(1)).findAll();
	}
//	@Test
//	void testUserRegistration() {
//		Users user = new Users();
//		user.setEmailId("test@example.com");
//		user.setPassword("password");
//		user.setUserName("John");
//		
//		when(userRepository.findByEmailId("test@example.com")).thenReturn(null);
//		when(userRepository.saveAll(any())).thenReturn((List<Users>) user);
//		//assertDoesNotThrow(() -> ((Object) userService).createUser(user));
//		verify(userRepository, times(1)).findByEmailId("test@example.com");
//		verify(userRepository, times(1)).save(user);
//	}
	@Test
	void testGetUserDashboard() throws IdNotFoundException {
		Users user = new Users();
		user.setUserId(1);
		user.setUserName("john_doe");
		user.setEmailId("john@example.com");
		user.setUserName("John");
	
		user.setMobileNo(1234567890);
		user.setUserType('u');
		when(userRepository.existsById(1)).thenReturn(true);
		when(userRepository.findByUserId(1)).thenReturn(user);
		UsersDto result = userService.getUserDashBoard(1);
		assertNotNull(result);
		assertEquals(user.getUserId(), result.getUserId());
		assertEquals(user.getUserName(), result.getUserName());
		assertEquals(user.getEmailId(), result.getEmailId());
		assertEquals(user.getUserName(), result.getUserName());
		
		assertEquals(user.getMobileNo(), result.getMobileNo());
		verify(userRepository, times(1)).existsById(1);
		verify(userRepository, times(1)).findByUserId(1);
	}
	@Test
	void testGetAdminDashboard() throws IdNotFoundException {
		Users user = new Users();
		user.setUserId(1);
		user.setUserType('A');
		when(userRepository.existsById(1)).thenReturn(true);
		when(userRepository.findByUserId(1)).thenReturn(user);
		UsersDto result = userService.getUserDashBoard(1);
		assertEquals(user.getUserId(), result.getUserId());
		verify(userRepository, times(1)).existsById(1);
		verify(userRepository, times(1)).findByUserId(1);
	}
//	@Test
//	void testUserLogin_WhenCredentialsAreCorrect_ShouldReturnSuccessMessage()
//			throws InvalidEmailException, InvalidPasswordException {
//		String email = "john@example.com";
//		String password = "password123";
//		Users user = new Users();
//		user.setEmailId(email);
//		user.setPassword(password);
//		user.setUserType('u');
//		when(userRepository.findByEmailId(email)).thenReturn(user);
//		String result = userService.userLogin(email, password);
//		assertNotNull(result);
//		assertEquals("User Login Successfully", result);
//		verify(userRepository, times(1)).findByEmailId(email);
//	}
//	@Test
//	void testUserLogin_WhenUserDoesNotExist_ShouldThrowInvalidEmailException()
//			throws InvalidEmailException, InvalidPasswordException {
//		String email = "john@example.com";
//		String password = "password123";
//		when(userRepository.findByEmailId(email)).thenReturn(null);
//		InvalidEmailException exception = assertThrows(InvalidEmailException.class, () -> {
//			userService.userLogin(email, password);
//		});
//		assertEquals("INVALID_EMAIL", exception.getMessage());
//		verify(userRepository, times(1)).findByEmailId(email);
//	}
//	@Test
//	void testUpdateUserById_WhenUserExists_ShouldReturnUpdatedUser() throws IdNotFoundException {
//		int userId = 1;
//		Users existingUser = new Users();
//		existingUser.setUserId(userId);
//		existingUser.setEmailId("john@example.com");
//		existingUser.setPassword("password123");
//		existingUser.setUserName("John");
//		
//		existingUser.setUserType('u');
//		Users updatedUser = new Users();
//		updatedUser.setUserId(userId);
//		updatedUser.setEmailId("john.doe@example.com");
//		updatedUser.setPassword("newPassword");
//		updatedUser.setUserName("Updated John");
//		
//		when(userRepository.existsById(userId)).thenReturn(true);
//		when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
//		when(userRepository.save(existingUser)).thenReturn(existingUser);
//		//Users result = userService.updateUserById(userId, existingUser);
//		assertNotNull(result);
//		assertEquals(existingUser.getUserId(), ((UsersDto) result).getUserId());
//		assertEquals(existingUser.getEmailId(), ((UsersDto) result).getEmailId());
//		assertEquals(existingUser.getPassword(), ((User) result).getPassword());
//		assertEquals(existingUser.getUserName(), ((UsersDto) result).getUserName());
//		//assertEquals(existingUser.getLastName(), result.getLastName());
//		verify(userRepository, times(1)).existsById(userId);
//		verify(userRepository, times(1)).findById(userId);
//		verify(userRepository, times(1)).save(existingUser);
//	}
//	@Test
//	void testUpdateUserById_WhenUserDoesNotExist_ShouldThrowIdNotFoundException() {
//		int userId = 1;
//		Users updatedUser = new Users();
//		updatedUser.setUserId(userId);
//		updatedUser.setEmailId("john.doe@example.com");
//		updatedUser.setPassword("newPassword");
//		updatedUser.setUserName("Updated John");
//		//updatedUser.setLastName("Updated Doe");
//		when(userRepository.existsById(userId)).thenReturn(false);
//		IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> {
//			userService.updateUserById(userId, updatedUser);
//		});
//		assertEquals("USER_ID_NOT_FOUND_INFO", exception.getMessage());
//		verify(userRepository, times(1)).existsById(userId);
//		verify(userRepository, never()).findById(userId);
//		verify(userRepository, never()).save(updatedUser);
//	}
//	@Test
//    void testDeleteUserById_WhenUserExists_ShouldReturnSuccessMessage() throws IdNotFoundException {
//        int userId = 1;
//
//        when(userRepository.existsById(userId)).thenReturn(true);
//        String result = userService.deleteUser(userId);
//        assertNotNull(result);
//        assertEquals("user Deleted Successfully", result);
//        verify(userRepository, times(1)).existsById(userId);
//        verify(userRepository, times(1)).deleteById(userId);
//    }
//    @Test
//    void testDeleteUserById_WhenUserDoesNotExist_ShouldThrowIdNotFoundException() {
//        int userId = 1;
//
//        when(userRepository.existsById(userId)).thenReturn(false);
//
//        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> {
//            userService.deleteUser(userId);
//        });
//
//        assertEquals("USER_ID_NOT_FOUND_INFO", exception.getMessage());
//
//        verify(userRepository, times(1)).existsById(userId);
//        verify(userRepository, never()).deleteById(userId);
//    }
//    @Test
//    void testForgotUserPassword_WhenValidData_ShouldReturnSuccessMessage() throws InvalidPasswordException, InvalidEmailException {
//        UsersDto userDto = new UsersDto();
//        userDto.setEmailId("john@example.com");
//        userDto.setPassword("newPassword");
//        userDto.setConfirmPassword("newPassword");
//        Users existingUser = new Users();
//        existingUser.setEmailId("john@example.com");
//        when(userRepository.findByEmailId(userDto.getEmailId())).thenReturn(existingUser);
//        when(userRepository.save(existingUser)).thenReturn(existingUser);
//       // String result = userService.forgotUserPassword(userDto);
//        assertEquals("Password Reset Successful", result);
//        verify(userRepository, times(1)).findByEmailId(userDto.getEmailId());
//        verify(userRepository, times(1)).save(existingUser);
//    }
//    @Test
//    void testResetUserPassword_WhenValidData_ShouldReturnSuccessMessage() throws IdNotFoundException, InvalidPasswordException {
//        int userId = 1;
//        UsersDto userDto = new UsersDto();
//        userDto.setPassword("newPassword");
//        userDto.setConfirmPassword("newPassword");
//        Users existingUser = new Users();
//        existingUser.setUserId(userId);
//        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(existingUser));
//        when(userRepository.save(existingUser)).thenReturn(existingUser);
//        String result = userService.resetUserPassword(userId, userDto);
//
//        assertEquals("Password Reset Successful", result);
//
//        verify(userRepository, times(1)).findById(userId);
//        verify(userRepository, times(1)).save(existingUser);
    //}

}
