#引入流
- 流允许你以声明性方式处理数据集合（通过查询语句来表达，而不是临时编写一个实现）
- 流还可以透明的并行处理，无需编写任何多线程代码
- 流的目的在于表达计算，集合的目的在于数据
-         List<String> threeHighCaloricDishNames = menu.stream().
                  filter(d->d.getCalories()>30).
                  map(Dish::getName).
                  limit(3).
                  collect(Collectors.toList()); 
- filter：接受一个lambda，从流中排除某些元素
- map:接受一个lambda，将元素转换成其他形式或提取信息
- limit：截断流，使其元素不超过给定数量
- collect：将流转换为其他形式
- 集合和流之间的差异在于什么时候进行计算。集合是一个内存中的数据结构，它包含数据结构中目前所有的值--集合中的每一个元素都得先算出来才能添加到集合中。（你可以往集合中加东西或删除东西，但是不管什么时候，集合中的每个元素都是放在内存中的，
元素都得先算出来才能成为集合的一部分）；相比之下，流则是在概念下固定的数据结构（你不能添加或删除元素），其元素则是按需计算。流像一个延迟创建的集合：只有在消费需求的时候才会计算值。
- 流和迭代器一样，只能遍历一次。遍历完后，这个流就已经被消费掉了，可以从原始数据获取新的流再处理。流只能被消费一次
- 集合和流的另一个区别是遍历数据的方式不同，流是使用内部迭代，集合是使用外部迭代。
- java.util.stream.Stream中定义的操作主要分为两类：可以连接起来的流操作称为中间操作，关闭流的操作称为终端操作。
- 中间操作会返回一个流，这让多个操作可以连接起来形成一个查询。重要的是，除非流水线上触发一个终端操作，否则中间操作不会做任何处理，这是因为中间操作一般可以合并起来，在终端操作时一次性全部处理
- 流的使用一般包括三件事：一个数据源（如集合）来执行一个查询；一个中间操作链，形成一条流水线；一个中断操作，执行流水线，并能生成结果。
- 筛选：流的distinct方法：会返回一个元素各异的流（hashCode和equals放方法的实现）
- 截断流：流的limit方法，截断流，会返回一个不超过给定长度的流
- 跳过元素：流的skip方法，跳过元素，返回一个扔掉了前n个元素的流，如果流中的元素不足n个，则返回一个空流
- 映射：流的map方法，他会接受一个函数作为参数。这个函数会被应用到每个元素上，并将其映射成一个新的元素（使用映射一词，是因为他和转换类似，但其中的细微差别在于它是创建一个新版本而不是去修改）
- 扁平流：流的flatmap方法让你把流中的每一个值都转换成另一个流，然后把所有的流连接起来成为一个流。
- 查找和匹配：allMatch，anyMatch，noneMatch，findMatch，findAny方法提供匹配功能
- anyMatch方法标识流中是否有一个元素能匹配给定的谓词
- allMatch方法工作原理与anyMatch类似，他会看流中的元素能否都匹配给定的谓词
- noneMatch，与allMatch相对，能确保流中没有任何元素与给定的谓词匹配
- findAny方法将返回当前流中的任意元素
- Optional<T>类是一个容器类，代表一个值存在或不存在。ifPresent()将在Optional包含值的是否返回true，否则返回false；ifPresent(Consumer<T> block)会在值存在的时候执行给定的代码块；T get()会在值存在的时候返回值，否侧抛出NoSuchElement异常，
T orElse(T other)会在只存在的时候返回值，否则返回一个默认值
- 归约操作：使用reduce操作来表达更复杂的查询，此类查询需要将流中的元素反复结合起来，得到一个值，这样的查询可以被归类为归约操作。
- reduce接受两个参数：一个初始值，这里是0；一个BinaryOperator<T>来将两个元素结合起来生成一个新值
- 原始类型流特化  降低装箱和拆箱带来的效率问题
- Optional类是一个表示值存在或不存在的容器
- 可以使用Stream.of，通过显示值创建一个流














