package repository;

import lombok.extern.log4j.Log4j2;
import model.SinglevaluedConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.id.IdentifierGenerationException;

import javax.persistence.OptimisticLockException;
import java.util.List;

@Log4j2
public class SinglevaluedConfigurationRepositoryMySql implements SinglevaluedConfigurationRepository {

    public SinglevaluedConfiguration selectByAttributeName(String attributeName) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(SinglevaluedConfiguration.class, attributeName);
    }

    public void insert(SinglevaluedConfiguration singlevaluedConfiguration) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(singlevaluedConfiguration);
            transaction.commit();
        } catch (ConstraintViolationException | IllegalArgumentException | IdentifierGenerationException exception) {
            log.error(exception.getMessage());
        }
    }

    public void update(SinglevaluedConfiguration singlevaluedConfiguration) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(singlevaluedConfiguration);
            transaction.commit();
        } catch (OptimisticLockException | IllegalArgumentException exception) {
            log.error(exception.getMessage());
        }
    }

    public void delete(SinglevaluedConfiguration singlevaluedConfiguration) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(singlevaluedConfiguration);
            transaction.commit();
        } catch (IllegalArgumentException exception) {
            log.error(exception.getMessage());
        }
    }

    public List<SinglevaluedConfiguration> selectAll() {
        return (List<SinglevaluedConfiguration>) HibernateSessionFactory.getSessionFactory().openSession()
                .createQuery("From SinglevaluedConfiguration").list();
    }
}
