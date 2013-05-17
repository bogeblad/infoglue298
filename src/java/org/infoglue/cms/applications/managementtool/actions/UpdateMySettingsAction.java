/* ===============================================================================
 *
 * Part of the InfoGlue Content Management Platform (www.infoglue.org)
 *
 * ===============================================================================
 *
 *  Copyright (C)
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2, as published by the
 * Free Software Foundation. See the file LICENSE.html for more information.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY, including the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc. / 59 Temple
 * Place, Suite 330 / Boston, MA 02111-1307 / USA.
 *
 * ===============================================================================
 */

package org.infoglue.cms.applications.managementtool.actions;

import java.util.HashMap;
import java.util.Map;

import org.infoglue.cms.applications.common.actions.InfoGlueAbstractAction;
import org.infoglue.cms.util.ChangeNotificationController;
import org.infoglue.cms.util.NotificationMessage;
import org.infoglue.cms.util.RemoteCacheUpdater;

import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.module.propertyset.PropertySetManager;

/**
 * This class implements the action class for viewRepositoryProperties.
 * The use-case lets the user see all extra-properties for a repository
 * 
 * @author Mattias Bogeblad  
 */

public class UpdateMySettingsAction extends InfoGlueAbstractAction
{ 
	private PropertySet propertySet				= null; 
	
	private String languageCode 				= null;
	private String defaultToolId				= null;
	private String defaultRepositoryId			= null;

	    
    /**
     * The main method that fetches the Value-objects for this use-case
     */
    
    public String doExecute() throws Exception
    {
        Map args = new HashMap();
	    args.put("globalKey", "infoglue");
	    PropertySet ps = PropertySetManager.getInstance("jdbc", args);
	    
	    ps.setString("principal_" + this.getInfoGluePrincipal().getName() + "_languageCode", languageCode);
	    ps.setString("principal_" + this.getInfoGluePrincipal().getName() + "_defaultToolId", defaultToolId);
	    ps.setString("principal_" + this.getInfoGluePrincipal().getName() + "_defaultRepositoryId", defaultRepositoryId);

		NotificationMessage notificationMessage = new NotificationMessage("UpdateMySettingsAction.doExecute():", "ServerNodeProperties", this.getInfoGluePrincipal().getName(), NotificationMessage.SYSTEM, "0", "ServerNodeProperties");
		ChangeNotificationController.getInstance().addNotificationMessage(notificationMessage);
		
        return "success";
    }
    

    public void setLanguageCode(String languageCode)
    {
        this.languageCode = languageCode;
    }
    
    public void setDefaultToolId(String defaultToolId)
    {
        this.defaultToolId = defaultToolId;
    }

    public void setDefaultRepositoryId(String defaultRepositoryId)
    {
        this.defaultRepositoryId = defaultRepositoryId;
    }
}
