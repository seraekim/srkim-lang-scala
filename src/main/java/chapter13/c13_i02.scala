package chapter13

/**
 * 13.2 관련 코드에 간결하게 접근하기
 * 
 * 코드를 패키지 계층으로 나누는 이유는 사람을 위한 것뿐 아니라 컴파일러도 포함된다.
 * 같은 패키지 안에 있는 코드 접근할 때, 짧은 이름 쓸 수 있다.
 */
package bobsrockets {
  package navigation {
    class Navigator {
      // bobsrockets.navigation.StarMap
      val map = new StarMap
    }
    class StarMap
  }
  
  class Ship {
    // bobsrockets.navigation.Navigator
    val nav = new navigation.Navigator
  }
  
  package fleets {
    class Fleet {
      // bobsrockets.Ship
      def addShip() { new Ship }
    }
  }
}

package bobsrockets {
  class Ship2
}
package bobsrockets.fleets {
  class Fleet2 {
    // bobsrockets.fleets 을 최상단으로 옮겼으니 Ship2에 접근 불가
    // def addShip(){ new Ship2 }
  }
}

/*
 * 패키지가 괄호로 인해 오른쪽으로 치우쳐서 불편하다면 괄호 없이 여러 package 절을 사용할 수 있다.
 * 이를 연쇄 패키지 절(chained package clause)라 한다.
 * http://www.scala-lang.org/docu/files/package-clauses/packageclauses.html
 */
/*package bobsrockets
package fleets
class Fleet {
  def addShip() {}
}*/

package launch {
  class Booster3
}

package bobsrockets {
  package navigation {
    package launch {
      class Booster1
    }
    class MissionControl {
      val b1 = new launch.Booster1
      val b2 = new bobsrockets.launch.Booster2
      val b3 = new _root_.chapter13.launch.Booster3
    }
  }
  package launch {
    class Booster2
  }
}