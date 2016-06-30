/*=====================================================================================================
                          LEGAL NOTICE
------------------------------------------------------------------
Company Name: Softtek
Copyright Legend: © 2016 Softtek, Publisher.  All rights reserved.
No part of this publication may be reproduced, stored in a retrieval system, or transmitted in any form or by any means, electronic, 
mechanical, photocopy, recording or otherwise, without the prior written consent of the Publisher 
------------------------------------------------------------------
*/
package com.softtek.automation.logic;

import java.util.List;

import org.springframework.stereotype.Component;

import com.softtek.automation.ExecutionContext;
import com.softtek.automation.ExecutionResult;
import com.softtek.automation.TestLogger;
import com.softtek.automation.steps.AbstractSteps;
import com.softtek.automation.structure.AbstractStructure;

@Component
public abstract class  AbstractBusinessCase extends AbstractSteps{	
	
	public ExecutionResult run(ExecutionContext context){
		
		TestLogger.getInstance(this).info("Running ...");
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
			//sourceList.get(i).compare(targetList.get(i));
			TestLogger.getInstance(this).info("comparing indexes -> " + i);
			compareStructures(sourceList.get(i), targetList.get(i));
		}
		
	}
	 
	protected void compareStructures(AbstractStructure source, AbstractStructure target){
		source.compare(target);
	}
}
