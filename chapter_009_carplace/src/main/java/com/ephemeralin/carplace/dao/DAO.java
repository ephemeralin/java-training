package com.ephemeralin.carplace.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Dao.
 */
public abstract class DAO<T> {

    private Class<T> type;

    public DAO() {
        this.type = (Class<T>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }

    public boolean delete(SessionFactory sf, T entity) {
        Session session = sf.getCurrentSession();
        boolean success = false;
        if (entity != null) {
            session.delete(entity);
            success = true;
        }
        return success;
    }

    public List findByCriteria (SessionFactory sf, HashMap<String, Object> criterias) {
        Session session = sf.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<Predicate> predicates = new ArrayList<>();
        for (Map.Entry<String, Object> entry : criterias.entrySet()) {
            String parName = entry.getKey();
            Object parValue = entry.getValue();
            predicates.add(builder.equal(root.get(parName), parValue));
        }
        query.select(root).where(predicates.toArray(new Predicate[]{}));
        return session.createQuery(query).getResultList();
    }
}
