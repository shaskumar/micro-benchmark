package benchmark

import org.openjdk.jmh.annotations.{Benchmark, BenchmarkMode, Mode, OutputTimeUnit, Scope, State}

import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Array(Mode.Throughput))
class ScalaLambdaFunction {

  @Benchmark
  def mapPlusFilterCase: Map[PlusShift, Double] = mapPlusFilter(randomMap)
  @Benchmark
  def collectCase: Map[PlusShift, Double] = collect(randomMap)

  def mapPlusFilter(rows: Map[String, String]): Map[PlusShift, Double] = {
    rows
      .filter(_._1 != "ad")
      .map { row =>
        val rowKey = row._1.split("_")
        (PlusShift(rowKey.head, rowKey.last.toInt), row._2.toDouble)
      }
  }

  def collect(rows: Map[String, String]): Map[PlusShift, Double] = {
    rows.collect {
      case row if row._1 != "ad" =>
        val rowKey = row._1.split("_")
        (PlusShift(rowKey.head, rowKey.last.toInt), row._2.toDouble)
    }
  }

  def randomMap: Map[String, String] = {

    val p = for {
      i <- 1 to 400
    } yield {
      s"key_${i%40}" -> s"$i"
    }
    p.toMap

  }
}

case class PlusShift(shiftName: String, strength: Int)

/*object ScalaLambdaFunction extends App {

   val scalaLambdaFunction = new ScalaLambdaFunction
   println(scalaLambdaFunction.randomMap)
}*/
