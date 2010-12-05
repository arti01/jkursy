package org.arti01.entit;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
 
public class DataWiekszaOdValidator implements
     ConstraintValidator<DataWiekszaOd, Date> {

@Override
public void initialize(DataWiekszaOd constraintAnnotation) {
	// TODO Auto-generated method stub
	
}

@Override
public boolean isValid(Date value, ConstraintValidatorContext context) {
	// TODO Auto-generated method stub
	return false;
}
}