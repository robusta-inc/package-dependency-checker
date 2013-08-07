Package Dependency Checker
==========================

A library that checks the violation of dependency in a Java source folder(s). 

Specification
==============
<ol>
<li>the location of the source folder (or, comma separated multiple source paths) that need to be scanned for Java source files</li>
<li>source packages (e.g. com.foo.bar) (single or comma separated)</li>
<li>target packages (single or comma separated) that source package(s) is “expected” to contain natural dependency on (e.g.., org.*, java.*, com.foo.buzz.* </li>
</ol>

Usage
=====
```
java –jar dependencyChecker.jar -dir /path/to/java/source -source com.foo.bar.impl -target java.util,javax.servlet.*
```

Assumptions
===========
* package names are assumed to be as per convention - http://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html 
* Class names and method names (static imports) are assumed to be as per conventions - http://docs.oracle.com/javase/tutorial/java/javaOO/classdecl.html

Artifact
========
Download [dependencyChecker.jar](https://robusta.ci.cloudbees.com/job/build-package-dependency-checker/lastSuccessfulBuild/artifact/target/dependencyChecker.jar)

Continuously Integrated
=======================
https://robusta.ci.cloudbees.com/job/build-package-dependency-checker


