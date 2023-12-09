package custom_exception;

@SuppressWarnings("serial")
public class CustomXception extends RuntimeException {

	public CustomXception(String prompt) {
		super(prompt);
	}
	
}
