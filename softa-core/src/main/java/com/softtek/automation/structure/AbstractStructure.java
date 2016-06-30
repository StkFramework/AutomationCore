/*=====================================================================================================
                          LEGAL NOTICE
------------------------------------------------------------------
Company Name: Softtek
Copyright Legend: © 2016 Softtek, Publisher.  All rights reserved.
No part of this publication may be reproduced, stored in a retrieval system, or transmitted in any form or by any means, electronic, 
mechanical, photocopy, recording or otherwise, without the prior written consent of the Publisher 
------------------------------------------------------------------
*/
package com.softtek.automation.structure;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.softtek.automation.structure.mapping.ElementLink;

public abstract class AbstractStructure {


	public void compare(Object compareObject)  {        
		
		//BeanMap map = new BeanMap(oldObject);
		try{
			Object currentObject = this;
			BeanMap map = new BeanMap(currentObject);
		
		    PropertyUtilsBean propUtils = new PropertyUtilsBean();
		
		    for (Object propNameObject : map.keySet()) {
		        String propertyName = (String) propNameObject;            
		        Object property1;
				
				property1 = propUtils.getProperty(currentObject, propertyName);
				
		        Object property2 = propUtils.getProperty(compareObject, propertyName);
		        
		        //System.out.println("Property => "  + propertyName + " | OLD_ObjectProVal = " + property1 + " | NEW_ObjectProVal = " + property2);
		    	        
		        if (property1 == null) continue;
		        
		        if (!property1.equals(property2)) {
		            throw new AssertionError("Fail:Field values doesn't match comparing " + this.getClass().getCanonicalName() + 
		            		" classes : [ currentObject."  + propertyName + " = " + property1 + " | compareObject." + propertyName +" = " + property2 + "]" );
		        } 
		    }
		} catch (IllegalAccessException e) {
			throw new Error(e);
		} catch (InvocationTargetException e) {
			throw new Error(e);
		} catch (NoSuchMethodException e) {
			throw new Error(e);
		}

	}

	public void copyValuesFrom(Object source){
		
	    PropertyUtilsBean propUtils = new PropertyUtilsBean();
	    
	  //Gets propertys map from target object (this)
	    BeanMap targetBeanMap = new BeanMap(this);
	    
	    //Gets propertys map from source object
	    BeanMap sourceBeanMap = new BeanMap(source);	    
	   	    
	    for (Object propNameObject : sourceBeanMap.keySet()) {
	    	
	    	try {
				
	    		//Avoid trhows an exception if a property from source object doesn't exist in target object. If all properties doesn't match don't copy anything.
	    		if(!targetBeanMap.containsKey(propNameObject.toString())){continue;}	    		
	    		
	    		//MANDATORY: Avoid trhows an exception for property class
	    		if (propNameObject.toString().equals("class")){continue;}
				
	    		//Gets the property from target object (this)
	    		PropertyDescriptor thisPropDesc = new PropertyDescriptor((String)propNameObject, this.getClass());
					    		
	    		//Get the value from source object
	    		Object value = sourceBeanMap.get(propNameObject.toString());
				
				if(value!= null ){
					//Set the property value to the target object (this)
					propUtils.getWriteMethod(thisPropDesc).invoke(this, value);
				}	    	
	    	
	    	} catch (IntrospectionException e) {
	    		throw new Error(e);
			} catch (IllegalArgumentException e) {
				throw new Error(e);
			} catch (IllegalAccessException e) {
				throw new Error(e);
			} catch (InvocationTargetException e) {
				throw new Error(e);
			}
	    }
	}
		
	public String toString(){	
		
		return ReflectionToStringBuilder.toString(this);		
	}
	

	

}
