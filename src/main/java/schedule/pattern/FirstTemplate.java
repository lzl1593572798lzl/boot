package schedule.pattern;

/**
 * create by lzl ON 2018/08/14
 */
public class FirstTemplate extends AbstractTemplatePattern {

    @Override
    public void addCondiments() {
        System.out.println("加入 FirstTemplate 的调料");
    }

    @Override
    public void putInCup() {
        System.out.println("放入 FirstTemplate 的饮品");
    }

    @Override
    public void prepareTemplate() {
        super.boilWater();
        putInCup();
        super.addHotWater();
        addCondiments();
    }
}
