package com.xinchentechnote.fix.benchmark;

import com.xinchentechnote.fix.codec.LogonCodec;
import com.xinchentechnote.fix.codec.reflect.FixJsonRuntimeCodec;
import com.xinchentechnote.fix.codec.utils.FileUtils;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.*;
import quickfix.fix44.Logon;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 2)
@Measurement(iterations = 5, time = 2)
@Fork(2)
@State(Scope.Thread)
public class FixJsonCodecBenchmark {

  private LogonCodec logonCodec;

  private FixJsonRuntimeCodec fixJsonRuntimeCodec;

  @Setup(Level.Trial)
  public void setup() throws Exception {
    logonCodec = new LogonCodec();
    fixJsonRuntimeCodec =
        new FixJsonRuntimeCodec(FileUtils.readFileToStringFromClassPath("FIX44.xml"));
  }

  @Benchmark
  public String encode_generated(TestData data) throws Exception {
    return logonCodec.encode(data.logon);
  }

  @Benchmark
  public String encode_reflection(TestData data) throws Exception {
    return fixJsonRuntimeCodec.encode(data.logon);
  }

  @State(Scope.Thread)
  public static class TestData {
    Logon logon;
    String json;

    @Setup(Level.Trial)
    public void setup() throws Exception {
      json = FileUtils.readFileToStringFromClassPath("Logon.json");
      System.out.println("----------------------------");
      System.out.println(json);
      logon = new LogonCodec().decode(json);
      System.out.println(logon.toString());
      System.out.println("----------------------------");
    }
  }
}
