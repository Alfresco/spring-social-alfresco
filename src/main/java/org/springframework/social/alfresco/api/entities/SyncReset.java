/*
 * Copyright 2014 Alfresco Software, Ltd.  All rights reserved.
 *
 * License rights for this program may be obtained from Alfresco Software, Ltd. 
 * pursuant to a written agreement and any use of this program without such an 
 * agreement is prohibited. 
 */
package org.springframework.social.alfresco.api.entities;

import java.io.Serializable;

/**
 * 
 * @author steveglover
 *
 */
public class SyncReset implements Serializable
{
	private static final long serialVersionUID = 3779760431483705844L;

	private boolean resetAll;
	private ResetReason resetReason;

    public SyncReset()
    {
    }

    public SyncReset(ResetReason resetReason)
    {
        this.resetAll = false;
        this.resetReason = resetReason;
    }

    public SyncReset(boolean resetAll, ResetReason resetReason)
    {
        super();
        this.resetAll = resetAll;
        this.resetReason = resetReason;
    }

    public void setMessage(ResetReason resetReason)
	{
		this.resetReason = resetReason;
	}

	public ResetReason getResetReason()
	{
		return resetReason;
	}

	public boolean isResetAll()
    {
        return resetAll;
    }
    
    public void setResetAll(boolean resetAll)
    {
        this.resetAll = resetAll;
    }

    @Override
    public String toString()
    {
        return "Reset [resetAll=" + resetAll + ", resetReason=" + resetReason
                + "]";
    }
}
