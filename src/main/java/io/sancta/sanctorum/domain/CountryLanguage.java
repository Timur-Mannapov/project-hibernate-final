package io.sancta.sanctorum.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;

@Table(schema = "world", name = "country_language")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class CountryLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    Country country;

    String language;

    @Column(name = "is_official", columnDefinition = "BIT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    Boolean isOfficial;

    BigDecimal percentage;
}
