# regression
[![Build Status](https://travis-ci.org/moc-yuto/regression.svg?branch=master)](https://travis-ci.org/moc-yuto/regression)

calculate regression using scala/breeze

# Installation

You need to use **Java1.8**

## SBT

For SBT, Add these lines to your SBT project definition:

```scala
libraryDependencies  ++= Seq(
  // other dependencies here
  "org.scalanlp" %% "breeze" % "0.12",
  // native libraries are not included by default. add this if you want them (as of 0.7)
  // native libraries greatly improve performance, but increase jar sizes. 
  // It also packages various blas implementations, which have licenses that may or may not
  // be compatible with the Apache License. No GPL code, as best I know.
  "org.scalanlp" %% "breeze-natives" % "0.12",
  // the visualization library is distributed separately as well. 
  // It depends on LGPL code.
    "org.scalanlp" %% "breeze-viz" % "0.12"
)
resolvers +=  "mocyuto GitHub" at "http://moc-yuto.github.io/regression/repository/"
```
