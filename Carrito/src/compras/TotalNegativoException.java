package compras;

public class TotalNegativoException extends Exception{
	private final float valorDeCarrito;

	public TotalNegativoException(float valorDeCarrito) {
		this.valorDeCarrito = valorDeCarrito;		
	}

	public float getValorDeCarrito() {
		return valorDeCarrito;
	} 
	
	@Override
	public String getMessage() {
		return "El resultado de la operación es negativo: " + valorDeCarrito ;
	}
}
