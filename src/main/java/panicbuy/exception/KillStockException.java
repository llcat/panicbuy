package panicbuy.exception;

public class KillStockException extends RuntimeException{

	public KillStockException(String message, Throwable cause) {
		super(message, cause);
	}

	public KillStockException(String message) {
		super(message);
	}
	
}
