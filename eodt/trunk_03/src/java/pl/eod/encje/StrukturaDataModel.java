/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.encje;

import java.util.List;
import javax.faces.context.FacesContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import org.richfaces.model.FilterField;

public class StrukturaDataModel extends JPADataModel<Struktura, Uzytkownik, Dzial> {
public StrukturaDataModel() {
        super(Struktura.class);
    }

    @Override
    public Expression<Boolean> createFilterCriteria(CriteriaBuilder criteriaBuilder, Root<Struktura> root) {
        Expression<Boolean> filterCriteria = null;
        if(getArrangeableState().getFilterFields()==null) return null;
        List<FilterField> filterFields = getArrangeableState().getFilterFields();
        Join<Struktura, Uzytkownik>juser=root.join(Struktura_.userId);
        Join<Struktura, Dzial> jstatus=root.join(Struktura_.dzialId);
        if (filterFields != null && !filterFields.isEmpty()) {
            FacesContext facesContext = FacesContext.getCurrentInstance();

            for (FilterField filterField : filterFields) {
                String propertyName = (String) filterField.getFilterExpression().getValue(facesContext.getELContext());
                Object filterValue = filterField.getFilterValue();

                Expression<Boolean> predicate=null;
                if(filterField.getFilterExpression().getValue(facesContext.getELContext()).equals("fullname")){
                    predicate = createFilterCriteriaForFieldJoin1(propertyName, filterValue, juser, criteriaBuilder);
                }
                if(filterField.getFilterExpression().getValue(facesContext.getELContext()).equals("opis")){
                    WnStatusy filterValueS=(WnStatusy) filterValue;
                    if(filterValueS!=null){
                        predicate = createFilterCriteriaForFieldJoin2(propertyName, filterValueS.getOpis(), jstatus, criteriaBuilder);
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
    
    @Override
    protected Object getId(Struktura t) {
        return t.getId();
    }
}