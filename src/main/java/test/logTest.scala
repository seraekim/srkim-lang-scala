package test

object logTest extends App{
  def f() = {
    println(log.currentFileName)
    println(log.currentLine)
    println(log.currentPackage)
    println(log.currentClassName)
    println(log.currentFuncName)
  }
  f()
}