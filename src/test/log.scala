package test
import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object log {

  def currentLine: Int = macro SrcFileImpl.currentLine
  def currentFileName: String = macro SrcFileImpl.currentFileName
  def currentPackage: String = macro SrcFileImpl.currentPackage
  def currentClassName: String = macro SrcFileImpl.currentClassName
  def currentFuncName: String = macro SrcFileImpl.currentFuncName

}

class SrcFileImpl(val c: blackbox.Context) {

  import c.universe._

  def getPackage(symbol: Symbol): String =
    if (symbol.isPackage) symbol.fullName else getPackage(symbol.owner)

  def getClass(symbol: Symbol): String =
    if (symbol.isClass) symbol.name.toTypeName.toString else getClass(symbol.owner)

  def currentPackage: c.Expr[String] = c.Expr(q"${getPackage(c.internal.enclosingOwner)}")

  def currentFileName: c.Expr[String] = c.Expr(q"${c.enclosingPosition.source.file.name}")

  def currentLine: c.Expr[Int] = c.Expr(q"${c.enclosingPosition.line}")

  def currentClassName: c.Expr[String] = c.Expr(q"${getClass(c.internal.enclosingOwner)}")

  def currentFuncName: c.Expr[String] = c.Expr(q"${c.internal.enclosingOwner.name.toTermName.toString}")

}