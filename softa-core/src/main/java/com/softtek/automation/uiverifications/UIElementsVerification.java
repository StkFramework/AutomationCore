/*=====================================================================================================
                          LEGAL NOTICE
------------------------------------------------------------------
Company Name: Softtek
Copyright Legend: © 2016 Softtek, Publisher.  All rights reserved.
No part of this publication may be reproduced, stored in a retrieval system, or transmitted in any form or by any means, electronic, 
mechanical, photocopy, recording or otherwise, without the prior written consent of the Publisher 
------------------------------------------------------------------
*/
package com.softtek.automation.uiverifications;

import org.openqa.selenium.WebDriver;

import com.softtek.automation.ExecutionResult;
import com.softtek.automation.driver.TestDriver;

public interface UIElementsVerification {
	
	void setTestDriver(TestDriver<WebDriver> driver);
	
	void setViewsSource(String source);
	ExecutionResult veryfyElements(String view) throws Exception;
}
