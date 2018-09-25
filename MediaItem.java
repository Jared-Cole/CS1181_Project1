import java.io.Serializable;

public class MediaItem implements Comparable<MediaItem>, Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String format;
	private String loanedTo;
	private String loanedOn;
	
	
	public MediaItem() {
		this.name = "NotSet";
		this.format = "Unknown";
		this.loanedTo = null;
		this.loanedOn = null;
	}


	public MediaItem(String name, String format) {
		this.name = name;
		this.format = format;
		
	}
	
	

	public MediaItem(String name, String format, String loanedTo, String loanedOn) {
		this.name = name;
		this.format = format;
		this.loanedTo = loanedTo;
		this.loanedOn = loanedOn;
	}


	@Override
	public int compareTo(MediaItem o) {		
		return this.name.compareTo(o.name);
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFormat() {
		return format;
	}


	public void setFormat(String format) {
		this.format = format;
	}


	@Override
	public String toString() {
		return  name + ", " + format + ", " + loanedTo + ", " + loanedOn + "\n";
	}


	public String getLoanedTo() {
		return loanedTo;
	}


	public void setLoanedTo(String loanedTo) {
		this.loanedTo = loanedTo;
	}


	public String getLoanedOn() {
		return loanedOn;
	}


	public void setLoanedOn(String loanedOn) {
		this.loanedOn = loanedOn;
	}
	

	
}//class
