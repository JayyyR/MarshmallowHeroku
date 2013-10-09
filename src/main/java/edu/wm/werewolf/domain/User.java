package edu.wm.werewolf.domain;

public class User {
	
	private String firstName;
	private String lastName;
	private String id;
	private String hashedPassword;
	private String imageURL;
	
	private String email;


	public User(String firstName, String lastName, String id,
			String hashedPassword, String imageURL, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.hashedPassword = hashedPassword;
		this.imageURL = imageURL;
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}


}
