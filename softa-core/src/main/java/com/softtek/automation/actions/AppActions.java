/*=====================================================================================================
                          LEGAL NOTICE
------------------------------------------------------------------
Company Name: Softtek
Copyright Legend: © 2016 Softtek, Publisher.  All rights reserved.
No part of this publication may be reproduced, stored in a retrieval system, or transmitted in any form or by any means, electronic, 
mechanical, photocopy, recording or otherwise, without the prior written consent of the Publisher 
------------------------------------------------------------------
*/
package com.softtek.automation.actions;

import com.softtek.automation.Application;
import com.softtek.automation.ApplicationsSet;
import com.softtek.automation.ExecutionResult;

public interface AppActions extends Actions {

	Application getApplication();

	void setApplication(Application application);

	void setApplicationsSet(ApplicationsSet applicationsSet);

	ApplicationsSet getApplicationsSet();

	ExecutionResult OpenApplication(String name);

	ExecutionResult OpenDefault();

	ExecutionResult ChangeToApp(String name);

	ExecutionResult CloseApp(String name);

	ExecutionResult CloseCurrentApp();
	
	ExecutionResult WaitForSeconds(String seconds) throws Exception;

	ExecutionResult MoveToUrl(String name) throws Exception;
	
	ExecutionResult MoveToFrame(String name) throws Exception;
	
	ExecutionResult MoveToIdFrame(String name) throws Exception;
	
	ExecutionResult MoveToDefaultContent() throws Exception;
	
	ExecutionResult MoveToWindow(String index) throws Exception;

	ExecutionResult DoAcceptInAlert() throws Exception;

	ExecutionResult GetTextFromAlert() throws Exception;
	
	
}
