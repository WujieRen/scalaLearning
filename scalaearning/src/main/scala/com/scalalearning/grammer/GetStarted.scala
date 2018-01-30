package com.scalalearning.grammer

/**
  * Created by renwujie on 2018/01/30 at 11:11
  */
object GetStarted {
  def main(args: Array[String]): Unit = {
    val z = 9
    if (z < 10) {
      //The simple string interpolator(插值)
      println(s"a >= 10, $z")
    }

    var result = if (z > 10) 1 else 0
    println(result)

    //Range(): [)
    val range = Range(1, 10, 2)
    println(range)
    //to: []
    val to = 1 to 10
    println(to)
    //until: [)
    val until = 1.until(10)
    println(until)

    //最基本的语法
    println("最基本的for循环写法")
    for (item <- to) {
      print(item + " ")
    }
    println

    println("for循环的守护模式，实现continue或break的功能")
    for (item <- to if item < 5) {
      print(item + " : ")
    }
    println

    for (item <- to if item < 5 && item != 3) {
      print(item + " : ")
    }
    println

    for {
      item <- to
      if item < 5 && item != 2
    } {
      print(item + " & ")
    }
    println

    //嵌套循环——例子：实现一个99乘法表
    //这个不太标准
    for (i <- until) {
      for (k <- until) {
        if (k <= i) {
          print(s"$k*$i=${i * k} ")
        }
      }
      println
    }
    //标准
    for (i <- until) {
      for (k <- 1 to i) {
        print(s"$i*$k=${i * k}\t")
      }
      println
    }
    //
    for {
      i <- until
      k <- 1 to i
    } {
      print(s"$i*$k=${i * k}\t")
      if (i == k) println
    }

    //for循环绑定临时变量
    val names = Array("renwujie", "baidu", "google", "")
    for (name <- names) {
      if (name.nonEmpty) {
        print(name + "_")
      }
    }
    println

    for {
      name <- names
      tmp = name.trim
      if (tmp.nonEmpty)
    } {
      print(tmp + "_")
    }
    println

    //可以基于for循环创建一个新的集合使用关键字：yield
    val nlist = for (i <- until) yield {
      i * i
    }
    nlist.foreach { v =>
      print(v + "_")
    }
    println

    //例子
    for (i <- 1 to 20) {
      for {
        i <- 1 to 9
        k <- 1 to i
      } {
        print("---")
        if (i == k) println()
      }

      //      for {
      //        i <- Range(9, 0, -1)
      //        k <- Range(i, 0, -1)
      //      } {
      //        print("---")
      //        if (k == 1) {
      //          println()
      //        }
      //      }
      for {
        i <- 1 to 9
        k <- i to 9
      } {
        print("---")
        if (i == k) println()
      }
      for {
        i <- 1 to 9
        k <- i to 9
      } {
        if (i == k && i != 1) println()
        print("---")
      }
    }
    println

    //函数---------------
    def max(x: Int, y: Int):Int = {
      if(x > y) x
      y
    }
    println(max(3, 5))

    //调用时可以是f1也可以是f1
    def f1(): Unit = {
      println("invoke")
    }

    // 定义一个函数的时候，如果函数没有参数，可以不给定输入参数的括号；
    // 调用函数的时候只能是f2来调用
    def f2: Unit = {
      println("invoke f2")
    }

    def sayHello(name: String, say: String="Hi"): Unit = {
      println(s"$say, $name")
    }
    sayHello("qingwa")

    //变长参数
    //1.只能出现一次
    //2.必须是最后一个
    //3.在函数中处理当作集合(array)
    def printStr(str: String, strs: String*): Unit = {
      print(s"$str --- ")
        for(item <- strs) {
          print("$item +++ ")
        }
      println
    }
    printStr("wawawa", "lalala", "hahaha", "guoguoguo")

    //局部函数(在函数内部定义使用的函数，函数的作用域：外部函数内)
    def outer(): Unit = {
      inner()
      println("outer method")

      def inner(): Unit = {
        println("invoke inner method")
      }

      inner()
    }
    outer()

    /*
    高阶函数：如果函数f有一个参数g，g是函数类型的，那么f就叫高阶函数
     */
    def greeting(name:String, sayFunc: (String) => Unit): Unit = {
      sayFunc(name)
    }

    val sayFunc = (name: String) => {
      println(name)
    }
    greeting("renwujie", sayFunc)

    def sayHelloFunc(name: String): Unit = {
      println(s"hello $name!")
    }
//    val sayHelloFunc = sayFunc    这个会报错
//    val sayHelloFunc = sayFunc _  这个是将这个函数原型付给一个变量，这样这个变量就是函数了
    greeting("renwujie", sayHelloFunc)
    greeting("renwujie", sayHelloFunc _)

    // 使用匿名函数来传递高阶函数的参数（一般情况匿名函数的使用位置是高阶函数的调用）
    greeting("renwujie", (name: String) => println(s"Hi $name"))

    /**
      * demo2
      */
    def opera(x: Int, y: Int, op: (Int, Int) => Int): Unit = {
      println(s"result=${op(x, y)}")
    }

    //TODO:高阶函数的自动类型推断 —— 前提：函数的调用没有歧义/异议
    //1.可以不写数据类型,scala会自动推断数据类型，根据定义的函数来推断
    greeting("renwujie", (name) => println(s"${name}, Hi"))
    opera(1, 2, (x,y) => x + y)

    // 2. 如果输入参数只有一个的情况下，可以省略小括号
    greeting("renwujie", name => println(s"Hi ${name}"))

    //3. 如果左侧的输入参数在右侧的代码体中都使用了，
    // 且使用顺序和参数列表中的顺序一致，
    // 并且所有参数仅仅使用一次，
    // 那么可以使用下划线代替，输入参数列表就不用写了(要求使用下划线代替后没有异议)
    greeting("renwujie", println(_))
    opera(1, 2, _+_)

    //4. 如果右侧的函数体仅仅是调用其它已经存在的函数，
    // 且传入的参数是所有左侧输入的参数列表中的参数，
    // 且顺序一致，那么可以直接省略下划线
    greeting("renwujie", println)

    /**
      * 函数的闭包
      *   当函数的参数超出其作用域时，我们还能对参数进行访问
      *
      *   在这里我们首先定义了返回值为函数的一个函数，然后我们调用了该函数，传入的参数为Spark，通常当函数scala运行结束后，我们是不能在访问该参数了，因为局部变量Spark的作用域也就是生命周期结束了，但是当我们调用funcResult函数后，发现它是能访问参数Spark的。这就是函数的闭包。scala内部是创建了一个函数的内部对象，将参数Spark作为一个成员保留在了这个对象中。
      */
    //方式①
    def prt(content: String) = (message: String) => {
      println(s"$content : $message")
    }
    val funcResult = prt("renwujie")
    funcResult("flink")

    //方式②
    def prtName(name: String) = {
      val sex = (sex: String) => {
        println(s"${name} : ${sex}")
      }
      sex
    }
    var tmpFunc = prtName("rwj")
//    println(tmpFunc)
    tmpFunc("male")


  }
}
