package main.animal

import main.RunImpl

class S11Wolf(implicit val a: Int, implicit val b: Int) extends RunImpl {

  def run(): Unit = {
    println(getClass.getSimpleName)
  }

  override def baseRun(): Unit = run()
}
