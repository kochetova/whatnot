import java.text.SimpleDateFormat
import java.time.LocalDate

import main.plant.Chamomile

object Main {

  def main(args: Array[String]): Unit = {

    val a: Int = 2
    val b: Int = 2

    val startDate: String = "2020-01-01"
    val endDate: String = "2020-02-29"

//    val charmoline = new Chamomile()

    val startDateLocal: LocalDate = LocalDate.parse(startDate)
    val endDateLocal: LocalDate = LocalDate.parse(endDate)

    val table = new Table(startDateLocal, endDateLocal)(a: Int, b: Int)
    table.run()
  }

}
