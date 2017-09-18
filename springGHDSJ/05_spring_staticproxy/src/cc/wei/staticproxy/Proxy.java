package cc.wei.staticproxy;
/*
 * 中介
 */
public class Proxy implements Rent {
	private Host host;
	@Override
	public void rent() {
		fare();
		host.rent();
	}
	public void fare() {
		System.out.println("收取中介费");
	}
	public void setHost(Host host) {
		this.host = host;
	}
}
