import java.io.File
import java.net.URL
import java.util

import main.RunImpl

object MainActual {

  def main(args: Array[String]): Unit = {
    run()
  }

  def run(): Unit = {

    val packageNameArr = Array("main.animal", "main.plant")

    // Create classes list
    val animalList: List[RunImpl] = getClassListFrom(packageNameArr)

    // Call run methods from our classes
    animalList.foreach(runImpl => runImpl.baseRun())
  }

  def getClassListFrom(packageNameArr: Array[String]): List[RunImpl] = {

    // Create empty list
    var list = List.empty[RunImpl]

    packageNameArr.foreach(packageName => {

      // Get classes from package
      val classLoader = getClass.getClassLoader
      val path = packageName.replace('.', '/')
      val resources: util.Enumeration[URL] = classLoader.getResources(path)

      val first: URL = resources.nextElement()
      val packageFile = new File(first.getFile)
      packageFile
        .listFiles()
        .withFilter(_.getName.endsWith(".class"))
        .foreach {
          file =>
            val clazz: Class[_] = Class.forName(packageName + '.' + file.getName.dropRight(6))
            clazz match {
              case m: Class[RunImpl] => list = m.newInstance() :: list
              case _ => println("Nothing")
            }
        }
    })

    list.sortBy(element => element.getClass.getSimpleName)
  }

}

