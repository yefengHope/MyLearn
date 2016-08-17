package org.noka.item;
import java.io.Serializable;
import java.util.Date;

/**
* NK_SYS_LOG
*/
public class LogItem implements  Serializable {
     
	private static final long serialVersionUID = 5551057058631551307L;
	private Long loid = null;// bigint identity ;
	private Date lodate;//操作时间
	private String loaddr;//操作IP地址
	private Long louserid;//操作人ID
	private String lotext;//日志内容
	private String lobinfo;//浏览器信息
	private String loalt1;//备用字段1
	private String loalt2;//备用字段2
	private String loalt3;//备用字段3
	private String loalt4;//备用字段4
	private String loalt5;//备用字段5

	public Long getLoid() {
		return loid;
	}

	public void setLoid(Long loid) {
		this.loid = loid;
	}

	public Date getLodate() {
		return lodate;
	}

	public void setLodate(Date lodate) {
		this.lodate = lodate;
	}

	public String getLoaddr() {
		return loaddr;
	}

	public void setLoaddr(String loaddr) {
		this.loaddr = loaddr;
	}

	public Long getLouserid() {
		return louserid;
	}

	public void setLouserid(Long louserid) {
		this.louserid = louserid;
	}

	public String getLotext() {
		return lotext;
	}

	public void setLotext(String lotext) {
		this.lotext = lotext;
	}

	public String getLobinfo() {
		return lobinfo;
	}

	public void setLobinfo(String lobinfo) {
		this.lobinfo = lobinfo;
	}

	public String getLoalt1() {
		return loalt1;
	}

	public void setLoalt1(String loalt1) {
		this.loalt1 = loalt1;
	}

	public String getLoalt2() {
		return loalt2;
	}

	public void setLoalt2(String loalt2) {
		this.loalt2 = loalt2;
	}

	public String getLoalt3() {
		return loalt3;
	}

	public void setLoalt3(String loalt3) {
		this.loalt3 = loalt3;
	}

	public String getLoalt4() {
		return loalt4;
	}

	public void setLoalt4(String loalt4) {
		this.loalt4 = loalt4;
	}

	public String getLoalt5() {
		return loalt5;
	}

	public void setLoalt5(String loalt5) {
		this.loalt5 = loalt5;
	}
}
