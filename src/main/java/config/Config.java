package config;

public class Config {
	
	private int delayMinute = 120;
	private String message = "You must wait %DELAY% minutes before creating a new Zygarde";
			
	public Config() {
		
	}
	
	public int getDelay() {
		return this.delayMinute;
	}

	public String getMessage() {
		return this.message;
	}
}
