package sless.ast.exp.selector

import sless.ast.exp.value.ValueExp

case class SelectorModifierExp(s: SelectorExp, suffix: String)(implicit extensions: Seq[SelectorExp] = List())
  extends SelectorExp(extensions) {
  override def compile(): String = s.compile() + suffix
  override def pretty(spaces: Int): String = s.pretty(spaces) + suffix
  override def grounded: Boolean = s.grounded
  override def replace(el: SelectorExp, rep: SelectorExp): SelectorExp =
    if (this == el) rep else copy(s.replace(el,rep), suffix)
  override def addExtension(toExtend: SelectorExp): SelectorExp = SelectorModifierExp(s, suffix)(toExtend +: extensions)
  override def extensionPairs: Seq[(SelectorExp, SelectorExp)] = extensions.map((this,_)) ++ s.extensionPairs
  override def extend(toExtend: SelectorExp): SelectorExp = super.extend(s.extend(toExtend))
}

case class SelectorAttributeExp(s: SelectorExp, attr: String, v: ValueExp)(implicit extensions: Seq[SelectorExp] = List())
  extends SelectorExp(extensions) {
  override def compile(): String = s.compile() + "[" + attr + "=" + v.compile() + "]"
  override def pretty(spaces: Int): String = s.pretty(spaces) + "[" + attr + "=" + v.pretty(spaces) + "]"
  override def grounded: Boolean = s.grounded
  override def replace(el: SelectorExp, rep: SelectorExp): SelectorExp =
    if (this == el) rep else copy(s.replace(el,rep), attr, v)
  override def addExtension(toExtend: SelectorExp): SelectorExp = SelectorAttributeExp(s, attr, v)(toExtend +: extensions)
  override def extensionPairs: Seq[(SelectorExp, SelectorExp)] = extensions.map((this,_)) ++ s.extensionPairs
  override def extend(toExtend: SelectorExp): SelectorExp = super.extend(s.extend(toExtend))
}