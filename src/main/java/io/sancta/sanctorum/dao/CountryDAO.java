package io.sancta.sanctorum.dao;

import io.sancta.sanctorum.domain.Country;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CountryDAO {
    SessionFactory sessionFactory;



    public List<Country> getAll(){
        String hql = "select c from Country c join fetch c.languages";
        Query<Country> query = sessionFactory.getCurrentSession().createQuery(hql, Country.class);
        return query.list();
    }
}
