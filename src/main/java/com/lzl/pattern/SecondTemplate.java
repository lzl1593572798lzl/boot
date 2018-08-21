package com.lzl.pattern;

/**
 * create by lzl ON 2018/08/14
 */
public class SecondTemplate extends AbstractTemplatePattern {

    @Override
    public void addCondiments() {
        System.out.println("加入 SecondTemplate 的调料");
    }

    @Override
    public void putInCup() {
        System.out.println("加入 SecondTemplate 的饮品");
    }

    @Override
    public void prepareTemplate() {
        super.boilWater();
        putInCup();
        super.addHotWater();
        addCondiments();
    }
}
