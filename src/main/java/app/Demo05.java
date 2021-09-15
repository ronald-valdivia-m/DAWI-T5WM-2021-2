package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Usuario;

public class Demo05 {
	public static void main(String[] args) {
		// 1. fabrica el acceso a los datos
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql8");
		// 2. crea el manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		
		List<Usuario> lstUsuario = em.createQuery("select a from Usuario a", Usuario.class).getResultList();

		System.out.println("Nro de usuarios : " + lstUsuario.size());

		for (Usuario u : lstUsuario) {
			System.out.println(">>> " + u);
		}
		System.out.println("-----------------------------------------");
		
		TypedQuery<Usuario> consulta = em.createQuery("select a from Usuario a", Usuario.class);
		List<Usuario> lstUsuario2 = consulta.getResultList();
		System.out.println("Nro de usuarios : " + lstUsuario2.size());

		for (Usuario u : lstUsuario2) {
			System.out.println(">>> " + u);
		}
		System.out.println("-----------------------------------------");
		
		System.out.println("Listado de usuarios por tipo: 2");
		String sql = "select a from Usuario a where a.tipo = :xtipo";
		TypedQuery<Usuario> consulta2 = em.createQuery(sql, Usuario.class);
		consulta2.setParameter("xtipo", 2);
		List<Usuario> lstUsuario3 = consulta2.getResultList();
		System.out.println("Nro de usuarios : " + lstUsuario3.size());

		for (Usuario u : lstUsuario3) {
			System.out.println(">>> " + u);
		}
		
		em.close();
		
	}	
}
