package cc.wei.hibernate.day1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.omg.PortableServer.THREAD_POLICY_ID;

import cc.wei.hibernate.day1.domain.User;

public class UserDaoJdbcImpl implements IUserDao {

	@Override
	public void save(User u) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn=JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			ps=conn.prepareStatement("insert into user(name,password,sex,borndate) values (?,?,?,?)");
			ps.setString(1, u.getName());
			ps.setString(2, u.getPassword());
			ps.setInt(3, u.getSex());
			ps.setDate(4, new java.sql.Date(u.getBornDate().getTime()));
			ps.execute();
			
			conn.commit();
		}catch(Exception e) {
			try {
				if(conn!=null) {
					conn.rollback();
				}
			}catch(Exception e1) {
				e1.printStackTrace();
				throw new RuntimeException();
			}
		}finally {
			try {
				if(ps!=null)
					ps.close();
				if(conn!=null)
					conn.close();
			}catch (Exception e2) {
				e2.printStackTrace();
				throw new RuntimeException();
			}
		}
		
	}

	@Override
	public void delete(Long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn=JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			ps=conn.prepareStatement("delete from user where id = ?");
			ps.setLong(1, id);
			ps.executeUpdate();
			
			conn.commit();
		}catch(Exception e) {
			try {
				if(conn!=null) {
					conn.rollback();
				}
			}catch(Exception e1) {
				e1.printStackTrace();
				throw new RuntimeException();
			}
		}finally {
			try {
				if(ps!=null)
					ps.close();
				if(conn!=null)
					conn.close();
			}catch (Exception e2) {
				e2.printStackTrace();
				throw new RuntimeException();
			}
		}

	}

	@Override
	public void update(User u) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn=JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			ps=conn.prepareStatement("update user set name=?,password=?,sex=?,borndate=? where id =?");
			ps.setString(1, u.getName());
			ps.setString(2, u.getPassword());
			ps.setInt(3, u.getSex());
			ps.setDate(4, new java.sql.Date(u.getBornDate().getTime()));
			ps.setLong(5, u.getId());
			ps.executeUpdate();
			
			conn.commit();
		}catch(Exception e) {
			try {
				if(conn!=null) {
					conn.rollback();
				}
			}catch(Exception e1) {
				e1.printStackTrace();
				throw new RuntimeException();
			}
		}finally {
			try {
				if(ps!=null)
					ps.close();
				if(conn!=null)
					conn.close();
			}catch (Exception e2) {
				e2.printStackTrace();
				throw new RuntimeException();
			}
		}

	}

	@Override
	public User get(Long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn=JdbcUtil.getConnection();
			ps=conn.prepareStatement("select * from USER where id=?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			List<User> users = this.mapping(rs);
			if(users.size()==1) return users.get(0);
			return null;
		}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			
		}finally {
			try {
				if(ps!=null)
					ps.close();
				if(conn!=null)
					conn.close();
			}catch (Exception e2) {
				e2.printStackTrace();
				throw new RuntimeException();
			}
		}
	}

	private List<User> mapping(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<User> us = new ArrayList<User>();
		if(rs!=null) {
			while(rs.next()) {
				User u = new User();
				u.setId(rs.getLong("id"));
				u.setName(rs.getString("name"));
				u.setPassword(rs.getString("password"));
				u.setSex(rs.getInt("sex"));
				u.setBornDate(new Date(rs.getDate("borndate").getTime()));
				us.add(u);
			}
		}
		return us;
	}

	@Override
	public List<User> getAllUsers() {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn=JdbcUtil.getConnection();
			ps=conn.prepareStatement("select * from USER");
			ResultSet rs = ps.executeQuery();
			List<User> users = this.mapping(rs);
			return users;
			
		}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
		}finally {
			try {
				if(ps!=null)
					ps.close();
				if(conn!=null)
					conn.close();
			}catch (Exception e2) {
				e2.printStackTrace();
				throw new RuntimeException();
			}
		}
	}

	@Override
	public List<User> findUsersByNames(String name) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn=JdbcUtil.getConnection();
			ps=conn.prepareStatement("select * from USER where name like ?");
			ps.setString(1, "%"+name+"%");
			ResultSet rs = ps.executeQuery();
			List<User> users = this.mapping(rs);
			return users;
			
		}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
		}finally {
			try {
				if(ps!=null)
					ps.close();
				if(conn!=null)
					conn.close();
			}catch (Exception e2) {
				e2.printStackTrace();
				throw new RuntimeException();
			}
		}
	}

}
