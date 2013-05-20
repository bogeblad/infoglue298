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

package org.infoglue.cms.entities.management;

import org.infoglue.cms.entities.kernel.IBaseEntity;
import org.infoglue.cms.exception.ConstraintException;

public interface SystemUser extends IBaseEntity
{
    public SystemUserVO getValueObject();
    
    public void setValueObject(SystemUserVO valueObject);

    public java.lang.String getUserName();
    
    public void setUserName(java.lang.String userName) throws ConstraintException;
    
    public java.lang.String getPassword();
    
    public void setPassword(java.lang.String password) throws ConstraintException;
    
    public java.lang.String getFirstName();
    
    public void setFirstName(java.lang.String firstName) throws ConstraintException;
    
    public java.lang.String getLastName();
    
    public void setLastName(java.lang.String lastName) throws ConstraintException;
    
    public java.lang.String getEmail();
    
    public void setEmail(java.lang.String email) throws ConstraintException;
    
    public java.util.Collection getRoles();
    
    public void setRoles(java.util.Collection roles);

    public java.util.Collection getGroups();
    
    public void setGroups(java.util.Collection groups);

}
