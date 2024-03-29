package sless.ast.exp.selector

case class SelectorParentExp()(implicit extensions: Seq[SelectorExp] = List()) extends SelectorExp(extensions) {
  override def grounded: Boolean = false
  override def withExtensions(extensions: Seq[SelectorExp]): SelectorExp = copy()(extensions)
}
