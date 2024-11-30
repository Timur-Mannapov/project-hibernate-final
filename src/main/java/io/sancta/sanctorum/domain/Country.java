package io.sancta.sanctorum.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(schema = "world", name = "country")
public class Country {
    @Id
    Integer id;

    String code;

    @Column(name = "code_2")
    String alternativeCode;

    String name;

    @Enumerated(EnumType.ORDINAL)
    Continent continent;

    String region;

    @Column(name = "surface_area")
    BigDecimal surfaceArea;

    @Column(name = "indep_year")
    Short independenceYear;

    Integer population;

    @Column(name = "life_expectancy")
    BigDecimal lifeExpectancy;

    @Column(name = "gnp")
    BigDecimal GNP;

    @Column(name = "gnp_old")
    BigDecimal GNPOld;

    @Column(name = "local_name")
    String localName;

    @Column(name = "government_form")
    String governmentForm;

    @Column(name = "head_of_state")
    String headOfState;

    @JoinColumn(name = "capital")
    @OneToOne(fetch = FetchType.LAZY)
    City city;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    Set<CountryLanguage> languages;
}
