/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.ejb;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Denda;
import session.remote.RemoteDenda;

/**
 *
 * @author 1773031_1873024_1873032
 */
@Stateless
@LocalBean
public class EjbDenda implements RemoteDenda {

    @PersistenceContext(unitName = "1773031_1873024_1873032_PERPUS-ejbPU")
    private EntityManager em;

    @Override
    public List<Denda> getAllData() {
        Query query = em.createNamedQuery("Denda.findAll");
        return query.getResultList();
    }

    @Override
    public void insert(Denda param) {
        em.persist(param);
    }

    @Override
    public void update(Denda param) {
        em.merge(param);
    }

    @Override
    public void delete(Denda param) {
        em.remove(em.merge(param));
    }

    @Override
    public Integer getTotalKas() {
        int kas = 0;
        kas = getAllData().stream().map((d) -> d.getTotalDenda()).reduce(kas, Integer::sum);
        return kas;
    }

}
