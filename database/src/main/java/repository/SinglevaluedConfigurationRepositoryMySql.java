package repository;

import lombok.extern.log4j.Log4j2;
import model.SinglevaluedConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Log4j2
public class SinglevaluedConfigurationRepositoryMySql implements SinglevaluedConfigurationRepository {

    public SinglevaluedConfiguration selectByAttributeName(String attributeName) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(SinglevaluedConfiguration.class, attributeName);
    }

    public void insert(SinglevaluedConfiguration singlevaluedConfiguration) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(singlevaluedConfiguration);
        transaction.commit();
        session.close();
    }

    public void update(SinglevaluedConfiguration singlevaluedConfiguration) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(singlevaluedConfiguration);
        transaction.commit();
        session.close();
    }

    public void delete(SinglevaluedConfiguration singlevaluedConfiguration) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(singlevaluedConfiguration);
        transaction.commit();
        session.close();
    }

    public List<SinglevaluedConfiguration> selectAll() {
        return (List<SinglevaluedConfiguration>) HibernateSessionFactory.getSessionFactory().openSession()
                .createQuery("From SinglevaluedConfiguration").list();
    }
}
