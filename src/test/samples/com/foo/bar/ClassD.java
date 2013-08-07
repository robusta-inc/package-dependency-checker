package com.foo.bar;

import _com._foo._bar.ClassB;

import static _com._foo._bar.ClassB.InnerClassB.staticApi;

public class ClassD extends ClassB {
    private InnerClassB innerClassB;

    public void doNothing() {
        staticApi();
    }
}
