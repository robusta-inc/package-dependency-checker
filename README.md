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
