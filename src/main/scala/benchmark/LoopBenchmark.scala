package benchmark

import org.openjdk.jmh.annotations.{Benchmark, BenchmarkMode, Mode, Scope, State}

@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.Throughput))
class LoopBenchmark {

  val arr1 = Seq.fill(80)(math.random)
  val arr2 = Array.fill(80)(math.random)

  @Benchmark def withWhile: Double = imperativeWhile(arr1, arr2)
  @Benchmark def withIter: Double = imperativeWhileWithIter(arr1, arr2)
  @Benchmark def withForLoopItr: Double = forLoopItr(arr1, arr2)

  def imperativeWhile(hist: Seq[Double], soFar: Array[Double]): Double = {
    var res: Double = 0
    val n = hist.size
    var i = 0
    while (i < n) {
      res += Math.abs(hist(i) - soFar(i))
      i += 1
    }
    res / 2.0
  }

  def imperativeWhileWithIter(hist: Seq[Double], soFar: Array[Double]): Double = {
    var res: Double = 0
    val n = hist.size
    var i = 0
    val itHist = hist.iterator
    while (itHist.hasNext) {
      res += Math.abs(itHist.next - soFar(i))
      i += 1
    }
    res / 2.0
  }

  def forLoopItr(hist: Seq[Double], soFar: Array[Double]): Double = {
    var res: Double = 0
    val n = hist.size
    val itHist = hist.iterator
    for (i <- 0 until n) {
      res += Math.abs(itHist.next - soFar(i))
    }
    res / 2.0
  }

}
