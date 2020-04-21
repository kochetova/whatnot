package main.plant

import main.RunImpl

class Oak(implicit val a: Int, implicit val b: Int) extends RunImpl {

  def run(): Unit = {
    println(getClass.getSimpleName)

  }

  override def baseRun(): Unit = run()
}
