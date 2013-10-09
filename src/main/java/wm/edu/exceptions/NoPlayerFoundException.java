package wm.edu.exceptions;

public class NoPlayerFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	private String userID;
	
	public NoPlayerFoundException(String userID){
		super();
		this.userID=userID;
	}
	
	public String getUserID() {
		return userID;
	}
	

}
