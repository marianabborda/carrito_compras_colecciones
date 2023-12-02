package compras;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



//import org.junit.jupiter.api.Test;

class TestCarrito {
	private Persona persona; 
	private Carrito carrito;
	
	void setup(String ruta) {
		 String archivoEntrada = ruta;	 
		 List<String>lista = new ArrayList<String>();
		 Carrito carrito = new Carrito(LocalDateTime.parse("2023-01-13T11:12:00"));
		 persona = new Persona ("Juan","Rodrigez",  LocalDateTime.parse("1980-04-24T08:12:00"), carrito);
		 this.carrito= persona.getCarrito();
		 
		 try {			 
	           lista = Files.readAllLines(Paths.get(archivoEntrada));
	           lista.remove(0);
	          
	           for(String linea : lista) {
	        	  String[] datosDeProducto = linea.split(" ");        	  
	        	 
	        	  Producto p = new Producto(datosDeProducto[1], datosDeProducto[2],Float.parseFloat(datosDeProducto[0]));
	        	  this.carrito.agregarProducto(p);
	        	  
	          	}		 				    
		       }catch(IOException e) {
	              System.out.println("Hubo un error en la operación:");
	           }
		 
	}
	
	@Test 
	void testValorCeroException() {
		setup("comprasTotalCero.text");
		Descuento desFijo = new DescuentoFijo(3000);
		Assertions.assertThrows(ValorCeroException.class,()->{this.carrito.costoFinal(desFijo);} );
	}
	
	@Test
	void TotalNegativoException() {
		setup("comprasResultadoNegativo.text");
		Descuento desFijo = new DescuentoFijo(3000);
		Assertions.assertThrows( TotalNegativoException.class,()->{this.carrito.costoFinal(desFijo);} );
	}
	
}
