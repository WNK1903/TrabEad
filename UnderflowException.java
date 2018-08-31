package bstPackage;
public class UnderflowException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnderflowException() {
		super("Underflow!");
	}
}
