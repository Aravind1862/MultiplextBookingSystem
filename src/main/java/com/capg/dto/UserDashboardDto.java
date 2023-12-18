package com.capg.dto;




import java.util.List;

public class UserDashboardDto {
	private String name;
	private String email;
	private long mobileNumber;
	private List<ShowDto> latestShows;
	private List<BookingDto> upcomingBookings;
	private List<BookingDto> pastBookings;
	private String updateProfile;
	private String searchMovies;
	private String bookTicket;
	private String cancelBooking;
 
	public UserDashboardDto() {
		super();
	}
 
	public UserDashboardDto(String name, String email, long mobileNumber, List<ShowDto> latestShows,
			List<BookingDto> upcomingBookings, List<BookingDto> pastBookings) {
		super();
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.latestShows = latestShows;
		this.upcomingBookings = upcomingBookings;
		this.pastBookings = pastBookings;
	}
	
	public String getUpdateProfile() {
		return updateProfile;
	}
	
	public void setUpdateProfile(String updateProfile) {
		this.updateProfile = updateProfile;
	}
	
	public String getSearchMovies() {
		return searchMovies;
	}
	
	public void setSearchMovies(String searchMovies) {
		this.searchMovies = searchMovies;
	}
	
	public String getBookTicket() {
		return bookTicket;
	}
	
	public void setBookTicket(String bookTicket) {
		this.bookTicket = bookTicket;
	}
	
	public String getCancelBooking() {
		return cancelBooking;
	}
	
	public void setCancelBooking(String cancelBooking) {
		this.cancelBooking = cancelBooking;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public String getEmail() {
		return email;
	}
 
	public void setEmail(String email) {
		this.email = email;
	}
 
	public long getMobileNumber() {
		return mobileNumber;
	}
 
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
 
	public List<ShowDto> getLatestShows() {
		return latestShows;
	}
 
	public void setLatestShows(List<ShowDto> latestShows) {
		this.latestShows = latestShows;
	}
 
	public List<BookingDto> getUpcomingBookings() {
		return upcomingBookings;
	}
 
	public void setUpcomingBookings(List<BookingDto> upcomingBookings) {
		this.upcomingBookings = upcomingBookings;
	}
 
	public List<BookingDto> getPastBookings() {
		return pastBookings;
	}
 
	public void setPastBookings(List<BookingDto> pastBookings) {
		this.pastBookings = pastBookings;
	}
}
 