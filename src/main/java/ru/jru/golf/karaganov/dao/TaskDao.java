package ru.jru.golf.karaganov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.jru.golf.karaganov.entity.Task;

import java.util.List;

@Repository
public class TaskDao {
    public TaskDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private final SessionFactory sessionFactory;
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<Task> getAll(int offset, int limit){
        String queryText = "select t from Task t";
        Query<Task> query = getSession().createQuery( queryText, Task.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int getAllCount(){
        String queryText = "select count(t) from Task t";
        Query<Long> query = getSession().createQuery(queryText, Long.class);
        return Math.toIntExact(query.uniqueResult());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Task getById(int id){
        String queryText = "select t from Task t where t.id = :ID";
        Query<Task> query = getSession().createQuery(queryText, Task.class);
        query.setParameter("ID", id);
        return query.uniqueResult();
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrEdit(Task task){
        getSession().persist(task);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Task task){
        getSession().remove(task);
    }

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }
}
