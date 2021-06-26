package data;

public class ZygardeTime implements IZygardeTime {

	private long time;
	
	public ZygardeTime() {
		this.time = 0;
	}
	
	@Override
	public long getTime() {
		return this.time;
	}

	@Override
	public void setTime(long time) {
		this.time = time;
	}
}
