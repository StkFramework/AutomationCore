/*=====================================================================================================
                          LEGAL NOTICE
------------------------------------------------------------------
Company Name: Softtek
Copyright Legend: © 2016 Softtek, Publisher.  All rights reserved.
No part of this publication may be reproduced, stored in a retrieval system, or transmitted in any form or by any means, electronic, 
mechanical, photocopy, recording or otherwise, without the prior written consent of the Publisher 
------------------------------------------------------------------
*/
package com.softtek.automation;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

public class ExecutionContext {

	private Map<String, Object> internalCache;
	private Map<String, Object> volatilContext;

	@PostConstruct
	public void init() {
		internalCache = new HashMap<String, Object>(ConstantsUtils.CONTEXT_CACHE_SIZE);
		volatilContext = new HashMap<String, Object>();

	}

	public Object getElementFromChache(String key) {
		if (internalCache.size() < ConstantsUtils.CONTEXT_CACHE_SIZE) {
			return internalCache.get(key);
		}
		return internalCache.remove(key);
	}

	public void putElementOnCache(String key, Object object) {
		internalCache.put(key, object);
	}

	public Object getElement(String key) {
		return this.volatilContext.get(key);
		//return this.volatilContext.remove(key);
	}

	public void putElement(String key, Object object) {
		this.volatilContext.put(key, object);
	}
	
	public Map<String, Object> getVolatileContext(){
		return this.volatilContext;
	}
	
	public Map<String, Object> getCacheContext(){
		return this.internalCache;
	}
}
