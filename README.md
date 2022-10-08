# micro-benchmark
Microbenchmarking of Scala or Java 

Run with following command
```
sbt "jmh:run -i 10 -wi 10 -f1 -t1 .*LoopBenchmark.*"
```

Sample Result

```
[info] Benchmark                      Mode  Cnt        Score       Error  Units
[info] LoopBenchmark.withForLoopItr  thrpt   10  2260950,355 ± 15142,125  ops/s
[info] LoopBenchmark.withIter        thrpt   10  3427854,482 ± 21028,852  ops/s
[info] LoopBenchmark.withWhile       thrpt   10    81143,036 ±   158,184  ops/s
```
