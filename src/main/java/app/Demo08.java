package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Usuario;

public class Demo08 {
	public static void main(String[] args) {
		// 1. fabrica el acceso a los datos
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql8");
		// 2. crea el manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		
		// Forma 1: JPQL
		String sql = "select a from Usuario a where a.usuario = :xusr and a.clave = :cla";
		TypedQuery<Usuario> query1 = em.createQuery(sql, Usuario.class);
		query1.setParameter("xusr", "u001@gmail.com");
		query1.setParameter("xcla", "10001");
		Usuario u = query1.getSingleResult();
		if (u == null) {
			System.out.println("Usuario NO existe!!!");
			System.out.println(u);
		}
		System.out.println("-------------------------------------------");
		
		
		// Forma 2: JPQL
		String sql2 = "{call usp_validaAcceso(?,?)}";
		Query query2 = em.createNativeQuery(sql2, Usuario.class);
		query2.setParameter(1, "u001@gmail.com");
		query2.setParameter(2, "10001");
		Usuario u2 = (Usuario) query2.getSingleResult();
		if (u == null) {
			System.out.println("Usuario NO existe!!!");
			System.out.println(u2);
		}
		System.out.println("-------------------------------------------");		
		
		
		em.close();
	}	
}
