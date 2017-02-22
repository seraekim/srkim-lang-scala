package chapter24

/**
 * 24.5 시퀀스 트레이트: Seq, IndexedSeq, LinearSeq
 * 
 * Seq 트레이트는 시퀀스를 표현한다. 시퀀스는 길이가 정해져 있고, 각 원소의 위치를 0부터 시작하는
 * 정해진 인덱스로 지정할 수 있는 일종의 Iterable이다.
 * 
 *  - 인덱스와 길이 연산: apply, isDefinedAt, length, indices, lengthCompare.
 *  Seq의 경우 apply는 인덱스로 원소를 찾는 것을 의미한다. Seq[T] 타입 시퀀스는 Int를 받아서
 *  T 타입의 원소를 돌려주는 부분 함수다. 즉, Seq[T]는 PartialFunction[Int, T]를 확장한다.
 *  시퀀스에 있는, length 메소드는 일반적인 컬렉션에 있는 size 메소드에 대한 별칭이다. lengthCompare
 *  메소드를 사용하면 어느 한쪽의 길이가 무한하더라도 두 시퀀스의 길이를 비교할 수 있다.
 *  
 *  - 인덱스 찾기 연산: indexOf, lastIndexOf, indexOfSlice, lastIndexOfSlice,
 *  indexWhere, lastIndexWhere, segmentLength, prefixLength. 주어진 값과 같거나
 *  어떤 술어(조건 함수)를 만족하는 원소의 인덱스를 반환한다.
 *  
 *  - 추가 연산: +:, :+, padTo. 시퀀스의 맨 앞이나 뒤에 원소를 추가한 새 시퀀스를 반환 한다.
 *  - 변경 연산: updated, patch. 원래의 시퀀스 일부 원소를 바꿔서 나오는 새로운 시퀀스를 반환
 *  - 정렬 연산: sorted, sortWith, sortBy는 시퀀스 원소들을 여러 기준에 따라 정렬한다.
 *  - 반전 연산: reverse, reverseIterator, reverseMap. 시퀀스의 원소를 역순, 즉 마지막에서
 *  맨 앞쪽으로 처리하거나 역순으로 토해낸다.
 *  - 비교 연산: startsWith, endsWith, contains, corresponds, containsSlice.
 *  두 시퀀스 간의 관계를 판단하거나, 시퀀스에서 원소를 찾는다.
 *  - 중복 집합 연산: intersect, diff, union, distinct. 두 시퀀스에 대해 집합과 비슷한 연산을
 *  수행하거나, 중복을 제거한다.
 *  
 * 어떤 시퀀스가 변경 가능하다면, 추가로 부수 효과를 통해 시퀀스의 원소를 변경할 수 있는 update 메소드를 제공한다.
 * 3장에서 이미 말했듯 seq(idx) = elem 은 seq.update(idx, elem)을 짧게 쓴 것일 뿐이다.
 * update와 updated를 구분해야 한다. update는 그 자리에서 변경하고, 변경 가능한 시퀀스에서만 쓰인다.
 * updated는 모든 시퀀스에서 사용 가능하며, 원래의 시퀀스를 변경하지 않고, 새로운 시퀀스를 반환한다.
 * 
 * 각 Seq 트레이트에는 두 가지 하위 트레이트 LinearSeq와 IndexedSeq가 있다. 이들은 새로운 연산을
 * 추가하지는 않지만,성능 특성이 다르다. 선형 시퀀스는 더 효율적인 head, tail 연산을 제공하지만
 * 인덱스 시퀀스는 효율적인 apply, length, (변경가능인 경우) update 연산을 제공한다. List나
 * Stream은 가장 많이 쓰이는 선형 시퀀스다. 많이 쓰이는 인덱스 시컨스는 Array와 ArrayBuffer이다.
 * Vector 클래스는 선형 접근과 인덱스 접근 사이에 흥미로운 절충점을 제공한다. 따라서, 인덱스와 선형 접근을
 * 모두 사용해야 하는 혼합 접근 패턴의 경우 벡터가 좋은 기반 클래스가 될 수 있다.
 * 
 * 1. 버퍼
 * 
 * 변경 가능한 시퀀스의 중요한 하위 범주로 버퍼가 있다.
 * 맨 뒤에 추가 : +=, ++=
 * 맨 앞에 추가 : +=:, ++=:, insert, insertAll
 * 원소제거 : -=, remove
 * 
 * 가장 많이 사용하는 버퍼구현은 ListBuffer, ArrayBuffer가 있다. 
 */
object c24_i05 {
  
}