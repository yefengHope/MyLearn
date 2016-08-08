package org.noka.dbutil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.connection.ConnectionProvider;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DruidConnectionProvider implements  ConnectionProvider{
	private DruidDataSource dataSource;
	
	 public DruidConnectionProvider(){
	     dataSource = new DruidDataSource();
	 }
	 
	@Override
	public void close() throws HibernateException {
		if(null!=dataSource){
			dataSource.close();
		}
	}

	@Override
	public void closeConnection(Connection conn) throws SQLException {
		conn.close();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void configure(Properties pro) throws HibernateException {
		try
        {
			Map map = new HashMap();
			Set<Object> keys = pro.keySet();  
	        for(Object key: keys){
	            map.put(key, pro.get(key));
	        } 
            DruidDataSourceFactory.config(dataSource, map);
        }
        catch(SQLException e)
        {
            throw new IllegalArgumentException("config error", e);
        }
	}

	@Override
	public Connection getConnection() throws SQLException {
		 return dataSource.getConnection();
	}

	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}

}
