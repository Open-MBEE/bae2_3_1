bae
===

Behavior Analysis Engine

A constraint solving tool that takes a declarative model specified in Java or [K](https://github.com/Open-MBEE/K/tree/develop) (via [kservices](https://github.com/Open-MBEE/kservices)) and treats it as a constraint problem.  While it can be run standalone, there are also MagicDraw plugins and an interface to EMS/[MMS](https://github.com/Open-MBEE/mms) (also in MBEE).  Implementations of `TimeVarying<T>` may be used to represent system variables that change over time.  `Event`s may be modeled to affect these variables, defining behavior.  A `Solver`'s solution includes an assignment to `Parameter`s to satisfy `Constraints` and a schedule of generated events and changes in values of state variables.


For setup, see [AESetupInstructions-*.pdf](https://github.com/Open-MBEE/bae/blob/develop/AESetupInstructions-2013-09-17.pdf) .  To run on K models, see [kservices](https://github.com/Open-MBEE/kservices). 



