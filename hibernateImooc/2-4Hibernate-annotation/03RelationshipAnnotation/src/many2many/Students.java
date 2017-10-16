package many2many;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity; /*JPA注解*/
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	private String sname;
	private String gender;// 性别
	private Date birthday;// 出生日期
	private String major;// 专业
	@ManyToMany
	@JoinTable(name="teachers_students",joinColumns={@JoinColumn(name="sid")},inverseJoinColumns={@JoinColumn(name="tid")})
	private Set<Teachers> teacher; //学生持有教师的集合 

	
	public Set<Teachers> getTeacher() {
		return teacher;
	}

	public void setTeacher(Set<Teachers> teacher) {
		this.teacher = teacher;
	}

	public Students(int sid,String sname, String gender, Date birthday, String major) {
		// super();
		this.sid = sid;
		this.sname = sname;
		this.gender = gender;
		this.birthday = birthday;
		this.major = major;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Students() {
		super();
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
