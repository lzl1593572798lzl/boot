package schedule.pattern;

/**
 * create by lzl ON 2018/08/14
 */
public class TemplateMain {

    public static void main(String[] args) {
        AbstractTemplatePattern firstTemplate = new FirstTemplate();
        firstTemplate.prepareTemplate();

        AbstractTemplatePattern secondTemplate = new SecondTemplate();
        secondTemplate.prepareTemplate();
    }
}
