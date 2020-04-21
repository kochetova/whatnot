package main.animal

import main.RunImpl

// класс Dog наследует интерфейс RunImpl
class S1Dog(implicit val a: Int, implicit val b: Int) extends RunImpl {

  def run(): Unit = {
    print(getClass.getSimpleName.toString + " " + s"$date_str" + "\n")
  }

  override def baseRun(): Unit = run()
}
