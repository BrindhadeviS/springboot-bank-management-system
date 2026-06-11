package edu.jsp.BankingApplication.exception;

public class ResourcesNotFoundException  extends RuntimeException{

	String resourcesName;
	String fieldName;
	long fieldId;

	public ResourcesNotFoundException(String resourcesName, String fieldName, long fieldId) {
		this.resourcesName = resourcesName;
		this.fieldName = fieldName;
		this.fieldId = fieldId;
	}
	
	@Override
	public String getMessage() 
	{
		return  resourcesName  + " not found for " +  fieldName + " = " +  fieldId;
	}
	
	

}
