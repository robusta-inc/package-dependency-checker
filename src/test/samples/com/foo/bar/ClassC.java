package com.foo.bar;

import _com._foo._bar.ClassA;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ClassC extends ClassA {
    List<String> stringList = newArrayList();

    public List<String> stringList() {
        return stringList;
    }
}
