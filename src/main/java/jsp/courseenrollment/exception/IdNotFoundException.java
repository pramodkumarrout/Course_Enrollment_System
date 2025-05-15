package jsp.courseenrollment.exception;

public class IdNotFoundException extends RuntimeException{
	@Override
	public String getMessage()
	{
		return "ID not available in Database";
	}
}