package com.example.tatangit.lautannusantara.Library.OpenWeather.Model.Hourly;

import com.google.gson.annotations.SerializedName;

public class Sys{

	@SerializedName("pod")
	private String pod;

	public void setPod(String pod){
		this.pod = pod;
	}

	public String getPod(){
		return pod;
	}

	@Override
 	public String toString(){
		return 
			"Sys{" + 
			"pod = '" + pod + '\'' + 
			"}";
		}
}