package app;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo03 {
	public static void main(String[] args) {
		// 1. fabrica el acceso a los datos
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql8");
		// 2. crea el manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		
		// -- proceso -> grabar datos en la entidad Usuario
		Usuario u = new Usuario();
		u.setCodigo(1234);
		u.setNombre("Prosor");
		u.setApellido("Apellido");
		u.setUsuario("profe111@mail.com");
		u.setClave("456");
		u.setFnacim("2020/08/24");
		u.setTipo(1);
		u.setEstado(1);
		
		em.getTransaction().begin();
		
		em.merge(u);
		
		em.getTransaction().commit();
		
		em.close();
	}	
}
