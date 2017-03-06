package chapter28

/**
 * 28.6 역 직렬화
 * 
 */
object c28_i06 extends App {
  val therm = new CCTherm {
    val description: String = "hot dog #5"
    val yearMade: Int = 1952
    val dateObtained: String = "March 14, 2006"
    val bookPrice: Int = 2199
    val purchasePrice: Int = 500
    val condition: Int = 9
  }                                               //> therm  : chapter28.CCTherm = hot dog #5
  therm.toXML                                     //> res0: scala.xml.Elem = <cctherm>
                                                  //|       <description>hot dog #5</description>
                                                  //|       <yearMade>1952</yearMade>
                                                  //|       <dateObtained>March 14, 2006</dateObtained>
                                                  //|       <bookPrice>2199</bookPrice>
                                                  //|       <purchasePrice>500</purchasePrice>
                                                  //|       <condition>9</condition>
                                                  //|     </cctherm>
  
  val node = therm.toXML                          //> node  : scala.xml.Elem = <cctherm>
                                                  //|       <description>hot dog #5</description>
                                                  //|       <yearMade>1952</yearMade>
                                                  //|       <dateObtained>March 14, 2006</dateObtained>
                                                  //|       <bookPrice>2199</bookPrice>
                                                  //|       <purchasePrice>500</purchasePrice>
                                                  //|       <condition>9</condition>
                                                  //|     </cctherm>
  def fromXML(node: scala.xml.Node): CCTherm = new CCTherm {
    val description: String = (node \ "description").text
    val yearMade: Int = (node \ "yearMade").text.toInt
    val dateObtained: String = (node \ "dateObtained").text
    val bookPrice: Int = (node \ "bookPrice").text.toInt
    val purchasePrice: Int = (node \ "purchasePrice").text.toInt
    val condition: Int = (node \ "condition").text.toInt
  }                                               //> fromXML: (node: scala.xml.Node)chapter28.CCTherm
  fromXML(node)                                   //> res1: chapter28.CCTherm = hot dog #5
  
  
}