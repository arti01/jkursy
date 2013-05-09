/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.encje;

import com.google.common.base.Strings;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.richfaces.model.FilterField;

/**
 *
 * @author arti01
 */
public class WnUrlopDataModel extends JPADataModel<WnUrlop> {

    public WnUrlopDataModel() {
        super(WnUrlop.class);
    }

    @Override
    public Expression<Boolean> createFilterCriteria(CriteriaBuilder criteriaBuilder, Root<WnUrlop> root) {
        Expression<Boolean> filterCriteria = null;
        List<FilterField> filterFields = getArrangeableState().getFilterFields();
        Join<WnUrlop, Uzytkownik>juser=root.join(WnUrlop_.uzytkownik);
        Join<WnUrlop, WnStatusy> jstatus=root.join(WnUrlop_.statusId);
        if (filterFields != null && !filterFields.isEmpty()) {
            FacesContext facesContext = FacesContext.getCurrentInstance();

            for (FilterField filterField : filterFields) {
                System.err.println("rrrrrrrrrrrrrrrrrrrrrrrrrr");
                System.err.println(filterField.getFilterExpression().getValue(facesContext.getELContext()));
                System.err.println(facesContext.getELContext());
                System.err.println(filterField.getFilterValue());
                
                String propertyName = (String) filterField.getFilterExpression().getValue(facesContext.getELContext());
                Object filterValue = filterField.getFilterValue();

                Expression<Boolean> predicate=null;
                if(filterField.getFilterExpression().getValue(facesContext.getELContext()).equals("fullname")){
                    predicate = createFilterCriteriaForField(propertyName, filterValue, juser, criteriaBuilder);
                }
                if(filterField.getFilterExpression().getValue(facesContext.getELContext()).equals("opis")){
                    WnStatusy filterValueS=(WnStatusy) filterValue;
                    if(filterValueS!=null){
                        predicate = createFilterCriteriaForField(propertyName, filterValueS.getOpis(), jstatus, criteriaBuilder);
                    }
                }

                if (predicate == null) {
                    continue;
                }

                if (filterCriteria == null) {
                    filterCriteria = predicate.as(Boolean.class);
                } else {
                    filterCriteria = criteriaBuilder.and(filterCriteria, predicate.as(Boolean.class));
                }
            }
        }
        return filterCriteria;
    }
    
    protected Expression<Boolean> createFilterCriteriaForField(String propertyName, Object filterValue, Join root,
            CriteriaBuilder criteriaBuilder) {
        String stringFilterValue = (String) filterValue;
        if (Strings.isNullOrEmpty(stringFilterValue)) {
            return null;
        }

        stringFilterValue = stringFilterValue.toLowerCase(getArrangeableState().getLocale());

        Path<String> expression = root.get(propertyName);
        Expression<Integer> locator = criteriaBuilder.locate(criteriaBuilder.lower(expression), stringFilterValue, 1);
        return criteriaBuilder.gt(locator, 0);
    }
    
    
    
    @Override
    public void walk(FacesContext context, DataVisitor visitor, Range range, Object argument) {
        System.err.println("walkuuuuuuuuuuuuuuuuuuuuuuuuuu");
        CriteriaQuery<WnUrlop> criteriaQuery = this.createSelectCriteriaQuery();
        TypedQuery<WnUrlop> query = getEntityManager().createQuery(criteriaQuery);

        SequenceRange sequenceRange = (SequenceRange) range;
        if (sequenceRange.getFirstRow() >= 0 && sequenceRange.getRows() > 0) {
            query.setFirstResult(sequenceRange.getFirstRow());
            query.setMaxResults(sequenceRange.getRows());
        }

        List<WnUrlop> data = query.getResultList();
        for (WnUrlop t : data) {
            visitor.process(context, getId(t), argument);
        }
    }
    
    @Override
    public int getRowCount() {
        //CriteriaQuery<Long> criteriaQuery = createCountCriteriaQuery();
        Query q = getEntityManager().createNamedQuery("WnUrlop.findAll");
            //q.setParameter("adrEmail", email);
        return q.getResultList().size();
    }
    
    @Override
    protected Object getId(WnUrlop t) {
        return t.getId();
    }
}