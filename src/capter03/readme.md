#Lambda表达式
- 函数式接口就是只定义一个抽象方法的接口。
- Lambda表达式允许你直接以内联的形式为函数式接口的抽象方法提供实现，并把整个表达式作为函数式接口的实例
- 函数式接口的抽象方法的签名基本上就是lambda表达式的签名，这种抽象方法叫做函数描述符
- 方法引用：它的基本思想是，如果一个Lambda代表的只是“直接调用这个方法”，那最好还是用名称来调用它，而不是去描述如何调用它。当你需要使用方法引用时，目标引用放在分隔符::前，方法的名称放在后面 。
