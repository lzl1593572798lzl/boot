package com.lzl.pattern;

/**
 * create by lzl ON 2018/08/14
 */
public abstract class AbstractTemplatePattern {

    public void boilWater() {
        System.out.println("烧开水");
    }

    public abstract void putInCup();

    public void addHotWater() {
        System.out.println("加入热水");
    }

    public abstract void addCondiments();

    public void prepareTemplate() {
        boilWater();
        putInCup();
        addCondiments();
        addCondiments();
    }
}
