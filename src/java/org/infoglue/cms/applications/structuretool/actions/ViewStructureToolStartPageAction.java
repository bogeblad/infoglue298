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

package org.infoglue.cms.applications.structuretool.actions;

import org.infoglue.cms.applications.common.actions.InfoGlueAbstractAction;
import org.infoglue.cms.controllers.kernel.impl.simple.SiteNodeController;
import org.infoglue.cms.entities.structure.SiteNodeVO;


/**
 * This class implements the action class for the startpage in the siteNode tool.
 * 
 * @author Mattias Bogeblad  
 */

public class ViewStructureToolStartPageAction extends InfoGlueAbstractAction
{
	private static final long serialVersionUID = 1L;

    private Integer repositoryId;
    
    public String doExecute() throws Exception
    {
    	return "success";
    }
          
    public void setRepositoryId(Integer repositoryId)
    {
    	this.repositoryId = repositoryId;
    }
    
    public Integer getRootSiteNodeId()
    {
    	try
    	{
    		if(this.repositoryId != null && this.repositoryId.intValue() > -1)
    		{
    			SiteNodeVO siteNodeVO = SiteNodeController.getController().getRootSiteNodeVO(this.repositoryId);
	    		if(siteNodeVO != null)
	    			return siteNodeVO.getSiteNodeId();
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	return null;
    }       
         
}
