package main

trait RunImpl {

  protected var date_str: String = _

  def setDate(date_str: String): Unit = {
    this.date_str = date_str
  }

  def baseRun(): Unit

}
