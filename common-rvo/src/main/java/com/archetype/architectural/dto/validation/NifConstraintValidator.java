package com.archetype.architectural.dto.validation;

import com.archetype.architectural.dto.domainx.FindClientStatusQuery;
import com.archetype.base.core.model.request.BaseRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NifConstraintValidator implements ConstraintValidator<ValidateNif, BaseRequest> {

	@Override
	public boolean isValid(BaseRequest value, ConstraintValidatorContext context) {

		var nif=Integer.valueOf( ((FindClientStatusQuery) value).getNumeroDocumento().substring(0, ((FindClientStatusQuery) value).getNumeroDocumento().length()-1));
		var letter=((FindClientStatusQuery) value).getNumeroDocumento().substring( ((FindClientStatusQuery) value).getNumeroDocumento().length()-1,((FindClientStatusQuery) value).getNumeroDocumento().length());
    	var letters = "TRWAGMYFPDXBNJZSQVHLCKE";
   		Character lettervalue=letters.charAt(nif % letters.length());
    	return lettervalue.toString().equalsIgnoreCase(letter) ;
	}

}