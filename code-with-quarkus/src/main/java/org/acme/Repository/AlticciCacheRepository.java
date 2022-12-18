package org.acme.Repository;


import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import org.acme.Model.Alticci;


@ApplicationScoped
public class AlticciCacheRepository implements PanacheRepository<Alticci>  {
    

    public List<Alticci> findAllUntil(int n) {
        return findAll().range(0, n).list();
    }

    public Alticci findById(long id) {
        return findByIdOptional(id).orElse(null);
    }

    public long getLastAlticci() {
        Alticci a = find("select a from Alticci a where a.id = (select max(b.id) from Alticci b)").firstResult();
        if(a == null) {return 0;}
        return a.id;
    }

    @Transactional
    public void saveAlticci(int result) {
        Alticci a = new Alticci(result);
        a.persist();
    }
}
