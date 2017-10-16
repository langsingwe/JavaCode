package one2one;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity; /*JPA注解*/
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/*
 * 学生实体类
 */
@Entity
public class Students {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sid;// 学号
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="pid",unique=true)
	private IdCard card;
	private String gender;// 性别
	private Date birthday;// 出生日期
	private String major;// 专业

	public Students() {
		super();
	}

	public Students(int sid, String gender, Date birthday, String major, IdCard card) {
		this.sid = sid;
		this.gender = gender;
		this.birthday = birthday;
		this.major = major;
		this.card = card;
	}
	

	public Students(IdCard card, String gender, Date birthday, String major) {
		super();
		this.card = card;
		this.gender = gender;
		this.birthday = birthday;
		this.major = major;
	}

	public IdCard getCard() {
		return card;
	}

	public void setCard(IdCard card) {
		this.card = card;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

}
