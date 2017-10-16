package many2many;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/*
 * 教室实体类
 */
@Entity
public class Teachers {

	@Id
	@GeneratedValue(generator="tid")
	@GenericGenerator(name="tid",strategy="assigned")
	@Column(length=4)
	private String tid;// 教室的编号
	private String tname;// 姓名

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public Teachers() {
		super();
	}

	public Teachers(String tname, String tid) {
		super();
		this.tname = tname;
		this.tid = tid;
	}

}
