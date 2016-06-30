/*=====================================================================================================
                          LEGAL NOTICE
------------------------------------------------------------------
Company Name: Softtek
Copyright Legend: © 2016 Softtek, Publisher.  All rights reserved.
No part of this publication may be reproduced, stored in a retrieval system, or transmitted in any form or by any means, electronic, 
mechanical, photocopy, recording or otherwise, without the prior written consent of the Publisher 
------------------------------------------------------------------
*/
package com.softtek.automation.driver;

import java.util.Properties;

public interface TestDriver<T> {

	void setUpDriver();

	void refreshDriver();

	T getDriverInstance();

	Properties getProperties();

	void setProperties(Properties props);

}
