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

import java.io.IOException;

import org.apache.log4j.Logger;
import org.infoglue.cms.applications.common.actions.InfoGlueAbstractAction;
import org.infoglue.cms.util.CmsPropertyHandler;

/**
 */

public class AdminReleaseAction extends InfoGlueAbstractAction
{
	private static final long serialVersionUID = 1L;
	
    private final static Logger logger = Logger.getLogger(AdminReleaseAction.class.getName());

 	private String adminCommand = "";
 	private String output = "";
 
    public String doExecute() throws Exception
    {
		output = ExecuteCommand(getAdminCommand());
        return "success";
    }

	private static String executeUnix(String unixCmd) throws IOException
	{
		Process pr = Runtime.getRuntime().exec(unixCmd); 
		String str;
		String op ="";

		try { 
			   java.io.InputStream is = pr.getInputStream();
			   java.io.InputStreamReader isr = new java.io.InputStreamReader(is);
			   java.io.BufferedReader br = new java.io.BufferedReader(isr);
			   while ((str = br.readLine()) != null) {
			   	logger.info(str);
					op+=str +"<br>";
			   }
			   is.close();
				op+="<font color='red'>";
			   is = pr.getErrorStream();
			   isr = new java.io.InputStreamReader(is);
			   br = new java.io.BufferedReader(isr);
			   
			   while ((str = br.readLine()) != null) {
			   	logger.info(str);
					op+=str +"<br>";
			   }
			   is.close();
				op+="</font>";
			   
		} 
		catch (Exception e) { 
		    logger.info("InterruptedException raised: "+e.getMessage()); 
			op += e.getMessage();
		} 
			
		
		return op;
		
	}


	public static String ExecuteCommand (String cmd) throws Exception
	{ 
		String op = "RESULT OF ACTION:<br>";
		String buildName = CmsPropertyHandler.getBuildName();
		String admin_tools = CmsPropertyHandler.getAdminToolsPath();
		String dbRelease = CmsPropertyHandler.getDbRelease();
		String dbScriptPath = CmsPropertyHandler.getDbScriptPath();

		logger.info("admin tools: " + admin_tools);
		logger.info("cmd: " + cmd);
		
		if (admin_tools == null) return "error: cant find admintools";
		
		if (cmd.compareTo("MAKEDEFAULT")==0)
		{
		    logger.info("Executing makedefault ");
			String unixCmd = "sh " + admin_tools + "/makedefaultrelease.sh " + buildName;
			try { 
				op+=executeUnix(unixCmd);
			} 
			catch (Exception e) { 
			    logger.info("Exception raised: "+e.getMessage()); 
				op += e.getMessage();
			} 			
		}

		if (cmd.compareTo("RELOADDATA")==0)
		{
			// String unixCmd[] = {"sh", admin_tools + "/reloaddata.sh", dbScriptPath};
			String unixCmd = "sh " + admin_tools + "/reloaddata.sh " + dbScriptPath;

			logger.info("Executing reloaddata ");

			try { 
				op+=executeUnix(unixCmd);
			} 
			catch (Exception e) { 
			    logger.info("Exception raised: "+e.getMessage()); 
				op += e.getMessage();
			} 
			
		}
		
		return op;
		
	} 
               

	/**
	 * Returns the adminCommand.
	 * @return String
	 */
	public String getAdminCommand()
	{
		return this.adminCommand;
	}

	/**
	 * Sets the adminCommand.
	 * @param adminCommand The adminCommand to set
	 */
	public void setAdminCommand(String adminCommand)
	{
		this.adminCommand = adminCommand;
	}

	/**
	 * Returns the output.
	 * @return String
	 */
	public String getOutput()
	{
		return output;
	}

	/**
	 * Sets the output.
	 * @param output The output to set
	 */
	public void setOutput(String output)
	{
		this.output = output;
	}

}
