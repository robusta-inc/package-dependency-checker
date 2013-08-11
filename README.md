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

Sample Output
=============
```
1. com.robusta.pdc.scanner.RecursivePackageFinder.java depends on java.util
2. com.robusta.pdc.scanner.RecursivePackageFinder.java depends on java.io
3. com.robusta.pdc.scanner.AbstractDirectoryFindingPackageFinder.java depends on com.google.common.collect
4. com.robusta.pdc.scanner.AbstractDirectoryFindingPackageFinder.java depends on java.util
5. com.robusta.pdc.scanner.AbstractDirectoryFindingPackageFinder.java depends on java.io
6. com.robusta.pdc.scanner.PackageFinder.java depends on java.util
7. com.robusta.pdc.scanner.PackageCallbackSourceFolderScanner.java depends on java.util
8. com.robusta.pdc.scanner.NonRecursivePackageFinder.java depends on java.util
9. com.robusta.pdc.scanner.NonRecursivePackageFinder.java depends on java.io
10. com.robusta.pdc.scanner.QDocSourceFileScanner.java depends on java.io
```

Assumptions
===========
* package names are assumed to be as per convention - http://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html 
* Class names and method names (static imports) are assumed to be as per conventions - http://docs.oracle.com/javase/tutorial/java/javaOO/classdecl.html

Artifact
========
Download [dependencyChecker.jar](https://robusta.ci.cloudbees.com/job/build-package-dependency-checker/lastSuccessfulBuild/artifact/target/dependencyChecker.jar)

Development notes
=======================
* CI - https://robusta.ci.cloudbees.com/job/build-package-dependency-checker
* TDD - https://robusta.ci.cloudbees.com/job/build-package-dependency-checker/lastBuild/testReport/
* Javadoc - https://robusta.ci.cloudbees.com/job/build-package-dependency-checker/javadoc

Extensions
==========
* Using appropriate implementation of scanners and callbacks, a wide variety of source folder scanning, source file scanning and source file content scanning can be achieved.
* Reporting formats can be customized to log to a file, push formatted xml to a web service etc.

Possible Improvements
=====================
* Dependency scanning can be made asynchronous so that more than a source file can be concurrently scanned.
* QDocSourceFileScanner uses JavaDocBuilder from qdox which consumes the compete source file content. For just import statement processing this could be an over kill esp for hugh java files. An alternate implementation that stops buffering file content after import statements would be more ideal.

License
=======
Apache 2.0

<img style="width: 178px; height: 61px;" src="https://www.cloudbees.com/sites/default/files/Button-Built-on-CB-1.png" alt="">
<img style="width: 178px; height: 61px;" src="https://www.cloudbees.com/sites/default/files/Button-Powered-by-CB.png" alt="">
