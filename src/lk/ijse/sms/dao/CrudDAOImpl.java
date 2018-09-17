package lk.ijse.sms.dao;

import lk.ijse.sms.entity.Exam;
import lk.ijse.sms.entity.StudentExam;
import org.hibernate.Session;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class CrudDAOImpl<T ,ID> implements CrudDAO<T,ID>
{

    private Session session;
    private Class<T> entity;

    public CrudDAOImpl() {
        entity = (Class<T>)(((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    public void save(T entity) throws Exception {

        session.persist(entity);
    }

    @Override
    public void update(T entity) throws Exception {
        session.update(entity);
    }

    @Override
    public void delete(ID id) throws Exception {
        T t = session.find(entity, id);
        session.remove(t);
    }

    @Override
    public T findByID(ID id) throws Exception {
        return  session.find(entity, id);
    }

    @Override
    public List<T> getAll() throws Exception {
        System.out.println(entity.getName());
       return session.createQuery("FROM "+entity.getName()).list();
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }
}
