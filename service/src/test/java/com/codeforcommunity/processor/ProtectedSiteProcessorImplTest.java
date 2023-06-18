package com.codeforcommunity.processor;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.codeforcommunity.auth.JWTData;
import com.codeforcommunity.enums.PrivilegeLevel;
import com.codeforcommunity.exceptions.ResourceDoesNotExistException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultDSLContext;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ProtectedSiteProcessorImplTest {

  /**
   * Method under test: {@link ProtectedSiteProcessorImpl#adoptSite(JWTData, int, Date)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testAdoptSite() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   org.jooq.exception.DetachedException: Cannot execute query. No Connection configured
    //       at org.jooq_3.12.4.SQL99.debug(null)
    //       at org.jooq.impl.AbstractQuery.execute(AbstractQuery.java:332)
    //       at org.jooq.impl.AbstractResultQuery.fetchLazy(AbstractResultQuery.java:501)
    //       at org.jooq.impl.AbstractResultQuery.fetchLazy(AbstractResultQuery.java:470)
    //       at org.jooq.impl.AbstractResultQuery.fetchLazyNonAutoClosing(AbstractResultQuery.java:484)
    //       at org.jooq.impl.AbstractResultQuery.fetchOne(AbstractResultQuery.java:653)
    //       at org.jooq.impl.SelectImpl.fetchOne(SelectImpl.java:2887)
    //       at org.jooq.impl.DefaultDSLContext.fetchExists(DefaultDSLContext.java:4298)
    //       at com.codeforcommunity.processor.ProtectedSiteProcessorImpl.checkSiteExists(ProtectedSiteProcessorImpl.java:77)
    //       at com.codeforcommunity.processor.ProtectedSiteProcessorImpl.adoptSite(ProtectedSiteProcessorImpl.java:220)
    //   See https://diff.blue/R013 to resolve this issue.

    ProtectedSiteProcessorImpl protectedSiteProcessorImpl = new ProtectedSiteProcessorImpl(
        new DefaultDSLContext(SQLDialect.SQL99));
    protectedSiteProcessorImpl.adoptSite(new JWTData(1, PrivilegeLevel.STANDARD), 1,
        mock(Date.class));
  }

  /**
   * Method under test: {@link ProtectedSiteProcessorImpl#adoptSite(JWTData, int, Date)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testAdoptSite2() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   org.jooq.exception.DetachedException: Cannot execute query. No Connection configured
    //       at org.jooq_3.12.4.CUBRID.debug(null)
    //       at org.jooq.impl.AbstractQuery.execute(AbstractQuery.java:332)
    //       at org.jooq.impl.AbstractResultQuery.fetchLazy(AbstractResultQuery.java:501)
    //       at org.jooq.impl.AbstractResultQuery.fetchLazy(AbstractResultQuery.java:470)
    //       at org.jooq.impl.AbstractResultQuery.fetchLazyNonAutoClosing(AbstractResultQuery.java:484)
    //       at org.jooq.impl.AbstractResultQuery.fetchOne(AbstractResultQuery.java:653)
    //       at org.jooq.impl.SelectImpl.fetchOne(SelectImpl.java:2887)
    //       at org.jooq.impl.DefaultDSLContext.fetchExists(DefaultDSLContext.java:4298)
    //       at com.codeforcommunity.processor.ProtectedSiteProcessorImpl.checkSiteExists(ProtectedSiteProcessorImpl.java:77)
    //       at com.codeforcommunity.processor.ProtectedSiteProcessorImpl.adoptSite(ProtectedSiteProcessorImpl.java:220)
    //   See https://diff.blue/R013 to resolve this issue.

    ProtectedSiteProcessorImpl protectedSiteProcessorImpl = new ProtectedSiteProcessorImpl(
        new DefaultDSLContext(SQLDialect.CUBRID));
    protectedSiteProcessorImpl.adoptSite(new JWTData(1, PrivilegeLevel.STANDARD), 1,
        mock(Date.class));
  }

  /**
   * Method under test: {@link ProtectedSiteProcessorImpl#adoptSite(JWTData, int, Date)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testAdoptSite3() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   org.jooq.exception.DetachedException: Cannot execute query. No Connection configured
    //       at org.jooq_3.12.4.DERBY.debug(null)
    //       at org.jooq.impl.AbstractQuery.execute(AbstractQuery.java:332)
    //       at org.jooq.impl.AbstractResultQuery.fetchLazy(AbstractResultQuery.java:501)
    //       at org.jooq.impl.AbstractResultQuery.fetchLazy(AbstractResultQuery.java:470)
    //       at org.jooq.impl.AbstractResultQuery.fetchLazyNonAutoClosing(AbstractResultQuery.java:484)
    //       at org.jooq.impl.AbstractResultQuery.fetchOne(AbstractResultQuery.java:653)
    //       at org.jooq.impl.SelectImpl.fetchOne(SelectImpl.java:2887)
    //       at org.jooq.impl.DefaultDSLContext.fetchExists(DefaultDSLContext.java:4298)
    //       at com.codeforcommunity.processor.ProtectedSiteProcessorImpl.checkSiteExists(ProtectedSiteProcessorImpl.java:77)
    //       at com.codeforcommunity.processor.ProtectedSiteProcessorImpl.adoptSite(ProtectedSiteProcessorImpl.java:220)
    //   See https://diff.blue/R013 to resolve this issue.

    ProtectedSiteProcessorImpl protectedSiteProcessorImpl = new ProtectedSiteProcessorImpl(
        new DefaultDSLContext(SQLDialect.DERBY));
    protectedSiteProcessorImpl.adoptSite(new JWTData(1, PrivilegeLevel.STANDARD), 1,
        mock(Date.class));
  }

  /**
   * Method under test: {@link ProtectedSiteProcessorImpl#adoptSite(JWTData, int, Date)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testAdoptSite4() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException
    //       at com.codeforcommunity.processor.ProtectedSiteProcessorImpl.checkSiteExists(ProtectedSiteProcessorImpl.java:77)
    //       at com.codeforcommunity.processor.ProtectedSiteProcessorImpl.adoptSite(ProtectedSiteProcessorImpl.java:220)
    //   See https://diff.blue/R013 to resolve this issue.

    ProtectedSiteProcessorImpl protectedSiteProcessorImpl = new ProtectedSiteProcessorImpl(null);
    protectedSiteProcessorImpl.adoptSite(new JWTData(1, PrivilegeLevel.STANDARD), 1,
        mock(Date.class));
  }

  /**
   * Method under test: {@link ProtectedSiteProcessorImpl#adoptSite(JWTData, int, Date)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testAdoptSite5() throws SQLException {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   org.jooq.exception.TooManyRowsException: Cursor returned more than one result
    //       at org.jooq_3.12.4.SQL99.debug(null)
    //       at org.jooq.impl.Tools.fetchOne(Tools.java:2087)
    //       at org.jooq.impl.AbstractResultQuery.fetchOne(AbstractResultQuery.java:653)
    //       at org.jooq.impl.SelectImpl.fetchOne(SelectImpl.java:2887)
    //       at org.jooq.impl.DefaultDSLContext.fetchExists(DefaultDSLContext.java:4298)
    //       at com.codeforcommunity.processor.ProtectedSiteProcessorImpl.checkSiteExists(ProtectedSiteProcessorImpl.java:77)
    //       at com.codeforcommunity.processor.ProtectedSiteProcessorImpl.adoptSite(ProtectedSiteProcessorImpl.java:220)
    //   See https://diff.blue/R013 to resolve this issue.

    ResultSet resultSet = mock(ResultSet.class);
    when(resultSet.wasNull()).thenReturn(true);
    when(resultSet.getInt(anyInt())).thenReturn(1);
    when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
    when(resultSet.getMetaData()).thenReturn(mock(ResultSetMetaData.class));
    doNothing().when(resultSet).close();
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    when(preparedStatement.getResultSet()).thenReturn(resultSet);
    when(preparedStatement.execute()).thenReturn(true);
    when(preparedStatement.getUpdateCount()).thenReturn(3);
    when(preparedStatement.getWarnings()).thenReturn(new SQLWarning());
    doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
    doNothing().when(preparedStatement).close();
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
    ProtectedSiteProcessorImpl protectedSiteProcessorImpl = new ProtectedSiteProcessorImpl(
        new DefaultDSLContext(connection, SQLDialect.SQL99));
    protectedSiteProcessorImpl.adoptSite(new JWTData(1, PrivilegeLevel.STANDARD), 1,
        mock(Date.class));
  }

  /**
   * Method under test: {@link ProtectedSiteProcessorImpl#adoptSite(JWTData, int, Date)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testAdoptSite6() throws SQLException {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   org.jooq.exception.DataAccessException: SQL [select 1 "one" where exists (select "sites"."id", "sites"."block_id", "sites"."lat", "sites"."lng", "sites"."city", "sites"."zip", "sites"."address", "sites"."deleted_at", "sites"."neighborhood_id", "sites"."owner" from "sites" where "sites"."id" = ?)]; Error while reading field: "one", at JDBC index: 1
    //       at org.jooq_3.12.4.SQL99.debug(null)
    //       at org.jooq.impl.Tools.translate(Tools.java:2728)
    //       at org.jooq.impl.DefaultExecuteContext.sqlException(DefaultExecuteContext.java:755)
    //       at org.jooq.impl.CursorImpl$CursorIterator.fetchNext(CursorImpl.java:1630)
    //       at org.jooq.impl.CursorImpl$CursorIterator.hasNext(CursorImpl.java:1581)
    //       at org.jooq.impl.CursorImpl.fetchNext(CursorImpl.java:353)
    //       at org.jooq.impl.Tools.fetchOne(Tools.java:2079)
    //       at org.jooq.impl.AbstractResultQuery.fetchOne(AbstractResultQuery.java:653)
    //       at org.jooq.impl.SelectImpl.fetchOne(SelectImpl.java:2887)
    //       at org.jooq.impl.DefaultDSLContext.fetchExists(DefaultDSLContext.java:4298)
    //       at com.codeforcommunity.processor.ProtectedSiteProcessorImpl.checkSiteExists(ProtectedSiteProcessorImpl.java:77)
    //       at com.codeforcommunity.processor.ProtectedSiteProcessorImpl.adoptSite(ProtectedSiteProcessorImpl.java:220)
    //   java.sql.SQLException: Error while reading field: "one", at JDBC index: 1
    //       at org.jooq.impl.CursorImpl$CursorIterator$CursorRecordInitialiser.setValue(CursorImpl.java:1735)
    //       at org.jooq.impl.CursorImpl$CursorIterator$CursorRecordInitialiser.operate(CursorImpl.java:1685)
    //       at org.jooq.impl.CursorImpl$CursorIterator$CursorRecordInitialiser.operate(CursorImpl.java:1650)
    //       at org.jooq.impl.RecordDelegate.operate(RecordDelegate.java:130)
    //       at org.jooq.impl.CursorImpl$CursorIterator.fetchNext(CursorImpl.java:1614)
    //       at org.jooq.impl.CursorImpl$CursorIterator.hasNext(CursorImpl.java:1581)
    //       at org.jooq.impl.CursorImpl.fetchNext(CursorImpl.java:353)
    //       at org.jooq.impl.Tools.fetchOne(Tools.java:2079)
    //       at org.jooq.impl.AbstractResultQuery.fetchOne(AbstractResultQuery.java:653)
    //       at org.jooq.impl.SelectImpl.fetchOne(SelectImpl.java:2887)
    //       at org.jooq.impl.DefaultDSLContext.fetchExists(DefaultDSLContext.java:4298)
    //       at com.codeforcommunity.processor.ProtectedSiteProcessorImpl.checkSiteExists(ProtectedSiteProcessorImpl.java:77)
    //       at com.codeforcommunity.processor.ProtectedSiteProcessorImpl.adoptSite(ProtectedSiteProcessorImpl.java:220)
    //   java.lang.IllegalArgumentException: foo
    //       at org.jooq.tools.jdbc.DefaultResultSet.getInt(DefaultResultSet.java:134)
    //       at org.jooq.impl.CursorImpl$CursorResultSet.getInt(CursorImpl.java:773)
    //       at org.jooq.impl.DefaultBinding$DefaultIntegerBinding.get0(DefaultBinding.java:2463)
    //       at org.jooq.impl.DefaultBinding$DefaultIntegerBinding.get0(DefaultBinding.java:2435)
    //       at org.jooq.impl.DefaultBinding$AbstractBinding.get(DefaultBinding.java:824)
    //       at org.jooq.impl.CursorImpl$CursorIterator$CursorRecordInitialiser.setValue(CursorImpl.java:1725)
    //       at org.jooq.impl.CursorImpl$CursorIterator$CursorRecordInitialiser.operate(CursorImpl.java:1685)
    //       at org.jooq.impl.CursorImpl$CursorIterator$CursorRecordInitialiser.operate(CursorImpl.java:1650)
    //       at org.jooq.impl.RecordDelegate.operate(RecordDelegate.java:130)
    //       at org.jooq.impl.CursorImpl$CursorIterator.fetchNext(CursorImpl.java:1614)
    //       at org.jooq.impl.CursorImpl$CursorIterator.hasNext(CursorImpl.java:1581)
    //       at org.jooq.impl.CursorImpl.fetchNext(CursorImpl.java:353)
    //       at org.jooq.impl.Tools.fetchOne(Tools.java:2079)
    //       at org.jooq.impl.AbstractResultQuery.fetchOne(AbstractResultQuery.java:653)
    //       at org.jooq.impl.SelectImpl.fetchOne(SelectImpl.java:2887)
    //       at org.jooq.impl.DefaultDSLContext.fetchExists(DefaultDSLContext.java:4298)
    //       at com.codeforcommunity.processor.ProtectedSiteProcessorImpl.checkSiteExists(ProtectedSiteProcessorImpl.java:77)
    //       at com.codeforcommunity.processor.ProtectedSiteProcessorImpl.adoptSite(ProtectedSiteProcessorImpl.java:220)
    //   See https://diff.blue/R013 to resolve this issue.

    ResultSet resultSet = mock(ResultSet.class);
    when(resultSet.wasNull()).thenThrow(new IllegalArgumentException("foo"));
    when(resultSet.getInt(anyInt())).thenThrow(new IllegalArgumentException("foo"));
    when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
    when(resultSet.getMetaData()).thenReturn(mock(ResultSetMetaData.class));
    doNothing().when(resultSet).close();
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    when(preparedStatement.getResultSet()).thenReturn(resultSet);
    when(preparedStatement.execute()).thenReturn(true);
    when(preparedStatement.getUpdateCount()).thenReturn(3);
    when(preparedStatement.getWarnings()).thenReturn(new SQLWarning());
    doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
    doNothing().when(preparedStatement).close();
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
    ProtectedSiteProcessorImpl protectedSiteProcessorImpl = new ProtectedSiteProcessorImpl(
        new DefaultDSLContext(connection, SQLDialect.SQL99));
    protectedSiteProcessorImpl.adoptSite(new JWTData(1, PrivilegeLevel.STANDARD), 1,
        mock(Date.class));
  }

  /**
   * Method under test: {@link ProtectedSiteProcessorImpl#adoptSite(JWTData, int, Date)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testAdoptSite7() throws SQLException {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   org.jooq.exception.TooManyRowsException: Cursor returned more than one result
    //       at org.jooq_3.12.4.SQL99.debug(null)
    //       at org.jooq.impl.Tools.fetchOne(Tools.java:2087)
    //       at org.jooq.impl.AbstractResultQuery.fetchOne(AbstractResultQuery.java:653)
    //       at org.jooq.impl.SelectImpl.fetchOne(SelectImpl.java:2887)
    //       at org.jooq.impl.DefaultDSLContext.fetchExists(DefaultDSLContext.java:4298)
    //       at com.codeforcommunity.processor.ProtectedSiteProcessorImpl.checkSiteExists(ProtectedSiteProcessorImpl.java:77)
    //       at com.codeforcommunity.processor.ProtectedSiteProcessorImpl.adoptSite(ProtectedSiteProcessorImpl.java:220)
    //   See https://diff.blue/R013 to resolve this issue.

    ResultSet resultSet = mock(ResultSet.class);
    when(resultSet.wasNull()).thenReturn(true);
    when(resultSet.getInt(anyInt())).thenReturn(0);
    when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
    when(resultSet.getMetaData()).thenReturn(mock(ResultSetMetaData.class));
    doNothing().when(resultSet).close();
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    when(preparedStatement.getResultSet()).thenReturn(resultSet);
    when(preparedStatement.execute()).thenReturn(true);
    when(preparedStatement.getUpdateCount()).thenReturn(3);
    when(preparedStatement.getWarnings()).thenReturn(new SQLWarning());
    doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
    doNothing().when(preparedStatement).close();
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
    ProtectedSiteProcessorImpl protectedSiteProcessorImpl = new ProtectedSiteProcessorImpl(
        new DefaultDSLContext(connection, SQLDialect.SQL99));
    protectedSiteProcessorImpl.adoptSite(new JWTData(1, PrivilegeLevel.STANDARD), 1,
        mock(Date.class));
  }

  /**
   * Method under test: {@link ProtectedSiteProcessorImpl#adoptSite(JWTData, int, Date)}
   */
  @Test
  void testAdoptSite8() throws SQLException {
    ResultSet resultSet = mock(ResultSet.class);
    when(resultSet.wasNull()).thenReturn(true);
    when(resultSet.getInt(anyInt())).thenReturn(1);
    when(resultSet.next()).thenReturn(false).thenReturn(true).thenReturn(false);
    when(resultSet.getMetaData()).thenReturn(mock(ResultSetMetaData.class));
    doNothing().when(resultSet).close();
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    when(preparedStatement.getResultSet()).thenReturn(resultSet);
    when(preparedStatement.execute()).thenReturn(true);
    when(preparedStatement.getUpdateCount()).thenReturn(3);
    when(preparedStatement.getWarnings()).thenReturn(new SQLWarning());
    doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
    doNothing().when(preparedStatement).close();
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
    ProtectedSiteProcessorImpl protectedSiteProcessorImpl = new ProtectedSiteProcessorImpl(
        new DefaultDSLContext(connection, SQLDialect.SQL99));
    assertThrows(ResourceDoesNotExistException.class,
        () -> protectedSiteProcessorImpl.adoptSite(new JWTData(1, PrivilegeLevel.STANDARD), 1,
            mock(Date.class)));
    verify(connection).prepareStatement(Mockito.<String>any());
    verify(preparedStatement).execute();
    verify(preparedStatement).getResultSet();
    verify(preparedStatement).getWarnings();
    verify(preparedStatement).setInt(anyInt(), anyInt());
    verify(preparedStatement).close();
    verify(resultSet).next();
    verify(resultSet).getMetaData();
    verify(resultSet).close();
  }

  /**
   * Method under test: {@link ProtectedSiteProcessorImpl#adoptSite(JWTData, int, Date)}
   */
  @Test
  void testAdoptSite9() throws SQLException {
    ResultSet resultSet = mock(ResultSet.class);
    when(resultSet.wasNull()).thenReturn(true);
    when(resultSet.getInt(anyInt())).thenReturn(1);
    when(resultSet.next()).thenReturn(true).thenReturn(false).thenReturn(false);
    when(resultSet.getMetaData()).thenReturn(mock(ResultSetMetaData.class));
    doNothing().when(resultSet).close();
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    when(preparedStatement.getResultSet()).thenReturn(resultSet);
    when(preparedStatement.execute()).thenReturn(true);
    when(preparedStatement.getUpdateCount()).thenReturn(3);
    when(preparedStatement.getWarnings()).thenReturn(new SQLWarning());
    doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
    doNothing().when(preparedStatement).close();
    ResultSet resultSet2 = mock(ResultSet.class);
    when(resultSet2.wasNull()).thenReturn(true);
    when(resultSet2.getInt(anyInt())).thenReturn(1);
    when(resultSet2.next()).thenReturn(true).thenReturn(true).thenReturn(false);
    doNothing().when(resultSet2).close();
    PreparedStatement preparedStatement2 = mock(PreparedStatement.class);
    when(preparedStatement2.executeUpdate()).thenReturn(1);
    when(preparedStatement2.getGeneratedKeys()).thenReturn(resultSet2);
    when(preparedStatement2.getWarnings()).thenReturn(new SQLWarning());
    doNothing().when(preparedStatement2).setDate(anyInt(), Mockito.<Date>any());
    doNothing().when(preparedStatement2).setInt(anyInt(), anyInt());
    doNothing().when(preparedStatement2).close();
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<String[]>any())).thenReturn(
        preparedStatement2);
    when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
    ProtectedSiteProcessorImpl protectedSiteProcessorImpl = new ProtectedSiteProcessorImpl(
        new DefaultDSLContext(connection, SQLDialect.SQL99));
    protectedSiteProcessorImpl.adoptSite(new JWTData(1, PrivilegeLevel.STANDARD), 1,
        mock(Date.class));
    verify(connection, atLeast(1)).prepareStatement(Mockito.<String>any());
    verify(connection).prepareStatement(Mockito.<String>any(), Mockito.<String[]>any());
    verify(preparedStatement2).executeUpdate();
    verify(preparedStatement2).getGeneratedKeys();
    verify(preparedStatement2).getWarnings();
    verify(preparedStatement2).setDate(anyInt(), Mockito.<Date>any());
    verify(preparedStatement2, atLeast(1)).setInt(anyInt(), anyInt());
    verify(preparedStatement2).close();
    verify(resultSet2, atLeast(1)).next();
    verify(resultSet2, atLeast(1)).getInt(anyInt());
    verify(resultSet2).close();
    verify(preparedStatement, atLeast(1)).execute();
    verify(preparedStatement, atLeast(1)).getResultSet();
    verify(preparedStatement, atLeast(1)).getWarnings();
    verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
    verify(preparedStatement, atLeast(1)).close();
    verify(resultSet, atLeast(1)).next();
    verify(resultSet).getInt(anyInt());
    verify(resultSet, atLeast(1)).getMetaData();
    verify(resultSet, atLeast(1)).close();
  }

  /**
   * Method under test: {@link ProtectedSiteProcessorImpl#adoptSite(JWTData, int, Date)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testAdoptSite10() throws SQLException {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   org.jooq.exception.DataAccessException: SQL [null]; Error while reading field: "adopted_sites"."user_id", at JDBC index: 1
    //       at org.jooq_3.12.4.SQL99.debug(null)
    //       at org.jooq_3.12.4.SQL99.debug(null)
    //       at org.jooq.impl.Tools.translate(Tools.java:2728)
    //       at org.jooq.impl.DefaultExecuteContext.sqlException(DefaultExecuteContext.java:755)
    //       at org.jooq.impl.CursorImpl$CursorIterator.fetchNext(CursorImpl.java:1630)
    //       at org.jooq.impl.CursorImpl$CursorIterator.hasNext(CursorImpl.java:1581)
    //       at org.jooq.impl.CursorImpl.fetchNext(CursorImpl.java:353)
    //       at org.jooq.impl.CursorImpl.fetch(CursorImpl.java:339)
    //       at org.jooq.impl.CursorImpl.fetch(CursorImpl.java:246)
    //       at org.jooq.impl.AbstractDMLQuery.execute(AbstractDMLQuery.java:1069)
    //       at org.jooq.impl.AbstractQuery.execute(AbstractQuery.java:368)
    //       at org.jooq.impl.TableRecordImpl.storeInsert0(TableRecordImpl.java:206)
    //       at org.jooq.impl.TableRecordImpl$1.operate(TableRecordImpl.java:177)
    //       at org.jooq.impl.RecordDelegate.operate(RecordDelegate.java:130)
    //       at org.jooq.impl.TableRecordImpl.storeInsert(TableRecordImpl.java:173)
    //       at org.jooq.impl.UpdatableRecordImpl.store0(UpdatableRecordImpl.java:196)
    //       at org.jooq.impl.UpdatableRecordImpl.access$000(UpdatableRecordImpl.java:85)
    //       at org.jooq.impl.UpdatableRecordImpl$1.operate(UpdatableRecordImpl.java:136)
    //       at org.jooq.impl.RecordDelegate.operate(RecordDelegate.java:130)
    //       at org.jooq.impl.UpdatableRecordImpl.store(UpdatableRecordImpl.java:132)
    //       at org.jooq.impl.UpdatableRecordImpl.store(UpdatableRecordImpl.java:124)
    //       at com.codeforcommunity.processor.ProtectedSiteProcessorImpl.adoptSite(ProtectedSiteProcessorImpl.java:229)
    //   java.sql.SQLException: Error while reading field: "adopted_sites"."user_id", at JDBC index: 1
    //       at org.jooq.impl.CursorImpl$CursorIterator$CursorRecordInitialiser.setValue(CursorImpl.java:1735)
    //       at org.jooq.impl.CursorImpl$CursorIterator$CursorRecordInitialiser.operate(CursorImpl.java:1685)
    //       at org.jooq.impl.CursorImpl$CursorIterator$CursorRecordInitialiser.operate(CursorImpl.java:1650)
    //       at org.jooq.impl.RecordDelegate.operate(RecordDelegate.java:130)
    //       at org.jooq.impl.CursorImpl$CursorIterator.fetchNext(CursorImpl.java:1614)
    //       at org.jooq.impl.CursorImpl$CursorIterator.hasNext(CursorImpl.java:1581)
    //       at org.jooq.impl.CursorImpl.fetchNext(CursorImpl.java:353)
    //       at org.jooq.impl.CursorImpl.fetch(CursorImpl.java:339)
    //       at org.jooq.impl.CursorImpl.fetch(CursorImpl.java:246)
    //       at org.jooq.impl.AbstractDMLQuery.execute(AbstractDMLQuery.java:1069)
    //       at org.jooq.impl.AbstractQuery.execute(AbstractQuery.java:368)
    //       at org.jooq.impl.TableRecordImpl.storeInsert0(TableRecordImpl.java:206)
    //       at org.jooq.impl.TableRecordImpl$1.operate(TableRecordImpl.java:177)
    //       at org.jooq.impl.RecordDelegate.operate(RecordDelegate.java:130)
    //       at org.jooq.impl.TableRecordImpl.storeInsert(TableRecordImpl.java:173)
    //       at org.jooq.impl.UpdatableRecordImpl.store0(UpdatableRecordImpl.java:196)
    //       at org.jooq.impl.UpdatableRecordImpl.access$000(UpdatableRecordImpl.java:85)
    //       at org.jooq.impl.UpdatableRecordImpl$1.operate(UpdatableRecordImpl.java:136)
    //       at org.jooq.impl.RecordDelegate.operate(RecordDelegate.java:130)
    //       at org.jooq.impl.UpdatableRecordImpl.store(UpdatableRecordImpl.java:132)
    //       at org.jooq.impl.UpdatableRecordImpl.store(UpdatableRecordImpl.java:124)
    //       at com.codeforcommunity.processor.ProtectedSiteProcessorImpl.adoptSite(ProtectedSiteProcessorImpl.java:229)
    //   java.lang.IllegalArgumentException: foo
    //       at org.jooq.tools.jdbc.DefaultResultSet.getInt(DefaultResultSet.java:134)
    //       at org.jooq.impl.CursorImpl$CursorResultSet.getInt(CursorImpl.java:773)
    //       at org.jooq.impl.DefaultBinding$DefaultIntegerBinding.get0(DefaultBinding.java:2463)
    //       at org.jooq.impl.DefaultBinding$DefaultIntegerBinding.get0(DefaultBinding.java:2435)
    //       at org.jooq.impl.DefaultBinding$AbstractBinding.get(DefaultBinding.java:824)
    //       at org.jooq.impl.CursorImpl$CursorIterator$CursorRecordInitialiser.setValue(CursorImpl.java:1725)
    //       at org.jooq.impl.CursorImpl$CursorIterator$CursorRecordInitialiser.operate(CursorImpl.java:1685)
    //       at org.jooq.impl.CursorImpl$CursorIterator$CursorRecordInitialiser.operate(CursorImpl.java:1650)
    //       at org.jooq.impl.RecordDelegate.operate(RecordDelegate.java:130)
    //       at org.jooq.impl.CursorImpl$CursorIterator.fetchNext(CursorImpl.java:1614)
    //       at org.jooq.impl.CursorImpl$CursorIterator.hasNext(CursorImpl.java:1581)
    //       at org.jooq.impl.CursorImpl.fetchNext(CursorImpl.java:353)
    //       at org.jooq.impl.CursorImpl.fetch(CursorImpl.java:339)
    //       at org.jooq.impl.CursorImpl.fetch(CursorImpl.java:246)
    //       at org.jooq.impl.AbstractDMLQuery.execute(AbstractDMLQuery.java:1069)
    //       at org.jooq.impl.AbstractQuery.execute(AbstractQuery.java:368)
    //       at org.jooq.impl.TableRecordImpl.storeInsert0(TableRecordImpl.java:206)
    //       at org.jooq.impl.TableRecordImpl$1.operate(TableRecordImpl.java:177)
    //       at org.jooq.impl.RecordDelegate.operate(RecordDelegate.java:130)
    //       at org.jooq.impl.TableRecordImpl.storeInsert(TableRecordImpl.java:173)
    //       at org.jooq.impl.UpdatableRecordImpl.store0(UpdatableRecordImpl.java:196)
    //       at org.jooq.impl.UpdatableRecordImpl.access$000(UpdatableRecordImpl.java:85)
    //       at org.jooq.impl.UpdatableRecordImpl$1.operate(UpdatableRecordImpl.java:136)
    //       at org.jooq.impl.RecordDelegate.operate(RecordDelegate.java:130)
    //       at org.jooq.impl.UpdatableRecordImpl.store(UpdatableRecordImpl.java:132)
    //       at org.jooq.impl.UpdatableRecordImpl.store(UpdatableRecordImpl.java:124)
    //       at com.codeforcommunity.processor.ProtectedSiteProcessorImpl.adoptSite(ProtectedSiteProcessorImpl.java:229)
    //   See https://diff.blue/R013 to resolve this issue.

    ResultSet resultSet = mock(ResultSet.class);
    when(resultSet.wasNull()).thenReturn(true);
    when(resultSet.getInt(anyInt())).thenReturn(1);
    when(resultSet.next()).thenReturn(true).thenReturn(false).thenReturn(false);
    when(resultSet.getMetaData()).thenReturn(mock(ResultSetMetaData.class));
    doNothing().when(resultSet).close();
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    when(preparedStatement.getResultSet()).thenReturn(resultSet);
    when(preparedStatement.execute()).thenReturn(true);
    when(preparedStatement.getUpdateCount()).thenReturn(3);
    when(preparedStatement.getWarnings()).thenReturn(new SQLWarning());
    doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
    doNothing().when(preparedStatement).close();
    ResultSet resultSet2 = mock(ResultSet.class);
    when(resultSet2.wasNull()).thenThrow(new IllegalArgumentException("foo"));
    when(resultSet2.getInt(anyInt())).thenThrow(new IllegalArgumentException("foo"));
    when(resultSet2.next()).thenReturn(true).thenReturn(true).thenReturn(false);
    doNothing().when(resultSet2).close();
    PreparedStatement preparedStatement2 = mock(PreparedStatement.class);
    when(preparedStatement2.executeUpdate()).thenReturn(1);
    when(preparedStatement2.getGeneratedKeys()).thenReturn(resultSet2);
    when(preparedStatement2.getWarnings()).thenReturn(new SQLWarning());
    doNothing().when(preparedStatement2).setDate(anyInt(), Mockito.<Date>any());
    doNothing().when(preparedStatement2).setInt(anyInt(), anyInt());
    doNothing().when(preparedStatement2).close();
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<String[]>any())).thenReturn(
        preparedStatement2);
    when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
    ProtectedSiteProcessorImpl protectedSiteProcessorImpl = new ProtectedSiteProcessorImpl(
        new DefaultDSLContext(connection, SQLDialect.SQL99));
    protectedSiteProcessorImpl.adoptSite(new JWTData(1, PrivilegeLevel.STANDARD), 1,
        mock(Date.class));
  }

  /**
   * Method under test: {@link ProtectedSiteProcessorImpl#adoptSite(JWTData, int, Date)}
   */
  @Test
  void testAdoptSite11() throws SQLException {
    ResultSet resultSet = mock(ResultSet.class);
    when(resultSet.wasNull()).thenReturn(true);
    when(resultSet.getInt(anyInt())).thenReturn(1);
    when(resultSet.next()).thenReturn(true).thenReturn(false).thenReturn(false);
    when(resultSet.getMetaData()).thenReturn(mock(ResultSetMetaData.class));
    doNothing().when(resultSet).close();
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    when(preparedStatement.getResultSet()).thenReturn(resultSet);
    when(preparedStatement.execute()).thenReturn(true);
    when(preparedStatement.getUpdateCount()).thenReturn(3);
    when(preparedStatement.getWarnings()).thenReturn(new SQLWarning());
    doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
    doNothing().when(preparedStatement).close();
    ResultSet resultSet2 = mock(ResultSet.class);
    when(resultSet2.wasNull()).thenReturn(true);
    when(resultSet2.getInt(anyInt())).thenReturn(0);
    when(resultSet2.next()).thenReturn(true).thenReturn(true).thenReturn(false);
    doNothing().when(resultSet2).close();
    PreparedStatement preparedStatement2 = mock(PreparedStatement.class);
    when(preparedStatement2.executeUpdate()).thenReturn(1);
    when(preparedStatement2.getGeneratedKeys()).thenReturn(resultSet2);
    when(preparedStatement2.getWarnings()).thenReturn(new SQLWarning());
    doNothing().when(preparedStatement2).setDate(anyInt(), Mockito.<Date>any());
    doNothing().when(preparedStatement2).setInt(anyInt(), anyInt());
    doNothing().when(preparedStatement2).close();
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<String[]>any())).thenReturn(
        preparedStatement2);
    when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
    ProtectedSiteProcessorImpl protectedSiteProcessorImpl = new ProtectedSiteProcessorImpl(
        new DefaultDSLContext(connection, SQLDialect.SQL99));
    protectedSiteProcessorImpl.adoptSite(new JWTData(1, PrivilegeLevel.STANDARD), 1,
        mock(Date.class));
    verify(connection, atLeast(1)).prepareStatement(Mockito.<String>any());
    verify(connection).prepareStatement(Mockito.<String>any(), Mockito.<String[]>any());
    verify(preparedStatement2).executeUpdate();
    verify(preparedStatement2).getGeneratedKeys();
    verify(preparedStatement2).getWarnings();
    verify(preparedStatement2).setDate(anyInt(), Mockito.<Date>any());
    verify(preparedStatement2, atLeast(1)).setInt(anyInt(), anyInt());
    verify(preparedStatement2).close();
    verify(resultSet2, atLeast(1)).next();
    verify(resultSet2, atLeast(1)).wasNull();
    verify(resultSet2, atLeast(1)).getInt(anyInt());
    verify(resultSet2).close();
    verify(preparedStatement, atLeast(1)).execute();
    verify(preparedStatement, atLeast(1)).getResultSet();
    verify(preparedStatement, atLeast(1)).getWarnings();
    verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
    verify(preparedStatement, atLeast(1)).close();
    verify(resultSet, atLeast(1)).next();
    verify(resultSet).getInt(anyInt());
    verify(resultSet, atLeast(1)).getMetaData();
    verify(resultSet, atLeast(1)).close();
  }
}

