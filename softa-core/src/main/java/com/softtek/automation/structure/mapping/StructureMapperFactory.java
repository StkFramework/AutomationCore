package com.softtek.automation.structure.mapping;

import com.softtek.automation.structure.AbstractStructure;

public interface StructureMapperFactory {	
	
	Object getMappedObject(Class<? extends AbstractStructure> clazz) throws Exception;

}
