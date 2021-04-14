package mykiddrawing.quiz.exception;

public class UnauthorisedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int i;
	String error;
	String message;
	public UnauthorisedException(int i, String error, String message) {
		// TODO Auto-generated constructor stub
		this.i=i;
		this.error=error;
		this.message=message;
	}

}
