bae
===

Behavior Analysis Engine

A constraint solving tool that takes a declarative model specified in Java and treats it as a constraint problem.  While it can be run standalone, there are also MagicDraw plugins and an interface to EMS/MMS (also in MBEE).  Implementations of TimeVarying<T> may be used to represent system variables that change over time.  Events may be modeled to affect these variable, defining behavior.  A Solver's solution includes an assignment to Parameters to satisfy Constraints and a schedule of generated events and changes in values of state variables.

See AESetupInstructions-*.pdf .

There may be examples in the instructions that are not included in the repository. New examples need to be added.

The javadoc can be found at [doc/index.html](doc/index.html).


