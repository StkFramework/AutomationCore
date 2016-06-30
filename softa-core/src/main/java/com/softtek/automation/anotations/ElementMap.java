/*=====================================================================================================
                          LEGAL NOTICE
------------------------------------------------------------------
Company Name: Softtek
Copyright Legend: � 2016 Softtek, Publisher.  All rights reserved.
No part of this publication may be reproduced, stored in a retrieval system, or transmitted in any form or by any means, electronic, 
mechanical, photocopy, recording or otherwise, without the prior written consent of the Publisher 
------------------------------------------------------------------
*/
package com.softtek.automation.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.softtek.automation.element.How;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface ElementMap {

	// String id();

	How how() default How.XPATH;

	String using();
	
	boolean verify() default false;

}
