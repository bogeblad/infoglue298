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

import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.infoglue.cms.controllers.kernel.impl.simple.ContentTypeDefinitionController;
import org.infoglue.cms.controllers.kernel.impl.simple.UserControllerProxy;
import org.infoglue.cms.controllers.kernel.impl.simple.UserPropertiesController;
import org.infoglue.cms.entities.management.ContentTypeDefinitionVO;
import org.infoglue.cms.entities.management.UserProperties;
import org.infoglue.cms.entities.management.UserPropertiesVO;
import org.infoglue.cms.security.InfoGluePrincipal;
import org.infoglue.cms.util.CmsPropertyHandler;

public class ViewUserPropertiesAction extends ViewEntityPropertiesAction
{
    private final static Logger logger = Logger.getLogger(ViewUserPropertiesAction.class.getName());

	private static final long serialVersionUID = 1L;

	private String userName;
	private UserPropertiesVO userPropertiesVO;
	private List userPropertiesVOList;
	
	
	/**
	 * Initializes all properties needed for the usecase.
	 * @param extranetUserId
	 * @throws Exception
	 */

	protected void initialize(String userName) throws Exception
	{
	    super.initialize();
				
		logger.info("userName:" + userName);
		
		List contentTypeDefinitionVOList = UserPropertiesController.getController().getContentTypeDefinitionVOList(userName);
		if(contentTypeDefinitionVOList != null && contentTypeDefinitionVOList.size() > 0)
			this.setContentTypeDefinitionVO((ContentTypeDefinitionVO)contentTypeDefinitionVOList.get(0));
		
		InfoGluePrincipal infoGluePrincipal = UserControllerProxy.getController().getUser(userName);
		userPropertiesVOList = UserPropertiesController.getController().getUserPropertiesVOList(userName, this.getLanguageId());
		if(userPropertiesVOList != null && userPropertiesVOList.size() > 0)
		{
			this.userPropertiesVO = (UserPropertiesVO)userPropertiesVOList.get(0);
			this.setContentTypeDefinitionId(this.userPropertiesVO.getContentTypeDefinitionId());
		}
		else
		{
			this.setContentTypeDefinitionId(this.getContentTypeDefinitionVO().getContentTypeDefinitionId());
		}
		
		logger.info("this.userPropertiesVO:" + this.userPropertiesVO);
		
		this.setAttributes(ContentTypeDefinitionController.getController().getContentTypeAttributes(this.getContentTypeDefinitionVO().getSchemaValue()));	
	
		logger.info("attributes:" + this.getContentTypeAttributes().size());		
		logger.info("availableLanguages:" + this.getAvailableLanguages().size());		
		
	} 

	public String doExecute() throws Exception
	{
		this.initialize(getUserName());   
		
        this.setCurrentAction("ViewUserProperties.action");
        this.setUpdateAction("UpdateUserProperties");
        this.setUpdateAndExitAction("UpdateUserProperties");
        this.setCancelAction("ViewSystemUser!v3.action");
        this.setToolbarKey("tool.managementtool.viewUserProperties.header");
        this.setTitleKey("tool.managementtool.viewUserProperties.header");
        this.setArguments("");
        this.setEntityName(UserProperties.class.getName());

		return "success";
	}

	public String doV3() throws Exception
	{
		this.initialize(getUserName());   

        this.setCurrentAction("ViewUserProperties!v3.action");
        this.setUpdateAction("UpdateUserProperties!v3");
        this.setUpdateAndExitAction("UpdateUserProperties!saveAndExitV3");
        this.setCancelAction("ViewSystemUser!v3.action");
        this.setToolbarKey("tool.managementtool.viewUserProperties.header");
        this.setTitleKey("tool.managementtool.viewUserProperties.header");
        this.setArguments("");
        this.setEntityName(UserProperties.class.getName());

		return "successV3";
	}

	/**
	 * Returns a list of digital assets available for this content version.
	 */
	
	public List getDigitalAssets()
	{
		List digitalAssets = null;
		
		try
		{
			if(this.userPropertiesVO != null && this.userPropertiesVO.getId() != null)
	       	{
	       		digitalAssets = UserPropertiesController.getController().getDigitalAssetVOList(this.userPropertiesVO.getId());
	       	}
		}
		catch(Exception e)
		{
			logger.warn("We could not fetch the list of digitalAssets: " + e.getMessage(), e);
		}
		
		return digitalAssets;
	}	

	
	
	

	/**
	 * Returns all current Category relationships for th specified attrbiute name
	 * @param attribute
	 * @return
	 */
	public List getRelatedCategories(String attribute)
	{
		try
		{
			if(this.userPropertiesVO != null && this.userPropertiesVO.getId() != null)
		    	return getPropertiesCategoryController().findByPropertiesAttribute(attribute, UserProperties.class.getName(),  this.userPropertiesVO.getId());
		}
		catch(Exception e)
		{
			logger.warn("We could not fetch the list of defined category keys: " + e.getMessage(), e);
		}

		return Collections.EMPTY_LIST;
	}

	
	public String getXML()
	{
	    return (this.userPropertiesVO == null) ? null : this.userPropertiesVO.getValue();
	}

	
	public String getUserName()
	{
		return userName;
	}

	public UserPropertiesVO getUserPropertiesVO()
	{
		return userPropertiesVO;
	}

	public List getUserPropertiesVOList()
	{
		return userPropertiesVOList;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
		this.setOwnerEntityId(userName);
	}
    
    public Integer getEntityId()
    {
        return this.userPropertiesVO.getId();
    }
    
    public void setOwnerEntityId(String ownerEntityId)
    {
        super.setOwnerEntityId(ownerEntityId);
        this.userName = ownerEntityId;
    }
     
    public String getReturnAddress() throws Exception
    {
    	String URIEncoding = CmsPropertyHandler.getURIEncoding();
        return this.getCurrentAction() + "?userName=" + URLEncoder.encode(this.userName, URIEncoding) + "&languageId=" + this.getLanguageId();
    }
    
    public String getCancelAddress() throws Exception
    {
    	String URIEncoding = CmsPropertyHandler.getURIEncoding();
        return this.getCancelAction() + "?userName=" + URLEncoder.encode(this.userName, URIEncoding);
    }

}
