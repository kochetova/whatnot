package main

import java.io.File
import java.net.URL
import java.util

import main.plant.Chamomile

class RunWorker(implicit val a: Int, implicit val b: Int) {

  // объявляем метод суперМегаГиперРан, который принимает на вход переменную типа массив строк
  // и возвращает значение типа Unit, то есть ничего
  def superMegaHyperRun(packageNameArr: Array[String], date_str: String): Unit = {

    // у массива вызываем метод foreach "для каждого", который принимает на вход лямда-выражение
    packageNameArr.foreach(
      // левая часть, первый аргумент (при каждой итерации элемент перебираемого массива), и правая часть с блоком кода
      packageName => {

        // левая часть: объявляем переменную типа list, который содержит экземпляры (объекты/инстансы) RunImpl
        // правая часть: создается пустой лист, который параметризован типом RunImpl
        // присваиваем объявленной переменной ссылку на список
        var list: List[RunImpl] = List.empty[RunImpl]

        // объявляем константу типа класслоадер, которой присваиваем правую часть
        // правая часть: ссылаемся сами на себя (this), вызываем метод getClass - метод класса Object (по умолчанию класс наследуется от Object)
        // который возвращает значение типа Class, потом вызываем метод getClassLoader, который вернет значение типа ClassLoader
        val classLoader: ClassLoader = this.getClass.getClassLoader
        // очередной элемент массива, который типа String, у него вызываем метод replace
        val path = packageName.replace('.', '/')
        // вызываем метод getResources у classLoader, который возвращает все ссылки на файлы в заданной пакете в виде ссылок на эти ресурсы
        val resources: util.Enumeration[URL] = classLoader.getResources(path)
        // вызываем метод nextElement, который возвращает первую ссылку из ресурсов
        val link: URL = resources.nextElement()
        // создаем экземпляр (объект) класса файл, который принимает путь, который берется из URL с помощью метода getFile
        val packageDirectory = new File(link.toURI).getCanonicalFile
        // метод listFiles возвращает список вложенных файлов указанной директории
        packageDirectory.listFiles()
          // методом withFilter фильтруем этот список файлов (фильтр - имя файла заканчивается на .class)
          .withFilter(_.getName.endsWith(".class"))
//          .withFilter(!_.getName.contains("$$"))
          // у полученного отфильтрованного списка вызываем метод foreach
          .foreach {
            // file - элемент массива с файлами при каждой итерации
            // в правой части передаем блок кода, в котором работаем с file
            file =>
              // класс по имени Class, у которого вызываем метод forName
              // который из имени пакета и имени файла получает класс по имени Class (удаляем .class)
              val clazz: Class[_] = Class.forName(packageName + '.' + file.getName.dropRight(6))
              // полученный класс проверяем по условиям
              clazz match {
                //              case m: Class[RunImpl] => m.newInstance.asInstanceOf[ {def baseRun(): Unit}].baseRun()
                // если clazz параметризован типом RunImpl, то приступаем к правой части -
                // из объекта класса по имени Class, который параметризирован RunImpl создаем объект класса Runimpl и добавляем его в list (см. строку 34)
                case neededType: Class[RunImpl] => {
                  //перед добавлением в лист создаем
                  val ourClassObject = neededType
                    .getDeclaredConstructor(classOf[Int], classOf[Int])
                    .newInstance(a, b)
//                  val ourClassObject = neededType.newInstance()
                  ourClassObject.setDate(date_str)
                  list = ourClassObject :: list
                }
                // если clazz что угодно другое, то ничего не делаем
                case _ =>
              }
          }

        // полученный лист сортируем по наименованиям классов в нем
        list.sortBy(element => element.getClass.getSimpleName)
          // и у отсортированного списка через foreach для каждого элемента вызываем baseRun
          .foreach(instance => instance.baseRun())

      })

  }

}

