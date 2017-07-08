/**
 * Decorator.java
 * Created by liurenyong at 2013-12-18
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.装饰模式;

/**
 * <定义>动态地给一个对象添加一些额外的职责，就增加功能来说，装饰模式比生成子类更灵活。
 * <本质>动态组合：动态是手段，组合是目的。
 * <优点>
 * 1.比继承更灵活：继承是静态的，使用继承所有的子类都有一样的功能，而装饰模式将功能分离到每个装饰器中，通过对象组合的方式，在运行时动态地组合功能，每个被装饰的对象最终有哪些功能呢，是由运行期动态组合的功能来决定的。
 * 2.更容易复用功能：装饰模式把一系列复杂的功能分散到每个装饰器中，一般一个装饰器只实现一个功能，使实现装饰器变得简单，更重要的是这样有利于装饰器功能的复用，可以给一个对象增加多个同样的装饰器，也可以把一个装饰器用来修饰不同的对象，从而实现复用装饰器的功能。
 * 3.简化高层定义：装饰模式可以通过组合装饰器的方式，为对象增添任意多的功能。
 * 4.把类中的装饰功能从类中搬移去除，这样可以简化原有的类。
 * 5.有效的把类的核心职责和装饰功能区分开了，而且可以去除相关类中重复的装饰逻辑.
 * <缺点>
 * 1.会产生很多细粒度对象：装饰模式是把一系列复杂的功能，分散到每个装饰器当中，一般一个装饰器只实现一个功能，这样会产生很多细粒度对象，而且功能越复杂，需要的细粒度对象越多。
 * <使用场景>
 * 1.如果要在不影响其他对象的情况下，以动态，透明的方式给对象添加职责。（这几乎就是装饰模式的主要功能）
 * 2.如果不适合使用子类来进行扩展的时候，可以考虑使用装饰模式，因为装饰模式是使用对象组合的方式。所谓不适合用子类扩展的方式，比如，扩展工恩呢该需要的子类太多，造成子类数目呈爆炸性增长。
 * @author liurenyong 2013-12-18
 */
public class Decorator extends Component{
    
    protected Component component = null;
    
    /**
     * 
     * @author liurenyong 2013-12-18
     */
    public Decorator(Component component) {
        this.component = component;
    }

    /* (non-Javadoc)
     * @see design.装饰模式.Component#operate()
     */
    @Override
    public void operate() {
        component.operate();
    }
    

}
