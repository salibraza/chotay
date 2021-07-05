// object Main extends App {
//   println("Hello World!")
// }

package tests

import java.sql.{Connection,DriverManager}
import scala.io.StdIn._

// akka specific
// import akka.actor.typed.ActorSystem
// import akka.actor.typed.scaladsl.Behaviors
// // akka http specific
// import akka.http.scaladsl.Http
// import akka.http.scaladsl.model._
// import akka.http.scaladsl.server.Directives._
// // spray specific (JSON marshalling)
// import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
// import spray.json.DefaultJsonProtocol._
// // cors
// import ch.megard.akka.http.cors.scaladsl.CorsDirectives._

object ScalaJdbcConnectSelect extends App {
    // connect to the database named "mysql" on port 8889 of localhost
    val url = "jdbc:mysql://localhost:3306/temp"
    val driver = "com.mysql.jdbc.Driver"
    val username = "root"
    val password = "1234"
    var connection:Connection = _
    try {
        Class.forName(driver)
        connection = DriverManager.getConnection(url, username, password)
        
      while (true){
        println("a. Insert new Customer info\n" +
          "b. Search for customer\n" +
          "c. Delete Customer record\n" +
          "d. Update customer record\n")
        
        println("Enter the Operation to perform (a,b,c.d)")
        val a= readChar()
        println("The value is "+ a)
        val statement = connection.createStatement
        if(a=='a'){
          println("Enter Name: ")
          var n = readLine()
          println("Enter Age: ")
          var age = readInt()
          println("Enter Location: ")
          var loc = readLine()
          var rs = statement.executeQuery("Select max(id) id from cusotmers;")
          var id = rs.getInt("id")
          id+=1;
          var query = "insert ignore into cutomers (id,name,location,age) values (\""+id.toString()+",\""+n+"\",\""+loc+"\","+age+");"
          var r = statement.executeUpdate(query);
        }

        if(a=='b'){
          println("Select filter:\n" +
            "1. Age\n" +
            "2. Name\n" +
            "3. Location\n")
          val filter = readInt()
          if(filter == 1){
            println("Enter Age: ")
            var v = readLine()
            val rs = statement.executeQuery("SELECT * FROM customers where age = "+v+";")
            while (rs.next) {
              val name = rs.getString("name")
              val age = rs.getInt("age")
              val loc = rs.getString("location")
              println("name: %s, age: %s, location: %s".format(name,age,loc))
            }
          }
          if(filter == 2){
            println("Enter Name: ")
            var v = readLine()
            val rs = statement.executeQuery("SELECT * FROM customers where name = \""+v+"\";")
            while (rs.next) {
              val name = rs.getString("name")
              val age = rs.getString("age")
              val loc = rs.getString("location")
              println("name: %s, age: %s, location: %s".format(name,age,loc))
            }
          }
          if(filter == 3){
            println("Enter Location: ")
            var v = readLine()
            val rs = statement.executeQuery("SELECT * FROM customers where location = \""+v+"\";")
            while (rs.next) {
              val name = rs.getString("name")
              val age = rs.getString("age")
              val loc = rs.getString("location")
              println("name: %s, age: %s, location: %s".format(name,age,loc))
            }
          }
        }
        
        val rs = statement.executeQuery("SELECT * FROM customers;")
        while (rs.next) {
            val host = rs.getString("id")
            val user = rs.getString("name")
            println("id = %s, name = %s".format(host,user))
        }
      }
    } catch {
        case e: Exception => e.printStackTrace
        println("Exception Thrown")
    }
    connection.close
}