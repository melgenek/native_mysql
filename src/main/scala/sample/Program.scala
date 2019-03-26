package sample

import com.mysql.cj.jdbc.MysqlDataSource

class Program {

  def run(): Unit = {
    if (System.getenv("cool") == "run") {
      val ds: MysqlDataSource = new MysqlDataSource()
      ds.setServerName("127.0.0.1")
      ds.setDatabaseName("test")

      System.out.println("Connecting to database...")
      val con = ds.getConnection("test", "test")

      val stmt = con.createStatement()
      val rs = stmt.executeQuery("SELECT 1 as CONSTANT FROM INFORMATION_SCHEMA.SCHEMATA AS a")

      rs.next()

      val constant = rs.getInt("CONSTANT")

      println(constant)
    }
  }

}
