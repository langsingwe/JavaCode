package many2one;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity; /*JPA注解*/
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/*
 * 学生实体类
 */
@Entity
public class Students {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sid;// 学号
	private String gender;// 性别
	private Date birthday;// 出生日期
	private String major;// 专业
	@ManyToOne(cascade= {CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="pid",referencedColumnName="CID")
	private ClassRoom classRoom;

	public Students(int sid, String gender, Date birthday, String major, ClassRoom classRoom) {
		super();
		this.sid = sid;
		this.gender = gender;
		this.birthday = birthday;
		this.major = major;
		this.classRoom = classRoom;
	}

	
	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}

	public Students() {
		super();
	}

	public Students(int sid, String gender, Date birthday, String major) {
		this.sid = sid;
		this.gender = gender;
		this.birthday = birthday;
		this.major = major;
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
