package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo04 {
	public static void main(String[] args) {
		// 1. fabrica el acceso a los datos
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql8");
		// 2. crea el manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		
		// -- proceso -> grabar datos en la entidad Usuario
		Usuario u = em.find(Usuario.class, 1234);

		em.close();
		
		System.out.println(u);
		u.setEstado(0);
	}	
}
