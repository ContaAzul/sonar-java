import java.util.stream.IntStream;

class Foo {
  int a;                          // Compliant
   int b;                         // Noncompliant {{Make this line start at column 3.}}
 int c;                           // Compliant - already reported

  public void foo1() {            // Compliant
    System.out.println();         // Compliant
    }                             // Compliant

 public void foo2() {             // Compliant
   System.out.println("hehe");    // Noncompliant
    System.out.println();         // Compliant - already reported
  }

  public void foo3() {            // Compliant
System.out.println();             // Noncompliant
System.out.println();             // Compliant - already reported
System.out.println();             // Compliant - already reported

if (0) {                          // Compliant - already reported
  System.out.println();           // Noncompliant
  if (0) {                        // Compliant - already reported
        System.out.println();     // Compliant
    System.out.println();         // Noncompliant {{Make this line start at column 9.}}
  }

      ; System.out.println();     // Compliant
}
}

  class Foo {

    int a;                        // Compliant

  int b;                          // Noncompliant

  }

  void foo() {
    IntStream
      .range(1, 5)
      .map((a -> {
        return a + 1;
      }));
    IntStream
      .range(1, 5)
      .map(a -> {
        if (a == 5) {
          a--;
        }
         a += 1; // Noncompliant {{Make this line start at column 9.}}
        return a + 1;
      });
    IntStream.range(1, 5).map(a -> {
      return a + 1;
    });
  }
}

enum Bar {
  A,
 B,
   C;

  public void foo1() {            // Compliant
  }

 public void foo2() {             // Noncompliant
 }
}

interface Qix {

 void foo1();                     // Noncompliant

  void foo2();                    // Compliant

}

static class Baz {

  void foo() {
    new MyInterface() {
      public void foo() {         // Compliant - not checked
      }

     public void foo() {          // Compliant - not checked
     }
    };
  }

  int[] foo = new int[] {
    0,
    new Foo()
  };

}

 static class Qiz {                      // Noncompliant
  public void foo() {
    switch (0) {
      case 0:
        System.out.println(); System.out.println(); // Compliant
        break;
    }

    System.out.println( // Compliant
        ); Sysout.out.println(); // Compliant

    switch (foo) { // Compliant
    }

    switch (foo) {
      case 0:
      case 1:
      case 2: {
        new Object().toString();
         new Object().toString(); // Noncompliant {{Make this line start at column 9.}}
        break;
      }
      case 3: {
        new Object().toString();
      }
      new Object().toString();
      break;
      case 4:
      { // Noncompliant {{Make this line start at column 15.}}
        new Object().toString();
        break;
      }
      case 5:
        if (abs(x - z) == 0.5) {
          return x + copySign(0.5, x);
        } else {
          return z;
        }
    }

    switch (foo) {
      case 1: break; // Noncompliant
      case 2
        : case 3: break; // Compliant
    }
  };
  static {
    try{
       while (keys.hasMoreElements()) { // Noncompliant {{Make this line start at column 7.}}
        s = keys.nextElement();
        rId = (String) s;
        cName = (String) exceptionClassNames.get(rId);
        exceptionRepositoryIds.put (cName, rId);
      }
    } catch (NoSuchElementException e) { }
  }
}
public @interface Example {
  public static class Inner {
    public static final String FOO = "foo";
  }
}

class IndentFoo {
  public static boolean showJobDifferences(final String name, final JobsDifference.JobDifference diff, boolean onlyOkButton) {
    FutureTask<Boolean> task = new FutureTask<>(() -> {
      Optional<ButtonType> result = new DifferenceDialog(name, diff, onlyOkButton).showAndWait();
      if (result.isPresent() && (result.get() == DifferenceDialog.ACTION_YES_ALL || result.get() == DifferenceDialog.ACTION_OK_ALL)) {
        skipDialog = true;
      }
      return result.get() == DifferenceDialog.ACTION_YES || result.get() == DifferenceDialog.ACTION_YES_ALL;
    });
  }

  @Override
  protected void append(final ILoggingEvent event) {
    synchronized (lock) {
      Platform.runLater(() -> {
        Text text = new Text();
        text.setText(patternLayout.doLayout(event));
        switch (event.getLevel().levelInt) {
          case Level.DEBUG_INT:
            text.setFill(Color.BLACK);
            break;
          default:
            text.setFill(Color.BLACK);
            break;
        }
        listView.getItems().add(text);
      });
    }
  }

}
