package io.sancta.sanctorum;

import io.sancta.sanctorum.dao.CityDAO;
import io.sancta.sanctorum.dao.CountryDAO;
import io.sancta.sanctorum.domain.City;
import io.sancta.sanctorum.domain.Country;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GeoController {
    SessionFactory sessionFactory;
    CityDAO cityDAO;
    CountryDAO countryDAO;

    public GeoController() {
        sessionFactory = prepareRelationalDatabase();
        cityDAO = new CityDAO(sessionFactory);
        countryDAO = new CountryDAO(sessionFactory);
    }

    public void run() {
        List<City> allCities = fetchData();
        System.out.println(allCities.size());

        shutdown();
    }

    private SessionFactory prepareRelationalDatabase() {
        return new Configuration().configure().buildSessionFactory();
    }

    private void shutdown() {
        if (Objects.nonNull(sessionFactory)) sessionFactory.close();
    }

    private List<City> fetchData() {
        try (Session session = sessionFactory.getCurrentSession()) {
            List<City> allCities;
            session.beginTransaction();

            List<Country> countries = countryDAO.getAll();

            int totalCount = cityDAO.getTotalCount();
            int step = 500;
            allCities = IntStream.iterate(0, i -> i < totalCount, i -> i + step)
                    .mapToObj(i -> cityDAO.getItems(i, step))
                    .flatMap(Collection::stream)
                    .toList();


            session.getTransaction().commit();
            return allCities;
        }
    }


}
