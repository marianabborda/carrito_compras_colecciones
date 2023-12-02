package compras;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Inicio {
 public static void main(String[] args) {
	 String archivoEntrada = "compras.txt";	 
	 List<String>lista = new ArrayList<String>();
	 Carrito carrito = new Carrito(LocalDateTime.parse("2023-01-13T11:12:00"));
	 Persona persona1 = new Persona ("Juan","Rodrigez",  LocalDateTime.parse("1980-04-24T08:12:00"), carrito);
	 Carrito miCarrito= persona1.getCarrito();
	 
	 try {
		 
           lista = Files.readAllLines(Paths.get(archivoEntrada));
           lista.remove(0);
          
           for(String linea : lista) {
        	  String[] datosDeProducto = linea.split(" ");        	  
        	 
        	  Producto p = new Producto(datosDeProducto[1], datosDeProducto[2],Float.parseFloat(datosDeProducto[0]));
        	  miCarrito.agregarProducto(p);
        	  
          	}		 				    
	       }catch(IOException e) {
              System.out.println("Hubo un error en la operación:");
           }
	 		
	 
         Descuento desFijo = new DescuentoFijo(300);
		 Descuento desPorcentaje = new DescuentoPorcentaje(0.20F);
		 Descuento desPorcentajeConTope = new DescPorcentajeConTope(0.30F);		 
		 desPorcentajeConTope.setValorDesc(0.20F);
		 
		 try {
			 double resultado = miCarrito.costoFinal(desFijo);	
			 System.out.println("El costo final es: " + resultado);
		 }catch(ValorCeroException | TotalNegativoException e) {
			 System.out.println(e);
		 }
		  				 
     }
}	 
	 
	 
	 
	 
	 
