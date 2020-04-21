import java.time.LocalDate

import main.RunWorker
import main.plant.Chamomile

class Table(startDate: LocalDate, endDate: LocalDate)(implicit val a: Int, implicit val b: Int){

  private def monthIterator(start: LocalDate, end: LocalDate): Iterator[LocalDate] = (Iterator.iterate(start)(_ plusMonths 1) takeWhile
    (curr => curr.isBefore(end.withDayOfMonth(end.lengthOfMonth())) || curr.isEqual(end.withDayOfMonth(end.lengthOfMonth())))).
    map(curr => curr.withDayOfMonth(curr.lengthOfMonth()))

  def run(): Unit = {

    val runWorker: RunWorker = new RunWorker()(a, b)

    val packageNameArr = Array("main.plant", "main.animal")

    val dates: List[LocalDate] = monthIterator(startDate, endDate).toList

    dates.foreach { date =>
      val date_str = date.toString
      runWorker.superMegaHyperRun(packageNameArr, date_str)
      createMonFeatures(date_str)
    }

  }

  def createMonFeatures(date: String): Unit = {
    print(getClass.getSimpleName.toString + " " + date + "\n")
  }

}
