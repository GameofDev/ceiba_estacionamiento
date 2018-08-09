package estacionamiento.excepciones;

public class IngresoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public IngresoException (String message) {
		super(message);
	}

}
 