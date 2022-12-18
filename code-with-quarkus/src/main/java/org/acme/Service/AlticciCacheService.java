package org.acme.Service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.acme.Repository.AlticciCacheRepository;


import org.acme.Model.Alticci;

@ApplicationScoped
public class AlticciCacheService {

    private AlticciCacheRepository alticciCacheRepository = new AlticciCacheRepository();

    public List<Alticci> findWithTop(int n) {
        return alticciCacheRepository.findAllUntil(n);
    }

    public int findAlticci(int n) {
        return alticciCacheRepository.findById(n).result;
    }

    public long findLastResult() {
        return alticciCacheRepository.getLastAlticci();
    }

    public void saveResult(int result) {
        alticciCacheRepository.saveAlticci(result);
    }
    

    public int calculate(int n) {
        //initial values are pre inserted in DataBase -> import.sql file in resources
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
             return 1;
        } else {
            int baseIndex = (int) findLastResult();
            //Value exists in the DataBase
            if(baseIndex >= n) return findAlticci(n);
            //Calculate and persist every next value
            int lastResult = 0;
            for(;baseIndex < n; baseIndex++) {
                /***I am having an error:
                 * 
                 * org.jboss.resteasy.spi.UnhandledException: javax.persistence.TransactionRequiredException: 
                 * Transaction is not active, consider adding @Transactional to your method to automatically activate one.
                 * 
                */
                lastResult = findAlticci(baseIndex-3) + findAlticci(baseIndex-2);
                saveResult(lastResult);
            }
            return lastResult;
        }
    }


}
