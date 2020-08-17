package jpa01;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

import entity.Livre;

public class TestJpa {

	private EntityManagerFactory factory = null;
	
	@Before
	public void init() {
		factory = Persistence.createEntityManagerFactory("pu_essai");
	}
	
	
	@Test
	public void findLivre() {
		
		EntityManager em = factory.createEntityManager();
		
		if(em != null) {
			Livre livr = em.find(Livre.class, 1);
			System.out.println(livr);
		}
		em.close();
		factory.close();
		
	}
	
	@Test
	public void listeLivre() {
		
		EntityManager em = factory.createEntityManager();
		
		if(em != null) {
			String query = "SELECT liv FROM Livre liv";
			TypedQuery<Livre> qu = em.createQuery(query, Livre.class);
			for(Livre liv : qu.getResultList()) {
				System.out.println(liv);
			}
		}
		em.close();
		factory.close();
		
	}
	
	@Test
	public void listeLivreWhere() {
		
		EntityManager em = factory.createEntityManager();
		
		if(em != null) {
			String query = "SELECT liv FROM Livre liv WHERE liv.id between 1 and 6";
			TypedQuery<Livre> qu = em.createQuery(query, Livre.class);
			for(Livre liv : qu.getResultList()) {
				System.out.println(liv);
			}
		}
		em.close();
		factory.close();
		
	}

	@Test
	public void insertLivre() {
		
		EntityManager em = factory.createEntityManager();
		Livre liv = new Livre();
		liv.setId(6);
//		liv.setAuteur("Emile Zola");
//		liv.setTitre("Germinales");
		liv.setAuteur("Victor Hugo");
		liv.setTitre("Les Miserables");
		
		//j'ouvre une transaction
		em.getTransaction().begin();
		
		// j'ajoute dans la BDD mon nouveau Livre
		em.persist(liv);
		
		// je commite et ferme la transaction
		em.getTransaction().commit();
		
		em.close();
		factory.close();
	}

	@Test
	public void updateLivre() {
		
		EntityManager em = factory.createEntityManager();
		
		//j'ouvre une transaction
		em.getTransaction().begin();
		
		// Find transcient : transactionnel
		Livre liv = em.find(Livre.class, 5);
		liv.setTitre("Du plaisir dans la cuisine");
		
		// j'ajoute dans la BDD mon nouveau Livre
		em.merge(liv);
		
		// je commite et ferme la transaction
		em.getTransaction().commit();
		
		em.close();
		factory.close();		
	}
	@Test
	public void deleteLivre() {
		
		EntityManager em = factory.createEntityManager();
		
		//j'ouvre une transaction
		em.getTransaction().begin();
		
		// Find transcient : transactionnel
		Livre liv = em.find(Livre.class, 2);
		
		// je supprime le Livre de la BDD
		em.remove(liv);
		
		// je commite et ferme la transaction
		em.getTransaction().commit();
		
		em.close();
		factory.close();		
	}
	@Test
	public void listeLivreVictorHugo() {
		
		EntityManager em = factory.createEntityManager();
		
		if(em != null) {
			String query = "SELECT liv FROM Livre liv WHERE liv.auteur = 'Victor Hugo'";
			TypedQuery<Livre> qu = em.createQuery(query, Livre.class);
			for(Livre liv : qu.getResultList()) {
				System.out.println(liv);
			}
		}
		em.close();
		factory.close();
		
	}
	@Test
	public void listeLivreGuerreEtPaix() {
		
		EntityManager em = factory.createEntityManager();
		
		if(em != null) {
			String query = "SELECT liv FROM Livre liv WHERE liv.titre = 'Guerre et Paix'";
			TypedQuery<Livre> qu = em.createQuery(query, Livre.class);
			for(Livre liv : qu.getResultList()) {
				System.out.println(liv);
			}
		}
		em.close();
		factory.close();
		
	}
	
}
