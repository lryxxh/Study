package demail.com.kd.dmail.watch;

/**
 * 
 * @author xuzhiqi
 */
public class UserObject {
	private String name = "";
	private String ip = "";
	private String fq = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getFq() {
		return fq;
	}

	public void setFq(String fq) {
		this.fq = fq;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
