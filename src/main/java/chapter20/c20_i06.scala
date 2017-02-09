package chapter20

/**
 * 20.6 추상 타입
 * 
 * type T라틑 추상 타입 선언의 의미와 사용법을 살펴보자.
 * 
 * 동물의 음식 섭취 행동을 모델링하자. 아마도 Food 클래스와 음식을 먹는 eat 메소드를 포함하는
 * Animal 클래스를 가지고 시작할 수 있을 것이다.
 */
class Food
abstract class Animal {
  /*
   * SuitableFood를 Animal의 서브클래 
   */
  type SuitableFood <: Food
  def eat(food: SuitableFood)
}
/*
 * 그 후, Cows가 Grass를 먹도록 이를 구체화할 수 있다.
 */
class Grass extends Food
class Cow extends Animal {
  // method eat overrides nothing.
  type SuitableFood = Grass
  override def eat(food: SuitableFood) {}
}

/*
 * 물고기 먹는 소 반례를 실행하면 다음과 같이 컴파일러 오류를 볼 수 있다.
 * 
 * 20.7 경로에 의존하는 타입
 * 
 * 여기서 재미있는 부분은 eat가 요구하는 타입인 bessy.SuitableFood 이다.
 * 위와 같은 타입을 경로에 의존하는 타입(path-dependent type)이라고 부르며, 여기서 경로는
 * 객체에 대한 참조를 의미한다. 스칼라의 객체가 타입을 멤버로 포함할 수 있음을 보여준다.
 * 
 * 경로에 의존하는 타입은 자바의 내부 클래스 타입과 문법이 비슷하지만, 결정적인 차이가 있다.
 * 경로 의존 타입은 외부 객체에 이름을 붙이는 반면, 내부 클래스 타입 이름은 외부 클래스에
 * 이름을 붙인다는 점이 다르다.
 */
class Fish extends Food

class DogFood extends Food
class Dog extends Animal {
  type SuitableFood = DogFood
  override def eat(food: DogFood) {}
}

class Outer {
  class Inner
}
object c20_i06 extends App {
  val bessy = new Cow
  bessy eat (new bessy.SuitableFood)
  //bessy eat (new Fish)
  
  val lassie = new Dog
  val bootsie = new Dog
  lassie eat (new bootsie.SuitableFood)
  
  val o1 = new Outer
  val o2 = new Outer
  // 자바의 내부 외부 클래스 개념에서는, 특정 외부 객체 내의 Inner를 가리킨다.
  // 스칼라의 Outer#Inner에서는, 임의의 외부 객체 내의 Inner를 가리킨다.
  new o1.Inner
  
  // new Outer#Inner //Outer is not a legal prefix for a constructor
}