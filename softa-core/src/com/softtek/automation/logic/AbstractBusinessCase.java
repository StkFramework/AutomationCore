package com.softtek.automation.logic;

import java.util.List;

import com.softtek.automation.ExecutionContext;
import com.softtek.automation.ExecutionResult;
import com.softtek.automation.structure.AbstractStructure;

public abstract class  AbstractBusinessCase {

	
	
	
	public ExecutionResult run(ExecutionContext context){
		ExecutionResult result = new ExecutionResult();
		
		init(context, result);
			
		if(result.isValidResult()){
			
			fillFormWithData(context, result);
			
			if(result.isValidResult()){
				
				readDataSources(context,result);			
				
				if(result.isValidResult()){
					
					fillWithData(context, result);
					
					if(result.isValidResult()){
						checkCase(context,result);
					}
				}
			}			
		}		
		
		return result;		
	}
	 
	protected abstract void init(ExecutionContext context, ExecutionResult result);
	protected abstract void fillFormWithData(ExecutionContext context, ExecutionResult result);
	protected abstract void readDataSources(ExecutionContext context, ExecutionResult result);
	protected abstract void fillWithData(ExecutionContext context, ExecutionResult result);	
	protected abstract void checkCase(ExecutionContext context, ExecutionResult result);
	
	
	protected void compareAbstractStructures(List<? extends AbstractStructure> sourceList, List<? extends AbstractStructure> targetList){
		
		for(int i=0; i < sourceList.size(); i++){			
			sourceList.get(i).compare(targetList.get(i));			
		}
		
	}
}
