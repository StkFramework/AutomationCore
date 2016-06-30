/*=====================================================================================================
                          LEGAL NOTICE
------------------------------------------------------------------
Company Name: Softtek
Copyright Legend: © 2016 Softtek, Publisher.  All rights reserved.
No part of this publication may be reproduced, stored in a retrieval system, or transmitted in any form or by any means, electronic, 
mechanical, photocopy, recording or otherwise, without the prior written consent of the Publisher 
------------------------------------------------------------------
*/
package com.softtek.automation.structure.mapping;

import com.softtek.automation.structure.AbstractStructure;

public interface StructureMapperFactory {	
	
	Object getMappedObject(Class<? extends AbstractStructure> clazz) throws Exception;

}
