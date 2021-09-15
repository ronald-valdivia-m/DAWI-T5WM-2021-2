package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Producto;
import model.Usuario;

public class Prod01 {
	
	public static void main(String[] args) {
		// 1. fabrica el acceso a los datos
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql8");
		// 2. crea el manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		
		// -- proceso -> grabar datos en la entidad Usuario
		Producto p = new Producto();
		p.setIdprod("P0050");
		p.setDescripcion("Prueba50");
		p.setStock(500);
		p.setPrecio(0.99);
		p.setIdcategoria(1);
		p.setEstado(0);
		
		em.getTransaction().begin();
		
		em.persist(p);
		
		em.getTransaction().commit();
		
		em.close();
	}
	
}
