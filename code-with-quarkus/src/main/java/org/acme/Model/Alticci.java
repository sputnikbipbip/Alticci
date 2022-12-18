package org.acme.Model;

import javax.persistence.Entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;


@Entity
public class Alticci extends PanacheEntity {

    //@Id @GeneratedValue private Long id;
    public int result;

    // Default constructor
    public Alticci() {
    }

    public Alticci(int result) {
        this.result = result;
    }
}
