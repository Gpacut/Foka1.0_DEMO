package pl.edu.agh.ki.mwo.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pl.edu.agh.ki.mwo.model.Work;
import pl.edu.agh.ki.mwo.model.Evaluation;

public class DatabaseConnector {
	
	protected static DatabaseConnector instance = null;
	
	public static DatabaseConnector getInstance() {
		if (instance == null) {
			instance = new DatabaseConnector();
		}
		return instance;
	}
	
	Session session;

	protected DatabaseConnector() {
		session = HibernateUtil.getSessionFactory().openSession();
	}
	
	public void teardown() {
		session.close();
		HibernateUtil.shutdown();
		instance = null;
	}
	
	public Iterable<Work> getWorks() {
		String hql = "FROM Work";
		Query query = session.createQuery(hql);
		List works = query.list();
		
		return works;
	}
	
	public void addWork(Work work) {
		Transaction transaction = session.beginTransaction();
		session.save(work);
		transaction.commit();
	}
	
	public void editWork(Work work) {
		Transaction transaction = session.beginTransaction();
		session.merge(work);
		transaction.commit();
	}
	
	public void editGrade(Evaluation workToEvaluation) {
		Transaction transaction = session.beginTransaction();
		session.merge(workToEvaluation);
		transaction.commit();
	}
	
	public void deleteWork(String workId) {
		String hql = "FROM Work S WHERE S.id=" + workId;
		Query query = session.createQuery(hql);
		List<Work> results = query.list();
		Transaction transaction = session.beginTransaction();
		for (Work s : results) {
			session.delete(s);
		}
		transaction.commit();
	}
	public void addToEvaluation(Evaluation workToEvaluation) {
		Transaction transaction = session.beginTransaction();
		session.save(workToEvaluation);
		transaction.commit();
	}

	public Iterable<Evaluation> getWorksToEval() {
		String hql = "FROM Evaluation";
		Query query = session.createQuery(hql);
		List worksToEval = query.list();
		
		return worksToEval;
	}
	
	public void deleteWorkToEvaluation(String workToEvaluationId) {
		String hql = "FROM Evaluation S WHERE S.id=" + workToEvaluationId;
		Query query = session.createQuery(hql);
		List<Evaluation> results = query.list();
		Transaction transaction = session.beginTransaction();
		for (Evaluation s : results) {
			session.delete(s);
		}
		transaction.commit();
	}
	
}
