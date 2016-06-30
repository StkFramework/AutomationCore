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


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogger {

	private static Logger LOGGER;

	public static Logger getInstance(Object object){		
		return LoggerFactory.getLogger(object.getClass()); 
	}
	
	public static void DEBUG(Object object, String message) {
		LOGGER = LoggerFactory.getLogger(object.getClass());
		
		LOGGER.debug(message);
	}
	
	public static void ERROR(Object object, String message) {
		LOGGER = LoggerFactory.getLogger(object.getClass());
		LOGGER.error(message);
	}
	
	public static void INFO(Object object, String message) {
		LOGGER = LoggerFactory.getLogger(object.getClass());
		LOGGER.info(message);
	}
}
