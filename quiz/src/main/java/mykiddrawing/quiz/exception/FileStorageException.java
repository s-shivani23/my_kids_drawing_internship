package mykiddrawing.quiz.exception;


public class FileStorageException extends Exception {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String ex;
	public FileStorageException(String string) {
		// TODO Auto-generated constructor stub
		this.ex=string;
	}

}