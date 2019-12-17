package sless.ast.exp.selector

case class SelectorParentExp()(extensions: Seq[SelectorExp] = List()) extends SelectorExp(extensions) {
  override def grounded: Boolean = false
  override def addExtension(toExtend: SelectorExp): SelectorExp = SelectorParentExp()(toExtend +: extensions)
}
