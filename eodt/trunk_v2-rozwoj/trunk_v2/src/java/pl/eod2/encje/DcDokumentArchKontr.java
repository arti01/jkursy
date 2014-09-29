/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.encje;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import pl.eod.abstr.AbstKontroler;
import static pl.eod2.encje.DcDokumentJpaController.logger;

public class DcDokumentArchKontr extends AbstKontroler<DcDokumentArch>{

    public DcDokumentArchKontr() {
        super(new DcDokumentArch());
    }
     
    public List<DcDokumentArch> findStatus(int statusId) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("DcDokumentArch.findByStatus");
            q.setParameter("statusId", statusId);
            return q.getResultList();
        } catch (NoResultException ex) {
            logger.log(Level.SEVERE, "blad", ex);
            return Collections.EMPTY_LIST;
        } finally {
            em.close();
        }
    }
}
