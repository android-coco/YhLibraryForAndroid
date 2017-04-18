package org.yh.library.bean;

import android.content.Intent;

@SuppressWarnings("serial")
public class EventBusBean extends YHModel
{
	private Intent intent;
	
	public EventBusBean()
	{
		super();
	}
	
	public EventBusBean(Intent intent)
	{
		super();
		this.intent = intent;
	}
	
	public Intent getIntent()
	{
		return intent;
	}
	
	public void setIntent(Intent intent)
	{
		this.intent = intent;
	}
	
}
