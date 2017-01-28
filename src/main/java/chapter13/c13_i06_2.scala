package printmenu

import chapter13.Fruits
import chapter13.showFruit
//import chapter13.chapter13.showFruit

// 아마도 directory 구조를 printmenu에 바꾸고 해야 제대로 실행이 될 것이다.
object c13_i06_2 {
  def print(args: Array[String]) {
    for (fruit <- Fruits.menu) {
      showFruit(fruit)
    }
  }
}