package org.example.atumari.festival.dao;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * @Auth: K. J. S.
 * @Date: 2023. 07. 28.
 *
 */
public class LogPreparedStatement implements java.sql.PreparedStatement {
	private ArrayList parameterValues;
	private String sqlTemplate;
	private PreparedStatement wrappedStatement;

	//생성자
	public LogPreparedStatement(Connection connection, String sql) throws SQLException {
		wrappedStatement = connection.prepareStatement(sql);
		sqlTemplate = sql;
		parameterValues = new ArrayList();
	}

	//생성자
	public LogPreparedStatement(Connection connection, StringBuffer sql) throws SQLException {
		this(connection, sql.toString());
	}
	
	//오버라이드된 메서드
	@Override
	public void setArray(int parameterIndex, Array x) throws SQLException {
		wrappedStatement.setArray(parameterIndex, x);
		saveQueryParamValue(parameterIndex, x);
	}
	
	@Override
	public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
		wrappedStatement.setAsciiStream(parameterIndex, x);
		saveQueryParamValue(parameterIndex, x);
	}
	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
		wrappedStatement.setAsciiStream(parameterIndex, x, length);
		saveQueryParamValue(parameterIndex, x);
	}
	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
		wrappedStatement.setAsciiStream(parameterIndex, x, length);
		saveQueryParamValue(parameterIndex, x);
	}
	
	@Override
	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		wrappedStatement.setNull(parameterIndex, sqlType);
		saveQueryParamValue(parameterIndex, null);
	}
	
	@Override
	public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
		wrappedStatement.setNull(parameterIndex, sqlType, typeName);
		saveQueryParamValue(parameterIndex, null);
	}
	
	@Override
	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		wrappedStatement.setBoolean(parameterIndex, x);
		saveQueryParamValue(parameterIndex, new Boolean(x));
	}

	@Override
	public void setShort(int parameterIndex, short x) throws SQLException {
		wrappedStatement.setShort(parameterIndex, x);
		saveQueryParamValue(parameterIndex, new Integer(x));
	}

	@Override
	public void setInt(int parameterIndex, int x) throws SQLException {
		wrappedStatement.setInt(parameterIndex, x);
		saveQueryParamValue(parameterIndex, new Integer(x));
	}

	@Override
	public void setLong(int parameterIndex, long x) throws SQLException {
		wrappedStatement.setLong(parameterIndex, x);
		saveQueryParamValue(parameterIndex, new Long(x));
	}

	@Override
	public void setFloat(int parameterIndex, float x) throws SQLException {
		wrappedStatement.setFloat(parameterIndex, x);
		saveQueryParamValue(parameterIndex, new Float(x));
	}

	@Override
	public void setString(int parameterIndex, String x) throws SQLException {
		wrappedStatement.setString(parameterIndex, x);
		saveQueryParamValue(parameterIndex, x);
	}
	
	@Override
	public void setNString(int parameterIndex, String value) throws SQLException {
		wrappedStatement.setNString(parameterIndex, value);
		saveQueryParamValue(parameterIndex, value);
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
		wrappedStatement.setNCharacterStream(parameterIndex, value);
		saveQueryParamValue(parameterIndex, value);
	}
	
	@Override
	public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
		wrappedStatement.setNCharacterStream(parameterIndex, value, length);
		saveQueryParamValue(parameterIndex, value);
	}
	
	@Override
	public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
		wrappedStatement.setSQLXML(parameterIndex, xmlObject);
	}
	
	@Override
	public void setTime(int parameterIndex, Time x) throws SQLException {
		wrappedStatement.setTime(parameterIndex, x);
		saveQueryParamValue(parameterIndex, x);
	}
	
	@Override
	public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
		wrappedStatement.setTime(parameterIndex, x, cal);
		saveQueryParamValue(parameterIndex, x);
	}
	
	@Override
	public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
		wrappedStatement.setTimestamp(parameterIndex, x);
		saveQueryParamValue(parameterIndex, x);
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
		wrappedStatement.setTimestamp(parameterIndex, x, cal);
		saveQueryParamValue(parameterIndex, x);
	}
	
	@Override
	public void setObject(int parameterIndex, Object x) throws SQLException {
		wrappedStatement.setObject(parameterIndex, x);
		saveQueryParamValue(parameterIndex, x);		
	}
	
	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
		wrappedStatement.setObject(parameterIndex, x, targetSqlType);
		saveQueryParamValue(parameterIndex, x);
	}
	
	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
		wrappedStatement.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
		saveQueryParamValue(parameterIndex, x);
	}

	@Override
	public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
		wrappedStatement.setBigDecimal(parameterIndex, x);
		saveQueryParamValue(parameterIndex, x);
	}
	
	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
		wrappedStatement.setBinaryStream(parameterIndex, x, length);
		saveQueryParamValue(parameterIndex, x);
	}
	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
		wrappedStatement.setBinaryStream(parameterIndex, x, length);
	}
	@Override
	public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
		wrappedStatement.setBinaryStream(parameterIndex, x);
	}
	
	@Override
	public void setBlob(int parameterIndex, Blob x) throws SQLException {
		wrappedStatement.setBlob(parameterIndex, x);
		saveQueryParamValue(parameterIndex, x);
		
	}
	@Override
	public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
		wrappedStatement.setBlob(parameterIndex, inputStream, length);
	}
	@Override
	public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
		wrappedStatement.setBlob(parameterIndex, inputStream);
	}
	
	@Override
	public void setByte(int parameterIndex, byte x) throws SQLException {
		wrappedStatement.setByte(parameterIndex, x);
		saveQueryParamValue(parameterIndex, new Integer(x));
		
	}
	@Override
	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		wrappedStatement.setBytes(parameterIndex, x);
		saveQueryParamValue(parameterIndex, x);
	}
	
	@Override
	public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
		wrappedStatement.setCharacterStream(parameterIndex, reader);
	}
	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
		wrappedStatement.setCharacterStream(parameterIndex, reader, length);
		saveQueryParamValue(parameterIndex, reader);
		
	}
	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
		wrappedStatement.setCharacterStream(parameterIndex, reader, length);
	}
	
	@Override
	public void setClob(int parameterIndex, Reader reader) throws SQLException {
		wrappedStatement.setClob(parameterIndex, reader);
	}
	@Override
	public void setClob(int parameterIndex, Clob x) throws SQLException {
		wrappedStatement.setClob(parameterIndex, x);
		saveQueryParamValue(parameterIndex, x);
	}
	@Override
	public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
		wrappedStatement.setClob(parameterIndex, reader, length);
	}
	
	@Override
	public void setNClob(int parameterIndex, Reader reader) throws SQLException {
		wrappedStatement.setNClob(parameterIndex, reader);
	}
	@Override
	public void setNClob(int parameterIndex, NClob value) throws SQLException {
		wrappedStatement.setNClob(parameterIndex, value);
	}
	@Override
	public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
		wrappedStatement.setNClob(parameterIndex, reader, length);
	}
	
	@Override
	public void setDate(int parameterIndex, java.sql.Date x) throws SQLException {
		wrappedStatement.setDate(parameterIndex, x);
		saveQueryParamValue(parameterIndex, x);
	}
	@Override
	public void setDate(int parameterIndex, java.sql.Date x, Calendar cal) throws SQLException {
		wrappedStatement.setDate(parameterIndex, x, cal);
		saveQueryParamValue(parameterIndex, x);
	}
	
	@Override
	public void setDouble(int parameterIndex, double x) throws SQLException {
		wrappedStatement.setDouble(parameterIndex, x);
		saveQueryParamValue(parameterIndex, new Double(x));
	}
	
	@Override
	public void setRef(int parameterIndex, Ref x) throws SQLException {
		wrappedStatement.setRef(parameterIndex, x);
		saveQueryParamValue(parameterIndex, x);
	}
	
	@Override
	public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
		wrappedStatement.setUnicodeStream(parameterIndex, x, length);
		saveQueryParamValue(parameterIndex, x);
	}
	
	@Override
	public Connection getConnection() throws SQLException {
		return wrappedStatement.getConnection();
	}
	
	@Override
	public boolean execute() throws SQLException {
		return wrappedStatement.execute();
	}
	@Override
	public boolean execute(String sql) throws SQLException {
		return wrappedStatement.execute(sql);
	}
	@Override
	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		return wrappedStatement.execute(sql, autoGeneratedKeys);
	}
	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		return wrappedStatement.execute(sql, columnIndexes);
	}
	@Override
	public boolean execute(String sql, String[] columnNames) throws SQLException {
		return wrappedStatement.execute(sql, columnNames);
	}
	
	@Override
	public ResultSet executeQuery() throws SQLException {
		return wrappedStatement.executeQuery();
	}
	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		return wrappedStatement.executeQuery(sql);
	}
	@Override
	public int executeUpdate() throws SQLException {
		return wrappedStatement.executeUpdate();
	}
	@Override
	public int executeUpdate(String sql) throws SQLException {
		return wrappedStatement.executeUpdate(sql);
	}
	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		return wrappedStatement.executeUpdate(sql, autoGeneratedKeys);
	}
	@Override
	public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		return wrappedStatement.executeUpdate(sql, columnIndexes);
	}
	@Override
	public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		return wrappedStatement.executeUpdate(sql, columnNames);
	}
	@Override
	public int[] executeBatch() throws SQLException {
		return wrappedStatement.executeBatch();
	}
	
	@Override
	public void addBatch() throws SQLException {
		wrappedStatement.addBatch();
	}
	@Override
	public void addBatch(String sql) throws SQLException {
		wrappedStatement.addBatch(sql);
	}
	@Override
	public void clearBatch() throws SQLException {
		wrappedStatement.clearBatch();
	}

	@Override
	public void close() throws SQLException {
		wrappedStatement.close();
	}
	
	@Override
	public boolean isClosed() throws SQLException {
		return wrappedStatement.isClosed();
	}
	
	@Override
	public void closeOnCompletion() throws SQLException {
		wrappedStatement.closeOnCompletion();
	}
	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		return wrappedStatement.isCloseOnCompletion();
	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		return wrappedStatement.getMaxFieldSize();
	}

	@Override
	public void setMaxFieldSize(int max) throws SQLException {
		wrappedStatement.setMaxFieldSize(max);
	}

	@Override
	public int getMaxRows() throws SQLException {
		return wrappedStatement.getMaxRows();
	}

	@Override
	public void setMaxRows(int max) throws SQLException {
		wrappedStatement.setMaxRows(max);
	}

	@Override
	public void setEscapeProcessing(boolean enable) throws SQLException {
		wrappedStatement.setEscapeProcessing(enable);
	}

	@Override
	public int getQueryTimeout() throws SQLException {
		return wrappedStatement.getQueryTimeout();
	}

	@Override
	public void setQueryTimeout(int seconds) throws SQLException {
		wrappedStatement.setQueryTimeout(seconds);
	}

	@Override
	public void cancel() throws SQLException {
		wrappedStatement.cancel();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return wrappedStatement.getWarnings();
	}

	@Override
	public void clearWarnings() throws SQLException {
		wrappedStatement.clearWarnings();
	}

	@Override
	public void setCursorName(String name) throws SQLException {
		wrappedStatement.setCursorName(name);
	}
	
	@Override
	public ResultSet getResultSet() throws SQLException {
		return wrappedStatement.getResultSet();
	}
	@Override
	public int getResultSetType() throws SQLException {
		return wrappedStatement.getResultSetType();
	}
	@Override
	public int getResultSetConcurrency() throws SQLException {
		return wrappedStatement.getResultSetConcurrency();
	}
	@Override
	public int getResultSetHoldability() throws SQLException {
		return wrappedStatement.getResultSetHoldability();
	}

	@Override
	public int getUpdateCount() throws SQLException {
		return wrappedStatement.getUpdateCount();
	}

	@Override
	public boolean getMoreResults() throws SQLException {
		return wrappedStatement.getMoreResults();
	}
	@Override
	public boolean getMoreResults(int current) throws SQLException {
		return wrappedStatement.getMoreResults(current);
	}

	@Override
	public void setFetchDirection(int direction) throws SQLException {
		wrappedStatement.setFetchDirection(direction);
	}

	@Override
	public int getFetchDirection() throws SQLException {
		return wrappedStatement.getFetchDirection();
	}

	@Override
	public void setFetchSize(int rows) throws SQLException {
		wrappedStatement.setFetchSize(rows);
	}

	@Override
	public int getFetchSize() throws SQLException {
		return wrappedStatement.getFetchSize();
	}
	
	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		return wrappedStatement.getGeneratedKeys();
	}

	@Override
	public void setPoolable(boolean poolable) throws SQLException {
		wrappedStatement.setPoolable(poolable);
	}

	@Override
	public boolean isPoolable() throws SQLException {
		return wrappedStatement.isPoolable();
	}
	
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return wrappedStatement.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return wrappedStatement.isWrapperFor(iface);
	}

	@Override
	public void clearParameters() throws SQLException {
		wrappedStatement.clearParameters();
	}
	
	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		return wrappedStatement.getMetaData();
	}

	@Override
	public void setURL(int parameterIndex, URL x) throws SQLException {
		wrappedStatement.setURL(parameterIndex, x);
	}

	@Override
	public ParameterMetaData getParameterMetaData() throws SQLException {
		return wrappedStatement.getParameterMetaData();
	}

	@Override
	public void setRowId(int parameterIndex, RowId x) throws SQLException {
		wrappedStatement.setRowId(parameterIndex, x);
	}


	private void saveQueryParamValue(int position, Object obj) {
		String strValue;
		if (obj instanceof String || obj instanceof Date) {
			strValue = "'" + obj + "'";
		} else {
			if (obj == null) {
				strValue = "null";
			} else {
				strValue = obj.toString();
			}
		}
		while (position >= parameterValues.size()) {
			parameterValues.add(null);
		}
		parameterValues.set(position, strValue);
	}

	public String toString() {
		return this.getQueryString();
	}
	
	public String getQueryString() {
		StringBuffer buf = new StringBuffer();
		int qMarkCount = 0;
		ArrayList chunks = new ArrayList();
		StringTokenizer tok = new StringTokenizer(sqlTemplate + " ", "?");
		while (tok.hasMoreTokens()) {
			String oneChunk = tok.nextToken();
			buf.append(oneChunk);

			try {
				Object value;
				if (parameterValues.size() > 1 + qMarkCount) {
					value = parameterValues.get(1 + qMarkCount++);
				} else {
					if (tok.hasMoreTokens()) {
						value = null;
					} else {
						value = "";
					}
				}
				buf.append("" + value);
			} catch (Throwable e) {
				buf.append("Error : " + e.toString());
			}
		}
		return buf.toString().trim();
	}
}